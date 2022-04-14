package zw.co.afrosoft.myblog.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import zw.co.afrosoft.myblog.domain.User;
import zw.co.afrosoft.myblog.domain.UserRole;

import java.util.List;

public interface UserRoleRepository extends JpaRepository<UserRole, Long> {
    List<UserRole> findAllByUser(User user);
}
