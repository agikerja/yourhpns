package com.ta.yourhpns;

import android.content.Intent;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.SearchView;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.ta.yourhpns.adapter.BarangAdapter;
import com.ta.yourhpns.api.ApiHelper;
import com.ta.yourhpns.api.ApiInterface;
import com.ta.yourhpns.model.BarangModel;
import com.ta.yourhpns.model.ListBarang;
import com.ta.yourhpns.model.ListBarangModel;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class BarangActivity extends AppCompatActivity {


    ApiInterface mApiInterface;
    ActionBar actionBar;
    FloatingActionButton flTambahBarang;
    private RecyclerView mRecyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_barang);

        actionBar = getSupportActionBar();
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        flTambahBarang = findViewById(R.id.flTambahBarang);
        mRecyclerView = findViewById(R.id.rvBarang);
        mApiInterface = ApiHelper.getClient().create(ApiInterface.class);
        mLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        mRecyclerView.setLayoutManager(mLayoutManager);

        showData();

        flTambahBarang.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(BarangActivity.this, TambahBarangActivity.class);
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
        super.onBackPressed();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.toolbar,menu);

        MenuItem menuItem = menu.findItem(R.id.caribar);
        SearchView searchView = (SearchView) menuItem.getActionView();
        searchView.setQueryHint("Cari di sini...");
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String s) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {

                return false;
            }
        });
        return super.onCreateOptionsMenu(menu);

    }

    private void showData(){
        ApiInterface mApiInterface = ApiHelper.getClient().create(ApiInterface.class);
        Call<ListBarangModel> call = mApiInterface.getListBarang();
        call.enqueue(new Callback<ListBarangModel>() {
            @Override
            public void onResponse(Call<ListBarangModel> call, Response<ListBarangModel> response) {
                List<ListBarang> listBarangs = response.body().getListBarang();
                mAdapter = new BarangAdapter(listBarangs,getApplicationContext());
                mRecyclerView.setAdapter(mAdapter);
            }

            @Override
            public void onFailure(Call<ListBarangModel> call, Throwable t) {

            }
        });
    }
}
