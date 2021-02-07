package com.example.ezycommerce;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {
    private TextView user;
    private Button bisnis,misteri,aksesoris,masak;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        user = findViewById(R.id.UserTag);
        bisnis = findViewById(R.id.BusinessBtn);
        misteri = findViewById(R.id.MysteryBtn);
        aksesoris = findViewById(R.id.AccessoriesBtn);
        masak = findViewById(R.id.CookBtn);
        //FragmentManager

        bisnis.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentManager fragmentManager = getSupportFragmentManager();
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                fragmentTransaction.replace(R.id.Container, new BusinessFragment());
                fragmentTransaction.commit();
            }
        });

        aksesoris.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentManager fragmentManager = getSupportFragmentManager();
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                fragmentTransaction.replace(R.id.Container, new AccessoriesFragment());
                fragmentTransaction.commit();
            }
        });

        masak.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentManager fragmentManager = getSupportFragmentManager();
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                fragmentTransaction.replace(R.id.Container, new CookingFragment());
                fragmentTransaction.commit();
            }
        });

        misteri.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentManager fragmentManager = getSupportFragmentManager();
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                fragmentTransaction.replace(R.id.Container, new MysteryFragment());
                fragmentTransaction.commit();
            }
        });


        //Retroit
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://u73olh7vwg.execute-api.ap-northeast-2.amazonaws.com/staging/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        JsonPlaceHolderApi jsonPlaceHolderApi = retrofit.create(JsonPlaceHolderApi.class);
        //Call User
        Call<Post> call = jsonPlaceHolderApi.getPosts();

        call.enqueue(new Callback<Post>() {
            @Override
            public void onResponse(Call<Post> call, Response<Post> response) {
                if(!response.isSuccessful()){
                    user.setText("Code : " + response.code());
                    return;
                }

                //Username
                Post posts = response.body();
                String content = "";
                user.setText(posts.getnama()+"\n"+ posts.getnim());
            }

            @Override
            public void onFailure(Call<Post> call, Throwable t) {
                    user.setText(t.getMessage());
            }
        });

    }
}