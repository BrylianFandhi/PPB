package com.dinus.consignment;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatRatingBar;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class coment_apps extends AppCompatActivity {
    TextView hasil;
    Button button, btnback;
    EditText saran;
    AppCompatRatingBar ratingBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_coment_apps);

        hasil = findViewById(R.id.jdl_comment);
        btnback = findViewById(R.id.back);

        ratingBar = findViewById(R.id.ratingBar);
        ratingBar.setOnRatingBarChangeListener((ratingBar, rating, fromUser) ->
                //TextView akan menampilkan nilai dari hasil rating bar.
                hasil.setText(String.valueOf(rating)));

        button = findViewById(R.id.btn_saran);
        button.setOnClickListener(v ->
                //Menampilkan pesan berupa nilai yang diinput user
                Toast.makeText(coment_apps.this, "Rating Apps " + ratingBar.getRating(), Toast.LENGTH_SHORT).show());


        btnback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(coment_apps.this, main_market.class);
                startActivity(i);
            }
        });
    }


}