package com.example.ezycommerce;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.List;
import java.util.zip.Inflater;

public class RecycleAD extends RecyclerView.Adapter<RecycleAD.MyViewHolder> {

    private Context mcontext;
    private List<Detail> productlist;

    public RecycleAD(Context mcontext, List<Detail> productlist) {
        this.mcontext = mcontext;
        this.productlist = productlist;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view;
        LayoutInflater layoutInflater = LayoutInflater.from(mcontext);
        view = layoutInflater.inflate(R.layout.product_recycle,parent,false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {

        holder.name.setText(productlist.get(position).getName());
        holder.price.setText(String.valueOf(productlist.get(position).getPrice()));
        Glide.with(mcontext)
                .load(productlist.get(position).getImg())
                .into(holder.img);
        holder.constraintLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(mcontext,DetailBook.class);
                intent.putExtra("nama",productlist.get(position).getName());
                intent.putExtra("harga",productlist.get(position).getPrice());
                intent.putExtra("deskripsi",productlist.get(position).getDescription());
                intent.putExtra("gambar",productlist.get(position).getImg());
                mcontext.startActivities(new Intent[]{intent});
            }
        });
    }

    @Override
    public int getItemCount() {
        return productlist.size();
    }


    public static class MyViewHolder extends RecyclerView.ViewHolder{

        TextView name;
        TextView price;
        ImageView img;
        ConstraintLayout constraintLayout;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            name = itemView.findViewById(R.id.NamaProduk);
            price = itemView.findViewById(R.id.HargaProduk);
            img = itemView.findViewById(R.id.GambarProduk);
            constraintLayout = itemView.findViewById(R.id.RecycleSelect);

        }
    }
}
