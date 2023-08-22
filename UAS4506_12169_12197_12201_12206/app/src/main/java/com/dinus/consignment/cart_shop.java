package com.dinus.consignment;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RelativeLayout;
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

public class cart_shop extends AppCompatActivity {
    RecyclerView recyclerView;
    private DatabaseReference database;
    private Adapter_Cart adapterItem;
    private ArrayList<Cart> list;
    TextView ttlHarga;
    Button CO, btnback;
    RelativeLayout buy;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cart_shop);

        ttlHarga = findViewById(R.id.total);
        CO = findViewById(R.id.btn_CO);
        btnback = findViewById(R.id.back);
        buy = findViewById(R.id.bottom_shop);

        recyclerView = findViewById(R.id.isi_cart);
        database = FirebaseDatabase.getInstance().getReference("Cart");
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        list = new ArrayList<>();
        adapterItem = new Adapter_Cart(this, list);
        recyclerView.setAdapter(adapterItem);

        database.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {

                if (snapshot.exists()) {
                    buy.setVisibility(View.VISIBLE);
                }
                else{
                    buy.setVisibility(View.INVISIBLE);
                }

                Integer total = 0;
                for(DataSnapshot dataSnapshot : snapshot.getChildren()){
                    Cart cart = dataSnapshot.getValue(Cart.class);
                    cart.setKey(dataSnapshot.getKey());
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

        CO.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(cart_shop.this, checkout_cart.class);
                startActivity(i);
            }
        });

        btnback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(cart_shop.this, main_market.class);
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