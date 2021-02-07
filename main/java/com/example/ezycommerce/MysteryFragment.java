package com.example.ezycommerce;

import android.content.Context;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


public class MysteryFragment extends Fragment {

    private TextView textView;
    RecyclerView recyclerView;
    Context mContext;

    List<Detail> bookslist;


    public MysteryFragment
            () {
        // Required empty public constructor

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        mContext = container.getContext();


        View view = inflater.inflate(R.layout.fragment_accessories, container, false);

        textView = view.findViewById(R.id.ErrorMyst);

        recyclerView = view.findViewById(R.id.RecycleViewAcc);
        bookslist = new ArrayList<>();


        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://u73olh7vwg.execute-api.ap-northeast-2.amazonaws.com/staging/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();


        JsonPlaceHolderApi jsonPlaceHolderApi = retrofit.create(JsonPlaceHolderApi.class);

        Call<Post> call = jsonPlaceHolderApi.getPosts();

        call.enqueue(new Callback<Post>() {
            @Override
            public void onResponse(Call<Post> call, Response<Post> response) {
                if (!response.isSuccessful()) {
                    textView.setText("Code: " + response.code());
                    return;
                }




                Post posts = response.body();


                List<Detail> product = posts.getProductsDetail();

                for (Detail products : product){

                    if (products.getCategory().equals("mystery")){
                        bookslist.add(products);
                    }
                }

                PutDataIntoRecycler(bookslist);

            }

            @Override
            public void onFailure(Call<Post> call, Throwable t) {
                textView.setText(t.getMessage());
            }
        });

        return view;
    }

    private void PutDataIntoRecycler(List<Detail> productlist) {
        RecycleAD recycleAD = new RecycleAD(mContext,productlist);
        recyclerView.setLayoutManager(new LinearLayoutManager(mContext));
        recyclerView.setAdapter(recycleAD);

    }
}