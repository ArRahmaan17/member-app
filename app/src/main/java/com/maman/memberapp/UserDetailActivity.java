package com.maman.memberapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.maman.memberapp.model.TransactionModel;

import java.util.ArrayList;

public class UserDetailActivity extends AppCompatActivity {
    private ArrayList<TransactionModel> transactionLists = new ArrayList<TransactionModel>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_detail);
        initialDetailUser();
    }

    public void initialDetailUser() {
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

    public void initialRvTransaction() {
        LinearLayoutManager transactionLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        RecyclerView transactionRv = (RecyclerView) findViewById(R.id.container_details_transaction);
        transactionRv.setLayoutManager(transactionLayoutManager);
        TransactionAdapter transactionAdapter = new TransactionAdapter(this, transactionLists);
        transactionRv.setAdapter(transactionAdapter);
    }
}