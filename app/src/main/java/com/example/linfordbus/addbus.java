package com.example.linfordbus;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.List;

public class addbus extends AppCompatActivity {

    Spinner spin,spin2,spin3;
    String st="", st2="", st3="";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_buses);

        // Initialise
        spin = findViewById(R.id.adddepcity);
        spin2 = findViewById(R.id.addarrivalcity);
        spin3 = findViewById(R.id.addseats);

        EditText busid = findViewById(R.id.addid);
        TextView dt = findViewById(R.id.adddate);
        Button addbus = findViewById(R.id.addnewbusBtn);
        DBHelper DB = new DBHelper(this);

        //spinner
        ArrayAdapter<String> myAdapter = new ArrayAdapter<>(addbus.this,
                android.R.layout.simple_list_item_1, getResources().getStringArray(R.array.arrival));
        myAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        ArrayAdapter<String> myAdapter2 = new ArrayAdapter<>(addbus.this,
                android.R.layout.simple_list_item_1, getResources().getStringArray(R.array.departure));
        myAdapter2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        ArrayAdapter<String> myAdapter3 = new ArrayAdapter<>(addbus.this,
                android.R.layout.simple_list_item_1, getResources().getStringArray(R.array.seats));
        myAdapter2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        spin.setAdapter(myAdapter);
        spin2.setAdapter(myAdapter2);
        spin3.setAdapter(myAdapter3);

        spin.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                st = parent.getItemAtPosition(position).toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        spin2.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                st2 = parent.getItemAtPosition(position).toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });

        spin3.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                st3 = parent.getItemAtPosition(position).toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });

        //Calendar
        Calendar calendar = Calendar.getInstance();
        final int year = calendar.get(Calendar.YEAR);
        final int month = calendar.get(Calendar.MONTH);
        final int day = calendar.get(Calendar.DAY_OF_MONTH);

        dt.setOnClickListener(v -> {
            DatePickerDialog datePickerDialog = new DatePickerDialog(
                    addbus.this, (view, year1, month1, day1) -> {
                        month1 = month1 +1;
                        String date = day1 + "/" + month1 + "/" + year1;
                        dt.setText(date);

                        if (year1 < Calendar.getInstance().get(Calendar.YEAR)){
                            dt.setError("Invalid date");
                            dt.setText("");
                        }
                    },year,month,day
            );
            datePickerDialog.show();
        });

        addbus.setOnClickListener(v -> {

            String id = busid.getText().toString();
            String arrival = st;
            String departure = st2;
            String date = dt.getText().toString();
            String total_seats = st3;

            if (id.equals("") || departure.equals("") || departure.equals("Select Departure City") || arrival.equals("") || arrival.equals("Select Arrival City") ||date.equals("") || total_seats.equals("")) {
                Toast.makeText(addbus.this, "Please enter all fields", Toast.LENGTH_SHORT).show();
            } else {
                if (st.equals(st2)){
                    Toast.makeText(addbus.this, "Arrival and Departure cannot be the same!", Toast.LENGTH_LONG).show();
                } else {
                    boolean checkid = DB.checkid(id);
                    if (!checkid) {
                        Boolean insert = DB.insertBus(id, departure, arrival, date, total_seats);
                        if (insert) {
                            Toast.makeText(addbus.this, "Bus has been added", Toast.LENGTH_SHORT).show();
                            Intent intent = new Intent(getApplicationContext(), adminpanel.class);
                            startActivity(intent);
                        } else {
                            Toast.makeText(addbus.this, "ERROR", Toast.LENGTH_SHORT).show();
                        }
                    } else {
                        Toast.makeText(addbus.this, "ID already exists", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });
    }
}