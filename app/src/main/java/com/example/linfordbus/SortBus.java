package com.example.linfordbus;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;

public class SortBus extends AppCompatActivity {
    int count = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.sort_bus);

        Intent intent = getIntent();
        int pos = intent.getIntExtra("Pos",0);
        DBHelper db = new DBHelper(this);
        Cursor cursor = db.viewbuses();

        while(cursor.moveToNext()){
            int total_seats = Integer.parseInt(cursor.getString(4));

            if(total_seats == 30 && count == pos){
                Intent intent2 = new Intent(SortBus.this, SelectSeat30.class);
                startActivity(intent2);
            }
            if(total_seats == 35 && count == pos){
                Intent intent2 = new Intent(SortBus.this, SelectSeat35.class);
                startActivity(intent2);
            }
            if(total_seats == 40 && count == pos){
                Intent intent2 = new Intent(SortBus.this, SelectSeat40.class);
                startActivity(intent2);
            }
            count++;
        }
        count = 0;
    }
}