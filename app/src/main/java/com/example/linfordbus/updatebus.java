package com.example.linfordbus;

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

import java.util.Calendar;

public class updatebus extends AppCompatActivity {

    Spinner u1, u2, u3;
    String str1="", str2="", str3="";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.update_buses);

        u1 = findViewById(R.id.update_dept_city);
        u2 = findViewById(R.id.update_arrival_city);
        u3 = findViewById(R.id.update_seatnumber);

        EditText busid = findViewById(R.id.update_id);
        TextView dt = findViewById(R.id.update_date);
        Button updatebus = findViewById(R.id.updatenewbus);
        DBHelper DB = new DBHelper(this);

        //spinner
        ArrayAdapter<String> myAdapter = new ArrayAdapter<>(updatebus.this,
                android.R.layout.simple_list_item_1, getResources().getStringArray(R.array.arrival));
        myAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        ArrayAdapter<String> myAdapter2 = new ArrayAdapter<>(updatebus.this,
                android.R.layout.simple_list_item_1, getResources().getStringArray(R.array.departure));
        myAdapter2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        ArrayAdapter<String> myAdapter3 = new ArrayAdapter<>(updatebus.this,
                android.R.layout.simple_list_item_1, getResources().getStringArray(R.array.seats));
        myAdapter2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        u1.setAdapter(myAdapter);
        u2.setAdapter(myAdapter2);
        u3.setAdapter(myAdapter3);

        u1.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                str1 = parent.getItemAtPosition(position).toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        u2.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                str2 = parent.getItemAtPosition(position).toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });

        u3.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                str3 = parent.getItemAtPosition(position).toString();
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
                    updatebus.this, (view, year1, month1, day1) -> {
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

        updatebus.setOnClickListener(v -> {
            String id = busid.getText().toString();
            String arrival = str1;
            String departure = str2;
            String date = dt.getText().toString();
            String total_seats = str3;

            if (id.equals("") || departure.equals("") || departure.equals("Select Departure City") || arrival.equals("") || arrival.equals("Select Arrival City") || date.equals("") || total_seats.equals("")) {
                Toast.makeText(updatebus.this, "Please enter all fields", Toast.LENGTH_SHORT).show();
            } else {
                if (str1.equals(str2)){
                    Toast.makeText(updatebus.this, "Arrival and Departure cannot be the same!", Toast.LENGTH_LONG).show();
                } else {
                    boolean checkid = DB.checkid(id);
                    if (checkid) {
                        Boolean update = DB.updateBus(id, departure, arrival, date, total_seats);
                        if (update) {
                            Toast.makeText(updatebus.this, "Bus updated successfully", Toast.LENGTH_SHORT).show();
                            Intent intent = new Intent(getApplicationContext(), adminpanel.class);
                            startActivity(intent);
                        } else {
                            Toast.makeText(updatebus.this, "New entry not updated", Toast.LENGTH_SHORT).show();
                        }
                    } else {
                        Toast.makeText(updatebus.this, "ID doesnot exists", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });
    }
}