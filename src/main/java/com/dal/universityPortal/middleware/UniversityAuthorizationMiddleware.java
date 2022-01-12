package com.dal.universityPortal.middleware;

import com.dal.universityPortal.model.User;
import com.dal.universityPortal.model.UserType;
import com.dal.universityPortal.service.AuthenticationServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
public class UniversityAuthorizationMiddleware implements HandlerInterceptor {

    @Autowired
    AuthenticationServiceImpl authenticationService;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws IOException {
        User currentUser = authenticationService.getCurrentUser(request.getSession());
        boolean isAuthorized = currentUser.getTypeEnum().equals(UserType.UNIVERSITY);
        if (isAuthorized) {
            return true;
        }
        response.sendRedirect("/error/unauthorized");
        return false;
    }
}
