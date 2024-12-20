package com.xss.xss.global;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletRequestWrapper;
import java.util.HashMap;
import java.util.Map;

public class XssDefendRequestWrapper extends HttpServletRequestWrapper {

    public XssDefendRequestWrapper(HttpServletRequest request) {
        super(request);
    }

    @Override
    public String getParameter(String name) {
        String value = super.getParameter(name);
        return XssEscape.escape(value);
    }

    @Override
    public String[] getParameterValues(String name) {
        String[] values = super.getParameterValues(name);
        if (values == null) {
            return null;
        }
        for (int i = 0; i < values.length; i++) {
            values[i] = XssEscape.escape(values[i]);
        }
        return values;
    }

    @Override
    public Map<String, String[]> getParameterMap() {
        Map<String, String[]> parameterMap = super.getParameterMap();
        Map<String, String[]> escapedMap = new HashMap<>();
        for (Map.Entry<String, String[]> entry : parameterMap.entrySet()) {
            String[] escapedValues = new String[entry.getValue().length];
            for (int i = 0; i < escapedValues.length; i++) {
                escapedValues[i] = XssEscape.escape(entry.getValue()[i]);
            }
            escapedMap.put(entry.getKey(), escapedValues);
        }
        return escapedMap;
    }


    @Override
    public String getHeader(String name) {
        String value = super.getHeader(name);
        return XssEscape.escape(value);
    }
}
