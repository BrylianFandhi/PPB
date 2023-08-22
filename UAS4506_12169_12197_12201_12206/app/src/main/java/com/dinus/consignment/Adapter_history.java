package com.dinus.consignment;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Locale;

public class Adapter_history extends RecyclerView.Adapter<Adapter_history.ViewHolder>{
    Context context;
    ArrayList<History> list;

    public Adapter_history(Context context, ArrayList<History> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public Adapter_history.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(context).inflate(R.layout.list_checkout, parent, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull Adapter_history.ViewHolder holder, int position) {
        History history = list.get(position);
        holder.icon.setImageResource(history.getLogo1());
        holder.title.setText(history.getTitle1());
        holder.harga_c.setText(formatRupiah(Double.parseDouble(String.valueOf(history.getHarga1()))));
        holder.jumlah.setText(history.getTotal1()+"x");
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder{
        TextView title, harga_c, jumlah;
        ImageView icon;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            title = itemView.findViewById(R.id.text_title);
            harga_c = itemView.findViewById(R.id.harga);
            icon = itemView.findViewById(R.id.imageList);
            jumlah = itemView.findViewById(R.id.jumlah);
        }
    }

    private String formatRupiah(Double number){
        Locale localeID = new Locale("in", "ID");
        NumberFormat formatRupiah = NumberFormat.getCurrencyInstance(localeID);
        return formatRupiah.format(number);
    }
}
