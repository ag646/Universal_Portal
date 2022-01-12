package com.dal.universityPortal.service;

import com.dal.universityPortal.model.FieldValidator;

import java.util.ArrayList;
import java.util.List;

public class ModelValidatorService {

    private List<String> errorMessages;

    public boolean isValid(List<FieldValidator> fieldValidators) {
        errorMessages = new ArrayList<>();
        fieldValidators.forEach(fieldValidator -> {
            if (!fieldValidator.getValidator().isValid(fieldValidator.getValue())) {
                this.errorMessages.add(fieldValidator.getField()+": "
                        +fieldValidator.getValidator().getErrorMessage());
            }
        });
        return this.errorMessages.isEmpty();
    }

    public List<String> getErrorMessages() {
        return errorMessages;
    }
}
