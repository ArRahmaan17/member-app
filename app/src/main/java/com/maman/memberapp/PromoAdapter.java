package com.maman.memberapp;

import android.app.Dialog;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.maman.memberapp.model.PromosModel;

import org.w3c.dom.Text;

import java.util.ArrayList;

public class PromoAdapter extends RecyclerView.Adapter<PromoAdapter.PromoHolder> {
    private Context context;
    private ArrayList<PromosModel> PromoList = new ArrayList<PromosModel>();
    public PromoAdapter(Context context, ArrayList<PromosModel> promoList) {
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
        PromosModel promos_model = PromoList.get(position);
        holder.title.setText(promos_model.getTitle());

        holder.itemView.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Dialog promoDialog = new Dialog(v.getContext());
                        promoDialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
                        promoDialog.setContentView(R.layout.detail_promo);
                        promoDialog.setCancelable(true);
                        TextView detailTitlePromo = promoDialog.findViewById(R.id.title_detail_promo);
                        detailTitlePromo.setText(promos_model.getTitle());
                        promoDialog.show();
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
