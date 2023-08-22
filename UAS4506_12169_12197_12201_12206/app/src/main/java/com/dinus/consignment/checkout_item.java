package com.dinus.consignment;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

public class checkout_item extends AppCompatActivity implements AdapterView.OnItemSelectedListener {
    private ImageView logo_a;
    private TextView title_a, harga_a, type_a, total_a;
    Button btndone, btnback;
    String method;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_checkout_item);

        Spinner spinner = findViewById(R.id.edt_kurir);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.kurir, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);
        spinner.setOnItemSelectedListener(this);

        Spinner spinner2 = findViewById(R.id.metode);
        ArrayAdapter<CharSequence> adapter2 = ArrayAdapter.createFromResource(this, R.array.pembayaran, android.R.layout.simple_spinner_item);
        adapter2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner2.setAdapter(adapter2);
        spinner2.setOnItemSelectedListener(this);


        logo_a = findViewById(R.id.imageList);
        title_a = findViewById(R.id.text_title);
        harga_a = findViewById(R.id.harga);
        total_a = findViewById(R.id.total);
        type_a = findViewById(R.id.jenis);
        btndone = findViewById(R.id.selesai);
        btnback = findViewById(R.id.back);

        int logonah = getIntent().getIntExtra("logoni", 0);
        String namaApp = getIntent().getStringExtra("titleni");
        String hagarApp = getIntent().getStringExtra("hargani");
        String typeApp = getIntent().getStringExtra("typeni");
        int totalApp = getIntent().getIntExtra("TOTAL", 0);

        logo_a.setImageResource(logonah);
        title_a.setText(namaApp);
        harga_a.setText(hagarApp);
        type_a.setText(typeApp);


        btnback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(checkout_item.this, main_market.class);
                startActivity(i);
            }
        });

        btndone.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(checkout_item.this, bayar_langsung.class);
                i.putExtra("logoni",logonah);
                i.putExtra("titleni", namaApp);
                i.putExtra("hargani", hagarApp);
                i.putExtra("TOTAL", totalApp);
                i.putExtra("METODE", method);
                startActivity(i);
            }
        });


    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        String text = parent.getItemAtPosition(position).toString();
        Toast.makeText(parent.getContext(), text,Toast.LENGTH_SHORT).show();
        method = text;
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }
}