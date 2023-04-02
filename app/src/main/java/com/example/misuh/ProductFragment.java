package com.example.misuh;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

public class ProductFragment extends Fragment {
    private Button btnNasiGoreng, btnMieGoreng, btnNasiKuning, btnBuburAyam, btnSateAyam, btnBihunGoreng, btnSapoTahu;
    private FloatingActionButton btnPayment;
    // Variables
    ArrayList<String> arrPesanan = new ArrayList<String>();
    ArrayList<Integer> arrJumlahPesanan = new ArrayList<Integer>();
    ArrayList<Integer> arrHarga = new ArrayList<Integer>();

    private Integer getTotalPrice(){
        Integer total = 0;
        for(int i = 0 ; i < arrHarga.size() ; i++){
            total += arrHarga.get(i).intValue();
        }
        return total;
    }
    private void addOrder(String name, int price){
        if(!arrPesanan.contains(name)){
            arrPesanan.add(name);
            arrJumlahPesanan.add(1);
            arrHarga.add(price);
        }
        else{
            // Search for specified food name
            int index;
            for(int i = 0 ; i < arrPesanan.size() ; i++){
                if(arrPesanan.get(i).equals(name)){
                    index = i;
                    // Update quantity
                    arrJumlahPesanan.set(index, Integer.valueOf(arrJumlahPesanan.get(index)) + 1);
                    // Update price
                    arrHarga.set(index, Integer.valueOf(arrHarga.get(index)) + price);
                    break;
                }
            }
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_product, container, false);

        // Init
        btnNasiGoreng = view.findViewById(R.id.btnNasiGoreng);
        btnMieGoreng = view.findViewById(R.id.btnMieGoreng);
        btnNasiKuning = view.findViewById(R.id.btnNasiKuning);
        btnBuburAyam = view.findViewById(R.id.btnBuburAyam);
        btnSateAyam = view.findViewById(R.id.btnSateAyam);
        btnBihunGoreng = view.findViewById(R.id.btnBihunGoreng);
        btnSapoTahu = view.findViewById(R.id.btnSapoTahu);
        btnPayment = view.findViewById(R.id.floatingActionButton);


        btnPayment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(arrPesanan.size() != 0){
                    Intent intent = new Intent(getActivity(), BillActivity.class);
                    intent.putExtra("order", arrPesanan);
                    intent.putExtra("quantity", arrJumlahPesanan);
                    intent.putExtra("price", arrHarga);
                    startActivity(intent);

                    // Remove All Order
                    arrHarga.clear();
                    arrPesanan.clear();
                    arrJumlahPesanan.clear();
                }
                else{
                    Toast.makeText(getContext(), "", Toast.LENGTH_SHORT).show();
                }
            }
        });
        btnNasiGoreng.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Kasih ingpo
                Toast.makeText(getActivity(), "Pesanan Nasi Goreng ditambahkan!", Toast.LENGTH_SHORT).show();
                // Masukin data
                addOrder("Nasi Goreng", 37000);
            }
        });
        btnMieGoreng.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Kasih ingpo
                Toast.makeText(getActivity(), "Pesanan Mie Goreng ditambahkan!", Toast.LENGTH_SHORT).show();
                // Masukin data
                addOrder("Mie Goreng", 33000);
            }
        });
        btnNasiKuning.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Kasih ingpo
                Toast.makeText(getActivity(), "Pesanan Nasi Kuning ditambahkan!", Toast.LENGTH_SHORT).show();
                // Masukin data
                addOrder("Nasi Kuning", 15000);
            }
        });
        btnBuburAyam.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Kasih ingpo
                Toast.makeText(getActivity(), "Pesanan Bubur Ayam ditambahkan!", Toast.LENGTH_SHORT).show();
                // Masukin data
                addOrder("Bubur Ayam", 15000);
            }
        });
        btnSateAyam.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Kasih ingpo
                Toast.makeText(getActivity(), "Pesanan Sate Ayam ditambahkan!", Toast.LENGTH_SHORT).show();
                // Masukin data
                addOrder("Sate Ayam", 25000);
            }
        });
        btnBihunGoreng.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Kasih ingpo
                Toast.makeText(getActivity(), "Pesanan Bihun Goreng ditambahkan!", Toast.LENGTH_SHORT).show();
                // Masukin data
                addOrder("Bihun Goreng", 21000);
            }
        });
        btnSapoTahu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Kasih ingpo
                Toast.makeText(getActivity(), "Pesanan Sapo Tahu ditambahkan!", Toast.LENGTH_SHORT).show();
                // Masukin data
                addOrder("Sapo Tahu", 24000);
            }
        });

        //return inflater.inflate(R.layout.fragment_product, container, false);
        return view;
    }
}