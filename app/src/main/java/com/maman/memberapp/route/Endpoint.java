package com.maman.memberapp.route;

import com.maman.memberapp.credentials.LoginCredentials;
import com.maman.memberapp.credentials.ProfileCompleteCredentials;
import com.maman.memberapp.credentials.RegistrationCredentials;
import com.maman.memberapp.response.LoginResponse;
import com.maman.memberapp.response.RegistrationResponse;
import com.maman.memberapp.response.SuccessResponse;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.PATCH;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface Endpoint {
    @POST("login")
    Call<LoginResponse> login(@Body LoginCredentials login);
    @POST("registration")
    Call<RegistrationResponse> registration(@Body RegistrationCredentials registration);
    @PATCH("complete-profile/{id}")
    Call<SuccessResponse> completeProfile(@Path ("id") int id, @Body ProfileCompleteCredentials completeCredentials);
    @PATCH("edit-profile/{id}")
    Call<SuccessResponse> editProfile(@Path ("id") int id, @Body ProfileCompleteCredentials completeCredentials);

    @DELETE("logout/{id}")
    Call<SuccessResponse> logout(@Path ("id") int id);
}
