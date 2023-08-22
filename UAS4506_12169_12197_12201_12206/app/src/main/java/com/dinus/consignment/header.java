package com.dinus.consignment;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

public class header extends AppCompatActivity {
    TextView user;
    DBhelper DB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_header);
        user=findViewById(R.id.Username);
        DB=new DBhelper(this);
        Intent i=getIntent();
        user.setText(i.getStringExtra("textUser"));
    }
}