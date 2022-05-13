package com.ta.yourhpns.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.ta.yourhpns.R;
import com.ta.yourhpns.model.ListUserProfile;

import java.util.List;

public class UserAdapter extends RecyclerView.Adapter<UserAdapter.ViewHolder> {
    private List<ListUserProfile> listprofile;
    Context context;

    public UserAdapter(List<ListUserProfile> listprofile, Context context) {
        this.listprofile = listprofile;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_user,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.tvNamaMgr.setText(listprofile.get(position).getNama());
        holder.tvUsernameMgr.setText(listprofile.get(position).getUsername());
        if(listprofile.get(position).getRole().equals("4")){
            holder.tvRoleMgr.setText("Customer");
        }else{
            if(listprofile.get(position).getRole().equals("3")){
                holder.tvRoleMgr.setText("Pegawai");
            }else{
                if(listprofile.get(position).getRole().equals("2")){
                    holder.tvRoleMgr.setText("Admin");
                }else{
                    holder.tvRoleMgr.setText("Superadmin");
                }
            }
        }
    }

    @Override
    public int getItemCount() {
        return listprofile.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        TextView tvNamaMgr, tvUsernameMgr, tvRoleMgr;
        public ViewHolder(View itemView) {
            super(itemView);
            tvNamaMgr = itemView.findViewById(R.id.tvNamaMgr);
            tvUsernameMgr = itemView.findViewById(R.id.tvUsernameMgr);
            tvRoleMgr = itemView.findViewById(R.id.tvRoleMgr);
        }
    }
}
