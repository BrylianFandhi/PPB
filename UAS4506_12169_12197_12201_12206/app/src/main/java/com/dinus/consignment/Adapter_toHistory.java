package com.dinus.consignment;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Locale;

public class Adapter_toHistory extends RecyclerView.Adapter<Adapter_toHistory.ViewHolder>{
    Context context;
    ArrayList<Cart> list;
    History history;

    public Adapter_toHistory(Context context, ArrayList<Cart> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public Adapter_toHistory.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(context).inflate(R.layout.list_checkout, parent, false);
        return new Adapter_toHistory.ViewHolder(v);
    }


    @Override
    public void onBindViewHolder(@NonNull Adapter_toHistory.ViewHolder holder, int position) {
        Cart cart = list.get(position);
        holder.icon.setImageResource(cart.getLogo());
        holder.title.setText(cart.getTitle());
        holder.harga_c.setText(formatRupiah(Double.parseDouble(String.valueOf(cart.getHarga()))));
//        holder.total.setText(cart.getHarga());
        holder.jumlah.setText(cart.getTotal()+"x");

        history = new History();
        final DatabaseReference hreff = FirebaseDatabase.getInstance().getReference().child("History");
        history.setLogo1(cart.getLogo());
        history.setTitle1(cart.getTitle());
        history.setHarga1(cart.getHarga());
        history.setTotal1(cart.getTotal());
        hreff.push().setValue(history);

    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder{
        TextView title, harga_c, jumlah, total;
        ImageView icon;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            title = itemView.findViewById(R.id.text_title);
            harga_c = itemView.findViewById(R.id.harga);
            icon = itemView.findViewById(R.id.imageList);
            jumlah = itemView.findViewById(R.id.jumlah);
            total = itemView.findViewById(R.id.total);
        }
    }

    private String formatRupiah(Double number){
        Locale localeID = new Locale("in", "ID");
        NumberFormat formatRupiah = NumberFormat.getCurrencyInstance(localeID);
        return formatRupiah.format(number);
    }
}