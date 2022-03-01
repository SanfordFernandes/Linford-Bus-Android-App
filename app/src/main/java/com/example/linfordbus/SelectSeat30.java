package com.example.linfordbus;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.Checkable;
import android.widget.TextView;
import android.widget.Toast;

public class SelectSeat30 extends AppCompatActivity {

    int amt = 0;
    TextView tv1;

    //Set checkboxes
    CheckBox a1, a2, a3, a4, a5, a6, a7, a8, a9, a10, b1, b2, b3, b4, b5, b6, b7, b8, b9, b10,
            c1, c2, c3, c4, c5, c6, c7, c8, c9, c10;

    @SuppressLint("UseCompatLoadingForDrawables")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.select_seat30);

        tv1 = findViewById(R.id.price_tag);
        Button btn_pay = findViewById(R.id.doPayment);

        btn_pay.setOnClickListener(v -> {
            if (amt!=0) {
                Intent i = new Intent(SelectSeat30.this, payment.class);
                i.putExtra("amt", amt);
                startActivity(i);
            } else
                Toast.makeText(getApplicationContext(), "Please seat a seat/s!", Toast.LENGTH_SHORT).show();
        });

        a1 = findViewById(R.id.Btn_a1);
        a2 = findViewById(R.id.Btn_a2);
        a3 = findViewById(R.id.Btn_a3);
        a4 = findViewById(R.id.Btn_a4);
        a5 = findViewById(R.id.Btn_a5);
        a6 = findViewById(R.id.Btn_a6);
        a7 = findViewById(R.id.Btn_a7);
        a8 = findViewById(R.id.Btn_a8);
        a9 = findViewById(R.id.Btn_a9);
        a10 = findViewById(R.id.Btn_a10);

        b1 = findViewById(R.id.Btn_b1);
        b2 = findViewById(R.id.Btn_b2);
        b3 = findViewById(R.id.Btn_b3);
        b4 = findViewById(R.id.Btn_b4);
        b5 = findViewById(R.id.Btn_b5);
        b6 = findViewById(R.id.Btn_b6);
        b7 = findViewById(R.id.Btn_b7);
        b8 = findViewById(R.id.Btn_b8);
        b9 = findViewById(R.id.Btn_b9);
        b10 = findViewById(R.id.Btn_b10);

        c1 = findViewById(R.id.Btn_c1);
        c2 = findViewById(R.id.Btn_c2);
        c3 = findViewById(R.id.Btn_c3);
        c4 = findViewById(R.id.Btn_c4);
        c5 = findViewById(R.id.Btn_c5);
        c6 = findViewById(R.id.Btn_c6);
        c7 = findViewById(R.id.Btn_c7);
        c8 = findViewById(R.id.Btn_c8);
        c9 = findViewById(R.id.Btn_c9);
        c10 = findViewById(R.id.Btn_c10);
    }

    public void checkCheckBox(View v){
        amt = 0;

        if (a1.isChecked()) selectedOrNot(1);
        if (a2.isChecked()) selectedOrNot(1);
        if (a3.isChecked()) selectedOrNot(1);
        if (a4.isChecked()) selectedOrNot(1);
        if (a5.isChecked()) selectedOrNot(1);
        if (a6.isChecked()) selectedOrNot(1);
        if (a7.isChecked()) selectedOrNot(1);
        if (a8.isChecked()) selectedOrNot(1);
        if (a9.isChecked()) selectedOrNot(1);
        if (a10.isChecked()) selectedOrNot(1);

        if (b1.isChecked()) selectedOrNot(1);
        if (b2.isChecked()) selectedOrNot(1);
        if (b3.isChecked()) selectedOrNot(1);
        if (b4.isChecked()) selectedOrNot(1);
        if (b5.isChecked()) selectedOrNot(1);
        if (b6.isChecked()) selectedOrNot(1);
        if (b7.isChecked()) selectedOrNot(1);
        if (b8.isChecked()) selectedOrNot(1);
        if (b9.isChecked()) selectedOrNot(1);
        if (b10.isChecked()) selectedOrNot(1);

        if (c1.isChecked()) selectedOrNot(1);
        if (c2.isChecked()) selectedOrNot(1);
        if (c3.isChecked()) selectedOrNot(1);
        if (c4.isChecked()) selectedOrNot(1);
        if (c5.isChecked()) selectedOrNot(1);
        if (c6.isChecked()) selectedOrNot(1);
        if (c7.isChecked()) selectedOrNot(1);
        if (c8.isChecked()) selectedOrNot(1);
        if (c9.isChecked()) selectedOrNot(1);
        if (c10.isChecked()) selectedOrNot(1);

        tv1.setText("Price: " + amt);
    }

    public void selectedOrNot(int i) {
        if(i == 1)
            amt += 1000;
        else
            amt -= 1000;
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        startActivity(new Intent(SelectSeat30.this, ViewBuses.class));
        finish();
    }
}