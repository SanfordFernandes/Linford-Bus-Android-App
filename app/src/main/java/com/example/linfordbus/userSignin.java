package com.example.linfordbus;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.content.Intent;
import android.util.Patterns;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.regex.Pattern;

public class userSignin extends AppCompatActivity {

    DBHelper DB;

    // defining our own password pattern
    private static final Pattern PASSWORD_PATTERN =
            Pattern.compile("^" +
                    "(?=.*[@#$%^&+=])" +     // at least 1 special character
                    "(?=\\S+$)" +            // no white spaces
                    ".{6,}" +                // at least 6 characters
                    "$");

    private static final Pattern us_PATTERN =
            Pattern.compile("^" +
                    "(?=\\S+$)" +            // no white spaces
                    ".{6,}" +                // at least 6 characters
                    "$");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.cust_signup);

        Button btnsignup = findViewById(R.id.cust_signup);
        EditText fname = findViewById(R.id.custsignup_fname);
        EditText email2 = findViewById(R.id.custsignup_email);
        EditText username1 = findViewById(R.id.custsignup_username);
        EditText pw = findViewById(R.id.custsignup_pw);
        DB = new DBHelper(this);

        btnsignup.setOnClickListener(v -> {
            String fullname = fname.getText().toString();
            String email = email2.getText().toString();
            String username = username1.getText().toString();
            String password = pw.getText().toString();

            if (fullname.equals("") ||email.equals("")||username.equals("")||password.equals("")){
                Toast.makeText(userSignin.this, "Please enter all fields", Toast.LENGTH_SHORT).show();
            } else if (!us_PATTERN.matcher(fullname).matches()){
                fname.setError(getResources().getString(R.string.UserFull_error));
            } else if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()){
                email2.setError(getResources().getString(R.string.email_error));
            } else if (!us_PATTERN.matcher(username).matches()) {
                username1.setError(getResources().getString(R.string.UserFull_error));
            } else if (!PASSWORD_PATTERN.matcher(password).matches()) {
                pw.setError(getResources().getString(R.string.pw_error));
            } else {

                boolean checkuser = DB.checkusername(username);
                if (!checkuser){
                    Boolean insert = DB.insertData(fullname,email,username,password);
                    if (insert){
                        Toast.makeText(userSignin.this, "Registered Successfully", Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(getApplicationContext(), userLogin.class);
                        startActivity(intent);
                    }
                    else {
                        Toast.makeText(userSignin.this, "Registration Failed", Toast.LENGTH_SHORT).show();
                    }
                }
                else {
                    Toast.makeText(userSignin.this, "User Already exists please signin", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(getApplicationContext(), userLogin.class);
                    startActivity(intent);
                }
            }
        });
    }
}