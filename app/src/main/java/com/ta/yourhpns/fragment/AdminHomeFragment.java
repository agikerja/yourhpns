package com.ta.yourhpns.fragment;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.ta.yourhpns.BahanBakuActivity;
import com.ta.yourhpns.BarangActivity;
import com.ta.yourhpns.BuketAdminActivity;
import com.ta.yourhpns.R;
import com.ta.yourhpns.TambahBuketAdminActivity;
import com.ta.yourhpns.UserMgrActivity;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link AdminHomeFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class AdminHomeFragment extends Fragment {
    CardView cvKeBarang, cvKeBuketAdmin, cvKeUserMgr;
    TextView tvWelcome;
    private static final String SHARED_PREF_NAME = "mypref";
    private static final String KEY_USERNAME = "username";
    private static final String KEY_NAMA = "nama";
    String keyUser = "";
    String keyNama = "";
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public AdminHomeFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment AdminHomeFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static AdminHomeFragment newInstance(String param1, String param2) {
        AdminHomeFragment fragment = new AdminHomeFragment();
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

        View view = inflater.inflate(R.layout.fragment_admin_home, container, false);

        cvKeBarang = view.findViewById(R.id.cvKeBarang);
        cvKeBuketAdmin = view.findViewById(R.id.cvKeBuketAdmin);
        cvKeUserMgr = view.findViewById(R.id.cvKeUserMgr);
        tvWelcome = view.findViewById(R.id.tvWelcome);
        SharedPreferences preferences = this.getActivity().getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE);
        keyUser = preferences.getString(KEY_USERNAME,null);
        keyNama = preferences.getString(KEY_NAMA,"");
        tvWelcome.setText("Selamat Datang, "+keyNama);

        cvKeBarang.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(getActivity(), BahanBakuActivity.class);
                startActivity(intent);
            }
        });
        cvKeBuketAdmin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), BuketAdminActivity.class);
                startActivity(intent);
            }
        });
        cvKeUserMgr.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), UserMgrActivity.class);
                startActivity(intent);
            }
        });

        return view;
    }
}