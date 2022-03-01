package com.example.linfordbus;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Patterns;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.regex.Pattern;

public class adminsignin extends AppCompatActivity {

    EditText email,username,password,code,fullname;
    Button back,btnsignup;
    DBHelper DB;

    // defining our own password pattern
    private static final Pattern PASSWORD_PATTERN =
            Pattern.compile("^" +
                    "(?=.*[@#$%^&+=])" +     // at least 1 special character
                    "(?=\\S+$)" +            // no white spaces
                    ".{6,}" +                // at least 4 characters
                    "$");

    private static final Pattern us_PATTERN =
            Pattern.compile("^" +
                    "(?=\\S+$)" +            // no white spaces
                    ".{6,}" +                // at least 6 characters
                    "$");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.admin_signup);

        btnsignup = findViewById(R.id.admin_signup);
        email = findViewById(R.id.admin_signup_email);
        username = findViewById(R.id.admin_signup_uname);
        fullname = findViewById(R.id.admin_signup_fname);
        code = findViewById(R.id.admin_signup_code);
        password = findViewById(R.id.admin_signup_pswd);
        btnsignup.setOnClickListener(v -> ValidateThis());
    }

    public void ValidateThis(){
        String Fullname = fullname.getText().toString();
        String Email = email.getText().toString();
        String Username = username.getText().toString();
        String Password = password.getText().toString();
        String Code = code.getText().toString();
        DB = new DBHelper(this);

        if (Fullname.equals("") ||Email.equals("")||Username.equals("")||Password.equals("") || Code.equals("")) {
            Toast.makeText(adminsignin.this, "Please enter all fields", Toast.LENGTH_SHORT).show();
        } else if (!us_PATTERN.matcher(Fullname).matches()){
            fullname.setError(getResources().getString(R.string.UserFull_error));
        } else if (!us_PATTERN.matcher(Username).matches()) {
            username.setError(getResources().getString(R.string.UserFull_error));
        } else if (!Patterns.EMAIL_ADDRESS.matcher(email.getText().toString()).matches()){
            email.setError(getResources().getString(R.string.email_error));
        } else if (!PASSWORD_PATTERN.matcher(Password).matches()) {
            password.setError(getResources().getString(R.string.pw_error));
        } else {

            boolean checkadminuser = DB.checkadminusername(Username);
            if (!checkadminuser){
                if (Code.equals("989898")){
                    Boolean insert = DB.insertadmin(Fullname,Email,Username,Password);
                    if (insert){
                        Toast.makeText(adminsignin.this, "Registered Successfully", Toast.LENGTH_LONG).show();
                        Intent intent = new Intent(getApplicationContext(), adminLogin.class);
                        startActivity(intent);
                    }
                    else {
                        Toast.makeText(adminsignin.this, "Registration Failed", Toast.LENGTH_LONG).show();
                    }
                }
                else{
                    Toast.makeText(adminsignin.this, "The Admin Code is Wrong", Toast.LENGTH_LONG).show();
                }
            }
            else {
                Toast.makeText(adminsignin.this, "User Already exists please signin", Toast.LENGTH_LONG).show();
                Intent intent = new Intent(getApplicationContext(), adminLogin.class);
                startActivity(intent);
            }
        }
    }
}