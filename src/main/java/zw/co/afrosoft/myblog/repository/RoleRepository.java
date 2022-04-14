package zw.co.afrosoft.myblog.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import zw.co.afrosoft.myblog.domain.Role;

import java.util.Optional;

public interface RoleRepository extends JpaRepository<Role,Long> {
    Optional<Role> findByName(String name);
}
