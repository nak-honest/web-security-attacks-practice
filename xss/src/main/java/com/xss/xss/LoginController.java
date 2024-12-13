package com.xss.xss;

import com.xss.xss.infrastructure.TokenCookieProvider;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseCookie;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class LoginController {

    private LoginService loginService;

    public LoginController(LoginService loginService) {
        this.loginService = loginService;
    }

    @GetMapping("/login-for-attack")
    public ResponseEntity<Void> loginForAttack(@RequestParam("name") String name) {
        // XSS 공격이 가능하도록 httpOnly를 false로 설정한다.
        ResponseCookie cookie = TokenCookieProvider.createCookie(loginService.login(name), false);

        return ResponseEntity.ok()
                .header(HttpHeaders.SET_COOKIE, cookie.toString())
                .contentType(MediaType.APPLICATION_JSON)
                .build();
    }

    @GetMapping("/login-for-defend")
    public ResponseEntity<Void> loginForDefend(@RequestParam("name") String name) {
        // httpOnly를 true로 설정하면 JS 로 액세스 토큰을 꺼내는 것이 불가능하다.
        ResponseCookie cookie = TokenCookieProvider.createCookie(loginService.login(name), true);

        return ResponseEntity.ok()
                .header(HttpHeaders.SET_COOKIE, cookie.toString())
                .contentType(MediaType.APPLICATION_JSON)
                .build();
    }
}
