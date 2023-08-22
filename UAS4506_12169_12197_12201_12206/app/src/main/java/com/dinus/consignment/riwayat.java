package com.dinus.consignment;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class riwayat extends AppCompatActivity {
    RecyclerView recyclerView;
    DatabaseReference database;
    Adapter_history adapterItem;
    ArrayList<History> list;
    Button btndelete, btnback;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_riwayat);

        recyclerView = findViewById(R.id.isi_cart);
        database = FirebaseDatabase.getInstance().getReference("History");
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        btndelete = findViewById(R.id.delete);
        btnback = findViewById(R.id.back);

        list = new ArrayList<>();
        adapterItem = new Adapter_history(this, list);
        recyclerView.setAdapter(adapterItem);

        database.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for(DataSnapshot dataSnapshot : snapshot.getChildren()){
                    History history = dataSnapshot.getValue(History.class);
                    history.setKey1(dataSnapshot.getKey());
                    list.add(history);
                }
                adapterItem.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        btndelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                database.removeValue();
                Intent i = new Intent(riwayat.this, riwayat.class);
                startActivity(i);
            }
        });

        btnback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(riwayat.this, main_market.class);
                startActivity(i);
            }
        });
    }
}