package com.example.ezycommerce;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class CartDatabaseHelper extends SQLiteOpenHelper {
    public CartDatabaseHelper(Context context) {
        super(context, "database.db", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table CartDetail(name TEXT primary key, price TEXT, quantity TEXT)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("drop table if exists CartDetail" );
    }

    public Boolean insertdata(String name, String price, int quantity){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("name",name);
        contentValues.put("price",price);
        contentValues.put("quantity",quantity);
        long result =  db.insert("CartDetail",null,contentValues);
        if(result == -1){
            return false;
        }else {
            return true;
        }
    }

    public Cursor getdata(){
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery("Select * From CartDetail",null);
        return cursor;
    }

    public Boolean updatedata(String name, String price, int quantity){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("price",price);
        contentValues.put("quantity",quantity);
        Cursor cursor = db.rawQuery("Select * from CartDetail where name = ?", new String[] {name});
        if (cursor.getCount() > 0) {
            long result = db.update("CartDetail", contentValues, "name=?", new String[]{name});
            if (result == -1) {
                return false;
            } else {
                return true;
            }
        }else{
            return false;
        }
    }
}

