package KangRak.board.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity(name = "post")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Post extends BaseEntity{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String title;

    private String context;

    private Integer like;

    private Integer view;

    @OneToMany
    @JoinColumn()
    private List<Comment> comments;

    @ManyToOne
    @JoinColumn(name = "User_id")
    private User user;

}
