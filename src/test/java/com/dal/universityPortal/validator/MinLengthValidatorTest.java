package com.dal.universityPortal.validator;

import org.junit.jupiter.api.Test;

import static com.dal.universityPortal.constant.ErrorConstant.MIN_LENGTH_ERROR;
import static org.junit.jupiter.api.Assertions.*;

public class MinLengthValidatorTest {

    String shortString = "aa";
    String longString = "aaaaaa";
    String noString = "";
    Integer minLength = 3;
    MinLengthValidator validator = new MinLengthValidator(minLength);

    @Test
    void stringWithoutMinLength_returnsFalse() {
        assertFalse(validator.isValid(shortString));
    }

    @Test
    void stringWithMinLength_returnsTrue() {
        assertTrue(validator.isValid(longString));
    }

    @Test
    void stringWithNoLength_returnsFalse() {
        assertFalse(validator.isValid(noString));
    }

    @Test
    void getErrorMessage_returnsErrorMessage() {
        assertEquals(String.format(MIN_LENGTH_ERROR, minLength),
                validator.getErrorMessage());
    }
}
