package KangRak.board.dto.Mypage;

import KangRak.board.entity.RankStatus;
import KangRak.board.entity.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class MypageDto {

    private RankStatus rank;          // 사용자 랭크 (enum 타입)
    private Integer point;      // 사용자 포인트
    private String nickname;    // 사용자 닉네임
    private Integer postNum;    // 사용자가 작성한 게시물 수
    private Integer commentNum; // 사용자가 작성한 댓글 수
    private Integer likeNum;    // 사용자가 받은 좋아요 수

    public static MypageDto toMypage(User user, int likeNum , int postNum , int commentNum) {
        MypageDto mypageDto = new MypageDto();

        mypageDto.setRank(user.getRank());
        mypageDto.setPoint(user.getPoint());
        mypageDto.setNickname(user.getNickname());
        mypageDto.setPostNum(postNum);
        mypageDto.setCommentNum(commentNum);
        mypageDto.setLikeNum(likeNum);

        return  mypageDto;
    }
}
