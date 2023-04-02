package com.example.misuh;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.google.android.material.button.MaterialButton;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Formatter;

public class BillActivity extends AppCompatActivity {
    private MaterialButton btnConfirm, btnCancel;

    private int getTotalPrice(ArrayList<Integer> arrPriceList){
        Integer tempTotal = 0;
        // Count total
        for(int i = 0 ; i < arrPriceList.size() ; i++){
            tempTotal += Integer.valueOf(arrPriceList.get(i));
        }
        return tempTotal;
    }
    private void displayBill(ArrayList<String> arrOrderList, ArrayList<Integer> arrPriceList, ArrayList<Integer> arrQuantityList){
        Formatter fmt = new Formatter();
        fmt.format("-------------------------------------------------\n");
        fmt.format("%10s %15s %20s\n", "Quantity", "Name", "Price");
        fmt.format("-------------------------------------------------\n");
        for(int i = 0 ; i < arrOrderList.size() ; i++){
            arrQuantityList.get(i);
            fmt.format("%10s %15s ", Integer.toString(arrQuantityList.get(i)), arrOrderList.get(i).toString());
            Integer tempVal = Integer.valueOf(arrPriceList.get(i));
            String tempFormatted = String.format("Rp %,d,-", tempVal);
            fmt.format("%20s\n", tempFormatted);
        }

        fmt.format("-------------------------------------------------\n");

        String tempFormatted = String.format("Rp %,d,-", getTotalPrice(arrPriceList));
        fmt.format("%26s %20s\n", "Total Harga", tempFormatted);

        // Put to textview
        TextView billView = findViewById(R.id.billView);
        billView.setText(fmt.toString());
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bill);
        getSupportActionBar().hide();
        // Init component
        btnConfirm = findViewById(R.id.btnConfirm);
        btnCancel = findViewById(R.id.btnCancel);

        // Get Passed Intent Data
        Bundle extras = getIntent().getExtras();
        if(extras != null){
            ArrayList<String> arrOrderList = extras.getStringArrayList("order");
            ArrayList<Integer> arrPriceList = extras.getIntegerArrayList("price");
            ArrayList<Integer> arrQuantityList = extras.getIntegerArrayList("quantity");
            // Display
            displayBill(arrOrderList, arrPriceList, arrQuantityList);
            btnConfirm.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    // Call Payment Activity
                    Intent intent = new Intent(BillActivity.this, PaymentActivity.class);
                    // Pass total price data
                    intent.putExtra("totalPrice", getTotalPrice(arrPriceList));
                    startActivity(intent);
                    // Back to Menu After Confirmation Page
                    finish();
                }
            });
        }

        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

    }
}