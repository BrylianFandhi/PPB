package com.dinus.consignment;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.LinearLayoutCompat;
import androidx.recyclerview.widget.RecyclerView;

import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Locale;

public class adapter_item extends RecyclerView.Adapter<adapter_item.ViewHolder> {

    private ArrayList<itemModel> dataItem;
    private Context context;

    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView head, subhead, price;
        ImageView icon;
        Button btnlanjut;
        LinearLayoutCompat parent_layout;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            head = itemView.findViewById(R.id.text_title);
            subhead = itemView.findViewById((R.id.jenis));
            price = itemView.findViewById(R.id.harga);
            icon = itemView.findViewById(R.id.imageList);
            parent_layout = itemView.findViewById(R.id.parent_layout);
            btnlanjut = itemView.findViewById(R.id.btn_beli);

        }
    }

    adapter_item(Context context, ArrayList<itemModel> dataItem){
        this.dataItem = dataItem;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item, parent, false);


        return new ViewHolder(view);
    }



    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        TextView head = holder.head;
        TextView subhead = holder.subhead;
        TextView price = holder.price;
        ImageView icon = holder.icon;

        head.setText(dataItem.get(position).getName());
        price.setText(formatRupiah(Double.parseDouble(String.valueOf(dataItem.get(position).getPrice()))));
        subhead.setText(dataItem.get(position).getType());
        icon.setImageResource(dataItem.get(position).getImage());

        holder.parent_layout.setOnClickListener(v -> {
            Toast.makeText(context, dataItem.get(position).getName(), Toast.LENGTH_SHORT).show();

            if(dataItem.get(position).getName().equals(dataItem.get(position).getName())){
                Intent intent = new Intent(context, detail_item.class);
                intent.putExtra("GAMBAR_DEFAULT", dataItem.get(position).getImage());
                intent.putExtra("TEKS_DEFAULT", dataItem.get(position).getName());
                intent.putExtra("HARGA_DEFAULT", dataItem.get(position).getPrice());
                intent.putExtra("TYPE_DEFAULT", dataItem.get(position).getType());
                intent.putExtra("DETAIL_DEFAULT", dataItem.get(position).getDesc());
                context.startActivities(new Intent[]{intent});
            }
        });


    }

    @Override
    public int getItemCount() {
        return dataItem.size();
    }

    void setFilter(ArrayList<itemModel> filterModel){
        dataItem = new ArrayList<>();
        dataItem.addAll(filterModel);
        notifyDataSetChanged();
    }

    private String formatRupiah(Double number){
        Locale localeID = new Locale("in", "ID");
        NumberFormat formatRupiah = NumberFormat.getCurrencyInstance(localeID);
        return formatRupiah.format(number);
    }

}

