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

import com.google.android.material.bottomsheet.BottomSheetDialog;
import com.ta.yourhpns.BarangActivity;
import com.ta.yourhpns.EditBarangActivity;
import com.ta.yourhpns.R;
import com.ta.yourhpns.api.ApiHelper;
import com.ta.yourhpns.api.ApiInterface;
import com.ta.yourhpns.model.BarangModel;
import com.ta.yourhpns.model.ListBarang;

import org.w3c.dom.Text;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class BarangAdapter extends RecyclerView.Adapter<BarangAdapter.ViewHolder>{

    private List<ListBarang> listBarangs;
    Context context;

    public BarangAdapter(List<ListBarang> listBarangs, Context context) {
        this.listBarangs = listBarangs;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_barang,parent,false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.tvNamaBarang.setText(listBarangs.get(position).getNamaBarang());
        holder.tvJenisBarang.setText(listBarangs.get(position).getJenisBarang());
        holder.tvHargaBarang.setText(listBarangs.get(position).getHargaBarang());
        holder.tvStokBarang.setText(listBarangs.get(position).getStokBarang());
        holder.ivEditBarang.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.d("Tes","Masuk");
                Intent intent = new Intent(view.getContext(), EditBarangActivity.class);
                intent.putExtra("id_barang", listBarangs.get(position).getIdBarang());
                intent.putExtra("nama_barang", listBarangs.get(position).getNamaBarang());
                intent.putExtra("jenis_barang", listBarangs.get(position).getJenisBarang());
                intent.putExtra("harga_barang", listBarangs.get(position).getHargaBarang());
                intent.putExtra("stok_barang", listBarangs.get(position).getStokBarang());
                view.getContext().startActivity(intent);
            }
        });

        holder.ivHapusBarang.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openBottomSheet(view,listBarangs.get(position).getIdBarang());
            }
        });
    }

    @Override
    public int getItemCount() {
        return listBarangs.size();
    }

    private void openBottomSheet(View v, String id) {
        //View view = activity.getLayoutInflater ().inflate (R.layout.bottom_sheet, null);
        // View view = inflater.inflate( R.layout.bottom_sheet, null );
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
                deleteBarang(v,id);
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
    private void deleteBarang(View view,String id){
        ApiInterface mApiInterface = ApiHelper.getClient().create(ApiInterface.class);
        Call<BarangModel> call = mApiInterface.deleteBarang(
                id
        );
        call.enqueue(new Callback<BarangModel>() {
            @Override
            public void onResponse(Call<BarangModel> call, Response<BarangModel> response) {
                if(response.isSuccessful()){
                    Toast.makeText(view.getContext(), "Berhasil hapus barang", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(view.getContext(), BarangActivity.class);
                    view.getContext().startActivity(intent);

                }else{

                }
            }

            @Override
            public void onFailure(Call<BarangModel> call, Throwable t) {

            }
        });

    }
    public class ViewHolder extends RecyclerView.ViewHolder{
        TextView tvNamaBarang;
        TextView tvJenisBarang;
        TextView tvHargaBarang;
        TextView tvStokBarang;
        ImageView ivEditBarang;
        ImageView ivHapusBarang;

        public ViewHolder(View itemView) {
            super(itemView);
            tvNamaBarang = itemView.findViewById(R.id.tvNamaBarang);
            tvJenisBarang = itemView.findViewById(R.id.tvJenisBarang);
            tvHargaBarang = itemView.findViewById(R.id.tvHargaBarang);
            tvStokBarang = itemView.findViewById(R.id.tvStokBarang);
            ivEditBarang = itemView.findViewById(R.id.ivEditBarang);
            ivHapusBarang = itemView.findViewById(R.id.ivHapusBarang);
        }
    }
}
