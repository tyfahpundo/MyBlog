package zw.co.afrosoft.myblog.util;

import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import zw.co.afrosoft.myblog.domain.Role;
import zw.co.afrosoft.myblog.repository.RoleRepository;

@Component
@Order(value = 1)
public class RoleCreator implements CommandLineRunner {
    private final AdminRoleProperties adminRoleProperties;
    private final UserRoleProperties userRoleProperties;
    private final RoleRepository roleRepository;

    public RoleCreator(AdminRoleProperties adminRoleProperties, UserRoleProperties userRoleProperties, RoleRepository roleRepository) {
        this.adminRoleProperties = adminRoleProperties;
        this.userRoleProperties = userRoleProperties;
        this.roleRepository = roleRepository;
    }

    @Override
    public void run(String... args) throws Exception {
        Role adminRole = new Role();
        adminRole.setName(adminRoleProperties.getName());
        roleRepository.save(adminRole);

        Role userRole = new Role();
        userRole.setName(userRoleProperties.getName());
        roleRepository.save(userRole);
    }
}
