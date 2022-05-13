package com.ta.yourhpns;

import android.content.Intent;
import android.media.Image;
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
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatSpinner;
import androidx.recyclerview.widget.RecyclerView;

import com.ta.yourhpns.adapter.BahanBakuAdapter;
import com.ta.yourhpns.api.ApiHelper;
import com.ta.yourhpns.api.ApiInterface;
import com.ta.yourhpns.model.BahanBakuModel;
import com.ta.yourhpns.model.BuketAdminModel;
import com.ta.yourhpns.model.DetailBahanBakuModel;
import com.ta.yourhpns.model.ListBahanBaku;
import com.ta.yourhpns.model.ListBahanBakuModel;
import com.ta.yourhpns.model.ListBuketAdmin;
import com.ta.yourhpns.model.ListDetailBahanBaku;

import org.w3c.dom.Text;

import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class TambahBuketAdminActivity extends AppCompatActivity {
    List<ListBahanBaku> list;
    List<ListDetailBahanBaku> listDetail;
    ApiInterface mApiInterface;
    ActionBar actionBar;
    String y1 = "";
    String y2 = "";
    String y3 = "";
    String y4 = "";
    String y5 = "";
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
        setContentView(R.layout.activity_tambah_buket_admin);
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

        ArrayAdapter<CharSequence> adapterJenis = ArrayAdapter.createFromResource(getApplicationContext(), R.array.jenisbuket, R.layout.spjabatan);
        adapterJenis.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spJenisBuket.setAdapter(adapterJenis);

        ieJmlBhnBaku1.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                if(!ieJmlBhnBaku1.getText().toString().equals("")){
                    if(!y1.equals("")){
                        String xy = y1;
                        Log.d("y",""+spBahan1.getText().toString());
                        int a = setHargaFix(ieJmlBhnBaku1.getText().toString(), xy);
                        Log.d("Sp1",""+spBahan1.getText().toString());
                        Log.d("hasil",""+a);
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

            }
        });
        ieJmlBhnBaku2.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                if(!ieJmlBhnBaku2.getText().toString().equals("")){
                    if(!y2.equals("")){
                        String xy = y2;
                        Log.d("y",""+spBahan2.getText().toString());
                        int a = setHargaFix(ieJmlBhnBaku2.getText().toString(), xy);
                        Log.d("Sp1",""+spBahan2.getText().toString());
                        Log.d("hasil",""+a);
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

            }
        });
        ieJmlBhnBaku3.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                if(!ieJmlBhnBaku3.getText().toString().equals("")){
                    if(!y3.equals("")){
                        String xy = y3;
                        Log.d("y",""+spBahan3.getText().toString());
                        int a = setHargaFix(ieJmlBhnBaku3.getText().toString(), xy);
                        Log.d("Sp1",""+spBahan3.getText().toString());
                        Log.d("hasil",""+a);
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

            }
        });
        ieJmlBhnBaku4.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                if(!ieJmlBhnBaku4.getText().toString().equals("")){
                    if(!y4.equals("")){
                        String xy = y4;
                        Log.d("y",""+spBahan4.getText().toString());
                        int a = setHargaFix(ieJmlBhnBaku4.getText().toString(), xy);
                        Log.d("Sp1",""+spBahan4.getText().toString());
                        Log.d("hasil",""+a);
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

            }
        });
        ieJmlBhnBaku5.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                if(!ieJmlBhnBaku5.getText().toString().equals("")){
                    if(!y5.equals("")){
                        String xy = y5;
                        Log.d("y",""+spBahan5.getText().toString());
                        int a = setHargaFix(ieJmlBhnBaku5.getText().toString(), xy);
                        Log.d("Sp1",""+spBahan5.getText().toString());
                        Log.d("hasil",""+a);
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

            }
        });


        mViewGroup = findViewById(R.id.viewcontainer);
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyyMMddHHmmss");
        LocalDateTime now = LocalDateTime.now();
        Log.d("Kode Unik","bucketxx"+dtf.format(now));
        getSpBahanBaku();
        ArrayAdapter<String> bahanBakuAdapter = new ArrayAdapter<>(TambahBuketAdminActivity.this, android.R.layout.simple_spinner_dropdown_item);
        txtShowhidebahanbaku.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (viewGroupIsVisible) {
                    mViewGroup.setVisibility(View.GONE);
                    txtShowhidebahanbaku.setText("+ Bahan Baku");
                } else {
                    mViewGroup.setVisibility(View.VISIBLE);
                    txtShowhidebahanbaku.setText("- Bahan Baku");
                }
                viewGroupIsVisible = !viewGroupIsVisible;
            }
        });



        btnSimpanBuketAdmin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                arrayValue.clear();
                jumlah.clear();
                harga.clear();
                String timeStamp = new SimpleDateFormat("yyyyMMddHHmmss").format(Calendar.getInstance().getTime());
                String kodebuket = ieNamaBuket.getText().toString()+""+timeStamp;
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
                    Toast.makeText(TambahBuketAdminActivity.this, "harga buket tidak boleh kurang dari modal", Toast.LENGTH_SHORT).show();
                }else{
                    if(b==sum){
                        Toast.makeText(TambahBuketAdminActivity.this, "Masa gada untung?", Toast.LENGTH_SHORT).show();
                    }else{
                        simpanBuketBaru(kodebuket);
                        for(int i=0;i<arrayValue.size();i++){
                            if(!arrayValue.get(i).equals("") && !jumlah.get(i).equals(0) && !harga.get(i).toString().equals("")){
                                simpanBahanBaku(arrayValue.get(i).toString(),jumlah.get(i).toString(),harga.get(i).toString(),kodebuket.toString());

                            }
                        }
                    }
                }
            }
        });

        btnBatalBuketAdmin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(TambahBuketAdminActivity.this, BuketAdminActivity.class);
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
        Intent intent = new Intent(TambahBuketAdminActivity.this, BuketAdminActivity.class);
        startActivity(intent);
    }

    public void simpanBahanBaku(String nama, String jumlah, String harga, String kode){
        ApiInterface mApiInterface = ApiHelper.getClient().create(ApiInterface.class);
        Call<DetailBahanBakuModel> call = mApiInterface.simpanDetailBahanBaku(nama,jumlah,harga,kode);
        call.enqueue(new Callback<DetailBahanBakuModel>() {
            @Override
            public void onResponse(Call<DetailBahanBakuModel> call, Response<DetailBahanBakuModel> response) {
                if(response.isSuccessful()){

                    Toast.makeText(getApplicationContext(), "Berhasil", Toast.LENGTH_SHORT).show();
                }else{
                    Toast.makeText(getApplicationContext(), "Gagal menyimpan bahanbaku "+response.raw().toString(), Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<DetailBahanBakuModel> call, Throwable t) {
                Toast.makeText(getApplicationContext(), "Ada masalah "+t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    public void simpanBuketBaru(String kodebuket){
        ApiInterface mApiInterface = ApiHelper.getClient().create(ApiInterface.class);
        Call<BuketAdminModel> call = mApiInterface.simpanBuket(
                ieNamaBuket.getText().toString(),
                spJenisBuket.getText().toString(),
                ieHargaBuket.getText().toString(),
                kodebuket);
        call.enqueue(new Callback<BuketAdminModel>() {
            @Override
            public void onResponse(Call<BuketAdminModel> call, Response<BuketAdminModel> response) {
                if(response.isSuccessful()){
                    Toast.makeText(getApplicationContext(), "Berhasil menyimpan buket", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(TambahBuketAdminActivity.this, BuketAdminActivity.class);
                    startActivity(intent);
                }else{
                    Toast.makeText(getApplicationContext(), "Gagal menyimpan buket", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<BuketAdminModel> call, Throwable t) {
                Toast.makeText(getApplicationContext(), "Ada masalah", Toast.LENGTH_SHORT).show();
            }
        });
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
    private void spinnerBahanBakuList(List<ListBahanBaku> listBahanBakus){
        String[] items = new String[listBahanBakus.size()];
        list = listBahanBakus;
        //Traversing through the whole list to get all the names
        for(int i=0; i<listBahanBakus.size(); i++){
            //Storing names to string array
            items[i] = listBahanBakus.get(i).getNamaBahanBaku();
        }

        ArrayAdapter<String> adapter;
        adapter = new ArrayAdapter<String>(TambahBuketAdminActivity.this, R.layout.spbahanbaku, items);
        //setting adapter to spinner
        spBahan1.setAdapter(adapter);
        spBahan1.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> parent, View view, int position, long rowId) {
                txtHargaBhnBaku1.setText("Rp "+setHarga(spBahan1.getText().toString(),list));
                y1=txtHargaBhnBaku1.getText().toString();
            }
        });
        spBahan2.setAdapter(adapter);
        spBahan2.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> parent, View view, int position, long rowId) {
                txtHargaBhnBaku2.setText("Rp "+setHarga(spBahan2.getText().toString(),list));
                y2=txtHargaBhnBaku2.getText().toString();
            }
        });
        spBahan3.setAdapter(adapter);
        spBahan3.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> parent, View view, int position, long rowId) {
                txtHargaBhnBaku3.setText("Rp "+setHarga(spBahan3.getText().toString(),list));
                y3=txtHargaBhnBaku3.getText().toString();
            }
        });
        spBahan4.setAdapter(adapter);
        spBahan4.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> parent, View view, int position, long rowId) {
                txtHargaBhnBaku4.setText("Rp "+setHarga(spBahan4.getText().toString(),list));
                y4=txtHargaBhnBaku4.getText().toString();
            }
        });
        spBahan5.setAdapter(adapter);
        spBahan5.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> parent, View view, int position, long rowId) {
                txtHargaBhnBaku5.setText("Rp "+setHarga(spBahan5.getText().toString(),list));
                y5=txtHargaBhnBaku5.getText().toString();
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
