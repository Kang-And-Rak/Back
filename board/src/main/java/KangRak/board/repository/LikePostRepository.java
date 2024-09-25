package KangRak.board.repository;

import KangRak.board.entity.Comment;
import KangRak.board.entity.LikePost;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LikePostRepository extends JpaRepository<LikePost, Integer> {
}
