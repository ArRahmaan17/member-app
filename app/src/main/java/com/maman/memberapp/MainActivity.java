package com.maman.memberapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.gson.Gson;
import com.maman.memberapp.credentials.ProfileCompleteCredentials;
import com.maman.memberapp.helper.ServiceGenerator;
import com.maman.memberapp.model.NewsModel;
import com.maman.memberapp.model.PromosModel;
import com.maman.memberapp.response.ErrorResponse;
import com.maman.memberapp.response.SuccessResponse;
import com.maman.memberapp.route.Endpoint;
import com.maman.memberapp.utils.ErrorUtils;

import java.util.ArrayList;
import android.view.Window;
import android.widget.Toast;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class MainActivity extends AppCompatActivity {
    private ArrayList<PromosModel> PromoList = new ArrayList<PromosModel>();
    private ArrayList<NewsModel> NewsList = new ArrayList<NewsModel>();
    private TextView userId, pointUser;
    private String Username;
    private Dialog globalDialog;
    private Button addNewPromoButton, addNewNewsButton;
    private CardView userCard;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        globalDialog = new Dialog(this);
        globalDialog.setCancelable(true);
        globalDialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        addNewPromoButton = findViewById(R.id.add_promo);
        addNewNewsButton = findViewById(R.id.add_news);
        addNewPromoButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                newPromo();
            }
        });
        addNewNewsButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                newNews();
            }
        });
        userCard = (CardView) findViewById(R.id.container_about_user);
        userCard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, UserProfileActivity.class);
                startActivity(intent);
            }
        });
        initializationApp();
    }

    private void newPromo(){
        globalDialog.setContentView(R.layout.new_promo);
    }

    private void newNews(){
        globalDialog.setContentView(R.layout.new_news);
    }


    private void initializationApp() {
        SharedPreferences sharedPreferences = getSharedPreferences("UserActive", Context.MODE_PRIVATE);
        Username = sharedPreferences.getString("name", "").toString();
        boolean developer = sharedPreferences.getBoolean("developer", false);
        boolean administration = sharedPreferences.getBoolean("administration", false);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        if(!developer || !administration){
            addNewPromoButton = findViewById(R.id.add_promo);
            addNewNewsButton = findViewById(R.id.add_news);
            addNewNewsButton.setVisibility(View.GONE);
            addNewPromoButton.setVisibility(View.GONE);
        }
        if(Username.isEmpty()){
            Dialog dialog = new Dialog(this);
            dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
            dialog.setContentView(R.layout.complete_user_profile);
            dialog.setCancelable(false);
            dialog.show();
            Button btnComplete = dialog.findViewById(R.id.button_completion_profile);
            TextView nameInput = dialog.findViewById(R.id.name_dialog);
            TextView addressInput = dialog.findViewById(R.id.address_dialog);
            TextView emailInput = dialog.findViewById(R.id.email_dialog);
            btnComplete.setOnClickListener(new View.OnClickListener(){
                @Override
                public void onClick(View view) {
                    String Token = "Bearer "+ sharedPreferences.getString("personal_token", "").toString();
                    int id = sharedPreferences.getInt("id", 0);
                    Endpoint endpoint = ServiceGenerator.createService(Endpoint.class, Token);
                    ProfileCompleteCredentials profileComplete = new ProfileCompleteCredentials(nameInput.getText().toString(), emailInput.getText().toString(), addressInput.getText().toString());
                    Call<SuccessResponse> call = endpoint.completeProfile(id,profileComplete);
                    call.enqueue(new Callback<SuccessResponse>() {
                        @Override
                        public void onResponse(Call<SuccessResponse> call, Response<SuccessResponse> response) {
                            if(!response.isSuccessful()){
                                ErrorResponse error = new ErrorUtils().parseError(response);
                                Gson gson = new Gson();
                                String errorsString = gson.toJson(error.getErrors());
                                ErrorResponse errorObject = gson.fromJson(errorsString, ErrorResponse.class);
                                if(!errorObject.getAddress().isEmpty()){
                                    Toast.makeText(MainActivity.this, response.code()+" " + errorObject.getAddress().get(0), Toast.LENGTH_SHORT).show();
                                }else if (!errorObject.getEmail().isEmpty()){
                                    Toast.makeText(MainActivity.this, response.code()+" " + errorObject.getEmail().get(0), Toast.LENGTH_SHORT).show();
                                }else if(!errorObject.getName().isEmpty()){
                                    Toast.makeText(MainActivity.this, response.code()+" " + errorObject.getName().get(0), Toast.LENGTH_SHORT).show();
                                }
                                return;
                            }
                            Toast.makeText(MainActivity.this, response.code()+ " " +response.body().getMessage(), Toast.LENGTH_SHORT).show();
                            editor.putString("name", nameInput.getText().toString());
                            editor.putString("address",addressInput.getText().toString());
                            editor.apply();
                            dialog.dismiss();
                            Username = sharedPreferences.getString("name", "").toString();
                            initializeAllData();
                        }
                        @Override
                        public void onFailure(Call<SuccessResponse> call, Throwable t) {
                            Toast.makeText(MainActivity.this, ""+t.getMessage(), Toast.LENGTH_SHORT).show();
                        }
                    });
                }
            });
        }
        else {
            initializeAllData();
        }
        initPromoRecyclerView();
    }
    private void initializeAllData(){
        userId = (TextView) findViewById(R.id.title_user);
        pointUser = (TextView) findViewById(R.id.title_point);
        userId.setText(Username);
        pointUser.setText("5.000.000");
        PromoList.add(new PromosModel("Dummy Promo 1", "Test Promo"));
        PromoList.add(new PromosModel("Dummy Promo 2", "Test Promo"));
        PromoList.add(new PromosModel("Dummy Promo 3", "Test Promo"));
        PromoList.add(new PromosModel("Dummy Promo 4", "Test Promo"));
        PromoList.add(new PromosModel("Dummy Promo 5", "Test Promo"));
        PromoList.add(new PromosModel("Dummy Promo 6", "Test Promo"));
        PromoList.add(new PromosModel("Dummy Promo 7", "Test Promo"));
        PromoList.add(new PromosModel("Dummy Promo 8", "Test Promo"));
        NewsList.add(new NewsModel("Lorem ipsum dolor sit amet consectetur 1", "senin, 20 Desember 2022", "Lorem ipsum dolor sit amet consectetur adipisicing elit. Consequatur, aliquam?..."));
        NewsList.add(new NewsModel("Lorem ipsum dolor sit amet consectetur 2", "senin, 20 Desember 2022", "Lorem ipsum dolor sit amet consectetur adipisicing elit. Consequatur, aliquam?..."));
        NewsList.add(new NewsModel("Lorem ipsum dolor sit amet consectetur 3", "senin, 20 Desember 2022", "Lorem ipsum dolor sit amet consectetur adipisicing elit. Consequatur, aliquam?..."));
        NewsList.add(new NewsModel("Lorem ipsum dolor sit amet consectetur 4", "senin, 20 Desember 2022", "Lorem ipsum dolor sit amet consectetur adipisicing elit. Consequatur, aliquam?..."));
        NewsList.add(new NewsModel("Lorem ipsum dolor sit amet consectetur 5", "senin, 20 Desember 2022", "Lorem ipsum dolor sit amet consectetur adipisicing elit. Consequatur, aliquam?..."));
    }
    private void initPromoRecyclerView(){
        LinearLayoutManager linearLayoutManagerPromo = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL,false);
        LinearLayoutManager linearLayoutManagerNews = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL,false);
        RecyclerView recyclerViewPromo = (RecyclerView) findViewById(R.id.container_promos);
        RecyclerView recyclerViewNews = (RecyclerView) findViewById(R.id.container_news);
        recyclerViewPromo.setLayoutManager(linearLayoutManagerPromo);
        recyclerViewNews.setLayoutManager(linearLayoutManagerNews);
        PromoAdapter promoAdapter = new PromoAdapter(this, PromoList);
        NewsAdapter newsAdapter = new NewsAdapter(this, NewsList);
        recyclerViewPromo.setAdapter(promoAdapter);
        recyclerViewNews.setAdapter(newsAdapter);
    }
}