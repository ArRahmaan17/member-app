package com.maman.memberapp.response;

public class LoginResponse {
    private String status, message, token;
    private Object user;

    public String getStatus() {
        return status;
    }

    public String getMessage() {
        return message;
    }

    public String getToken() {
        return token;
    }

    public Object getUser() {
        return user;
    }
}
