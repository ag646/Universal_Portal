package com.dal.universityPortal.validator;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static com.dal.universityPortal.constant.ErrorConstant.SPECIAL_CHAR_NOT_FOUND_ERROR;
import static com.dal.universityPortal.constant.RegexConstant.SPECIAL_CHAR_PRESENT_REGEX;

public class SpecialCharacterPresentValidator implements Validator<String> {

    public SpecialCharacterPresentValidator() {}

    @Override
    public boolean isValid(String string) {
        Pattern pattern = Pattern.compile(SPECIAL_CHAR_PRESENT_REGEX);
        Matcher matcher = pattern.matcher(string);
        return matcher.find();
    }

    @Override
    public String getErrorMessage() {
        return SPECIAL_CHAR_NOT_FOUND_ERROR;
    }
}
