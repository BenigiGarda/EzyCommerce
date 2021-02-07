package com.example.ezycommerce;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;

public class DetailBook extends AppCompatActivity {
    CartDatabaseHelper cartDatabaseHelper;
    TextView detailname,detailprice,detaildescription;
    ImageView imageView;
    Button buybtn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_book);

        cartDatabaseHelper = new CartDatabaseHelper(this);
        detailname = findViewById(R.id.Detail_Name);
        detaildescription = findViewById(R.id.Detail_Description);
        detailprice = findViewById(R.id.Detail_Price);
        imageView = findViewById(R.id.Detail_Image);
        buybtn = findViewById(R.id.BuyBtn);

        Bundle bundle = getIntent().getExtras();
        String image = bundle.getString("gambar");
        String name = bundle.getString("nama");
        float price = bundle.getFloat("harga");
        String description = bundle.getString("deskripsi");

        detailname.setText(name);
        detailprice.setText("$" + price);
        detaildescription.setText(description);
        Glide.with(this)
                .load(image)
                .into(imageView);

        buybtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String nametxt = detailname.getText().toString();
                String pricetxt = detailprice.getText().toString();
                int quantity = 1;

                Boolean checkdata = cartDatabaseHelper.insertdata(nametxt,pricetxt,quantity);
                if(checkdata == true){
                    Toast.makeText(DetailBook.this,"Data Stored",Toast.LENGTH_LONG).show();
                    Intent intent = new Intent(DetailBook.this,Cart.class);
                    startActivity(intent);
                }else {
                    Boolean updatedata = cartDatabaseHelper.updatedata(nametxt,pricetxt,quantity);
                    if(updatedata == true){
                        Toast.makeText(DetailBook.this,"Data updated",Toast.LENGTH_LONG).show();
                    }
                }
            }
        });
    }
}