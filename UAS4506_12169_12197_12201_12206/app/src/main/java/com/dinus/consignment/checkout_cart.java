package com.dinus.consignment;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Locale;

public class checkout_cart extends AppCompatActivity implements AdapterView.OnItemSelectedListener {
    RecyclerView recyclerView;
    DatabaseReference database;
    Adapter_checkout adapterItem;
    ArrayList<Cart> list;
    TextView ttlHarga;
    Button btnback, btnlanjut;
    Integer total = 0;
    Spinner spinner2;
    String method;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_checkout_cart);

        Spinner spinner = findViewById(R.id.edt_kurir);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.kurir, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);
        spinner.setOnItemSelectedListener(this);

        spinner2 = findViewById(R.id.metode);
        ArrayAdapter<CharSequence> adapter2 = ArrayAdapter.createFromResource(this, R.array.pembayaran, android.R.layout.simple_spinner_item);
        adapter2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner2.setAdapter(adapter2);
        spinner2.setOnItemSelectedListener(this);

        ttlHarga = findViewById(R.id.total);
        btnback = findViewById(R.id.back);
        btnlanjut = findViewById(R.id.btn_CO);

        recyclerView = findViewById(R.id.isi_cart);
        database = FirebaseDatabase.getInstance().getReference("Cart");
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        list = new ArrayList<>();
        adapterItem = new Adapter_checkout(this, list);
        recyclerView.setAdapter(adapterItem);

        database.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for(DataSnapshot dataSnapshot : snapshot.getChildren()){
                    Cart cart = dataSnapshot.getValue(Cart.class);
                    Integer cost = Integer.valueOf(cart.getHarga());
                    total = total + cost;
                    list.add(cart);
                }

                ttlHarga.setText(formatRupiah(Double.parseDouble(String.valueOf(total))));

                adapterItem.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        btnback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(checkout_cart.this, cart_shop.class);
                startActivity(i);
            }
        });

        btnlanjut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(checkout_cart.this, bayar.class);
                i.putExtra("TOTAL", formatRupiah(Double.parseDouble(String.valueOf(total))));
                i.putExtra("METODE", method);
                startActivity(i);
            }
        });

    }
    private String formatRupiah(Double number){
        Locale localeID = new Locale("in", "ID");
        NumberFormat formatRupiah = NumberFormat.getCurrencyInstance(localeID);
        return formatRupiah.format(number);
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