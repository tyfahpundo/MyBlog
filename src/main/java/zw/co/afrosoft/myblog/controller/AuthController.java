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
import zw.co.afrosoft.myblog.service.AuthService;

@RestController
@RequestMapping("/api/auth/")
@AllArgsConstructor
public class AuthController {
    private final AuthService authService;

    @PostMapping("signin")
    public ResponseEntity<String> authenticateUser(@RequestBody LoginDto loginDto){
        authService.authenticateUser(loginDto);
        return new ResponseEntity<>("User signed in successfully", HttpStatus.OK);
    }
    @PostMapping("/signup")
    public ResponseEntity<?> registerUser(@RequestBody SignUpDto signUpDto){
        authService.registerUser(signUpDto);
        return new ResponseEntity<>("User Registered Successfully",HttpStatus.OK);

    }
}
