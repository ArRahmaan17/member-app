package com.maman.memberapp;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.maman.memberapp.model.TransactionModel;

import java.util.ArrayList;

public class TransactionAdapter extends RecyclerView.Adapter<TransactionAdapter.TransactionHolder> {

    private Context context;
    private ArrayList<TransactionModel> transactionList = new ArrayList<TransactionModel>();

    public TransactionAdapter(Context context, ArrayList<TransactionModel> transactionList) {
        this.context = context;
        this.transactionList = transactionList;
    }

    @NonNull
    @Override
    public TransactionHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_transactions, parent, false);
        return new TransactionHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull TransactionHolder holder, int position) {
        TransactionModel transactions = transactionList.get(position);
        holder.titleTransaction.setText(transactions.getTitleTransaction().toString());
        holder.dateTransaction.setText(transactions.getDateTransaction().toString());
        holder.chargeTransaction.setText(transactions.getAmountTransaction().toString());
    }

    @Override
    public int getItemCount() {
        return transactionList.size();
    }

    public class TransactionHolder extends RecyclerView.ViewHolder {
       TextView titleTransaction, dateTransaction, chargeTransaction;
       public TransactionHolder(@NonNull View itemView) {
           super(itemView);
           titleTransaction = (TextView) itemView.findViewById(R.id.title_transaction);
           dateTransaction = (TextView) itemView.findViewById(R.id.date_transaction);
           chargeTransaction = (TextView) itemView.findViewById(R.id.charge_transaction);
       }
   }
}
