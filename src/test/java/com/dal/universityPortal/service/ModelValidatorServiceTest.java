package com.dal.universityPortal.service;

import com.dal.universityPortal.model.FieldValidator;
import com.dal.universityPortal.validator.Validator;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;

class ModelValidatorServiceTest {

    private static final String INVALID_INTEGER = "Invalid Integer";

    @Mock
    Validator<String> stringValidator;

    @Mock
    Validator<Integer> integerValidator;

    ModelValidatorService modelValidatorService = new ModelValidatorService();

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void shouldReturnTrueIfValidatorsReturnTrue() {
        Mockito.when(stringValidator.isValid(any())).thenReturn(true);
        Mockito.when(integerValidator.isValid(any())).thenReturn(true);
        assertTrue(modelValidatorService.isValid(getFieldValidators()));
    }

    @Test
    void shouldReturnTrueIfAnyValidatorsReturnFalse() {
        Mockito.when(stringValidator.isValid(any())).thenReturn(true);
        Mockito.when(integerValidator.isValid(any())).thenReturn(false);
        Mockito.when(integerValidator.getErrorMessage()).thenReturn(INVALID_INTEGER);
        assertFalse(modelValidatorService.isValid(getFieldValidators()));
        String errorMessage = modelValidatorService.getErrorMessages().get(0);
        assertTrue(errorMessage.contains(INVALID_INTEGER));
    }

    private List<FieldValidator> getFieldValidators() {
        List<FieldValidator> fieldValidators = new ArrayList<>();
        fieldValidators.add(new FieldValidator<>("stringField", "stringValue", stringValidator));
        fieldValidators.add(new FieldValidator<>("intField", 1, integerValidator));
        return fieldValidators;
    }
}
