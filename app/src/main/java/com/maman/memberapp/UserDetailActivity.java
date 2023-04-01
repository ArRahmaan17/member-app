package com.maman.memberapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.TextView;

import com.maman.memberapp.model.TransactionModel;

import java.util.ArrayList;

public class UserDetailActivity extends AppCompatActivity {
    private ArrayList<TransactionModel> transactionLists = new ArrayList<TransactionModel>();
    private TextView userName, userAddress, userPhoneNumber;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_detail);
        initialDetailUser();
    }

    public void initialDetailUser() {
        getDetailUserActive();
        transactionLists.add(new TransactionModel("Pembelian kunci Motor", "20 Desember 2010", "Rp 200.000.000"));
        transactionLists.add(new TransactionModel("Pembelian Sepeda Roda 1", "20 Januari 2019", "Rp 200.000.000"));
        transactionLists.add(new TransactionModel("Pembelian Sepeda Roda 1", "20 Januari 2019", "Rp 200.000.000"));
        transactionLists.add(new TransactionModel("Pembelian Sepeda Roda 1", "20 Januari 2019", "Rp 200.000.000"));
        transactionLists.add(new TransactionModel("Pembelian Sepeda Roda 1", "20 Januari 2019", "Rp 200.000.000"));
        transactionLists.add(new TransactionModel("Pembelian Sepeda Roda 1", "20 Januari 2019", "Rp 200.000.000"));
        transactionLists.add(new TransactionModel("Pembelian Sepeda Roda 1", "20 Januari 2019", "Rp 200.000.000"));
        transactionLists.add(new TransactionModel("Pembelian Sepeda Roda 1", "20 Januari 2019", "Rp 200.000.000"));
        transactionLists.add(new TransactionModel("Pembelian Sepeda Roda 1", "20 Januari 2019", "Rp 200.000.000"));
        transactionLists.add(new TransactionModel("Pembelian Sepeda Roda 1", "20 Januari 2019", "Rp 200.000.000"));
        transactionLists.add(new TransactionModel("Pembelian Sepeda Roda 1", "20 Januari 2019", "Rp 200.000.000"));
        transactionLists.add(new TransactionModel("Pembelian Sepeda Roda 1", "20 Januari 2019", "Rp 200.000.000"));
        initialRvTransaction();
    }
    public void getDetailUserActive(){
        SharedPreferences sharedPreferences = getSharedPreferences("UserActive", Context.MODE_PRIVATE);
        String Username = sharedPreferences.getString("name", "").toString();
        String PhoneNumber = sharedPreferences.getString("phone_number", "").toString();
        String Address = sharedPreferences.getString("address", "").toString();
        TextView userName = findViewById(R.id.detail_username);
        TextView userPhoneNumber = findViewById(R.id.detail_phone_number);
        TextView userAddress = findViewById(R.id.detail_address);
        userName.setText(Username);
        userPhoneNumber.setText(PhoneNumber);
        userAddress.setText(Address);
    }

    public void initialRvTransaction() {
        LinearLayoutManager transactionLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        RecyclerView transactionRv = (RecyclerView) findViewById(R.id.container_details_transaction);
        transactionRv.setLayoutManager(transactionLayoutManager);
        TransactionAdapter transactionAdapter = new TransactionAdapter(this, transactionLists);
        transactionRv.setAdapter(transactionAdapter);
    }
}