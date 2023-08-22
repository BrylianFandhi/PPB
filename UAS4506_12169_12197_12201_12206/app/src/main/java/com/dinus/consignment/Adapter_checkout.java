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

public class Adapter_checkout extends RecyclerView.Adapter<Adapter_checkout.ViewHolder>{
    Context context;
    ArrayList<Cart> list;
    History history;

    public Adapter_checkout(Context context, ArrayList<Cart> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public Adapter_checkout.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(context).inflate(R.layout.list_checkout, parent, false);
        return new ViewHolder(v);
    }


    @Override
    public void onBindViewHolder(@NonNull Adapter_checkout.ViewHolder holder, int position) {
        Cart cart = list.get(position);
        holder.icon.setImageResource(cart.getLogo());
        holder.title.setText(cart.getTitle());
        holder.harga_c.setText(formatRupiah(Double.parseDouble(String.valueOf(cart.getHarga()))));
//        holder.total.setText(cart.getHarga());
        holder.jumlah.setText(cart.getTotal()+"x");

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
