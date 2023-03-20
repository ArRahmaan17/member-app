package com.maman.memberapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.motion.widget.MotionLayout;

import android.os.Bundle;
import android.os.Handler;
import android.util.Log;

import com.google.android.material.textfield.TextInputEditText;

public class LoginActivity extends AppCompatActivity {
    private Handler handlerMotionLayout;
    private TextInputEditText Username, Password;
    private Handler handlerFocusInputText;
    private MotionLayout MotionLogin;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        MotionLogin = (MotionLayout) findViewById(R.id.motion_login);
        Username = (TextInputEditText) findViewById(R.id.username_input);
        handlerMotionLayout = new Handler();
        handlerFocusInputText = new Handler();
        Username.clearFocus();
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
}