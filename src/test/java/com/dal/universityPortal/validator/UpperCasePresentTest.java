package com.dal.universityPortal.validator;

import org.junit.jupiter.api.Test;

import static com.dal.universityPortal.constant.ErrorConstant.UPPERCASE_NOT_FOUND_ERROR;
import static org.junit.jupiter.api.Assertions.*;

public class UpperCasePresentTest {

    String stringWithUpperCase = "Aa";
    String stringWithoutUpperCase = "aa";
    String stringWithSpecialCharacter = "@a";
    UpperCasePresent validator = new UpperCasePresent();

    @Test
    void isValidWithStringWithUpperCase_returnsTrue() {
        assertTrue(validator.isValid(stringWithUpperCase));
    }

    @Test
    void isValidWithStringWithLowerCase_returnsFalse() {
        assertFalse(validator.isValid(stringWithoutUpperCase));
    }

    @Test
    void isValidWithStringWithSpecialChar_returnsFalse() {
        assertFalse(validator.isValid(stringWithSpecialCharacter));
    }

    @Test
    void getErrorMessage_returnsErrorMessage() {
        assertEquals(UPPERCASE_NOT_FOUND_ERROR, validator.getErrorMessage());
    }
}
