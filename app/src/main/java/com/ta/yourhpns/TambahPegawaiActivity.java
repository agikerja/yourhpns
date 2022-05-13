package com.ta.yourhpns;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import com.ta.yourhpns.api.ApiHelper;
import com.ta.yourhpns.api.ApiInterface;
import com.ta.yourhpns.model.BuketAdminModel;
import com.ta.yourhpns.model.ListUserProfileModel;
import com.ta.yourhpns.model.UserProfileModel;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class TambahPegawaiActivity extends AppCompatActivity {
    EditText ieNama, ieUsername, ieEmail, iePassword;
    AutoCompleteTextView spJabatan;
    Button btnSimpanPegawai;
    ApiInterface mApiInterface;
    String role = "";
    ActionBar actionBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        actionBar = getSupportActionBar();
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        setContentView(R.layout.activity_tambah_pegawai);
        ieNama = findViewById(R.id.ieNama);
        ieUsername = findViewById(R.id.ieUsername);
        ieEmail = findViewById(R.id.ieEmail);
        iePassword = findViewById(R.id.iePassword);
        spJabatan = findViewById(R.id.spJabatan);
        mApiInterface = ApiHelper.getClient().create(ApiInterface.class);
        btnSimpanPegawai = findViewById(R.id.btnSimpanPegawai);

        ArrayAdapter<CharSequence> adapterJabatan = ArrayAdapter.createFromResource(getApplicationContext(), R.array.jabatan, R.layout.spjabatan);
        adapterJabatan.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spJabatan.setAdapter(adapterJabatan);

        btnSimpanPegawai.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(spJabatan.getText().toString().equals("Admin")){
                    role = "2";
                }else{
                    role = "3";
                }
                simpanDataPegawai();
            }
        });
    }

    public void simpanDataPegawai(){
        ApiInterface mApiInterface = ApiHelper.getClient().create(ApiInterface.class);
        Call<UserProfileModel> call = mApiInterface.daftarPegawai(
                ieNama.getText().toString(),
                ieUsername.getText().toString(),
                ieEmail.getText().toString(),
                iePassword.getText().toString(),
                role);
        call.enqueue(new Callback<UserProfileModel>() {
            @Override
            public void onResponse(Call<UserProfileModel> call, Response<UserProfileModel> response) {
                if(response.isSuccessful()){
                    Toast.makeText(getApplicationContext(), "Berhasil menyimpan pegawai", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(TambahPegawaiActivity.this, UserMgrActivity.class);
                    startActivity(intent);
                }else{
                    Toast.makeText(getApplicationContext(), "Gagal menyimpan pegawai", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<UserProfileModel> call, Throwable t) {
                Toast.makeText(getApplicationContext(), "Ada masalah", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
