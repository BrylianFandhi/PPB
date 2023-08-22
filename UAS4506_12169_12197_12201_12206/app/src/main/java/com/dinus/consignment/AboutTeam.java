package com.dinus.consignment;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import java.util.ArrayList;

public class AboutTeam extends AppCompatActivity {
    Button btnback;

    RecyclerView recyclerView;
    RecyclerView.Adapter recyclerViewAdapter;
    RecyclerView.LayoutManager recyclerViewLayoutManager;
    ArrayList<model_team> data;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about_team);
        recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setHasFixedSize(true);

        recyclerViewLayoutManager = new GridLayoutManager(this, 1);
        recyclerView.setLayoutManager(recyclerViewLayoutManager);

        data = new ArrayList<>();
        for (int i = 0; i < data_team.nama.length; i++) {
            data.add(new model_team(
                    data_team.nama[i],
                    data_team.nim[i],
                    data_team.imageList[i]
            ));
        }

        recyclerViewAdapter = new adapter_team(data);
        recyclerView.setAdapter(recyclerViewAdapter);

        btnback = findViewById(R.id.back);

        btnback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(AboutTeam.this, about_us.class);
                startActivity(i);
            }
        });

    }
}