package com.example.gaominyu.slease.Main;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.v7.widget.RecyclerView;
import android.util.Base64;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.gaominyu.slease.Model.ItemPreview;
import com.example.gaominyu.slease.R;

import java.util.List;

public class ItemAdapter extends RecyclerView.Adapter<ItemAdapter.MyViewHolder> {
    private Context context;
    private List<ItemPreview> itemPreviewList;

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView name, price, deposit;
        public ImageView thumbnail;

        public MyViewHolder(View view) {
            super(view);
            name = view.findViewById(R.id.title);
            price = view.findViewById(R.id.price);
            deposit = view.findViewById(R.id.deposit);
            thumbnail = view.findViewById(R.id.thumbnail);
        }
    }


    public ItemAdapter(Context context, List<ItemPreview> itemPreviewList) {
        this.context = context;
        this.itemPreviewList = itemPreviewList;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.browse_item_row, parent, false);

        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, final int position) {
        final ItemPreview itemPreview = itemPreviewList.get(position);
        holder.name.setText(itemPreview.title);
        holder.deposit.setText("deposit: $" + itemPreview.deposit);
        holder.price.setText("rate：$" + itemPreview.rate + " per " + itemPreview.frequencyID);

        byte[] decodedString = Base64.decode(itemPreview.imageBase64, Base64.DEFAULT);
        Bitmap bitmap = BitmapFactory.decodeByteArray(decodedString, 0, decodedString.length);
        Glide.with(context)
                .load(bitmap)
                .into(holder.thumbnail);
    }

    @Override
    public int getItemCount() {
        return itemPreviewList.size();
    }
}