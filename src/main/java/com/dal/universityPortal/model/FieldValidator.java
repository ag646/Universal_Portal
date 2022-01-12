package com.dal.universityPortal.model;

import com.dal.universityPortal.validator.Validator;

public class FieldValidator<T> {

    private String field;
    private T value;
    private Validator<T> validator;

    public FieldValidator(String field, T value, Validator<T> validator) {
        this.field = field;
        this.value = value;
        this.validator = validator;
    }

    public String getField() {
        return field;
    }

    public T getValue() {
        return value;
    }

    public Validator<T> getValidator() {
        return validator;
    }
}
