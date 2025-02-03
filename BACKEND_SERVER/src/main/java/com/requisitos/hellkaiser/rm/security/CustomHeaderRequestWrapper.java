package com.requisitos.hellkaiser.rm.security;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletRequestWrapper;

import java.util.Collections;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;

public class CustomHeaderRequestWrapper extends HttpServletRequestWrapper {
    private final Map<String, String> customHeaders;

    public CustomHeaderRequestWrapper(HttpServletRequest request) {
        super(request);
        this.customHeaders = new HashMap<>();
    }

    public void addHeader(String name, String value) {
        customHeaders.put(name, value);
    }

    @Override
    public String getHeader(String name) {
        // Check the custom headers first
        if (customHeaders.containsKey(name)) {
            return customHeaders.get(name);
        }
        // Fall back to the original request
        return super.getHeader(name);
    }

    @Override
    public Enumeration<String> getHeaders(String name) {
        // Check the custom headers first
        if (customHeaders.containsKey(name)) {
            return Collections.enumeration(Collections.singletonList(customHeaders.get(name)));
        }
        // Fall back to the original request
        return super.getHeaders(name);
    }

    @Override
    public Enumeration<String> getHeaderNames() {
        // Combine custom headers with original headers
        Map<String, String> allHeaders = new HashMap<>();
        Collections.list(super.getHeaderNames()).forEach(headerName ->
                allHeaders.put(headerName, super.getHeader(headerName))
        );

        allHeaders.putAll(customHeaders);
        return Collections.enumeration(allHeaders.keySet());
    }
}