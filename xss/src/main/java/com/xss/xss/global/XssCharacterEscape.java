package com.xss.xss.global;

import com.fasterxml.jackson.core.SerializableString;
import com.fasterxml.jackson.core.io.CharacterEscapes;
import com.fasterxml.jackson.core.io.SerializedString;
import java.util.Arrays;

public class XssCharacterEscape extends CharacterEscapes {
    private final int[] asciiEscapes;

    public XssCharacterEscape() {
        asciiEscapes = CharacterEscapes.standardAsciiEscapesForJSON();
        Arrays.stream(XssEscape.values())
                .forEach(xssEscape -> asciiEscapes[xssEscape.getCharacter()] = CharacterEscapes.ESCAPE_CUSTOM);
    }

    @Override
    public int[] getEscapeCodesForAscii() {
        return asciiEscapes;
    }

    @Override
    public SerializableString getEscapeSequence(int ch) {
        return new SerializedString(XssEscape.escape(ch));
    }
}
