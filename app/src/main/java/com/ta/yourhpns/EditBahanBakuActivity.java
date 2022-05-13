package com.ta.yourhpns;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.NoCopySpan;
import android.util.Log;
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

public class EditBahanBakuActivity extends AppCompatActivity {
    ApiInterface mApiInterface;
    ActionBar actionBar;
    EditText ieNamaBahanBaku, ieJenisBahanBaku, ieHargaBahanBaku;
    Button btnSimpanBahanBaku, btnBatalBarangBaku;
    String id;
    Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_bahanbaku);

        ieNamaBahanBaku = findViewById(R.id.ieNamaBahanBaku);
        ieJenisBahanBaku = findViewById(R.id.ieJenisBahanBaku);
        ieHargaBahanBaku = findViewById(R.id.ieHargaBahanBaku);
        btnSimpanBahanBaku = findViewById(R.id.btnSimpanBahanBaku);
        btnBatalBarangBaku = findViewById(R.id.btnBatalBahanBaku);

        actionBar = getSupportActionBar();
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        Intent intent = getIntent();
        id = intent.getStringExtra("id_bahanbaku");
        ieNamaBahanBaku.setText(intent.getStringExtra("nama_bahanbaku"));
        ieJenisBahanBaku.setText(intent.getStringExtra("jenis_bahanbaku"));
        ieHargaBahanBaku.setText(intent.getStringExtra("harga_bahanbaku"));

        mApiInterface = ApiHelper.getClient().create(ApiInterface.class);
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
                            updateBahanBaku();
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
        Intent intent = new Intent(EditBahanBakuActivity.this, BahanBakuActivity.class);
        startActivity(intent);
    }

    private void updateBahanBaku(){
        ApiInterface mApiInterface = ApiHelper.getClient().create(ApiInterface.class);
        Call<BahanBakuModel> call = mApiInterface.updateBahanBaku(
                id,
                ieNamaBahanBaku.getText().toString(),
                ieJenisBahanBaku.getText().toString(),
                ieHargaBahanBaku.getText().toString());

        call.enqueue(new Callback<BahanBakuModel>() {
            @Override
            public void onResponse(Call<BahanBakuModel> call, Response<BahanBakuModel> response) {
                if(response.isSuccessful()){
                    Toast.makeText(EditBahanBakuActivity.this, "Berhasil mengedit bahan baku", Toast.LENGTH_LONG).show();
                    Intent intent = new Intent(EditBahanBakuActivity.this, BahanBakuActivity.class);
                    startActivity(intent);
                }else{
                    Toast.makeText(getApplicationContext(), "Gagal mengedit bahan baku", Toast.LENGTH_SHORT).show();
                }

            }

            @Override
            public void onFailure(Call<BahanBakuModel> call, Throwable t) {

            }
        });
    }
}
