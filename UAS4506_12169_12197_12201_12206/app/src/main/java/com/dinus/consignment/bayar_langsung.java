package com.dinus.consignment;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.text.NumberFormat;
import java.util.Locale;
import java.util.Random;

public class bayar_langsung extends AppCompatActivity {
    Button btnback, btnselesai;
    TextView methode, code, total;
    History history;
    DatabaseReference reff;
    final int min = 100000000, max = 1000000000;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bayar_langsung);

        btnback = findViewById(R.id.back);
        btnselesai = findViewById(R.id.selesai);
        methode = findViewById(R.id.methode);
        code = findViewById(R.id.code);
        total = findViewById(R.id.total);

        //        Generate number
        int num_random = new Random().nextInt((max - min) + 1) + min;
        code.setText(String.valueOf(num_random));

        int logonah = getIntent().getIntExtra("logoni", 0);
        String namaApp = getIntent().getStringExtra("titleni");
        String hagarApp = getIntent().getStringExtra("hargani");
        int totalApp = getIntent().getIntExtra("TOTAL", 0);
        methode.setText(getIntent().getStringExtra("METODE"));
        total.setText(hagarApp);

        history = new History();
        reff = FirebaseDatabase.getInstance().getReference().child("History");
        btnselesai.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                history.setLogo1(logonah);
                history.setTitle1(namaApp);
                history.setHarga1(totalApp);
                history.setTotal1(1);
                reff.push().setValue(history);

                Intent i = new Intent(bayar_langsung.this, sukses_checkout.class);
                startActivity(i);
            }
        });

        btnback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(bayar_langsung.this, checkout_item.class);
                startActivity(i);
            }
        });

    }

    private String formatRupiah(Double number){
        Locale localeID = new Locale("in", "ID");
        NumberFormat formatRupiah = NumberFormat.getCurrencyInstance(localeID);
        return formatRupiah.format(number);
    }
}