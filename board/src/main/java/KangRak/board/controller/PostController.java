package KangRak.board.controller;


import KangRak.board.dto.*;
import KangRak.board.entity.Post;
import KangRak.board.service.CommentService;
import KangRak.board.service.PostService;
import lombok.AllArgsConstructor;
import org.springframework.data.relational.core.sql.In;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/post")
public class PostController {

    private final PostService postService;

    @GetMapping("")
    public List<AllPostDto> getAllPost() {
        return postService.getAllPost();
    }

    @GetMapping("/{id}")
    public ResponseEntity<PostDetailDto> getPostDetail(@PathVariable int id) {
        return postService.getPostDetail(id);
    }

    @PostMapping("")
    public ResponseEntity<PostResponseDto> createPost(@RequestBody PostRequestDto postRequestDto) {

        // 현재 로그인된 사용자 정보 가져오기
        CustomUserDetails userDetails = (CustomUserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        Integer userId = userDetails.getId(); // UserDetails에서 사용자 닉네임 가져오기

        System.out.println(userId);
        // 게시글 생성 요청 처리
        return postService.createPost(postRequestDto, userId);
    }

    @PutMapping("/{id}")
    public ResponseEntity<PostDetailDto> modifyPost(@RequestBody PostRequestDto postRequestDto , @PathVariable int id) {

        return postService.modifyPost(postRequestDto , id);
    }

    @DeleteMapping("/{id}")
    public void deletePost(@PathVariable int id) {
        postService.deletePost(id);
    }

    @PostMapping("/{id}/like")
    public ResponseEntity<PostDetailDto> addLike(@PathVariable int id) {

        Integer postId = id;

        // 현재 로그인된 사용자 정보 가져오기
        CustomUserDetails userDetails = (CustomUserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        Integer userId = userDetails.getId(); // UserDetails에서 사용자 닉네임 가져오기

        return postService.addLike(postId, userId);
    }




}
