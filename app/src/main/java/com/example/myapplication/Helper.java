package com.example.myapplication;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class Helper extends SQLiteOpenHelper {

    public Helper(@Nullable Context context) {
        super(context, "product", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE biens(_id INTEGER PRIMARY KEY, nom TEXT,prix REAL )");

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS biens");
        onCreate(db);
    }


    public void insertBiens(Produit p){
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues cv =new ContentValues();
        cv.put("nom",p.getNom());
        cv.put("prix",p.getPrix());

        db.insert("biens",null,cv);
        db.close();
    }

    public void updateBiens(Produit p){
       SQLiteDatabase db = this.getWritableDatabase();

       ContentValues cv= new ContentValues();
       cv.put("nom",p.getNom());
       cv.put("prix",p.getPrix());
       db.update("biens",cv,"_id=?",new String[]{String.valueOf(p.getId())});
       db.close();
    }

    public void deleteBiens(int id){
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete("biens","_id=?",new String[]{String.valueOf(id)});
        db.close();
    }

    public Cursor getAllBiens(){
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor c = db.rawQuery("SELECT * FROM biens",null);
        return c;
    }
    public Produit getOneProduit(int id){
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor c= db.query("biens",new String[]{"_id","nom","prix"},"_id=?",
                new String[]{String.valueOf(id)},null,null,null);
        c.moveToFirst();
        Produit p = new Produit(c.getInt(0),c.getString(1),c.getDouble(2));
        return p;
    }



}
