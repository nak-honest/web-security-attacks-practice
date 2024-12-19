package com.xss.xss.global;

import java.util.Arrays;

public enum XssEscape {
    LESS_THAN('<', "&lt;"),
    GREATER_THAN('>', "&gt;"),
    DOUBLE_QUOTE('\'', "&quot;"),
    SINGLE_QUOTE('"', "&#x27;"),
    AMPERSAND('&', "&amp;");

    private final char character;
    private final String escape;

    XssEscape(char character, String escape) {
        this.character = character;
        this.escape = escape;
    }

    public static String escape(String input) {
        if (input == null) {
            return null;
        }

        for (XssEscape escape : XssEscape.values()) {
            input = input.replace(String.valueOf(escape.getCharacter()), escape.getEscape());
        }
        return input;
    }

    public static String escape(int character) {
        return Arrays.stream(XssEscape.values())
                .filter(xssEscape -> xssEscape.character == character)
                .map(XssEscape::getEscape)
                .findFirst()
                .orElse(null);
    }

    public char getCharacter() {
        return character;
    }

    public String getEscape() {
        return escape;
    }
}
