package com.xss.xss.infrastructure;

import org.springframework.http.ResponseCookie;

public class TokenCookieProvider {

    private static final String COOKIE_NAME = "token";

    private TokenCookieProvider() {
    }

    public static ResponseCookie createCookie(String token) {
        return ResponseCookie.from(COOKIE_NAME, token)
                .httpOnly(false)  // XSS 공격이 가능하도록
                .path("/")
                .build();
    }

    public static String getCookieName() {
        return COOKIE_NAME;
    }
}
