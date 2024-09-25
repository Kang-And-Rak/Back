package KangRak.board.service;

import KangRak.board.dto.CommentResponseDto;
import KangRak.board.dto.CommentResquestDto;
import KangRak.board.entity.Comment;
import KangRak.board.entity.Post;
import KangRak.board.entity.User;
import KangRak.board.repository.CommentRepository;
import KangRak.board.repository.PostRepository;
import KangRak.board.repository.UserRepository;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@AllArgsConstructor
public class CommentService {

    private final CommentRepository commentRepository;
    private final PostRepository postRepository;
    private final UserRepository userRepository;

    public ResponseEntity<CommentResponseDto> addComment
            (CommentResquestDto commentRequestDto, int postId, int userId) {

        // post 찾기
        Optional<Post> post = postRepository.findById(postId);
        // user 찾기
        Optional<User> user = userRepository.findById(userId);

        if(post.isPresent() && user.isPresent()) {
            Comment comment = Comment.toComment(commentRequestDto, post.get(), user.get());
            commentRepository.save(comment);

            CommentResponseDto commentResponseDto = CommentResponseDto.toCommentResponseDto(comment);

            return ResponseEntity.status(201).body(commentResponseDto);
        }

        return ResponseEntity.status(404).body(null);
    }
}
