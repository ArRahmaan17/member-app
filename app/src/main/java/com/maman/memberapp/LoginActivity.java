package com.maman.memberapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.motion.widget.MotionLayout;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputEditText;

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
                if(Username.getText().toString().isEmpty() && Password.getText().toString().isEmpty()){
                    Username.requestFocus();
                }else{
                    SharedPreferences sharedPreferences = getSharedPreferences("UserActive", Context.MODE_PRIVATE);
                    SharedPreferences.Editor editor = sharedPreferences.edit();
                    editor.putString("username", Username.getText().toString());
                    editor.apply();
                    IntentActivity();
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
        handlerFocusInputText = new Handler();

        // Delay the start of the animation by 3 seconds (adjust as needed)
        handlerMotionLayout.postDelayed(new Runnable() {
            @Override
            public void run() {
                MotionLogin.transitionToEnd();
            }
        }, 1500);
        handlerFocusInputText.postDelayed(new Runnable() {
            @Override
            public void run() {
//                Username.requestFocus();
                Log.d("focus", "run: focus input");
            }
        }, 3000);
    }
    public void IntentActivity(){
        Intent intent = new Intent(LoginActivity.this, MainActivity.class);
        startActivity(intent);
        finish();
    }
}