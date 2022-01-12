package com.dal.universityPortal.middleware;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import static com.dal.universityPortal.constant.MiddlewareConstant.excludedPaths;
import static com.dal.universityPortal.constant.MiddlewareConstant.universityAuthorizedOnlyPaths;

@Configuration
public class MiddlewareConfig implements WebMvcConfigurer {

    @Autowired
    AuthenticationMiddleware authenticationMiddleware;

    @Autowired
    AuthorizationMiddleware authorizationMiddleware;

    @Autowired
    UniversityAuthorizationMiddleware universityAuthorizationMiddleware;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(authenticationMiddleware).excludePathPatterns(excludedPaths);
        registry.addInterceptor(authorizationMiddleware).excludePathPatterns(excludedPaths);
        registry.addInterceptor(universityAuthorizationMiddleware).addPathPatterns(universityAuthorizedOnlyPaths);

    }
}
