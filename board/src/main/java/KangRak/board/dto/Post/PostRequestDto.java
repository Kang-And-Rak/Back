package KangRak.board.dto.Post;

import KangRak.board.entity.BaseEntity;
import lombok.Data;
import lombok.Getter;

@Getter
public class PostRequestDto extends BaseEntity {

    private String title;
    private String context;
}
