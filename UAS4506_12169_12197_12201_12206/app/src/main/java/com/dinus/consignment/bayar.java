package com.dinus.consignment;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Random;

public class bayar extends AppCompatActivity {
    private Button btnback, btnselesai;
    TextView methode, code, total;
    RecyclerView recyclerView;
    DatabaseReference database;
    private ArrayList<Cart> listed;
    private Adapter_toHistory adapterItem;
    final int min = 100000000, max = 1000000000;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bayar);

        btnback = findViewById(R.id.back);
        btnselesai = findViewById(R.id.selesai);
        methode = findViewById(R.id.methode);
        code = findViewById(R.id.code);
        total = findViewById(R.id.total);

//        listed = new ArrayList<>();
//        adapterItem = new Adapter_toHistory(this, listed);

        methode.setText(getIntent().getStringExtra("METODE"));
        total.setText(getIntent().getStringExtra("TOTAL"));

//        Generate number
        int num_random = new Random().nextInt((max - min) + 1) + min;
        code.setText(String.valueOf(num_random));

        recyclerView = findViewById(R.id.bantuan);
        database = FirebaseDatabase.getInstance().getReference("Cart");
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        listed = new ArrayList<>();
        adapterItem = new Adapter_toHistory(this, listed);
        recyclerView.setAdapter(adapterItem);



        btnback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(bayar.this, checkout_cart.class);
                startActivity(i);
            }
        });


        btnselesai.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(bayar.this, sukses_checkout.class);
                startActivity(i);
                database.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        for(DataSnapshot dataSnapshot : snapshot.getChildren()){
                            Cart cart = dataSnapshot.getValue(Cart.class);
                            listed.add(cart);
                        }
                        adapterItem.notifyDataSetChanged();
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                    }
                });
                database.removeValue();

            }
        });

    }
}