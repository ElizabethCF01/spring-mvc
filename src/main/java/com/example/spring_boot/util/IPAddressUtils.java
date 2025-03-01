package com.example.spring_boot.util;


import jakarta.servlet.http.HttpServletRequest;

public class IPAddressUtils {

    public static String getClientIpAddress(HttpServletRequest request) {
        String xForwardedForHeader = request.getHeader("X-Forwarded-For");
        if (xForwardedForHeader != null && !xForwardedForHeader.isEmpty()) {
            // The first IP in X-Forwarded-For is the  original client IP
            return xForwardedForHeader.split(",")[0].trim();
        }

        String proxyClientIp = request.getHeader("Proxy-Client-IP");
        if (proxyClientIp != null && !proxyClientIp.isEmpty()) {
            return proxyClientIp;
        }

        String wlProxyClientIp = request.getHeader("WL-Proxy-Client-IP");
        if (wlProxyClientIp != null && !wlProxyClientIp.isEmpty()) {
            return wlProxyClientIp;
        }

        // If there is no proxy headers, use getRemoteAddr()
        return request.getRemoteAddr();
    }
}


