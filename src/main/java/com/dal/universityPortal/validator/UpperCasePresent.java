package com.dal.universityPortal.validator;

import static com.dal.universityPortal.constant.ErrorConstant.UPPERCASE_NOT_FOUND_ERROR;

public class UpperCasePresent implements Validator<String> {

    @Override
    public boolean isValid(String string) {
        return string.chars().anyMatch(Character::isUpperCase);
    }

    @Override
    public String getErrorMessage() {
        return UPPERCASE_NOT_FOUND_ERROR;
    }
}
