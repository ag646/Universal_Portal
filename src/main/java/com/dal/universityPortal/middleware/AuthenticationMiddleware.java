package com.dal.universityPortal.middleware;

import com.dal.universityPortal.model.User;
import com.dal.universityPortal.service.AuthenticationServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static java.util.Objects.isNull;

@Component
public class AuthenticationMiddleware implements HandlerInterceptor {

    @Autowired
    AuthenticationServiceImpl authenticationService;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws IOException {
        User currentUser = authenticationService.getCurrentUser(request.getSession());
        if (isNull(currentUser)) {
            response.sendRedirect("/login");
            return false;
        }
        return true;
    }
}
