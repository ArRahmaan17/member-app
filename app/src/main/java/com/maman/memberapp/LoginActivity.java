package com.maman.memberapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.motion.widget.MotionLayout;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;
import com.google.android.material.textfield.TextInputEditText;
import com.google.gson.Gson;
import com.maman.memberapp.credentials.LoginCredentials;
import com.maman.memberapp.helper.ServiceGenerator;
import com.maman.memberapp.model.UserModel;
import com.maman.memberapp.response.LoginResponse;
import com.maman.memberapp.route.Endpoint;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class LoginActivity extends AppCompatActivity {
    private Handler handlerMotionLayout;
    private TextInputEditText Username, Password;
    private TextView registrationText;
    private Handler handlerFocusInputText;
    private Button BtnLogin;
    private MotionLayout MotionLogin;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        MotionLogin = (MotionLayout) findViewById(R.id.motion_login);
        Username = (TextInputEditText) findViewById(R.id.username_input);
        Password = (TextInputEditText) findViewById(R.id.password_input);
        BtnLogin = (Button) findViewById(R.id.login_button);
        registrationText = (TextView) findViewById(R.id.register_text);

        SharedPreferences sharedPreferences = getSharedPreferences("UserActive", Context.MODE_PRIVATE);
        if(!sharedPreferences.getAll().isEmpty()){
            IntentActivity();
        }
        BtnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(Username.getText().toString().isEmpty() || Password.getText().toString().isEmpty()){
                    Username.requestFocus();
                }else{
                    processLoginUser(Username.getText().toString(), Password.getText().toString());
                }
            }
        });
        registrationText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(LoginActivity.this, RegistrationActivity.class);
                startActivity(intent);
                finish();
            }
        });
        handlerMotionLayout = new Handler();

        // Delay the start of the animation by 3 seconds (adjust as needed)
        handlerMotionLayout.postDelayed(new Runnable() {
            @Override
            public void run() {
                MotionLogin.transitionToEnd();
            }
        }, 1500);
    }

    private boolean processLoginUser(String username, String password){
        Endpoint endpoint = ServiceGenerator.createService(Endpoint.class);
        LoginCredentials user = new LoginCredentials(username, password);
        Call<LoginResponse> call = endpoint.login(user);
        call.enqueue(new Callback<LoginResponse>() {
            @Override
            public void onResponse(Call<LoginResponse> call, Response<LoginResponse> response) {
                if(!response.isSuccessful()){
                    if(response.code() == 404){
                        Toast.makeText(LoginActivity.this, ""+response.code() + " Your credentials not match to our records", Toast.LENGTH_SHORT).show();
                    }else if(response.code() == 422){
                        Toast.makeText(LoginActivity.this, ""+response.code() + " Please Check Your Phone Number and Password", Toast.LENGTH_SHORT).show();
                    }
                    return;
                }
                Toast.makeText(LoginActivity.this, response.body().getStatus()+ " "+ response.body().getMessage(), Toast.LENGTH_SHORT).show();
                SharedPreferences sharedPreferences = getSharedPreferences("UserActive", Context.MODE_PRIVATE);
                SharedPreferences.Editor editor = sharedPreferences.edit();
                Gson gson = new Gson();
                String userString = gson.toJson(response.body().getUser());
                UserModel user = gson.fromJson(userString, UserModel.class);
                editor.putInt("id", user.getId());
                editor.putString("name", user.getName());
                editor.putString("phone_number", user.getPhone_number());
                editor.putString("address", user.getAddress());
                editor.putString("email", user.getEmail());
                editor.putString("referral_code", user.getReferral_code());
                editor.putString("qr_code", user.getQr_code());
                editor.putBoolean("developer", user.isDeveloper());
                editor.putBoolean("administration", user.isAdministration());
                editor.putString("personal_token", response.body().getToken());
                editor.apply();
                IntentActivity();
            }

            @Override
            public void onFailure(Call<LoginResponse> call, Throwable t) {

            }
        });
//        Toast.makeText(this, "Login "+ username+" "+password, Toast.LENGTH_SHORT).show();
        return false;
    }
    public void IntentActivity(){
        Intent intent = new Intent(LoginActivity.this, MainActivity.class);
        startActivity(intent);
        finish();
    }
}