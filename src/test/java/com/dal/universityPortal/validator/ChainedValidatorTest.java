package com.dal.universityPortal.validator;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;

@ExtendWith(MockitoExtension.class)
public class ChainedValidatorTest {
    private static final String shortStringWithUpperCase = "aA";
    private static final String minLengthValidatorError = "minLengthError";
    private static final String upperCasePresentValidatorError = "upperCaseError";
    private static final String stringWithUpperCase = "aaSA";
    private static final Integer minLength = 3;

    @Mock
    private final MinLengthValidator minLengthValidator = new MinLengthValidator(minLength);

    @Mock
    private UpperCasePresent upperCasePresent;

    private static ChainedValidator<String> validator;

    @BeforeEach
    public void setUp()  {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void chainedValidator_returnsTrue() {
        List<Validator<String>> chainedValidators = Arrays.asList(minLengthValidator, upperCasePresent);
        validator = new ChainedValidator<String>(chainedValidators);
        Mockito.when(minLengthValidator.isValid(any())).thenReturn(true);
        Mockito.when(upperCasePresent.isValid(any())).thenReturn(true);
        assertTrue(validator.isValid(stringWithUpperCase));
    }

    @Test
    void chainedValidatorWithOneError_returnsFalse() {
        List<Validator<String>> chainedValidators = Arrays.asList(minLengthValidator, upperCasePresent);
        validator = new ChainedValidator<String>(chainedValidators);
        Mockito.when(minLengthValidator.isValid(any())).thenReturn(false);
        Mockito.when(upperCasePresent.isValid(any())).thenReturn(true);
        Mockito.when(minLengthValidator.getErrorMessage()).thenReturn(minLengthValidatorError);
        assertFalse(validator.isValid(shortStringWithUpperCase));
        String expectedErrorString = String.join(", ", minLengthValidatorError);
        assertEquals(expectedErrorString, validator.getErrorMessage());
    }

    @Test
    void chainedValidatorWithTwoErrors_returnsFalse() {
        List<Validator<String>> chainedValidators = Arrays.asList(minLengthValidator, upperCasePresent);
        validator = new ChainedValidator<String>(chainedValidators);
        Mockito.when(minLengthValidator.isValid(any())).thenReturn(false);
        Mockito.when(upperCasePresent.isValid(any())).thenReturn(false);
        Mockito.when(minLengthValidator.getErrorMessage()).thenReturn(minLengthValidatorError);
        Mockito.when(upperCasePresent.getErrorMessage()).thenReturn(upperCasePresentValidatorError);
        assertFalse(validator.isValid(stringWithUpperCase));
        String expectedErrorString = String.join(", ", Arrays.asList(minLengthValidatorError, upperCasePresentValidatorError));
        assertEquals(expectedErrorString, validator.getErrorMessage());
    }
}
