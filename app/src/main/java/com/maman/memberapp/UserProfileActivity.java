package com.maman.memberapp;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.Manifest;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.maman.memberapp.model.TransactionModel;

import java.util.ArrayList;

public class UserProfileActivity extends AppCompatActivity {

    private static final int REQUEST_CODE_ACTIVITY_EDIT = 1;
    private ArrayList<TransactionModel> transactionLists = new ArrayList<TransactionModel>();
    private TextView userName, userAddress, userPhoneNumber;
    private ImageView imageQr;
    private Dialog qrCodeDialog;
    private RelativeLayout containerPrimaryInformation;
    private int STORAGE_PERMISSIONS_CODE = 1;

    @Override
    public void onBackPressed() {
        Intent intent = new Intent(UserProfileActivity.this, MainActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(intent);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        if(requestCode == REQUEST_CODE_ACTIVITY_EDIT || resultCode == RESULT_OK){
            String name = data.getStringExtra("name");
            userName.setText(name);
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_profile);
        imageQr = (ImageView) findViewById(R.id.user_qrcode);
        containerPrimaryInformation = (RelativeLayout) findViewById(R.id.container_primary_information);
        containerPrimaryInformation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(UserProfileActivity.this, EditUserActivity.class);
                startActivityForResult(intent, REQUEST_CODE_ACTIVITY_EDIT);
            }
        });
         imageQr.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    if(ContextCompat.checkSelfPermission(UserProfileActivity.this, Manifest.permission.WRITE_EXTERNAL_STORAGE) == PackageManager.PERMISSION_GRANTED){
                        downloadImageQrCode();
                    } else {
                        requestStoragePermissions();
                    }
                }
            });
        initialDetailUser();
    }
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        if(requestCode == STORAGE_PERMISSIONS_CODE){
            if(grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED){
                downloadImageQrCode();
            }else{
                Toast.makeText(this, "Access Danied To Download Your Barcode", Toast.LENGTH_SHORT).show();
            }
        }
    }

    private void requestStoragePermissions(){
        if(ActivityCompat.shouldShowRequestPermissionRationale(this, Manifest.permission.WRITE_EXTERNAL_STORAGE)){
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE}, STORAGE_PERMISSIONS_CODE);
        }else{
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE}, STORAGE_PERMISSIONS_CODE);
        }
    }

    private void downloadImageQrCode(){
        initializeDialogQrCode();
        qrCodeDialog.show();
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
         userName = findViewById(R.id.detail_username);
         userPhoneNumber = findViewById(R.id.detail_phone_number);
         userAddress = findViewById(R.id.detail_address);
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

    private void initializeDialogQrCode(){
        qrCodeDialog = new Dialog(this);
        qrCodeDialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        qrCodeDialog.setContentView(R.layout.qr_code_dialog);
        qrCodeDialog.setCancelable(true);
        SharedPreferences sharedPreferences = getSharedPreferences("UserActive", Context.MODE_PRIVATE);
        String Qrcode = sharedPreferences.getString("qr_code", "").toString();
        String Name = sharedPreferences.getString("name", "").toString();
        String imageUrl = "http://10.0.2.2:8000"+Qrcode;
//        String imageUrl = "https://cdn.pixabay.com/photo/2023/03/26/03/12/ladybug-7877480_960_720.jpg";
        ImageView containerImageDialog = qrCodeDialog.findViewById(R.id.qr_code_image);
        TextView containerTextView = qrCodeDialog.findViewById(R.id.text_detail_qrcode);
        containerTextView.setText("Barcode "+Name);
        Glide.with(this).load(imageUrl).into(containerImageDialog);
    }
}