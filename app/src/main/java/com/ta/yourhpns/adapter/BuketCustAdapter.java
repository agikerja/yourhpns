package com.ta.yourhpns.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.ta.yourhpns.R;
import com.ta.yourhpns.model.ListBuketAdmin;

import java.util.List;

public class BuketCustAdapter extends RecyclerView.Adapter<BuketCustAdapter.ViewHolder> {

    private List<ListBuketAdmin> listBuketCust;
    private final int limit = 5;
    Context context;

    public BuketCustAdapter(List<ListBuketAdmin> listBuketCust, Context context) {
        this.listBuketCust = listBuketCust;
        this.context = context;
    }


    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_buket_cust,parent,false);
        return new BuketCustAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.tvNamaBuketCust.setText(listBuketCust.get(position).getNamaBuket());
        holder.tvHargaBuketCust.setText(listBuketCust.get(position).getHargaBuket());

    }

    @Override
    public int getItemCount() {
        if(listBuketCust.size()>limit){
            return limit;
        }else{
            return listBuketCust.size();
        }
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView tvNamaBuketCust, tvHargaBuketCust;
        public ViewHolder(View itemView) {
            super(itemView);
            tvNamaBuketCust = itemView.findViewById(R.id.tvNamaBuketCust);
            tvHargaBuketCust = itemView.findViewById(R.id.tvHargaBuketCust);
        }
    }
}
