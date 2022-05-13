package com.ta.yourhpns;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import com.ta.yourhpns.api.ApiHelper;
import com.ta.yourhpns.api.ApiInterface;
import com.ta.yourhpns.model.BarangModel;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class EditBarangActivity extends AppCompatActivity {
    ApiInterface mApiInterface;
    ActionBar actionBar;
    EditText ieNamaBarang, ieJenisBarang, ieHargaBarang, ieStokBarang;
    ImageView ivSimpanBarang, ivBatalBarang;
    String id;
    Context context;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_barang);

        ieNamaBarang = findViewById(R.id.ieNamaBarang);
        ieJenisBarang = findViewById(R.id.ieJenisBarang);
        ieHargaBarang = findViewById(R.id.ieHargaBarang);
        ieStokBarang = findViewById(R.id.ieStokBarang);
        ivSimpanBarang = findViewById(R.id.ivSimpanBarang);
        ivBatalBarang = findViewById(R.id.ivBatalBarang);
        actionBar = getSupportActionBar();
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        Intent intent = getIntent();
        id = intent.getStringExtra("id_barang");
        ieNamaBarang.setText(intent.getStringExtra("nama_barang"));
        ieJenisBarang.setText(intent.getStringExtra("jenis_barang"));
        ieHargaBarang.setText(intent.getStringExtra("harga_barang"));
        ieStokBarang.setText(intent.getStringExtra("stok_barang"));

        mApiInterface = ApiHelper.getClient().create(ApiInterface.class);
        ivSimpanBarang.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(ieNamaBarang.getText().toString().isEmpty()){
                    Toast.makeText(getApplicationContext(), "Nama barang tidak boleh kosong", Toast.LENGTH_SHORT).show();
                }else{
                    if(ieJenisBarang.getText().toString().isEmpty()){
                        Toast.makeText(getApplicationContext(), "Jenis barang tidak boleh kosong", Toast.LENGTH_SHORT).show();
                    }else{
                        if(ieHargaBarang.getText().toString().isEmpty()){
                            Toast.makeText(getApplicationContext(), "Harga barang tidak boleh kosong", Toast.LENGTH_SHORT).show();
                        }else{
                            if(ieStokBarang.getText().toString().isEmpty()){
                                Toast.makeText(getApplicationContext(), "Stok barang tidak boleh kosong", Toast.LENGTH_SHORT).show();
                            }else{
                                updateBarang();
                            }
                        }
                    }
                }

            }
        });
    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }

    private void updateBarang(){
        ApiInterface mApiInterface = ApiHelper.getClient().create(ApiInterface.class);
        Call<BarangModel> call = mApiInterface.updateBarang(
                id,
                ieNamaBarang.getText().toString(),
                ieJenisBarang.getText().toString(),
                ieHargaBarang.getText().toString(),
                ieStokBarang.getText().toString());
        call.enqueue(new Callback<BarangModel>() {
            @Override
            public void onResponse(Call<BarangModel> call, Response<BarangModel> response) {
                if(response.isSuccessful()){
                    Toast.makeText(EditBarangActivity.this, "Berhasil mengedit barang", Toast.LENGTH_LONG).show();
                    Intent intent = new Intent(EditBarangActivity.this, BarangActivity.class);
                    startActivity(intent);
                }else{
                    Toast.makeText(getApplicationContext(), "Gagal mengedit barang", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<BarangModel> call, Throwable t) {

            }
        });

    }
}
