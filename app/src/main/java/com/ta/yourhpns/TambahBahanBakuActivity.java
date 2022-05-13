package com.ta.yourhpns;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import com.ta.yourhpns.api.ApiHelper;
import com.ta.yourhpns.api.ApiInterface;
import com.ta.yourhpns.model.BahanBakuModel;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class TambahBahanBakuActivity extends AppCompatActivity {
    ApiInterface mApiInterface;
    ActionBar actionBar;
    EditText ieNamaBahanBaku, ieJenisBahanBaku, ieHargaBahanBaku;
    Button btnBatalBahanBaku, btnSimpanBahanBaku;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tambah_bahanbaku);
        ieNamaBahanBaku = findViewById(R.id.ieNamaBahanBaku);
        ieJenisBahanBaku = findViewById(R.id.ieJenisBahanBaku);
        ieHargaBahanBaku = findViewById(R.id.ieHargaBahanBaku);
        btnBatalBahanBaku = findViewById(R.id.btnBatalBahanBaku);
        btnSimpanBahanBaku = findViewById(R.id.btnSimpanBahanBaku);
        actionBar = getSupportActionBar();
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        mApiInterface = ApiHelper.getClient().create(ApiInterface.class);
        btnBatalBahanBaku.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(TambahBahanBakuActivity.this, BahanBakuActivity.class);
                startActivity(intent);
            }
        });
        btnSimpanBahanBaku.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(ieNamaBahanBaku.getText().toString().isEmpty()){

                    Toast.makeText(getApplicationContext(), "Nama bahan baku tidak boleh kosong", Toast.LENGTH_SHORT).show();
                }else{
                    if(ieJenisBahanBaku.getText().toString().isEmpty()){
                        Toast.makeText(getApplicationContext(), "Jenis bahan baku tidak boleh kosong", Toast.LENGTH_SHORT).show();
                    }else{
                        if(ieHargaBahanBaku.getText().toString().isEmpty()){
                            Toast.makeText(getApplicationContext(), "Harga bahan baku tidak boleh kosong", Toast.LENGTH_SHORT).show();
                        }else{
                            simpanBahanBakuBaru();
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
        Intent intent = new Intent(TambahBahanBakuActivity.this, BahanBakuActivity.class);
        startActivity(intent);
    }

    private void simpanBahanBakuBaru(){
        ApiInterface mApiInterface = ApiHelper.getClient().create(ApiInterface.class);
        Call<BahanBakuModel> call = mApiInterface.simpanBahanBakuBaru(
                ieNamaBahanBaku.getText().toString(),
                ieJenisBahanBaku.getText().toString(),
                ieHargaBahanBaku.getText().toString());
        call.enqueue(new Callback<BahanBakuModel>() {
            @Override
            public void onResponse(Call<BahanBakuModel> call, Response<BahanBakuModel> response) {
                if(response.isSuccessful()){
                    Toast.makeText(getApplicationContext(), "Berhasil menyimpan bahan baku", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(TambahBahanBakuActivity.this, BahanBakuActivity.class);
                    startActivity(intent);
                }else{
                    Toast.makeText(getApplicationContext(), "Gagal menyimpan bahan baku", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<BahanBakuModel> call, Throwable t) {
                Toast.makeText(getApplicationContext(), "Ada masalah", Toast.LENGTH_SHORT).show();
            }
        });

    }

}
