package com.dal.universityPortal.validator;

import org.junit.jupiter.api.Test;

import static com.dal.universityPortal.constant.ErrorConstant.SPECIAL_CHAR_NOT_FOUND_ERROR;
import static org.junit.jupiter.api.Assertions.*;

public class SpecialCharacterPresentValidatorTest {

    String stringWithSpecialChar = "a@";
    String stringWithNoSpecialChar = "aa";
    SpecialCharacterPresentValidator validator = new SpecialCharacterPresentValidator();

    @Test
    void isValidWithStringWithSpecialChar_returnsTrue() {
        assertTrue(validator.isValid(stringWithSpecialChar));
    }

    @Test
    void isValidWithStringWithNoSpecialChar_returnsFalse() {
        assertFalse(validator.isValid(stringWithNoSpecialChar));
    }

    @Test
    void getErrorMessage_returnsErrorMessage() {
        assertEquals(SPECIAL_CHAR_NOT_FOUND_ERROR, validator.getErrorMessage());
    }
}
