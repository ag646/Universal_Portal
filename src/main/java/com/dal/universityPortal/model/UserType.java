package com.dal.universityPortal.model;

public enum UserType {

    UNIVERSITY("/university/"),
    STUDENT("/student/"),
    ADMIN("/admin/"),
    STAFF("/university/");

    UserType(String allowedRoute) {
        this.allowedRoute = allowedRoute;
    }

    private static final String ALL_CHAR = ".*";
    private final String allowedRoute;

    public boolean isRouteAllowed(String route) {
        String regexForRoute = allowedRoute + ALL_CHAR;
        return route.matches(regexForRoute);
    }
}
