package com.xss.xss;

import com.xss.xss.infrastructure.JwtTokenProvider;
import org.springframework.stereotype.Service;

@Service
public class LoginService {

    private final JwtTokenProvider jwtTokenProvider;

    public LoginService(JwtTokenProvider jwtTokenProvider) {
        this.jwtTokenProvider = jwtTokenProvider;
    }

    public String login(String name) {
        return jwtTokenProvider.createToken(name);
    }
}
