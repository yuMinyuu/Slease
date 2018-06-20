package com.example.gaominyu.slease;

import android.content.Context;
import android.graphics.Bitmap;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class GridElementAdapter extends RecyclerView.Adapter<GridElementAdapter.SimpleViewHolder>{
    private Context context;
    private List<String> elements;

    public GridElementAdapter(Context context){
        this.context = context;
        this.elements = new ArrayList<String>();
        // Fill dummy list
        for(int i = 0; i < 40 ; i++){
            this.elements.add(i, "Position : " + i);
        }
    }

    public static class SimpleViewHolder extends RecyclerView.ViewHolder {
        public final ImageView img1, img2, img3, img4, img5;
        public final Button button;

        public SimpleViewHolder(View view) {
            super(view);
            button = view.findViewById(R.id.button);
            img1 = (ImageView) view.findViewById(R.id.imageView1);
            img2 = (ImageView) view.findViewById(R.id.imageView2);
            img3 = (ImageView) view.findViewById(R.id.imageView3);
            img4 = (ImageView) view.findViewById(R.id.imageView4);
            img5 = (ImageView) view.findViewById(R.id.imageView5);
        }
    }

    @Override
    public SimpleViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        final View view = LayoutInflater.from(this.context).inflate(R.layout.partial_photo, parent, false);
        return new SimpleViewHolder(view);
    }

    @Override
    public void onBindViewHolder(SimpleViewHolder holder, final int position) {
        holder.button.setText(elements.get(position));
        holder.button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(context, "Position =" + position, Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public int getItemCount() {
        return this.elements.size();
    }
}