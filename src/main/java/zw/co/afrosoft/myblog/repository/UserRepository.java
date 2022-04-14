package zw.co.afrosoft.myblog.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import zw.co.afrosoft.myblog.domain.User;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User,Long> {
    Optional<User> findByEmail(String email);
    Optional<User> findByUsername(String email);
    Optional<User> findByUsernameOrEmail(String username, String email);
    Boolean existsByUsername(String username);
    Boolean existsByEmail(String email);
}
