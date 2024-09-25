package KangRak.board.service;

import KangRak.board.dto.*;
import KangRak.board.entity.Comment;
import KangRak.board.entity.LikePost;
import KangRak.board.entity.Post;
import KangRak.board.entity.User;
import KangRak.board.repository.CommentRepository;
import KangRak.board.repository.LikePostRepository;
import KangRak.board.repository.PostRepository;
import KangRak.board.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class PostService {

    private final PostRepository postRepository;
    private final UserRepository userRepository;
    private final CommentRepository commentRepository;
    private final LikePostRepository likePostRepository;

    public List<AllPostDto> getAllPost() {
        List<Post> allPosts = postRepository.findAll();
        List<AllPostDto> allPostDto = new ArrayList<>();

        for(Post post : allPosts) {
            AllPostDto p = AllPostDto.toAllPostDto(post);
            allPostDto.add(p);
        }

        return allPostDto;
    }

    public ResponseEntity<PostResponseDto> createPost(PostRequestDto postRequestDto , Integer userId) {

        Optional<User> optionalUser = userRepository.findById(userId);

        if (optionalUser.isPresent()) {

            User user = optionalUser.get();

            // Post 저장하고
            Post post = Post.toPost(postRequestDto, user);
            postRepository.save(post);

            // PostResponseDto로 return
            PostResponseDto postResponseDto = PostResponseDto.toPostResponseDto(post);
            return ResponseEntity.status(201).body(postResponseDto);
        }

        // User가 존재하지 않을 경우, 404 Not Found 상태 반환
        return ResponseEntity.status(404).body(null);
    }

    public PostDetailDto createPostDetailDto(Post post) {
        List<Comment> comments = commentRepository.findByPost(post);
        List<CommentDetailDto> commentDetailDtos = new ArrayList<>();

        for(Comment comment : comments) {
            CommentDetailDto commentDetailDto = CommentDetailDto.toCommentDetailDto(comment);
            commentDetailDtos.add(commentDetailDto);
        }

        PostDetailDto postDetailDto = PostDetailDto.toPostDetailDto(post,commentDetailDtos);
        return postDetailDto;
    }

    public ResponseEntity<PostDetailDto> modifyPost(PostRequestDto postRequestDto, int id) {

        Optional<Post> optionalPost = postRepository.findById(id);

        if(optionalPost.isPresent()) {

            Post post = optionalPost.get();

            post.setTitle(postRequestDto.getTitle());
            post.setContext(postRequestDto.getContext());

            // PostDetailDto 만드는 함수
            PostDetailDto postDetailDto = createPostDetailDto(post);

            return ResponseEntity.status(201).body(postDetailDto);
        }

        return ResponseEntity.status(404).body(null);
    }



    public ResponseEntity<PostDetailDto> getPostDetail(int id) {

        Optional<Post> optionalPost = postRepository.findById(id);

        if(optionalPost.isPresent()) {

            Post post = optionalPost.get();
            
            PostDetailDto postDetailDto = createPostDetailDto(post);

            return ResponseEntity.status(201).body(postDetailDto);
        }

        return ResponseEntity.status(404).body(null);
    }

    public void deletePost(int id) {
        Optional<Post> optionalPost = postRepository.findById(id);

        if(optionalPost.isPresent()) {
            Post post = optionalPost.get();
            postRepository.delete(post);
        }
    }


    public ResponseEntity<PostDetailDto> addLike(int postId , int userId) {

        Optional<Post> optionalPost = postRepository.findById(postId);
        Optional<User> optionalUser = userRepository.findById(userId);

        if(optionalPost.isPresent() && optionalUser.isPresent()) {

            Post post = optionalPost.get();
            User user = optionalUser.get();

            // Post 에 좋아요 1추가

            post.setLike(post.getLike()+1);

            LikePost likePost = new LikePost();
            likePost.setPost(post);
            likePost.setUser(user);

            likePostRepository.save(likePost);

            // Responsebody로 PostDetailDto 전달
            PostDetailDto postDetailDto = createPostDetailDto(post);

            return ResponseEntity.status(201).body(postDetailDto);
        }

        return ResponseEntity.status(404).body(null);
    }
}
