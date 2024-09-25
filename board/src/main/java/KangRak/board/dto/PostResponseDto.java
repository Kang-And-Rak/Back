package KangRak.board.dto;

import KangRak.board.entity.Post;
import lombok.Data;

@Data
public class PostResponseDto {

    private Integer id;
    private String title;
    private String context;
    private Integer like;
    private Integer view;
    private String nickname;

    public static PostResponseDto toPostResponseDto(Post post) {
        PostResponseDto newPost = new PostResponseDto();

        newPost.setId(post.getId());
        newPost.setTitle(post.getTitle());
        newPost.setContext(post.getContext());
        newPost.setLike(post.getLike());
        newPost.setView(post.getView());
        newPost.setNickname(post.getUser().getNickname());

        return newPost;
    }
}
