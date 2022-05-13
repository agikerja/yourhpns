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
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.ta.yourhpns.BahanBakuActivity;
import com.ta.yourhpns.BarangActivity;
import com.ta.yourhpns.EditBahanBakuActivity;
import com.ta.yourhpns.R;
import com.ta.yourhpns.api.ApiHelper;
import com.ta.yourhpns.api.ApiInterface;
import com.ta.yourhpns.model.BahanBakuModel;
import com.ta.yourhpns.model.BarangModel;
import com.ta.yourhpns.model.ListBahanBaku;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class BahanBakuAdapter extends RecyclerView.Adapter<BahanBakuAdapter.ViewHolder> {

    private List<ListBahanBaku> listBahanBakus;
    Context context;

    public BahanBakuAdapter(List<ListBahanBaku> listBahanBakus, Context context) {
        this.listBahanBakus = listBahanBakus;
        this.context = context;
    }


    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_bahan_baku,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.tvNamaBahanBaku.setText(listBahanBakus.get(position).getNamaBahanBaku());
        holder.tvJenisBahanBaku.setText(listBahanBakus.get(position).getJenisBahanBaku());
        holder.tvHargaBahanBaku.setText(listBahanBakus.get(position).getHargaBahanBaku());
        holder.ivEditBahanBaku.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(view.getContext(), EditBahanBakuActivity.class);
                intent.putExtra("id_bahanbaku", listBahanBakus.get(position).getIdBahanBaku());
                intent.putExtra("nama_bahanbaku", listBahanBakus.get(position).getNamaBahanBaku());
                intent.putExtra("jenis_bahanbaku", listBahanBakus.get(position).getJenisBahanBaku());
                intent.putExtra("harga_bahanbaku", listBahanBakus.get(position).getHargaBahanBaku());
                view.getContext().startActivity(intent);
            }


        });
        holder.ivHapusBahanBaku.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openBottomSheet(view,listBahanBakus.get(position).getIdBahanBaku());
            }
        });
    }
    private void openBottomSheet(View v, String id) {

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
                deleteBahanBaku(v,id);
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
    private void deleteBahanBaku(View view,String id){
        ApiInterface mApiInterface = ApiHelper.getClient().create(ApiInterface.class);
        Call<BahanBakuModel> call = mApiInterface.deleteBahanBaku(
                id
        );
        call.enqueue(new Callback<BahanBakuModel>() {
            @Override
            public void onResponse(Call<BahanBakuModel> call, Response<BahanBakuModel> response) {
                if(response.isSuccessful()){
                    Toast.makeText(view.getContext(), "Berhasil hapus bahan baku", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(view.getContext(), BahanBakuActivity.class);
                    view.getContext().startActivity(intent);
                }else{
                    Toast.makeText(view.getContext(), "Berhasil hapus bahan baku", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<BahanBakuModel> call, Throwable t) {

            }
        });

    }


    @Override
    public int getItemCount() {
        return listBahanBakus.size();
    }



    public class ViewHolder extends RecyclerView.ViewHolder{
        TextView tvNamaBahanBaku, tvJenisBahanBaku, tvHargaBahanBaku;
        ImageView ivEditBahanBaku, ivHapusBahanBaku;

        public ViewHolder(View itemView) {
            super(itemView);
            tvNamaBahanBaku = itemView.findViewById(R.id.tvNamaBahanBaku);
            tvJenisBahanBaku = itemView.findViewById(R.id.tvJenisBahanBaku);
            tvHargaBahanBaku = itemView.findViewById(R.id.tvHargaBahanBaku);
            ivEditBahanBaku = itemView.findViewById(R.id.ivEditBahanBaku);
            ivHapusBahanBaku = itemView.findViewById(R.id.ivHapusBahanBaku);

        }
    }
}
