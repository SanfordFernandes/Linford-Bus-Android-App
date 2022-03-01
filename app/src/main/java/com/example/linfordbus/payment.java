package com.example.linfordbus;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.InputType;
import android.widget.Button;
import android.widget.Toast;

import com.braintreepayments.cardform.view.CardForm;

public class payment extends AppCompatActivity {
    CardForm cardForm;
    Button buy,cancel;
    AlertDialog.Builder alertBuilder;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.payment_page);

        cancel = findViewById(R.id.go_back);
        cardForm = findViewById(R.id.card_form);
        buy = findViewById(R.id.btn_purchase);

        cardForm.cardRequired(true)
                .expirationRequired(true)
                .cvvRequired(true)
                .postalCodeRequired(true)
                .mobileNumberRequired(true)
                .mobileNumberExplanation("SMS is required on this number")
                .setup(payment.this);
        cardForm.getCvvEditText().setInputType(InputType.TYPE_CLASS_NUMBER | InputType.TYPE_NUMBER_VARIATION_PASSWORD);

        buy.setOnClickListener(view -> {
            if (cardForm.isValid()) {
                alertBuilder = new AlertDialog.Builder(payment.this);
                alertBuilder.setTitle("Confirm before purchase");

                alertBuilder.setMessage("Card number: " + cardForm.getCardNumber() + "\n" +
                        "Card expiry date: " + cardForm.getExpirationDateEditText().getText().toString() + "\n" +
                        "Card CVV: " + cardForm.getCvv() + "\n" +
                        "Postal code: " + cardForm.getPostalCode() + "\n" +
                        "Phone number: " + cardForm.getMobileNumber());

                alertBuilder.setPositiveButton("Confirm", (dialogInterface, i) -> {
                    dialogInterface.dismiss();

                    //invoke thank you activity
                    Intent i2 = new Intent(payment.this, Thank_you.class);

                    startActivity(i2);
                    Toast.makeText(payment.this, "Thank you for purchase", Toast.LENGTH_LONG).show();
                });

                alertBuilder.setNegativeButton("Cancel", (dialogInterface, i) -> dialogInterface.dismiss());
                AlertDialog alertDialog = alertBuilder.create();
                alertDialog.show();
            } else {
                Toast.makeText(payment.this, "Please complete the form", Toast.LENGTH_LONG).show();
            }
        });

        cancel.setOnClickListener(v -> {
            Intent intent = new Intent(getApplicationContext(), ViewBuses.class);
            startActivity(intent);
        });
    }
}