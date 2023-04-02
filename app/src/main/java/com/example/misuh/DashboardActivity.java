package com.example.misuh;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;
import android.view.Window;
import android.widget.Button;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class DashboardActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);
        getSupportActionBar().hide();
        // Manggil fragment di dashboard
        FragmentManager fm = getSupportFragmentManager();

        // Pake cara add
        // fm.beginTransaction().add(R.id.container, new ProductFragment()).commit();

        // Pake cara replace
        FragmentTransaction ft = fm.beginTransaction();
        ft.replace(R.id.container, new ProductFragment());
        ft.commit();


    }
}