package zw.co.afrosoft.myblog.service;

import zw.co.afrosoft.myblog.dtos.LoginDto;
import zw.co.afrosoft.myblog.dtos.SignUpDto;

public interface AuthService {
    void authenticateUser(LoginDto loginDto);
    void registerUser( SignUpDto signUpDto);
}
