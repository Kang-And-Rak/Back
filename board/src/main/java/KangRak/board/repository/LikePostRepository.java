package KangRak.board.repository;

import KangRak.board.entity.Comment;
import KangRak.board.entity.LikePost;
import KangRak.board.entity.Post;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LikePostRepository extends JpaRepository<LikePost, Integer> {

    Integer countByUserId(Integer userId);
}
