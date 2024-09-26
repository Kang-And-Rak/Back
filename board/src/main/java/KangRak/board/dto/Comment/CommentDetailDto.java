package KangRak.board.dto.Comment;

import KangRak.board.entity.Comment;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CommentDetailDto {

    private Integer id;             // 댓글 ID
    private String context;         // 댓글 내용
    private String nickname;        // 댓글 작성자 닉네임
    private LocalDate createdAt;    // 댓글 작성 날짜

    public static CommentDetailDto toCommentDetailDto (Comment comment) {
        CommentDetailDto commentDetailDto = new CommentDetailDto();

        commentDetailDto.setContext(comment.getContext());
        commentDetailDto.setNickname(comment.getUser().getNickname());
        commentDetailDto.setCreatedAt(comment.getCreatedDate().toLocalDate());

        return commentDetailDto;
    }
}
