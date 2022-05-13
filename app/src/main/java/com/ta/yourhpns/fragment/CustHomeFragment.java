package com.ta.yourhpns.fragment;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.AndroidViewModel;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.denzcoskun.imageslider.ImageSlider;
import com.denzcoskun.imageslider.models.SlideModel;
import com.ta.yourhpns.R;
import com.ta.yourhpns.adapter.BuketAdminAdapter;
import com.ta.yourhpns.adapter.BuketCustAdapter;
import com.ta.yourhpns.api.ApiHelper;
import com.ta.yourhpns.api.ApiInterface;
import com.ta.yourhpns.model.ListBuketAdmin;
import com.ta.yourhpns.model.ListBuketAdminModel;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link CustHomeFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class CustHomeFragment extends Fragment {
    ApiInterface mApiInterface;
    TextView tvCustWelcome;
    ImageSlider custSlider;
    private static final String SHARED_PREF_NAME = "mypref";
    private static final String KEY_USERNAME = "username";
    private static final String KEY_NAMA = "nama";
    String keyUser = "";
    String keyNama = "";
    private RecyclerView mRecyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public CustHomeFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment CustHomeFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static CustHomeFragment newInstance(String param1, String param2) {
        CustHomeFragment fragment = new CustHomeFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
       View view = inflater.inflate(R.layout.fragment_cust_home, container, false);

       SharedPreferences preferences = this.getActivity().getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE);
       keyUser = preferences.getString(KEY_USERNAME,null);
       keyNama = preferences.getString(KEY_NAMA,"");
       tvCustWelcome = view.findViewById(R.id.tvCustWelcome);
       tvCustWelcome.setText("Welcome, "+keyNama);
       custSlider = view.findViewById(R.id.custSlider);

       List<SlideModel> slideModels = new ArrayList<>();
       slideModels.add(new SlideModel(R.drawable.slide1));
       slideModels.add(new SlideModel(R.drawable.slide2));
       slideModels.add(new SlideModel(R.drawable.slide3));

       custSlider.setImageList(slideModels,true);

       mRecyclerView = view.findViewById(R.id.rvBuketCust);
       mApiInterface = ApiHelper.getClient().create(ApiInterface.class);
       mLayoutManager = new LinearLayoutManager(this.getActivity(), LinearLayoutManager.HORIZONTAL, false);
       mRecyclerView.setLayoutManager(mLayoutManager);

       showBuketCust();

       return view;
    }
    public void showBuketCust(){
        ApiInterface mApiInterface = ApiHelper.getClient().create(ApiInterface.class);
        Call<ListBuketAdminModel> call = mApiInterface.getListBuketAdmin();
        call.enqueue(new Callback<ListBuketAdminModel>() {
            @Override
            public void onResponse(Call<ListBuketAdminModel> call, Response<ListBuketAdminModel> response) {
                if(response.isSuccessful()){
                    List<ListBuketAdmin> listBuketCust = response.body().getListBuketAdmin();
                    mAdapter = new BuketCustAdapter(listBuketCust,getActivity().getApplicationContext());
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