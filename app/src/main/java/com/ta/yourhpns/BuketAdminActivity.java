package com.ta.yourhpns;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.ta.yourhpns.adapter.BuketAdminAdapter;
import com.ta.yourhpns.adapter.UserAdapter;
import com.ta.yourhpns.api.ApiHelper;
import com.ta.yourhpns.api.ApiInterface;
import com.ta.yourhpns.model.ListBuketAdmin;
import com.ta.yourhpns.model.ListBuketAdminModel;
import com.ta.yourhpns.model.ListUserProfile;
import com.ta.yourhpns.model.ListUserProfileModel;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class BuketAdminActivity extends AppCompatActivity {

    ApiInterface mApiInterface;
    ActionBar actionBar;
    FloatingActionButton flTambahBuketAdmin;
    ImageView ivEditBuketAdmin;
    private RecyclerView mRecyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_buket_admin);

        actionBar = getSupportActionBar();
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        flTambahBuketAdmin = findViewById(R.id.flTambahBuketAdmin);
        ivEditBuketAdmin = findViewById(R.id.ivEditBuketAdmin);
        mRecyclerView = findViewById(R.id.rvBuketAdmin);
        mApiInterface = ApiHelper.getClient().create(ApiInterface.class);
        mLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        mRecyclerView.setLayoutManager(mLayoutManager);

        showDataBuket();


        flTambahBuketAdmin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(BuketAdminActivity.this, TambahBuketAdminActivity.class);
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
        Intent intent = new Intent(BuketAdminActivity.this, AdminActivity.class);
        startActivity(intent);
    }

    private void showDataBuket() {
        ApiInterface mApiInterface = ApiHelper.getClient().create(ApiInterface.class);
        Call<ListBuketAdminModel> call = mApiInterface.getListBuketAdmin();
        call.enqueue(new Callback<ListBuketAdminModel>() {
            @Override
            public void onResponse(Call<ListBuketAdminModel> call, Response<ListBuketAdminModel> response) {
                if(response.isSuccessful()){
                    List<ListBuketAdmin> listBuketAdmins = response.body().getListBuketAdmin();
                    mAdapter = new BuketAdminAdapter(listBuketAdmins,getApplicationContext());
                    mRecyclerView.setAdapter(mAdapter);
                }
                else{
                    Log.d("eror?",""+response);
                }

            }

            @Override
            public void onFailure(Call<ListBuketAdminModel> call, Throwable t) {

            }
        });
    }
}
