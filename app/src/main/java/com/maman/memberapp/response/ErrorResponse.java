package com.maman.memberapp.response;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

public class ErrorResponse {
    private String status, message;
    private Object errors;
    private List<String> phone_number = new ArrayList<>();
    private List<String> password = new ArrayList<>();
    private List<String> refferal_code = new ArrayList<>();
    private List<String> name = new ArrayList<>();
    private List<String> email = new ArrayList<>();
    private List<String> address = new ArrayList<>();

    public List<String> getName() {
        return name;
    }

    public List<String> getEmail() {
        return email;
    }

    public List<String> getAddress() {
        return address;
    }

    public List<String> getPhone_number() {
        return phone_number;
    }

    public List<String> getPassword() {
        return password;
    }

    public List<String> getRefferal_code() {
        return refferal_code;
    }

    public String getStatus() {
        return status;
    }

    public String getMessage() {
        return message;
    }

        public Object getErrors() {
        return errors;
    }
}
