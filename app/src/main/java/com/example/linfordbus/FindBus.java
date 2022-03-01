package com.example.linfordbus;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

public class FindBus extends AppCompatActivity {
    Button btnView;
    DBHelper myDB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.view_buses);

        btnView = findViewById(R.id.viewbuses);
        myDB = new DBHelper(this);

        btnView.setOnClickListener(v -> {
            Intent intent = new Intent(FindBus.this, ViewBuses.class);
            startActivity(intent);
        });
    }
}