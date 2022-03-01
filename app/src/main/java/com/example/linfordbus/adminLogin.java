package com.example.linfordbus;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class adminLogin extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.admin_signin);

        EditText username = findViewById(R.id.admin_signin_uname);
        EditText password = findViewById(R.id.admin_signin_pswd);
        Button signin = findViewById(R.id.admin_signin);
        TextView signup = findViewById(R.id.AdminSignuplink);

        DBHelper DB = new DBHelper(this);

        signin.setOnClickListener(v -> {
            String user = username.getText().toString();
            String pass = password.getText().toString();

            if (user.equals("") || pass.equals(""))
                Toast.makeText(adminLogin.this, "Please enter all fields", Toast.LENGTH_SHORT).show();
            else{
                boolean checkadminuserpass = DB.checkadminusernamepassword(user, pass);
                if (checkadminuserpass){
                    Toast.makeText(adminLogin.this, "LogIn Successful", Toast.LENGTH_SHORT).show();
                    Intent intent= new Intent(getApplicationContext(), adminpanel.class);
                    startActivity(intent);
                }
                else{
                    Toast.makeText(adminLogin.this, "Invalid Credentials", Toast.LENGTH_SHORT).show();
                }
            }
        });

        signup.setOnClickListener(v -> {
            Intent intent = new Intent(getApplicationContext(), adminsignin.class);
            startActivity(intent);
        });
    }
}