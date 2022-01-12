package com.dal.universityPortal.validator;

public interface Validator<T> {

    boolean isValid(T t);
    String getErrorMessage();
}
