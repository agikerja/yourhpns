package com.ta.yourhpns;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import com.ta.yourhpns.api.ApiHelper;
import com.ta.yourhpns.api.ApiInterface;
import com.ta.yourhpns.model.BahanBakuModel;
import com.ta.yourhpns.model.BuketAdminModel;
import com.ta.yourhpns.model.BuketModel;
import com.ta.yourhpns.model.DetailBahanBakuModel;
import com.ta.yourhpns.model.ListBahanBaku;
import com.ta.yourhpns.model.ListBahanBakuModel;
import com.ta.yourhpns.model.ListBuketAdminModel;
import com.ta.yourhpns.model.ListDetailBahanBaku;
import com.ta.yourhpns.model.ListDetailBahanbakuModel;
import com.ta.yourhpns.model.ListUserProfile;
import com.ta.yourhpns.model.StatusModel;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class EditBuketAdminActivity extends AppCompatActivity {
    List<ListBahanBaku> list;
    int res = 0;
    int resultid = 0;
    ApiInterface mApiInterface;
    ActionBar actionBar;
    String f1 = "";
    String f2 = "";
    String f3 = "";
    String f4 = "";
    String f5 = "";
    int s1 = 0;
    int s2 = 0;
    int s3 = 0;
    int s4 = 0;
    int s5 = 0;
    String id = "";
    String kodebuket = "";
    int sum = 0;
    int sumBahan = 0;
    String sumx = "";
    int a = 0;
    int ax = 0;
    int b = 0;
    EditText ieNamaBuket, ieHargaBuket, ieJmlBhnBaku1, ieJmlBhnBaku2, ieJmlBhnBaku3, ieJmlBhnBaku4, ieJmlBhnBaku5;
    public static List<String> arrayValue = new ArrayList<String>();
    public static List<Integer> jumlah = new ArrayList<Integer>();
    public static List<String> harga = new ArrayList<String>();
    private boolean viewGroupIsVisible = true;
    private RecyclerView.Adapter mAdapter;
    private View mViewGroup;
    AutoCompleteTextView spBahan1, spBahan2, spBahan3, spBahan4, spBahan5, spJenisBuket;
    TextView txtShowhidebahanbaku, txtHargaBhnBaku1, txtHargaBhnBaku2, txtHargaBhnBaku3, txtHargaBhnBaku4, txtHargaBhnBaku5, txtSumBahan;
    Button btnSimpanBuketAdmin, btnBatalBuketAdmin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        actionBar = getSupportActionBar();
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        setContentView(R.layout.activity_edit_buket_admin);
        spBahan1 = findViewById(R.id.spBahan1);
        spBahan2 = findViewById(R.id.spBahan2);
        spBahan3 = findViewById(R.id.spBahan3);
        spBahan4 = findViewById(R.id.spBahan4);
        spBahan5 = findViewById(R.id.spBahan5);
        ieNamaBuket = findViewById(R.id.ieNamaBuket);
        spJenisBuket = findViewById(R.id.spJenisBuket);
        ieHargaBuket = findViewById(R.id.ieHargaBuket);
        ieJmlBhnBaku1 = findViewById(R.id.ieJmlBhnBaku1);
        ieJmlBhnBaku2 = findViewById(R.id.ieJmlBhnBaku2);
        ieJmlBhnBaku3 = findViewById(R.id.ieJmlBhnBaku3);
        ieJmlBhnBaku4 = findViewById(R.id.ieJmlBhnBaku4);
        ieJmlBhnBaku5 = findViewById(R.id.ieJmlBhnBaku5);
        btnSimpanBuketAdmin = findViewById(R.id.btnSimpanBuketAdmin);
        btnBatalBuketAdmin = findViewById(R.id.btnBatalBuketAdmin);
        txtShowhidebahanbaku = findViewById(R.id.txtShowhidebahanbaku);
        txtHargaBhnBaku1 = findViewById(R.id.txtHargaBhnBaku1);
        txtHargaBhnBaku2 = findViewById(R.id.txtHargaBhnBaku2);
        txtHargaBhnBaku3 = findViewById(R.id.txtHargaBhnBaku3);
        txtHargaBhnBaku4 = findViewById(R.id.txtHargaBhnBaku4);
        txtHargaBhnBaku5 = findViewById(R.id.txtHargaBhnBaku5);
        txtSumBahan = findViewById(R.id.txtSumBahan);
        mApiInterface = ApiHelper.getClient().create(ApiInterface.class);

        ArrayAdapter<CharSequence> adapterJenis = ArrayAdapter.createFromResource(getApplicationContext(), R.array.jenisbuket, R.layout.spjabatan);
        adapterJenis.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spJenisBuket.setAdapter(adapterJenis);



        Intent intent = getIntent();
        id = intent.getStringExtra("id_buket");
        ieNamaBuket.setText(intent.getStringExtra("nama_buket"));
        spJenisBuket.setText(intent.getStringExtra("jenis_buket"));
        ieHargaBuket.setText(intent.getStringExtra("harga_buket"));
        kodebuket = intent.getStringExtra("kode_buket");

        getSpDetailBahan();
        getSpBahanBaku();
        ieJmlBhnBaku1.addTextChangedListener(new TextWatcher() {

            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

                if(!ieJmlBhnBaku1.getText().toString().equals("")){
                    f1 = String.valueOf(getHargaByName(spBahan1.getText().toString()));
                    int a = setHargaFix(ieJmlBhnBaku1.getText().toString(), f1);
                    if(!f1.equals("")){

                        txtHargaBhnBaku1.setText("Rp "+a);
                        sum = sumHargaBuket(
                                txtHargaBhnBaku1.getText().toString(),
                                txtHargaBhnBaku2.getText().toString(),
                                txtHargaBhnBaku3.getText().toString(),
                                txtHargaBhnBaku4.getText().toString(),
                                txtHargaBhnBaku5.getText().toString());
                        txtSumBahan.setText("Rp "+sum);
                    }
                }
            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {


                if(!ieJmlBhnBaku1.getText().toString().equals("")){
                    f1 = String.valueOf(getHargaByName(spBahan1.getText().toString()));
                    int a = setHargaFix(ieJmlBhnBaku1.getText().toString(), f1);
                    if(!f1.equals("")){

                        txtHargaBhnBaku1.setText("Rp "+a);
                        sum = sumHargaBuket(
                                txtHargaBhnBaku1.getText().toString(),
                                txtHargaBhnBaku2.getText().toString(),
                                txtHargaBhnBaku3.getText().toString(),
                                txtHargaBhnBaku4.getText().toString(),
                                txtHargaBhnBaku5.getText().toString());
                        txtSumBahan.setText("Rp "+sum);
                    }
                }
            }

            @Override
            public void afterTextChanged(Editable editable) {

                if(!ieJmlBhnBaku1.getText().toString().equals("")){
                    f1 = String.valueOf(getHargaByName(spBahan1.getText().toString()));
                    int a = setHargaFix(ieJmlBhnBaku1.getText().toString(), f1);
                    if(!f1.equals("")){

                        txtHargaBhnBaku1.setText("Rp "+a);
                        sum = sumHargaBuket(
                                txtHargaBhnBaku1.getText().toString(),
                                txtHargaBhnBaku2.getText().toString(),
                                txtHargaBhnBaku3.getText().toString(),
                                txtHargaBhnBaku4.getText().toString(),
                                txtHargaBhnBaku5.getText().toString());
                        txtSumBahan.setText("Rp "+sum);
                    }
                }
            }
        });
        ieJmlBhnBaku2.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                if(!ieJmlBhnBaku2.getText().toString().equals("")){
                    f2 = String.valueOf(getHargaByName(spBahan2.getText().toString()));
                    int a = setHargaFix(ieJmlBhnBaku2.getText().toString(), f2);
                    if(!f2.equals("")){
                        txtHargaBhnBaku2.setText("Rp "+a);
                        sum = sumHargaBuket(
                                txtHargaBhnBaku1.getText().toString(),
                                txtHargaBhnBaku2.getText().toString(),
                                txtHargaBhnBaku3.getText().toString(),
                                txtHargaBhnBaku4.getText().toString(),
                                txtHargaBhnBaku5.getText().toString());
                        txtSumBahan.setText("Rp "+sum);
                    }
                }
            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                if(!ieJmlBhnBaku2.getText().toString().equals("")){
                    f2 = String.valueOf(getHargaByName(spBahan2.getText().toString()));
                    int a = setHargaFix(ieJmlBhnBaku2.getText().toString(), f2);
                    if(!f2.equals("")){

                        txtHargaBhnBaku2.setText("Rp "+a);
                        sum = sumHargaBuket(
                                txtHargaBhnBaku1.getText().toString(),
                                txtHargaBhnBaku2.getText().toString(),
                                txtHargaBhnBaku3.getText().toString(),
                                txtHargaBhnBaku4.getText().toString(),
                                txtHargaBhnBaku5.getText().toString());
                        txtSumBahan.setText("Rp "+sum);
                    }
                }
            }

            @Override
            public void afterTextChanged(Editable editable) {
                if(!ieJmlBhnBaku2.getText().toString().equals("")){
                    f2 = String.valueOf(getHargaByName(spBahan2.getText().toString()));
                    int a = setHargaFix(ieJmlBhnBaku2.getText().toString(), f2);
                    if(!f2.equals("")){

                        txtHargaBhnBaku2.setText("Rp "+a);
                        sum = sumHargaBuket(
                                txtHargaBhnBaku1.getText().toString(),
                                txtHargaBhnBaku2.getText().toString(),
                                txtHargaBhnBaku3.getText().toString(),
                                txtHargaBhnBaku4.getText().toString(),
                                txtHargaBhnBaku5.getText().toString());
                        txtSumBahan.setText("Rp "+sum);
                    }
                }
            }
        });
        ieJmlBhnBaku3.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                if(!ieJmlBhnBaku3.getText().toString().equals("")){
                    f3 = String.valueOf(getHargaByName(spBahan3.getText().toString()));
                    int a = setHargaFix(ieJmlBhnBaku3.getText().toString(), f3);
                    if(!f3.equals("")){

                        txtHargaBhnBaku3.setText("Rp "+a);
                        sum = sumHargaBuket(
                                txtHargaBhnBaku1.getText().toString(),
                                txtHargaBhnBaku2.getText().toString(),
                                txtHargaBhnBaku3.getText().toString(),
                                txtHargaBhnBaku4.getText().toString(),
                                txtHargaBhnBaku5.getText().toString());
                        txtSumBahan.setText("Rp "+sum);
                    }
                }
            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

                if(!ieJmlBhnBaku3.getText().toString().equals("")){
                    f3 = String.valueOf(getHargaByName(spBahan3.getText().toString()));
                    int a = setHargaFix(ieJmlBhnBaku3.getText().toString(), f3);
                    if(!f3.equals("")){

                        txtHargaBhnBaku3.setText("Rp "+a);
                        sum = sumHargaBuket(
                                txtHargaBhnBaku1.getText().toString(),
                                txtHargaBhnBaku2.getText().toString(),
                                txtHargaBhnBaku3.getText().toString(),
                                txtHargaBhnBaku4.getText().toString(),
                                txtHargaBhnBaku5.getText().toString());
                        txtSumBahan.setText("Rp "+sum);
                    }
                }
            }

            @Override
            public void afterTextChanged(Editable editable) {
                if(!ieJmlBhnBaku3.getText().toString().equals("")){
                    f3 = String.valueOf(getHargaByName(spBahan3.getText().toString()));
                    int a = setHargaFix(ieJmlBhnBaku3.getText().toString(), f3);
                    if(!f3.equals("")){
                        Log.d("f3",""+f3);

                        txtHargaBhnBaku3.setText("Rp "+a);
                        sum = sumHargaBuket(
                                txtHargaBhnBaku1.getText().toString(),
                                txtHargaBhnBaku2.getText().toString(),
                                txtHargaBhnBaku3.getText().toString(),
                                txtHargaBhnBaku4.getText().toString(),
                                txtHargaBhnBaku5.getText().toString());
                        txtSumBahan.setText("Rp "+sum);
                    }
                }
            }
        });
        ieJmlBhnBaku4.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                if(!ieJmlBhnBaku4.getText().toString().equals("")){
                    Log.d("f4atas",""+f4);
                    f4 = String.valueOf(getHargaByName(spBahan4.getText().toString()));
                    int a = setHargaFix(ieJmlBhnBaku4.getText().toString(), f4);
                    if(!f4.equals("")){
                        Log.d("f4w",""+f4);
                        txtHargaBhnBaku4.setText("Rp "+a);
                        sum = sumHargaBuket(
                                txtHargaBhnBaku1.getText().toString(),
                                txtHargaBhnBaku2.getText().toString(),
                                txtHargaBhnBaku3.getText().toString(),
                                txtHargaBhnBaku4.getText().toString(),
                                txtHargaBhnBaku5.getText().toString());
                        txtSumBahan.setText("Rp "+sum);
                    }
                }
            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

                if(!ieJmlBhnBaku4.getText().toString().equals("")){
                    f4 = String.valueOf(getHargaByName(spBahan4.getText().toString()));
                    int a = setHargaFix(ieJmlBhnBaku4.getText().toString(), f4);
                    if(!f4.equals("")){
                        Log.d("f4w",""+f4);
                        txtHargaBhnBaku4.setText("Rp "+a);
                        sum = sumHargaBuket(
                                txtHargaBhnBaku1.getText().toString(),
                                txtHargaBhnBaku2.getText().toString(),
                                txtHargaBhnBaku3.getText().toString(),
                                txtHargaBhnBaku4.getText().toString(),
                                txtHargaBhnBaku5.getText().toString());
                        txtSumBahan.setText("Rp "+sum);
                    }
                }
            }

            @Override
            public void afterTextChanged(Editable editable) {
                if(!ieJmlBhnBaku4.getText().toString().equals("")){
                    f4 = String.valueOf(getHargaByName(spBahan4.getText().toString()));
                    int a = setHargaFix(ieJmlBhnBaku4.getText().toString(), f4);
                    if(!f4.equals("")){
                        Log.d("f4w",""+f4);
                        txtHargaBhnBaku4.setText("Rp "+a);
                        sum = sumHargaBuket(
                                txtHargaBhnBaku1.getText().toString(),
                                txtHargaBhnBaku2.getText().toString(),
                                txtHargaBhnBaku3.getText().toString(),
                                txtHargaBhnBaku4.getText().toString(),
                                txtHargaBhnBaku5.getText().toString());
                        txtSumBahan.setText("Rp "+sum);
                    }
                }
            }
        });
        ieJmlBhnBaku5.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

                if(!ieJmlBhnBaku5.getText().toString().equals("")){
                    f5 = String.valueOf(getHargaByName(spBahan5.getText().toString()));
                    int a = setHargaFix(ieJmlBhnBaku5.getText().toString(), f5);
                    if(!f5.equals("")){
                        Log.d("f5",""+f5);

                        txtHargaBhnBaku5.setText("Rp "+a);
                        sum = sumHargaBuket(
                                txtHargaBhnBaku1.getText().toString(),
                                txtHargaBhnBaku2.getText().toString(),
                                txtHargaBhnBaku3.getText().toString(),
                                txtHargaBhnBaku4.getText().toString(),
                                txtHargaBhnBaku5.getText().toString());
                        txtSumBahan.setText("Rp "+sum);
                    }
                }
            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

                if(!ieJmlBhnBaku5.getText().toString().equals("")){
                    f5 = String.valueOf(getHargaByName(spBahan5.getText().toString()));
                    int a = setHargaFix(ieJmlBhnBaku5.getText().toString(), f5);
                    if(!f5.equals("")){
                        Log.d("f5",""+f5);

                        txtHargaBhnBaku5.setText("Rp "+a);
                        sum = sumHargaBuket(
                                txtHargaBhnBaku1.getText().toString(),
                                txtHargaBhnBaku2.getText().toString(),
                                txtHargaBhnBaku3.getText().toString(),
                                txtHargaBhnBaku4.getText().toString(),
                                txtHargaBhnBaku5.getText().toString());
                        txtSumBahan.setText("Rp "+sum);
                    }
                }
            }

            @Override
            public void afterTextChanged(Editable editable) {

                if(!ieJmlBhnBaku5.getText().toString().equals("")){
                    f5 = String.valueOf(getHargaByName(spBahan5.getText().toString()));
                    int a = setHargaFix(ieJmlBhnBaku5.getText().toString(), f5);
                    if(!f5.equals("")){
                        Log.d("f5",""+f5);

                        txtHargaBhnBaku5.setText("Rp "+a);
                        sum = sumHargaBuket(
                                txtHargaBhnBaku1.getText().toString(),
                                txtHargaBhnBaku2.getText().toString(),
                                txtHargaBhnBaku3.getText().toString(),
                                txtHargaBhnBaku4.getText().toString(),
                                txtHargaBhnBaku5.getText().toString());
                        txtSumBahan.setText("Rp "+sum);
                    }
                }
            }
        });




        btnSimpanBuketAdmin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                arrayValue.clear();
                jumlah.clear();
                harga.clear();

                arrayValue.add(spBahan1.getText().toString());
                arrayValue.add(spBahan2.getText().toString());
                arrayValue.add(spBahan3.getText().toString());
                arrayValue.add(spBahan4.getText().toString());
                arrayValue.add(spBahan5.getText().toString());
                if(ieJmlBhnBaku1.getText().toString().equals("")){
                    jumlah.add(0);
                }else{
                    jumlah.add(Integer.valueOf(ieJmlBhnBaku1.getText().toString()));
                }
                if(ieJmlBhnBaku2.getText().toString().equals("")){
                    jumlah.add(0);
                }else{
                    jumlah.add(Integer.valueOf(ieJmlBhnBaku2.getText().toString()));
                }
                if(ieJmlBhnBaku3.getText().toString().equals("")){
                    jumlah.add(0);
                }else{
                    jumlah.add(Integer.valueOf(ieJmlBhnBaku3.getText().toString()));
                }
                if(ieJmlBhnBaku4.getText().toString().equals("")){
                    jumlah.add(0);
                }else{
                    jumlah.add(Integer.valueOf(ieJmlBhnBaku4.getText().toString()));
                }
                if(ieJmlBhnBaku5.getText().toString().equals("")){
                    jumlah.add(0);
                }else{
                    jumlah.add(Integer.valueOf(ieJmlBhnBaku5.getText().toString()));
                }
                harga.add(txtHargaBhnBaku1.getText().toString());
                harga.add(txtHargaBhnBaku2.getText().toString());
                harga.add(txtHargaBhnBaku3.getText().toString());
                harga.add(txtHargaBhnBaku4.getText().toString());
                harga.add(txtHargaBhnBaku5.getText().toString());



                int b = Integer.valueOf(ieHargaBuket.getText().toString());
                if(b<sum){
                    Toast.makeText(EditBuketAdminActivity.this, "harga buket tidak boleh kurang dari modal", Toast.LENGTH_SHORT).show();
                }else{
                    if(b==sum){
                        Toast.makeText(EditBuketAdminActivity.this, "Masa gada untung?", Toast.LENGTH_SHORT).show();
                    }else{
                        updateBuketAdmin();
                        for(int i=0;i<arrayValue.size();i++){
                            if(!arrayValue.get(i).equals("") && !jumlah.get(i).equals(0) && !harga.get(i).toString().equals("")){
                                Log.d("nama bahan-baku",""+arrayValue.get(i).toString()+" ke "+i);
                                Log.d("jumlah bahan-baku",""+jumlah.get(i).toString()+" ke "+i);
                                Log.d("harga bahan-baku",""+harga.get(i).toString()+" ke "+i);
                                updateDetailBahanBaku(kodebuket,arrayValue.get(i).toString(),jumlah.get(i).toString(),harga.get(i).toString());
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
        Intent intent = new Intent(EditBuketAdminActivity.this, BuketAdminActivity.class);
        startActivity(intent);
    }
    public void getSpBahanBaku(){
        ApiInterface mApiInterface = ApiHelper.getClient().create(ApiInterface.class);
        Call<ListBahanBakuModel> call = mApiInterface.getListBahanBaku();
        call.enqueue(new Callback<ListBahanBakuModel>() {
            @Override
            public void onResponse(Call<ListBahanBakuModel> call, Response<ListBahanBakuModel> response) {
                List<ListBahanBaku> listBahanBakus = response.body().getListBahanBaku();
                spinnerBahanBakuList(listBahanBakus);
            }

            @Override
            public void onFailure(Call<ListBahanBakuModel> call, Throwable t) {

            }
        });

    }
    public int getHargaByName(String nama){
        ApiInterface mApiInterface = ApiHelper.getClient().create(ApiInterface.class);
        Call<ListBuketAdminModel> call = mApiInterface.showHarga(
                nama
        );
        call.enqueue(new Callback<ListBuketAdminModel>() {
            @Override
            public void onResponse(Call<ListBuketAdminModel> call, Response<ListBuketAdminModel> response) {
                if(response.isSuccessful()){
                    res = Integer.parseInt(response.body().getStatus());
                   //Log.d("rescekpoin",""+res);
                }else{
                    Toast.makeText(EditBuketAdminActivity.this, ""+getApplicationContext(), Toast.LENGTH_SHORT).show();
                }
            }
            @Override
            public void onFailure(Call<ListBuketAdminModel> call, Throwable t) {
                Toast.makeText(EditBuketAdminActivity.this, ""+getApplicationContext(), Toast.LENGTH_SHORT).show();
            }
        });
        return res;
    }

    private void spinnerBahanBakuList(List<ListBahanBaku> listBahanBakus){
        String[] items = new String[listBahanBakus.size()];
        list = listBahanBakus;
        //Traversing through the whole list to get all the names
        for(int i=0; i<listBahanBakus.size(); i++){
            //Storing names to string array
            items[i] = listBahanBakus.get(i).getNamaBahanBaku();
        }

        ArrayAdapter<String> adapter;
        adapter = new ArrayAdapter<String>(EditBuketAdminActivity.this, R.layout.spbahanbaku, items);
        //setting adapter to spinner
        spBahan1.setAdapter(adapter);
        spBahan1.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> parent, View view, int position, long rowId) {
                txtHargaBhnBaku1.setText("Rp "+setHarga(spBahan1.getText().toString(),list));

            }
        });
        spBahan2.setAdapter(adapter);
        spBahan2.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> parent, View view, int position, long rowId) {
                txtHargaBhnBaku2.setText("Rp "+setHarga(spBahan2.getText().toString(),list));

            }
        });
        spBahan3.setAdapter(adapter);
        spBahan3.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> parent, View view, int position, long rowId) {
                txtHargaBhnBaku3.setText("Rp "+setHarga(spBahan3.getText().toString(),list));

            }
        });
        spBahan4.setAdapter(adapter);
        spBahan4.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> parent, View view, int position, long rowId) {
                txtHargaBhnBaku4.setText("Rp "+setHarga(spBahan4.getText().toString(),list));

            }
        });
        spBahan5.setAdapter(adapter);
        spBahan5.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> parent, View view, int position, long rowId) {
                txtHargaBhnBaku5.setText("Rp "+setHarga(spBahan5.getText().toString(),list));
            }
        });
    }

    private String setHarga(String namaBahanBaku,List<ListBahanBaku> list){
        String hasil = "";
        for(int i = 0;i<list.size();i++){
            if(namaBahanBaku.equals(list.get(i).getNamaBahanBaku())){
                hasil = list.get(i).getHargaBahanBaku();

            }
        }
        return hasil;
    }
    public void getSpDetailBahan(){
        ApiInterface mApiInterface = ApiHelper.getClient().create(ApiInterface.class);
        Call<ListDetailBahanbakuModel> call = mApiInterface.showBahanBuket(kodebuket);

        call.enqueue(new Callback<ListDetailBahanbakuModel>() {
            @Override
            public void onResponse(Call<ListDetailBahanbakuModel> call, Response<ListDetailBahanbakuModel> response) {

                if(response.isSuccessful()){

                    List<ListDetailBahanBaku> list = response.body().getListDetailBahanbakus();
                    for(int i=0;i<list.size();i++){
                        if(i==0){
                            spBahan1.setText(list.get(i).getNamaDetailBahanBaku().toString());
                            ieJmlBhnBaku1.setText(list.get(i).getJumlahDetailBahanBaku().toString());
                            txtHargaBhnBaku1.setText(list.get(i).getHargaDetailBahanBaku().toString());
                        }else if(i==1){
                            spBahan2.setText(list.get(i).getNamaDetailBahanBaku().toString());
                            ieJmlBhnBaku2.setText(list.get(i).getJumlahDetailBahanBaku().toString());
                            txtHargaBhnBaku2.setText(list.get(i).getHargaDetailBahanBaku().toString());
                        }else if(i==2){
                            spBahan3.setText(list.get(i).getNamaDetailBahanBaku().toString());
                            ieJmlBhnBaku3.setText(list.get(i).getJumlahDetailBahanBaku().toString());
                            txtHargaBhnBaku3.setText(list.get(i).getHargaDetailBahanBaku().toString());
                        }else if(i==3){
                            spBahan4.setText(list.get(i).getNamaDetailBahanBaku().toString());
                            ieJmlBhnBaku4.setText(list.get(i).getJumlahDetailBahanBaku().toString());
                            txtHargaBhnBaku4.setText(list.get(i).getHargaDetailBahanBaku().toString());
                        }else if(i==4){
                            spBahan5.setText(list.get(i).getNamaDetailBahanBaku().toString());
                            ieJmlBhnBaku5.setText(list.get(i).getJumlahDetailBahanBaku().toString());
                            txtHargaBhnBaku5.setText(list.get(i).getHargaDetailBahanBaku().toString());
                        }

                    }

                }else{
                    Toast.makeText(EditBuketAdminActivity.this, ""+response.message(), Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<ListDetailBahanbakuModel> call, Throwable t) {

            }
        });

    }

    private void updateDetailBahanBaku(String kodeb, String nama, String jumlah, String harga){
        ApiInterface mApiInterface = ApiHelper.getClient().create(ApiInterface.class);
        Call<DetailBahanBakuModel> call = mApiInterface.updateDetail(kodeb,nama,jumlah,harga);
        call.enqueue(new Callback<DetailBahanBakuModel>() {
            @Override
            public void onResponse(Call<DetailBahanBakuModel> call, Response<DetailBahanBakuModel> response) {
                if(response.isSuccessful()){
                    Toast.makeText(getApplicationContext(), "Berhasil update detail", Toast.LENGTH_SHORT).show();
                }else{
                    Toast.makeText(getApplicationContext(), "Gagal menyimpan detail "+response.raw().toString(), Toast.LENGTH_SHORT).show();
                }
            }
            @Override
            public void onFailure(Call<DetailBahanBakuModel> call, Throwable t) {
                Toast.makeText(getApplicationContext(), "Ada masalah "+t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }
    private void updateBuketAdmin(){
        ApiInterface mApiInterface = ApiHelper.getClient().create(ApiInterface.class);
        Call<BuketAdminModel> call = mApiInterface.updateBuket(
                id,
                ieNamaBuket.getText().toString(),
                spJenisBuket.getText().toString(),
                ieHargaBuket.getText().toString());

        call.enqueue(new Callback<BuketAdminModel>() {
            @Override
            public void onResponse(Call<BuketAdminModel> call, Response<BuketAdminModel> response) {
                if(response.isSuccessful()){
                    Toast.makeText(EditBuketAdminActivity.this, "Berhasil mengedit buket admin", Toast.LENGTH_LONG).show();
                    Intent intent = new Intent(EditBuketAdminActivity.this, BuketAdminActivity.class);
                    startActivity(intent);
                }else{
                    Toast.makeText(getApplicationContext(), "Gagal mengedit buket admin", Toast.LENGTH_SHORT).show();
                }

            }

            @Override
            public void onFailure(Call<BuketAdminModel> call, Throwable t) {

            }
        });
    }



    private int setHargaFix(String a, String b){
        int hasil=0;
        String bx = b.replace("Rp ","");
        int temp1 = Integer.valueOf(a);
        int temp2 = Integer.valueOf(bx);
        hasil = temp1*temp2;
        return hasil;
    }

    private int sumHargaBuket(String a, String b, String c, String d, String e){
        int sum=0;
        String ax = a.replace("Rp ","");
        String bx = b.replace("Rp ","");
        String cx = c.replace("Rp ","");
        String dx = d.replace("Rp ","");
        String ex = e.replace("Rp ","");
        int temp1 = Integer.valueOf(ax);
        int temp2 = Integer.valueOf(bx);
        int temp3 = Integer.valueOf(cx);
        int temp4 = Integer.valueOf(dx);
        int temp5 = Integer.valueOf(ex);

        sum = temp1+temp2+temp3+temp4+temp5;
        return sum;
    }


}
