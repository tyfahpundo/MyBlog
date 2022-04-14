package zw.co.afrosoft.myblog.security;

import lombok.AllArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import zw.co.afrosoft.myblog.domain.Role;
import zw.co.afrosoft.myblog.domain.User;
import zw.co.afrosoft.myblog.domain.UserRole;
import zw.co.afrosoft.myblog.repository.UserRepository;
import zw.co.afrosoft.myblog.repository.UserRoleRepository;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class CustomUserDetailsService implements UserDetailsService {
    private final UserRepository userRepository;
    private final UserRoleRepository userRoleRepository;

    @Override
    public UserDetails loadUserByUsername(String usernameOrEmail) throws UsernameNotFoundException {
        User user = userRepository.findByUsernameOrEmail(usernameOrEmail,usernameOrEmail)
                            .orElseThrow(()-> new UsernameNotFoundException("User not found with username or email: "+usernameOrEmail));
        List<UserRole> roles = userRoleRepository.findAllByUser(user);

        return new org.springframework.security.core.userdetails.User(user.getEmail(),
                user.getPassword(), roles.stream()
                .map(userRole -> new SimpleGrantedAuthority(userRole.getRole().getName()))
                .collect(Collectors.toSet()));
    }

}
