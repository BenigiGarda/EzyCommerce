package com.example.ezycommerce;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Post {
    private String nama;
    private String nim;
    private List<Detail> products;


    public String getnama() {
        return nama;
    }
    public String getnim(){
        return nim;
    }

    public List<Detail> getProductsDetail() {
        return products;
    }
}
