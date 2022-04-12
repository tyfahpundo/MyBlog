package zw.co.afrosoft.myblog.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import zw.co.afrosoft.myblog.domain.Post;

public interface PostRepository extends JpaRepository<Post,Long> {

}
