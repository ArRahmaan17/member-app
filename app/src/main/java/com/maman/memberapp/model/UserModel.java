package com.maman.memberapp.model;

import android.content.Intent;

import com.google.gson.annotations.SerializedName;

public class UserModel {
    private int id;
    private String phone_number;
    private String name;
    private String email;
    private String address;
    private String referral_code, qr_code;
    private boolean developer, administration;

    public String getQr_code() {
        return qr_code;
    }

    public boolean isAdministration() {
        return administration;
    }

    private String created_at;
    private String updated_at;

    public UserModel(String phone_number, String name, String email, String address, String referral_code, boolean developer) {
        this.phone_number = phone_number;
        this.name = name;
        this.email = email;
        this.address = address;
        this.referral_code = referral_code;
        this.developer = developer;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getPhone_number() {
        return phone_number;
    }

    public void setPhone_number(String phone_number) {
        this.phone_number = phone_number;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getReferral_code() {
        return referral_code;
    }

    public void setReferral_code(String referral_code) {
        this.referral_code = referral_code;
    }

    public boolean isDeveloper() {
        return developer;
    }

    public void setDeveloper(boolean developer) {
        this.developer = developer;
    }

    public String getCreated_at() {
        return created_at;
    }

    public void setCreated_at(String created_at) {
        this.created_at = created_at;
    }

    public String getUpdated_at() {
        return updated_at;
    }

    public void setUpdated_at(String updated_at) {
        this.updated_at = updated_at;
    }
}
