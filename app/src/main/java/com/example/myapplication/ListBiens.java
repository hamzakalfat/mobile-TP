package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;
import android.widget.TextView;

public class ListBiens extends AppCompatActivity {
    ListView ls;
    Helper h=new Helper(ListBiens.this);
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_biens);
        ls= findViewById(R.id.lst);

        Cursor c= h.getAllBiens();
        SimpleCursorAdapter adapter= new SimpleCursorAdapter(ListBiens.this,R.layout.item,c,
                new String[]{c.getColumnName(0),c.getColumnName(1),c.getColumnName(2)},
                new int[]{R.id.id,R.id.nom,R.id.prix}, 1);
        ls.setAdapter(adapter);

        ls.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                TextView t= view.findViewById(R.id.id);
                Intent x = new Intent(ListBiens.this, DetailProduit.class );
                x.putExtra("id",t.getText().toString());
                startActivity(x);
            }
        });



    }
}