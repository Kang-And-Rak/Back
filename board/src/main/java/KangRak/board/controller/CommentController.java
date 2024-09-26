package KangRak.board.controller;

import KangRak.board.dto.Comment.CommentResponseDto;
import KangRak.board.dto.Comment.CommentResquestDto;
import KangRak.board.dto.CustomUserDetails;
import KangRak.board.service.CommentService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

@RestController
@AllArgsConstructor
public class CommentController {

    private final CommentService commentService;

    @PostMapping("/post/{id}/comment")
    public ResponseEntity<CommentResponseDto> addComment(@RequestBody CommentResquestDto commentRequestDto , @PathVariable int id) {

        Integer postId = id;

        // 현재 로그인된 사용자 정보 가져오기
        CustomUserDetails userDetails = (CustomUserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        Integer userId = userDetails.getId(); // UserDetails에서 사용자 id 가져오기

        return commentService.addComment(commentRequestDto,postId, userId);
    }

}
