package com.xss.xss.infrastructure;

import org.springframework.http.ResponseCookie;

public class TokenCookieProvider {

    private static final String COOKIE_NAME = "token";

    private TokenCookieProvider() {
    }

    public static ResponseCookie createCookie(String token, boolean httpOnly) {
        return ResponseCookie.from(COOKIE_NAME, token)
                .httpOnly(httpOnly)
                .path("/")
                .build();
    }
}
