package com.example.linfordbus;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class userLogin extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.custsignin);

        EditText username = findViewById(R.id.custsignin_uname);
        EditText password = findViewById(R.id.custsignin_pswd);
        Button signin = findViewById(R.id.cust_signin);
        DBHelper DB = new DBHelper(this);

        signin.setOnClickListener(v -> {
            String user = username.getText().toString();
            String pass = password.getText().toString();

            if (user.equals("") || pass.equals(""))
                Toast.makeText(userLogin.this, "Please enter all fields", Toast.LENGTH_SHORT).show();
            else{
                boolean checkuserpass = DB.checkusernamepassword(user, pass);
                if (checkuserpass){
                    Toast.makeText(userLogin.this, "Log-in Successful", Toast.LENGTH_SHORT).show();
                    Intent intent= new Intent(getApplicationContext(), FindBus.class);
                    startActivity(intent);
                }
                else{
                    Toast.makeText(userLogin.this, "Invalid Credentials", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}