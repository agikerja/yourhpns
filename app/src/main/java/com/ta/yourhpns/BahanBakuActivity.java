package com.ta.yourhpns;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.ta.yourhpns.adapter.BahanBakuAdapter;
import com.ta.yourhpns.api.ApiHelper;
import com.ta.yourhpns.api.ApiInterface;
import com.ta.yourhpns.model.ListBahanBaku;
import com.ta.yourhpns.model.ListBahanBakuModel;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class BahanBakuActivity extends AppCompatActivity {
    ApiInterface mApiInterface;
    ActionBar actionBar;
    FloatingActionButton flTambahBahanBaku;
    private RecyclerView mRecyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bahan_baku);
        actionBar = getSupportActionBar();
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        flTambahBahanBaku = findViewById(R.id.flTambahBahanBaku);
        mRecyclerView = findViewById(R.id.rvBahanBaku);
        mApiInterface = ApiHelper.getClient().create(ApiInterface.class);
        mLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        mRecyclerView.setLayoutManager(mLayoutManager);

        showBahanBaku();

        flTambahBahanBaku.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(BahanBakuActivity.this, TambahBahanBakuActivity.class);
                startActivity(intent);
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

        Intent intent = new Intent(BahanBakuActivity.this, AdminActivity.class);
        startActivity(intent);
    }

    private void showBahanBaku(){
        ApiInterface mApiInterface = ApiHelper.getClient().create(ApiInterface.class);
        Call<ListBahanBakuModel> call = mApiInterface.getListBahanBaku();
        call.enqueue(new Callback<ListBahanBakuModel>() {
            @Override
            public void onResponse(Call<ListBahanBakuModel> call, Response<ListBahanBakuModel> response) {
                List<ListBahanBaku> listBahanBakus = response.body().getListBahanBaku();
                mAdapter = new BahanBakuAdapter(listBahanBakus,getApplicationContext());
                mRecyclerView.setAdapter(mAdapter);
            }

            @Override
            public void onFailure(Call<ListBahanBakuModel> call, Throwable t) {

            }
        });
    }
}
