package com.dal.universityPortal.validator;

import java.util.ArrayList;
import java.util.List;

public class ChainedValidator<T> implements Validator<T> {

    List<Validator<T>> validators;
    List<String> errorMessages;

    public ChainedValidator (List<Validator<T>> validators) {
        this.validators = validators;
        this.errorMessages = new ArrayList<>();
    }

    public boolean isValid(T t) {
        for(Validator<T> validator: validators) {
            if(!validator.isValid(t)) {
                errorMessages.add(validator.getErrorMessage());
            }
        }
        return errorMessages.isEmpty();
    }

    public String getErrorMessage() {
        return String.join(", ", errorMessages);
    }
}
