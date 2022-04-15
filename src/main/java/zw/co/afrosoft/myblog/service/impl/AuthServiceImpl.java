package zw.co.afrosoft.myblog.service.impl;

import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import zw.co.afrosoft.myblog.domain.Role;
import zw.co.afrosoft.myblog.domain.User;
import zw.co.afrosoft.myblog.domain.UserRole;
import zw.co.afrosoft.myblog.dtos.LoginDto;
import zw.co.afrosoft.myblog.dtos.SignUpDto;
import zw.co.afrosoft.myblog.exception.UserNameAlreadyInUseException;
import zw.co.afrosoft.myblog.repository.RoleRepository;
import zw.co.afrosoft.myblog.repository.UserRepository;
import zw.co.afrosoft.myblog.repository.UserRoleRepository;
import zw.co.afrosoft.myblog.security.JwtTokenProvider;
import zw.co.afrosoft.myblog.service.AuthService;
@Service
@AllArgsConstructor
public class AuthServiceImpl implements AuthService {
    private final AuthenticationManager authenticationManager;
    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final PasswordEncoder passwordEncoder;
    private final UserRoleRepository userRoleRepository;
    private final JwtTokenProvider tokenProvider;


    @Override
    public String authenticateUser(LoginDto loginDto) {
        Authentication authentication = authenticationManager
                .authenticate(new UsernamePasswordAuthenticationToken(loginDto.getUsernameOrEmail(),loginDto.getPassword()));
        SecurityContextHolder.getContext().setAuthentication(authentication);
        return tokenProvider.generateToken(authentication);
    }

    @Override
    public void registerUser(SignUpDto signUpDto) {
        if(userRepository.existsByUsername(signUpDto.getUsername())){
            throw new UserNameAlreadyInUseException(HttpStatus.BAD_REQUEST,"Username Already In Use");
        }
        if(userRepository.existsByEmail(signUpDto.getEmail())){
            throw new UserNameAlreadyInUseException(HttpStatus.BAD_REQUEST,"Email Already In Use");
        }
        User user = new User();
        user.setName(signUpDto.getName());
        user.setUsername(signUpDto.getUsername());
        user.setEmail(signUpDto.getEmail());
        user.setPassword(passwordEncoder.encode(signUpDto.getPassword()));

        Role roles = roleRepository.findByName("ROLE_USER").get();
        UserRole userRole = new UserRole();
        userRole.setUser(user);
        userRole.setRole(roles);
        userRoleRepository.save(userRole);

        userRepository.save(user);
    }
}
