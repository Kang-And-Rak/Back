package KangRak.board.dto;

import KangRak.board.entity.Comment;
import lombok.Data;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
public class CommentResponseDto {

    private Integer id;
    private String nickname;
    private String context;
    private LocalDateTime createdAt;

    public static CommentResponseDto toCommentResponseDto(Comment comment) {

        CommentResponseDto commentResponseDto = new CommentResponseDto();

        commentResponseDto.setId(comment.getId());
        commentResponseDto.setNickname(comment.getUser().getNickname());
        commentResponseDto.setContext(comment.getContext());
        commentResponseDto.setCreatedAt(comment.getCreatedDate());

        return commentResponseDto;
    }
}
