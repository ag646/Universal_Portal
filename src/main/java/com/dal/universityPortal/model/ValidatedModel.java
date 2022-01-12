package com.dal.universityPortal.model;

import com.dal.universityPortal.service.ModelValidatorService;

import java.util.List;

public abstract class ValidatedModel {
    private final ModelValidatorService validator;

    ValidatedModel(ModelValidatorService validator) {
        this.validator = validator;
    }

    abstract List<FieldValidator> getFieldValidatorMapping();

    public boolean isValid() {
        return validator.isValid(getFieldValidatorMapping());
    }

    public List<String> getErrorMessages() {
        return validator.getErrorMessages();
    }
}
