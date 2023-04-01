package com.maman.memberapp.response;

import java.lang.reflect.Array;

public class RegistrationResponse {
    private String status, message;
    private Array errors;

    public String getStatus() {
        return status;
    }

    public String getMessage() {
        return message;
    }

    public Array getErrors() {
        return errors;
    }
}
