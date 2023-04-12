package com.maman.memberapp;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.textfield.TextInputEditText;
import com.google.gson.Gson;
import com.maman.memberapp.credentials.ProfileCompleteCredentials;
import com.maman.memberapp.helper.ServiceGenerator;
import com.maman.memberapp.response.ErrorResponse;
import com.maman.memberapp.response.SuccessResponse;
import com.maman.memberapp.route.Endpoint;
import com.maman.memberapp.utils.ErrorUtils;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class EditUserActivity extends AppCompatActivity {

    private TextView detailUsername, detailAddress, detailPhoneNumber, detailEmail;
    private FloatingActionButton EditUser, LogoutApp;
    private String username, address, phoneNumber, email;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_user);
        initializeActivity();
        EditUser = findViewById(R.id.edit_user_profile);
        EditUser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Dialog editUserDialog = new Dialog(EditUserActivity.this);
                editUserDialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
                editUserDialog.setContentView(R.layout.complete_user_profile);
                editUserDialog.setCancelable(true);
                editUserDialog.show();
                TextView Title = editUserDialog.findViewById(R.id.title);
                Title.setText("Edit Your Profile");
                TextInputEditText Name = editUserDialog.findViewById(R.id.name_dialog);
                TextInputEditText Email = editUserDialog.findViewById(R.id.email_dialog);
                TextInputEditText Address = editUserDialog.findViewById(R.id.address_dialog);
                Button button = editUserDialog.findViewById(R.id.button_completion_profile);
                SharedPreferences userPrefs = getSharedPreferences("UserActive", Context.MODE_PRIVATE);
                SharedPreferences.Editor editor = userPrefs.edit();
                Name.setText(userPrefs.getString("name", "").toString());
                Email.setText(userPrefs.getString("email", "").toString());
                Address.setText(userPrefs.getString("address", "").toString());
                String Token = "Bearer "+ userPrefs.getString("personal_token", "").toString();
                button.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        int id = userPrefs.getInt("id", 0);
                        Endpoint endpoint = ServiceGenerator.createService(Endpoint.class, Token);
                        ProfileCompleteCredentials profileComplete = new ProfileCompleteCredentials(Name.getText().toString(), Email.getText().toString(), Address.getText().toString());
                        Call<SuccessResponse> call = endpoint.editProfile(id,profileComplete);
                        call.enqueue(new Callback<SuccessResponse>() {
                            @Override
                            public void onResponse(Call<SuccessResponse> call, Response<SuccessResponse> response) {
                                if(!response.isSuccessful()){
                                    ErrorResponse error = new ErrorUtils().parseError(response);
                                    Gson gson = new Gson();
                                    String errorsString = gson.toJson(error.getErrors());
                                    ErrorResponse errorObject = gson.fromJson(errorsString, ErrorResponse.class);
                                    if(!errorObject.getAddress().isEmpty()){
                                        Toast.makeText(EditUserActivity.this, response.code()+" " + errorObject.getAddress().get(0), Toast.LENGTH_SHORT).show();
                                    }else if (!errorObject.getEmail().isEmpty()){
                                        Toast.makeText(EditUserActivity.this, response.code()+" " + errorObject.getEmail().get(0), Toast.LENGTH_SHORT).show();
                                    }else if(!errorObject.getName().isEmpty()){
                                        Toast.makeText(EditUserActivity.this, response.code()+" " + errorObject.getName().get(0), Toast.LENGTH_SHORT).show();
                                    }
                                    return;
                                }
                                Toast.makeText(EditUserActivity.this, response.code()+ " " +response.body().getMessage(), Toast.LENGTH_SHORT).show();
                                editor.putString("name", Name.getText().toString());
                                editor.putString("address",Address.getText().toString());
                                editor.putString("email",Email.getText().toString());
                                editor.apply();
                                editUserDialog.dismiss();
                                initializeActivity();
                            }
                            @Override
                            public void onFailure(Call<SuccessResponse> call, Throwable t) {
                                Toast.makeText(EditUserActivity.this, ""+t.getMessage(), Toast.LENGTH_SHORT).show();
                            }
                        });
                    }
                });
            }
        });
        LogoutApp = findViewById(R.id.logout_app);
        LogoutApp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showLogoutConfirmationDialog();
            }
        });
    }

    @Override
    public void onBackPressed() {
        Intent intent = getIntent();
        intent.putExtra("name", username);
        setResult(RESULT_OK, intent);
        finish();
    }

    private void initializeActivity(){
        detailUsername = (TextView) findViewById(R.id.detail_username_edit);
        detailAddress = (TextView) findViewById(R.id.detail_address_edit);
        detailPhoneNumber = (TextView) findViewById(R.id.detail_phone_number_edit);
        detailEmail = (TextView) findViewById(R.id.detail_email_edit);
        SharedPreferences sharedPreferences = getSharedPreferences("UserActive", Context.MODE_PRIVATE);
        username = sharedPreferences.getString("name","").toString();
        address = sharedPreferences.getString("address","").toString();
        email = sharedPreferences.getString("email","").toString();
        phoneNumber = sharedPreferences.getString("phone_number","").toString();
        detailUsername.setText(username);
        detailEmail.setText(email);
        detailAddress.setText(address);
        detailPhoneNumber.setText(phoneNumber);
    }

    private void showLogoutConfirmationDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Logout Confirmation");
        builder.setMessage("Are you sure you want to exit?");
        builder.setPositiveButton("Sure", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                doLogout();
            }
        });
        builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });
        AlertDialog dialog = builder.create();
        dialog.show();
    }

    private void doLogout(){
        SharedPreferences userPrefs = getSharedPreferences("UserActive", Context.MODE_PRIVATE);
        String Token = "Bearer "+ userPrefs.getString("personal_token", "");
        Log.d("logout", "doLogout: "+Token);
        int id = userPrefs.getInt("id", 0);
        Endpoint endpoint = ServiceGenerator.createService(Endpoint.class, Token);
        Call<SuccessResponse> call = endpoint.logout(id);
        call.enqueue(new Callback<SuccessResponse>() {
            @Override
            public void onResponse(Call<SuccessResponse> call, Response<SuccessResponse> response) {
                if (!response.isSuccessful()) {
                    ErrorResponse error = new ErrorUtils().parseError(response);
                    Toast.makeText(EditUserActivity.this, error.getStatus()+" "+error.getMessage(), Toast.LENGTH_SHORT).show();
                 return;
                }
                Toast.makeText(EditUserActivity.this, response.code()+" "+ response.body().getMessage(), Toast.LENGTH_SHORT).show();
                SharedPreferences.Editor editor = userPrefs.edit();
                editor.remove("address");
                editor.remove("name");
                editor.remove("qr_code");
                editor.remove("phone_number");
                editor.remove("developer");
                editor.remove("id");
                editor.remove("personal_token");
                editor.remove("email");
                editor.remove("administration");
                editor.apply();
                Intent intent = new Intent(EditUserActivity.this, LoginActivity.class);
                startActivity(intent);
                finish();
            }

            @Override
            public void onFailure(Call<SuccessResponse> call, Throwable t) {
                Toast.makeText(EditUserActivity.this, ""+t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }
}