package com.dal.universityPortal.exceptions;

import java.util.ArrayList;
import java.util.List;

public class ValidationException extends Exception {

    List<String> errors = new ArrayList<>();
    String error;

    public ValidationException(List<String> errors){
        super();
        this.errors = errors;
    }

    public ValidationException(String error){
        super();
        this.errors.add(error);
    }

    public List<String> getErrors(){
        return errors;
    }
}
