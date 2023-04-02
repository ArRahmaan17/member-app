package com.maman.memberapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputEditText;
import com.google.gson.Gson;
import com.maman.memberapp.credentials.RegistrationCredentials;
import com.maman.memberapp.helper.ServiceGenerator;
import com.maman.memberapp.response.ErrorResponse;
import com.maman.memberapp.response.RegistrationResponse;
import com.maman.memberapp.route.Endpoint;
import com.maman.memberapp.utils.ErrorUtils;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RegistrationActivity extends AppCompatActivity {

    private TextInputEditText phoneNumberInput, passwordInput, refferalCodeInput;
    private Button registrationButton;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);
        registrationButton = (Button) findViewById(R.id.button_register);
        registrationButton.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v) {
                RegistrationService();
            }
        });
    }
    public void RegistrationSuccesed(){
        Intent redirectToLogin = new Intent(this, LoginActivity.class);
        startActivity(redirectToLogin);
        finish();
    }

    public void RegistrationService(){
        phoneNumberInput = (TextInputEditText) findViewById(R.id.username_input_registration);
        passwordInput = (TextInputEditText) findViewById(R.id.password_input_registration);
        refferalCodeInput = (TextInputEditText) findViewById(R.id.code_referral_input_registration);
        String phoneNumber = phoneNumberInput.getText().toString().trim();
        String password = passwordInput.getText().toString().trim();
        String refferalCode = refferalCodeInput.getText().toString().trim();
        Endpoint endpoint = ServiceGenerator.createService(Endpoint.class);
        RegistrationCredentials registrationCredentials = new RegistrationCredentials(phoneNumber, password, refferalCode);
        Call<RegistrationResponse> call = endpoint.registration(registrationCredentials);
        call.enqueue(new Callback<RegistrationResponse>() {
            @Override
            public void onResponse(Call<RegistrationResponse> call, Response<RegistrationResponse> response) {
                if (!response.isSuccessful()) {
                    ErrorResponse error = new ErrorUtils().parseError(response);
                    Gson gson = new Gson();
                    String errorsString = gson.toJson(error.getErrors());
                    ErrorResponse errorObject = gson.fromJson(errorsString, ErrorResponse.class);
                    if(!errorObject.getPhone_number().isEmpty()){
                        Toast.makeText(RegistrationActivity.this, "" + errorObject.getPhone_number().get(0), Toast.LENGTH_SHORT).show();
                    }else if (!errorObject.getPassword().isEmpty()){
                        Toast.makeText(RegistrationActivity.this, "" + errorObject.getPassword().get(0), Toast.LENGTH_SHORT).show();
                    }else if(!errorObject.getRefferal_code().isEmpty()){
                        Toast.makeText(RegistrationActivity.this, "" + errorObject.getRefferal_code().get(0), Toast.LENGTH_SHORT).show();
                    }
                    return;
                }
                Toast.makeText(RegistrationActivity.this, "" + response.body().getMessage(), Toast.LENGTH_SHORT).show();
                RegistrationSuccesed();
            }

            @Override
            public void onFailure(Call<RegistrationResponse> call, Throwable t) {

            }
        });
    }
}