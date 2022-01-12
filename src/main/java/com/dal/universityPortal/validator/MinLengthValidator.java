package com.dal.universityPortal.validator;

import static com.dal.universityPortal.constant.ErrorConstant.MIN_LENGTH_ERROR;

public class MinLengthValidator implements Validator<String> {

    Integer minLength;

    public MinLengthValidator(Integer minLength) {
        this.minLength = minLength;
    }

    @Override
    public boolean isValid(String string) {
        return minLength <= string.length();
    }

    @Override
    public String getErrorMessage() {
        return String.format(MIN_LENGTH_ERROR, minLength);
    }
}
