package com.example.linfordbus;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

public class ViewBuses extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.viewbuses);

        ListView listView = findViewById(R.id.listView);
        DBHelper myDB = new DBHelper(this);

        ArrayList<String> theList = new ArrayList<>();
        Cursor data = myDB.viewbuses();

        if(data.getCount() == 0){
            Toast.makeText(this, "There are no contents in this list!",Toast.LENGTH_LONG).show();
        }else{
            while(data.moveToNext()) {
                theList.add("\nID:        "+data.getString(0)+"\n"
                        +"FROM: "+data.getString(1) +"\n"
                        +"To:        "+data.getString(2)+"\n"
                        +"Date:    "+data.getString(3)+"\n"
                        +"Seats:  "+data.getString(4)+"\n");

                ListAdapter listAdapter = new ArrayAdapter<>(this,android.R.layout.simple_list_item_1,theList);
                listView.setAdapter(listAdapter);

                listView.setOnItemClickListener((parent, view, position, id) -> {
                    if (position == id){
                        Intent intent = new Intent(view.getContext(), SortBus.class);
                        intent.putExtra("Pos", position);
                        startActivity(intent);
                    }
                });
            }
        }
    }
}