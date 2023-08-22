package com.dinus.consignment;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Locale;

public class Adapter_Cart extends RecyclerView.Adapter<Adapter_Cart.ViewHolder> {

    Context context;
    ArrayList<Cart> list;
    OnCallBack onCallBack;
    DatabaseReference reff;
    Cart cart;

    public Adapter_Cart(Context context, ArrayList<Cart> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(context).inflate(R.layout.list_cart, parent, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Cart cart = list.get(position);
        reff = FirebaseDatabase.getInstance().getReference("Cart");
        holder.icon.setImageResource(cart.getLogo());
        holder.title.setText(cart.getTitle());
        holder.harga_c.setText(formatRupiah(Double.parseDouble(String.valueOf(cart.getHarga()))));
        holder.jumlah.setText(cart.getTotal()+"x");
        holder.delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                reff.child(cart.getKey()).removeValue(new DatabaseReference.CompletionListener() {
                    @Override
                    public void onComplete(@Nullable DatabaseError error, @NonNull DatabaseReference ref) {
                        Intent reload = new Intent(context.getApplicationContext(), cart_shop.class);
                        context.startActivity(reload);
                        Toast.makeText(context.getApplicationContext(), "Data dihapus", Toast.LENGTH_SHORT).show();
                    }
                });
            }
        });

    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder{
        TextView title, harga_c, jumlah;
        ImageView icon;
        Button delete;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            title = itemView.findViewById(R.id.text_title);
            harga_c = itemView.findViewById(R.id.harga);
            icon = itemView.findViewById(R.id.imageList);
            jumlah = itemView.findViewById(R.id.jumlah);
            delete = itemView.findViewById(R.id.hapus);
        }
    }

    private String formatRupiah(Double number){
        Locale localeID = new Locale("in", "ID");
        NumberFormat formatRupiah = NumberFormat.getCurrencyInstance(localeID);
        return formatRupiah.format(number);
    }

    public interface OnCallBack{
        void onTblHapus(Cart cart);
    }
}
