package com.dal.universityPortal.constant;

import java.util.Arrays;
import java.util.List;

public class MiddlewareConstant {
    public static final List<String> excludedPaths = Arrays.asList("/registration", "/login",
            "/logout", "/error/*", "/reset_password", "/reset_password/*");
    public static final String universityAuthorizedOnlyPaths = "/university/add_staff";

    private MiddlewareConstant() {}
}
