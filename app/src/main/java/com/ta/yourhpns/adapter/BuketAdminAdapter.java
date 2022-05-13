package com.ta.yourhpns.adapter;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.ta.yourhpns.BahanBakuActivity;
import com.ta.yourhpns.BuketAdminActivity;
import com.ta.yourhpns.EditBahanBakuActivity;
import com.ta.yourhpns.EditBuketAdminActivity;
import com.ta.yourhpns.R;
import com.ta.yourhpns.api.ApiHelper;
import com.ta.yourhpns.api.ApiInterface;
import com.ta.yourhpns.model.BahanBakuModel;
import com.ta.yourhpns.model.BuketAdminModel;
import com.ta.yourhpns.model.DetailBahanBakuModel;
import com.ta.yourhpns.model.ListBuketAdmin;
import com.ta.yourhpns.model.ListDetailBahanBaku;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class BuketAdminAdapter extends RecyclerView.Adapter<BuketAdminAdapter.ViewHolder> {

    private List<ListBuketAdmin> listBuketAdmins;
    Context context;

    public BuketAdminAdapter(List<ListBuketAdmin> listBuketAdmins, Context context) {
        this.listBuketAdmins = listBuketAdmins;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_buket_admin,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.tvNamaBuketAdmin.setText(listBuketAdmins.get(position).getNamaBuket());
        holder.tvHargaBuketAdmin.setText(listBuketAdmins.get(position).getHargaBuket());
        holder.ivEditBuketAdmin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(view.getContext(), EditBuketAdminActivity.class);
                intent.putExtra("id_buket", listBuketAdmins.get(position).getIdBuket());
                intent.putExtra("nama_buket", listBuketAdmins.get(position).getNamaBuket());
                intent.putExtra("jenis_buket", listBuketAdmins.get(position).getJenisBuket());
                intent.putExtra("harga_buket", listBuketAdmins.get(position).getHargaBuket());
                intent.putExtra("kode_buket", listBuketAdmins.get(position).getKodeBuket());
                view.getContext().startActivity(intent);
            }
        });
        holder.ivHapusBuketAdmin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openBottomSheet(view, listBuketAdmins.get(position).getIdBuket(), listBuketAdmins.get(position).getKodeBuket());
            }
        });


    }

    private void openBottomSheet(View v, String id, String kodebuket) {
        Context context=v.getContext();
        LayoutInflater inflater = (LayoutInflater) context.getSystemService( Context.LAYOUT_INFLATER_SERVICE );
        View view = inflater.inflate (R.layout.modal_bottomsheet, null);
        final TextView txtYa = (TextView)view.findViewById( R.id.txtYa);
        final TextView txtTidak = (TextView)view.findViewById( R.id.txtTidak);
        final Dialog mBottomSheetDialog = new Dialog (context, R.style.Base_Theme_Material3_Dark_BottomSheetDialog);
        mBottomSheetDialog.setContentView (view);
        mBottomSheetDialog.setCancelable (true);
        mBottomSheetDialog.getWindow ().setLayout (LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.WRAP_CONTENT);
        mBottomSheetDialog.getWindow ().setGravity (Gravity.BOTTOM);
        mBottomSheetDialog.show ();

        txtYa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.d("ID",""+id);
                deleteBuketAdmin(v,id);
                deleteDetailBahanbaku(v,kodebuket);

                mBottomSheetDialog.dismiss();


            }
        });
        txtTidak.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mBottomSheetDialog.dismiss();
            }
        });
    }
    private void deleteBuketAdmin(View view,String id){
        ApiInterface mApiInterface = ApiHelper.getClient().create(ApiInterface.class);
        Call<BuketAdminModel> call = mApiInterface.deleteBuket(
                id
        );
        call.enqueue(new Callback<BuketAdminModel>() {
            @Override
            public void onResponse(Call<BuketAdminModel> call, Response<BuketAdminModel> response) {
                if(response.isSuccessful()){

                    Toast.makeText(view.getContext(), "Berhasil hapus buket admin", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(view.getContext(), BuketAdminActivity.class);
                    view.getContext().startActivity(intent);
                }else{
                    Toast.makeText(view.getContext(), "Berhasil hapus buket admin", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<BuketAdminModel> call, Throwable t) {

            }
        });

    }
    private void deleteDetailBahanbaku(View view, String kodebuket){
        ApiInterface mApiInterface = ApiHelper.getClient().create(ApiInterface.class);
        Call<DetailBahanBakuModel> call = mApiInterface.deleteDetail(
                kodebuket
        );
        call.enqueue(new Callback<DetailBahanBakuModel>() {
            @Override
            public void onResponse(Call<DetailBahanBakuModel> call, Response<DetailBahanBakuModel> response) {
                if(response.isSuccessful()){

                    Toast.makeText(view.getContext(), "Berhasil hapus detail bahan baku", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(view.getContext(), BuketAdminActivity.class);
                    view.getContext().startActivity(intent);
                }else{
                    Toast.makeText(view.getContext(), "Gagal hapus detail bahan baku", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<DetailBahanBakuModel> call, Throwable t) {

            }
        });
    }

    @Override
    public int getItemCount() { return listBuketAdmins.size(); }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView tvNamaBuketAdmin, tvHargaBuketAdmin;
        ImageView ivEditBuketAdmin, ivHapusBuketAdmin;

        public ViewHolder(View itemView) {
            super(itemView);
            tvNamaBuketAdmin = itemView.findViewById(R.id.tvNamaBuketAdmin);
            tvHargaBuketAdmin = itemView.findViewById(R.id.tvHargaBuketAdmin);
            ivEditBuketAdmin = itemView.findViewById(R.id.ivEditBuketAdmin);
            ivHapusBuketAdmin = itemView.findViewById(R.id.ivHapusBuketAdmin);

        }
    }
}
