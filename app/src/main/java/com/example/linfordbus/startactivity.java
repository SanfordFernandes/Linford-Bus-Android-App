package com.example.linfordbus;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

public class startactivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.welcome_users);

        Button userlogin = findViewById(R.id.welcome_user_login);
        Button usersignin = findViewById(R.id.welcome_user_signup);
        TextView adminlink = findViewById(R.id.AdminSignuplink);

        userlogin.setOnClickListener(v -> {
            Intent intent = new Intent(getApplicationContext(), userLogin.class);
            startActivity(intent);
        });

        usersignin.setOnClickListener(v -> {
            Intent intent = new Intent(getApplicationContext(), userSignin.class);
            startActivity(intent);
        });

        adminlink.setOnClickListener(v -> {
            Intent intent = new Intent(getApplicationContext(), adminLogin.class);
            startActivity(intent);
        });
   }
}