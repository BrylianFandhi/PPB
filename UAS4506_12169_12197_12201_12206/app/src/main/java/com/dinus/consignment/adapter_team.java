package com.dinus.consignment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class adapter_team extends RecyclerView.Adapter<adapter_team.ViewHolder> {
    private ArrayList<model_team> dataItem;

    static class ViewHolder extends RecyclerView.ViewHolder {
        TextView textNama;
        TextView textNim;
        ImageView image;

        ViewHolder(View v) {
            super(v);
            textNama = v.findViewById(R.id.text_nama);
            textNim = v.findViewById(R.id.text_nim);
            image = v.findViewById(R.id.imageList);
        }
    }

    adapter_team(ArrayList<model_team> data) {
        this.dataItem = data;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_nama, parent, false);
        //myonClickListener
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        TextView textNama = holder.textNama;
        TextView textNim = holder.textNim;
        ImageView image = holder.image;

        textNama.setText(dataItem.get(position).getName());
        textNim.setText(dataItem.get(position).getType());
        image.setImageResource(dataItem.get(position).getImage());
    }

    @Override
    public int getItemCount() {
        return dataItem.size();
    }

}
