package com.cursosp.projetosp.resources.exceptions;

import java.util.ArrayList;
import java.util.List;

public class ValidationError extends StrandartError{

    private List<FieldMessage> errors = new ArrayList<>();

    public ValidationError(Integer status, String msg, Long timeStamp) {
        super(status, msg, timeStamp);
    }

    public List<FieldMessage> getFieldMessages() {
        return errors;
    }

    public void addError(String fieldName, String message){
        errors.add(new FieldMessage(fieldName, message));
    }
}
