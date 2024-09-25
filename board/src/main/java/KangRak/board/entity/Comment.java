package KangRak.board.entity;

import KangRak.board.dto.CommentResponseDto;
import KangRak.board.dto.CommentResquestDto;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.web.bind.annotation.RequestBody;

@Entity
@Table(name = "comment")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Comment extends BaseEntity{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String context;

    @ManyToOne
    @JoinColumn(name = "User_id")
    private User user;

    @ManyToOne
    @JoinColumn(name = "Post_id")
    private Post post;

    public static Comment toComment(CommentResquestDto commentResquestDto, Post post, User user) {
        Comment comment = new Comment();

        comment.setContext(commentResquestDto.getContext());
        comment.setUser(user);
        comment.setPost(post);

        return comment;
    }
}
