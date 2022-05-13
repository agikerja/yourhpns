package com.ta.yourhpns;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.ta.yourhpns.adapter.BahanBakuAdapter;
import com.ta.yourhpns.adapter.UserAdapter;
import com.ta.yourhpns.api.ApiHelper;
import com.ta.yourhpns.api.ApiInterface;
import com.ta.yourhpns.model.ListBahanBaku;
import com.ta.yourhpns.model.ListBahanBakuModel;
import com.ta.yourhpns.model.ListUserProfile;
import com.ta.yourhpns.model.ListUserProfileModel;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class UserMgrActivity extends AppCompatActivity {
    List<ListUserProfile> list;
    ApiInterface mApiInterface;
    ActionBar actionBar;
    Button btnTambahPegawai;
    private RecyclerView mRecyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_superadmin_usermgr);
        actionBar = getSupportActionBar();
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        btnTambahPegawai = findViewById(R.id.btnTambahPegawai);
        mRecyclerView = findViewById(R.id.rvUserMgr);
        mApiInterface = ApiHelper.getClient().create(ApiInterface.class);
        mLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        mRecyclerView.setLayoutManager(mLayoutManager);

        showUser();
        btnTambahPegawai.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(UserMgrActivity.this, TambahPegawaiActivity.class);
                startActivity(intent);
            }
        });


    }

    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }

    @Override
    public void onBackPressed() {

        Intent intent = new Intent(UserMgrActivity.this, AdminActivity.class);
        startActivity(intent);
    }

    private void showUser() {
        ApiInterface mApiInterface = ApiHelper.getClient().create(ApiInterface.class);
        Call<ListUserProfileModel> call = mApiInterface.getListUserProfile();
        call.enqueue(new Callback<ListUserProfileModel>() {
            @Override
            public void onResponse(Call<ListUserProfileModel> call, Response<ListUserProfileModel> response) {
                List<ListUserProfile> listProfiles = response.body().getListUserProfile();
                mAdapter = new UserAdapter(listProfiles,getApplicationContext());
                mRecyclerView.setAdapter(mAdapter);
            }

            @Override
            public void onFailure(Call<ListUserProfileModel> call, Throwable t) {

            }
        });

    }

}
