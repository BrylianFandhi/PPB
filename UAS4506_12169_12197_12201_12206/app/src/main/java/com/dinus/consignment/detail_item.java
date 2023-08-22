package com.dinus.consignment;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.text.NumberFormat;
import java.util.Locale;

public class detail_item extends AppCompatActivity {

    Button btnlanjut;
    ImageButton btnkeranjang,plusquantity,minusqty;
    TextView quantitynumber;
    private DatabaseReference reff;
    Cart cart;
    int quantity = 1;
    int hargaqty;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_item);
        Button btnBack;
        plusquantity=findViewById(R.id.additem);
        minusqty=findViewById(R.id.minusitem);
        quantitynumber=findViewById(R.id.count);

        ImageView imageView = findViewById(R.id.imagedt);
        TextView title = findViewById(R.id.text_title);
        TextView harga = findViewById(R.id.harga);
        TextView type = findViewById(R.id.type_item);
        TextView detail = findViewById(R.id.deskripsi);


        Intent intent = getIntent();

        int logoApp = intent.getIntExtra("GAMBAR_DEFAULT", 0);
        String namaApp = intent.getStringExtra("TEKS_DEFAULT");
        int hagarApp = intent.getIntExtra("HARGA_DEFAULT",0);
        String typeApp = intent.getStringExtra("TYPE_DEFAULT");
        String detailApp = intent.getStringExtra("DETAIL_DEFAULT");
        hargaqty=hagarApp;

        plusquantity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=getIntent();
                int hagarApp=intent.getIntExtra("HARGA_DEFAULT",0);
                quantity+=1;
                displayQuantity();
                int hargas=hagarApp*quantity;
                hargaqty=hargas;
                String setnewPrice=String.valueOf(hargas);
                harga.setText(formatRupiah(Double.parseDouble(setnewPrice)));

            }
        });
        minusqty.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (quantity==0){
                    Toast.makeText(detail_item.this,"Can't decrase Quantity",Toast.LENGTH_LONG).show();
                }else {
                    quantity--;
                    displayQuantity();
                    Intent intent=getIntent();
                    int hagarApp=intent.getIntExtra("HARGA_DEFAULT",0);
                    int hargas=hagarApp*quantity;
                    String setnewPrice=String.valueOf(hargas);
                    harga.setText(formatRupiah(Double.parseDouble(setnewPrice)));
                }
            }
        });

        imageView.setImageResource(logoApp);
        title.setText(namaApp);
        harga.setText(formatRupiah(Double.parseDouble(String.valueOf(hagarApp))));
        type.setText(typeApp);
        detail.setText(detailApp);
        btnlanjut = findViewById(R.id.btn_beli);
        btnkeranjang = findViewById(R.id.to_cart);


        btnlanjut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String title_c = title.getText().toString();
                String harga_c = harga.getText().toString();
                String type_c = type.getText().toString();
                Intent i = new Intent(detail_item.this, checkout_item.class);
                i.putExtra("logoni",logoApp);
                i.putExtra("titleni", title_c);
                i.putExtra("TOTAL", hargaqty);
                i.putExtra("hargani", harga_c);
                i.putExtra("typeni", type_c);
                startActivity(i);
            }
        });

        cart = new Cart();
        reff = FirebaseDatabase.getInstance().getReference().child("Cart");
        btnkeranjang.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                cart.setLogo(logoApp);
                cart.setTitle(title.getText().toString().trim());
                cart.setHarga(hargaqty);
                cart.setTotal(quantity);
                reff.push().setValue(cart);
                Toast.makeText(detail_item.this, "Data Tersimpan", Toast.LENGTH_SHORT).show();
            }
        });

        btnBack = findViewById(R.id.back);
        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(detail_item.this, main_market.class);
                startActivity(i);
            }
        });

    }
    private void displayQuantity(){quantitynumber.setText(String.valueOf(quantity));}

    private String formatRupiah(Double number){
        Locale localeID = new Locale("in", "ID");
        NumberFormat formatRupiah = NumberFormat.getCurrencyInstance(localeID);
        return formatRupiah.format(number);
    }
}