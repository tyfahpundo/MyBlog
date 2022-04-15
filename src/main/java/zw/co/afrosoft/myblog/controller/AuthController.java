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
import zw.co.afrosoft.myblog.dtos.JwtAuthResponse;
import zw.co.afrosoft.myblog.dtos.LoginDto;
import zw.co.afrosoft.myblog.dtos.SignUpDto;
import zw.co.afrosoft.myblog.service.AuthService;

@RestController
@RequestMapping("/api/auth/")
@AllArgsConstructor
public class AuthController {
    private final AuthService authService;


    @PostMapping("signin")
    public ResponseEntity<JwtAuthResponse> authenticateUser(@RequestBody LoginDto loginDto){
        String token = authService.authenticateUser(loginDto);
        return ResponseEntity.ok(new JwtAuthResponse(token));
    }
    @PostMapping("/signup")
    public ResponseEntity<?> registerUser(@RequestBody SignUpDto signUpDto){
        authService.registerUser(signUpDto);
        return new ResponseEntity<>("User Registered Successfully",HttpStatus.OK);

    }
}
