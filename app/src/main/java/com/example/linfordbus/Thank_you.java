package com.example.linfordbus;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class Thank_you extends AppCompatActivity {

    TextView tv1;
    int amt, T_seats;
    Button go_home, go_viewbus;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.thank_you);

        tv1 = findViewById(R.id.thx_msg);
        go_home = findViewById(R.id.go_back);
        go_viewbus = findViewById(R.id.go_viewbus);

        Intent i1 = getIntent();
        amt = i1.getIntExtra("amt",0);
        T_seats = amt/1000;

        tv1.setText(T_seats + " seat/s have been booked for\n Rs- " + amt + " successfully!");

        go_home.setOnClickListener(v -> {
            Intent i2 = new Intent(Thank_you.this, startactivity.class);
            startActivity(i2);
        });

        go_viewbus.setOnClickListener(v -> {
            Intent i3 = new Intent(Thank_you.this, ViewBuses.class);
            startActivity(i3);
        });
    }
}