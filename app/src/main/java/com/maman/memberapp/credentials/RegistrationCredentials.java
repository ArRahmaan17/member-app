package com.maman.memberapp.credentials;

public class RegistrationCredentials {
    private String phone_number, password, referral_code;

    public RegistrationCredentials(String phone_number, String password, String referral_code) {
        this.phone_number = phone_number;
        this.password = password;
        this.referral_code = referral_code;
    }
}
