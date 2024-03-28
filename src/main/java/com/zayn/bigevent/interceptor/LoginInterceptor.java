package com.zayn.bigevent.interceptor;

import com.zayn.bigevent.utils.JWTUtil;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

/**
 * @author zayn
 * * @date 2024/3/27/21:02
 */
@Component
public class LoginInterceptor implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String token = request.getHeader("Authorization");
        if (token == null) {
            response.setStatus(401);
            return false;
        }
        return JWTUtil.verifyToken(token);
    }
}
