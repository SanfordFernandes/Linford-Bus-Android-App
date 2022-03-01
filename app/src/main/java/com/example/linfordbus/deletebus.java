package com.example.linfordbus;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class deletebus extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.delete_bus);

        EditText busid = findViewById(R.id.delete_id);
        Button deletebus = findViewById(R.id.deletenewbus);
        DBHelper DB = new DBHelper(this);

        deletebus.setOnClickListener(v -> {
            String id = busid.getText().toString();
            if (id.equals("")){
                Toast.makeText(deletebus.this, "Please Enter Bus ID", Toast.LENGTH_SHORT).show();
            } else {
                boolean checkid= DB.checkid(id);
                if (checkid){
                    Boolean delete = DB.deleteBus(id);
                    if (delete){
                        Toast.makeText(deletebus.this, "Bus deleted successfully", Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(getApplicationContext(), adminpanel.class);
                        startActivity(intent);
                    } else {
                        Toast.makeText(deletebus.this, "Entry not deleted", Toast.LENGTH_SHORT).show();
                    }
                } else {
                    Toast.makeText(deletebus.this, "ID does not exists", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}