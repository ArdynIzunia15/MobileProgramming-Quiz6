package com.example.misuh;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.material.button.MaterialButton;

public class PaymentActivity extends AppCompatActivity {
    private EditText txtTotalPrice, inputNominal, outputChanges;
    private MaterialButton btnConfirm, btnCheck;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_payment);
        getSupportActionBar().hide();
        // Init Components
        txtTotalPrice = findViewById(R.id.txtTotalPrice);
        inputNominal = findViewById(R.id.inputNominal);
        btnConfirm = findViewById(R.id.btnConfirm);
        btnConfirm.setEnabled(false);
        outputChanges = findViewById(R.id.outputChanges);
        btnCheck = findViewById(R.id.btnCheck);

        Bundle extras = getIntent().getExtras();
        if(extras != null){
            // Retrieve Intent Data
            Integer totalPrice = extras.getInt("totalPrice", 0);
            txtTotalPrice.setText(String.valueOf(totalPrice));

            btnCheck.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Integer tempNominal = Integer.valueOf(String.valueOf(inputNominal.getText()));
                    if(tempNominal >= totalPrice){
                        outputChanges.setText(String.format("%d", tempNominal - totalPrice));
                        btnConfirm.setEnabled(true);
                    }
                    else{
                        Toast.makeText(PaymentActivity.this, "Jumlah uang kurang >:(", Toast.LENGTH_SHORT).show();
                    }
                }
            });

            inputNominal.setOnFocusChangeListener(new View.OnFocusChangeListener() {
                @Override
                public void onFocusChange(View v, boolean hasFocus) {
                    if(!hasFocus){
                        Integer tempNominal = Integer.valueOf(String.valueOf(inputNominal.getText()));
                        if(tempNominal >= totalPrice){
                            outputChanges.setText(String.format("%d", tempNominal - totalPrice));
                            btnConfirm.setEnabled(true);
                        }
                        else{
                            Toast.makeText(PaymentActivity.this, "Jumlah uang kurang >:(", Toast.LENGTH_SHORT).show();
                        }
                    }
                }
            });

            btnConfirm.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(PaymentActivity.this, ThankYouActivity.class);
                    startActivity(intent);
                    finish();
                }
            });
        }
    }
}