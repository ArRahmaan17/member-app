package com.maman.memberapp;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.maman.memberapp.model.Promos_Model;

import java.util.ArrayList;

public class PromoAdapter extends RecyclerView.Adapter<PromoAdapter.PromoHolder> {
    private Context context;
    private ArrayList<Promos_Model> PromoList = new ArrayList<Promos_Model>();

    public PromoAdapter(Context context, ArrayList<Promos_Model> promoList) {
        this.context = context;
        PromoList = promoList;
    }

    @NonNull
    @Override
    public PromoHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_promo, parent, false);
        return new PromoHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull PromoHolder holder, int position) {
        Promos_Model promos_model = PromoList.get(position);
        holder.title.setText(promos_model.getTitle());
        holder.itemView.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Toast.makeText(context, "Loading "+promos_model.getTitle(), Toast.LENGTH_SHORT).show();
                    }
                }
        );
        holder.detail.setText(promos_model.getDetail());
    }

    @Override
    public int getItemCount() {
        return PromoList.size();
    }

    public class PromoHolder extends RecyclerView.ViewHolder{
        TextView detail,title;
        public PromoHolder(@NonNull View itemView) {
            super(itemView);
            detail = itemView.findViewById(R.id.content_promo);
            title = itemView.findViewById(R.id.title_promo);
        }
    }
}