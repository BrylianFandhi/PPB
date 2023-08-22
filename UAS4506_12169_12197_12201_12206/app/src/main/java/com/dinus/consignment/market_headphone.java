package com.dinus.consignment;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import java.util.ArrayList;

public class market_headphone extends AppCompatActivity {
    RecyclerView recyclerView;
    adapter_item recyclerViewAdapter;
    RecyclerView.LayoutManager recyclerViewLayoutManager;
    ArrayList<itemModel> data;
    Button btnBack;
    SearchView searchView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_market_headphone);

        searchView = findViewById(R.id.seach);

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                newText = newText.toLowerCase();
                ArrayList<itemModel> itemFilter = new ArrayList<>();
                for (itemModel model : data){
                    String nama = model.getName().toLowerCase();
                    if(nama.contains(newText)){
                        itemFilter.add(model);
                    }
                }
                recyclerViewAdapter.setFilter(itemFilter);
                return true;
            }
        });

        recyclerView = findViewById(R.id.contentItem);
        recyclerView.setHasFixedSize(true);

        recyclerViewLayoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(recyclerViewLayoutManager);
        recyclerView.addItemDecoration(new DividerItemDecoration(this, DividerItemDecoration.VERTICAL));

        data = new ArrayList<>();
        for (int i = 0; i < m_headphone.Headline.length; i++){
            data.add(new itemModel(m_headphone.Headline[i],
                    m_headphone.Subitem[i],
                    m_headphone.Jenis[0],
                    m_headphone.iconList[i],
                    m_headphone.Dekripsi[i]));
        }

        recyclerViewAdapter = new adapter_item(this, data);
        recyclerView.setAdapter(recyclerViewAdapter);

        btnBack = findViewById(R.id.back);
        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(market_headphone.this, main_market.class);
                startActivity(i);
            }
        });
    }
}