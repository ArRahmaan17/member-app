package com.maman.memberapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.maman.memberapp.model.NewsModel;
import com.maman.memberapp.model.PromosModel;
import java.util.ArrayList;


public class MainActivity extends AppCompatActivity {
    private static final String TAG = "MainActivity";
    private ArrayList<PromosModel> PromoList = new ArrayList<PromosModel>();
    private ArrayList<NewsModel> NewsList = new ArrayList<NewsModel>();
    private TextView userId, pointUser;
    private String Username;
    private CardView userCard;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        userCard = (CardView) findViewById(R.id.container_about_user);
        userCard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, UserDetailActivity.class);
                startActivity(intent);
            }
        });
        initializationApp();
    }
    private void initializationApp() {
        SharedPreferences sharedPreferences = getSharedPreferences("UserActive", Context.MODE_PRIVATE);
        Username = sharedPreferences.getString("name", "").toString();
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
        initPromoRecyclerView();
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