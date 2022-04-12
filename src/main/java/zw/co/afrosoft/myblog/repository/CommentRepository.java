package zw.co.afrosoft.myblog.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import zw.co.afrosoft.myblog.domain.Comment;

import java.util.List;

public interface CommentRepository extends JpaRepository<Comment,Long> {
    List<Comment> findByPostId(long postId);
}
