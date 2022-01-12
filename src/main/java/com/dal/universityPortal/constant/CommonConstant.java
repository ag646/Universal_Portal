package com.dal.universityPortal.constant;

public class CommonConstant {
    public static final String RESET_PASSWORD_MAIL_SUBJECT = "Reset Password Code";
    public static final String RESET_PASSWORD_MAIL_FORMAT = "The password reset code is: %s";
    public static final Integer RESET_CODE_RANGE = 999999;
    public static final String INITIAL_APPLICATION_STATUS = "Pending";
    public static final String INITIAL_OUTCOME_TYPE = "Grade";
    public static final String DB_CONNECTION_FORMAT = "jdbc:mysql://%s:%s/%s?user=%s&password=%s";
    public static final String PROPERTIES_FILE = "src/main/resources/application.properties";

    private CommonConstant() {}
}
