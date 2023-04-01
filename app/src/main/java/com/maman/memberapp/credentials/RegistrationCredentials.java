package com.maman.memberapp.credentials;

public class RegistrationCredentials {
    private String phone_number, password, refferal_code;

    public RegistrationCredentials(String phone_number, String password, String refferal_code) {
        this.phone_number = phone_number;
        this.password = password;
        this.refferal_code = refferal_code;
    }
}
