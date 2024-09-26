package KangRak.board.dto.Post;

import KangRak.board.dto.Comment.CommentDetailDto;
import KangRak.board.entity.Post;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PostDetailDto {

    private Integer id;             // 게시글 ID
    private String title;           // 게시글 제목
    private String context;         // 게시글 내용
    private Integer like;
    private Integer view;
    private String nickname;        // 작성자 닉네임
    private LocalDate createdAt;    // 생성 날짜
    private LocalDate modifiedAt;   // 수정 날짜
    private List<CommentDetailDto> comments; // 댓글 리스트


    public static PostDetailDto toPostDetailDto(Post post, List<CommentDetailDto> commentDetailDto) {

        PostDetailDto newPost = new PostDetailDto();

        newPost.setTitle(post.getTitle());
        newPost.setContext(post.getContext());
        newPost.setLike(post.getLike());
        newPost.setView(post.getView());
        newPost.setNickname(post.getUser().getNickname());
        newPost.setCreatedAt(post.getCreatedDate().toLocalDate());
        newPost.setModifiedAt(post.getLastModifiedDate().toLocalDate());
        newPost.setComments(commentDetailDto);

        return newPost;
    }
}
