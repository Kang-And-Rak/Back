package KangRak.board.dto.Post;

import KangRak.board.entity.BaseEntity;
import KangRak.board.entity.Post;
import lombok.Data;

@Data
public class AllPostDto extends BaseEntity {

    private Integer id;
    private String title;
    private String nickname;
    private Integer like;
    private Integer view;

    public static AllPostDto toAllPostDto(Post post) {
        AllPostDto allPost = new AllPostDto();

        allPost.setId(post.getId());
        allPost.setTitle(post.getTitle());
        allPost.setNickname(post.getUser().getNickname());
        allPost.setLike(post.getLike());
        allPost.setView(post.getView());

        return allPost;
    }
}
