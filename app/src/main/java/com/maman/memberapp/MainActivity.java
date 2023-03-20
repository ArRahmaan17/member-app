package com.maman.memberapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.app.ActionBar;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.maman.memberapp.model.News_model;
import com.maman.memberapp.model.Promos_Model;
import java.util.ArrayList;
import java.util.HashMap;


public class MainActivity extends AppCompatActivity {
    private static final String TAG = "MainActivity";
    private ArrayList<Promos_Model> PromoList = new ArrayList<Promos_Model>();
    private ArrayList<News_model> NewsList = new ArrayList<News_model>();
    private TextView userId, pointUser;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initializationApp();
    }
    private void initializationApp() {
        userId = (TextView) findViewById(R.id.title_user);
        pointUser = (TextView) findViewById(R.id.title_point);
        userId.setText("Test User");
        pointUser.setText("5.000.000");
        PromoList.add(new Promos_Model("Dummy Promo 1", "Test Promo"));
        PromoList.add(new Promos_Model("Dummy Promo 2", "Test Promo"));
        PromoList.add(new Promos_Model("Dummy Promo 3", "Test Promo"));
        PromoList.add(new Promos_Model("Dummy Promo 4", "Test Promo"));
        PromoList.add(new Promos_Model("Dummy Promo 5", "Test Promo"));
        PromoList.add(new Promos_Model("Dummy Promo 6", "Test Promo"));
        PromoList.add(new Promos_Model("Dummy Promo 7", "Test Promo"));
        PromoList.add(new Promos_Model("Dummy Promo 8", "Test Promo"));
        NewsList.add(new News_model("Lorem ipsum dolor sit amet consectetur 1", "senin, 20 Desember 2022", "Lorem ipsum dolor sit amet consectetur adipisicing elit. Consequatur, aliquam?..."));
        NewsList.add(new News_model("Lorem ipsum dolor sit amet consectetur 2", "senin, 20 Desember 2022", "Lorem ipsum dolor sit amet consectetur adipisicing elit. Consequatur, aliquam?..."));
        NewsList.add(new News_model("Lorem ipsum dolor sit amet consectetur 3", "senin, 20 Desember 2022", "Lorem ipsum dolor sit amet consectetur adipisicing elit. Consequatur, aliquam?..."));
        NewsList.add(new News_model("Lorem ipsum dolor sit amet consectetur 4", "senin, 20 Desember 2022", "Lorem ipsum dolor sit amet consectetur adipisicing elit. Consequatur, aliquam?..."));
        NewsList.add(new News_model("Lorem ipsum dolor sit amet consectetur 5", "senin, 20 Desember 2022", "Lorem ipsum dolor sit amet consectetur adipisicing elit. Consequatur, aliquam?..."));
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