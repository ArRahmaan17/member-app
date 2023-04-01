package com.maman.memberapp.route;

import com.maman.memberapp.credentials.LoginCredentials;
import com.maman.memberapp.credentials.RegistrationCredentials;
import com.maman.memberapp.response.LoginResponse;
import com.maman.memberapp.response.RegistrationResponse;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface Endpoint {
    @POST("login")
    Call<LoginResponse> login(@Body LoginCredentials login);
    @POST("registration")
    Call<RegistrationResponse> registration(@Body RegistrationCredentials registration);
}