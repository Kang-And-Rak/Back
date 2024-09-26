package KangRak.board.entity;

import KangRak.board.dto.Post.PostRequestDto;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "post")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Post extends BaseEntity{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String title;

    private String context;

    @Column(name = "`like`")
    private Integer like;

    private Integer view;

    @OneToMany(mappedBy = "post")
    private List<Comment> comments = new ArrayList<>();

    @ManyToOne
    @JoinColumn(name = "User_id")
    private User user;

    public static Post toPost(PostRequestDto postRequestDto, User user) {

        Post newPost = new Post();

        newPost.setTitle(postRequestDto.getTitle());
        newPost.setContext(postRequestDto.getContext());
        newPost.setLike(0);
        newPost.setView(0);
        newPost.setComments(new ArrayList<>());
        newPost.setUser(user);

        return newPost;
    }
}
