package com.dal.universityPortal.validator;
import java.util.regex.Pattern;

import static com.dal.universityPortal.constant.ErrorConstant.INVALID_MAIL_ID;
import static com.dal.universityPortal.constant.RegexConstant.EMAIL_REGEX;

public class EmailAddressValidator{

    public static boolean isValid(String mailID) {
        Pattern pat = Pattern.compile(EMAIL_REGEX);
        if (mailID == null) {
            return false;
        }
        return pat.matcher(mailID).matches();
    }
    public String getErrorMessage() {
        return INVALID_MAIL_ID;
    }
}
