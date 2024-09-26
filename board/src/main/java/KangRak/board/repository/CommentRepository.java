package KangRak.board.repository;

import KangRak.board.entity.Comment;
import KangRak.board.entity.Post;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CommentRepository extends JpaRepository<Comment, Integer> {

    // 특정 Post의 모든 댓글을 조회하는 메서드
    List<Comment> findByPost(Post post);

    Integer countByUserId(Integer userId);

}
