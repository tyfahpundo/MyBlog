package zw.co.afrosoft.myblog.controller;

import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import zw.co.afrosoft.myblog.domain.Role;
import zw.co.afrosoft.myblog.domain.User;
import zw.co.afrosoft.myblog.domain.UserRole;
import zw.co.afrosoft.myblog.dtos.LoginDto;
import zw.co.afrosoft.myblog.dtos.SignUpDto;
import zw.co.afrosoft.myblog.repository.RoleRepository;
import zw.co.afrosoft.myblog.repository.UserRepository;
import zw.co.afrosoft.myblog.repository.UserRoleRepository;

@RestController
@RequestMapping("/api/auth/")
@AllArgsConstructor
public class AuthController {
    private final AuthenticationManager authenticationManager;
    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final PasswordEncoder passwordEncoder;
    private final UserRoleRepository userRoleRepository;

    @PostMapping("signin")
    public ResponseEntity<String> authenticateUser(@RequestBody LoginDto loginDto){
       Authentication authentication = authenticationManager
               .authenticate(new UsernamePasswordAuthenticationToken(loginDto.getUsernameOrEmail(),loginDto.getPassword()));
        SecurityContextHolder.getContext().setAuthentication(authentication);
        return new ResponseEntity<>("User signed in successfully", HttpStatus.OK);
    }
    @PostMapping("/signup")
    public ResponseEntity<?> registerUser(@RequestBody SignUpDto signUpDto){
        if(userRepository.existsByUsername(signUpDto.getUsername())){
            return new ResponseEntity<>("Username is already taken !",HttpStatus.BAD_REQUEST);
        }
        if(userRepository.existsByEmail(signUpDto.getEmail())){
            return new ResponseEntity<>("Email already taken!",HttpStatus.BAD_REQUEST);
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
        return new ResponseEntity<>("User Registered Successfully",HttpStatus.OK);

    }
}
