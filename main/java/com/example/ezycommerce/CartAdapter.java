
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

public class CartAdapter extends RecyclerView.Adapter<CartAdapter.MyViewHolder> {

    private Context context;
    private List<CartModel> cartModels;

    public CartAdapter(Context context, List<CartModel> cartModels) {
        this.context = context;
        this.cartModels = cartModels;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view;
        LayoutInflater layoutInflater = LayoutInflater.from(context);
        view = layoutInflater.inflate(R.layout.cart_recycle,parent,false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
//        holder.name.setText();

    }

    @Override
    public int getItemCount() {
        return cartModels.size();
    }


    public static class MyViewHolder extends RecyclerView.ViewHolder{

        TextView name;
        TextView price;
        ImageView img;
        ConstraintLayout constraintLayout;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            name = itemView.findViewById(R.id.NamaProdukCart);
            price = itemView.findViewById(R.id.HargaProdukCart);
            img = itemView.findViewById(R.id.GambarProdukCart);
            constraintLayout = itemView.findViewById(R.id.Recycle_Select_cart);

        }
    }
}
