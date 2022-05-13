package com.ta.yourhpns.fragment;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.media.Image;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.denzcoskun.imageslider.ImageSlider;
import com.ta.yourhpns.AdminActivity;
import com.ta.yourhpns.BahanBakuActivity;
import com.ta.yourhpns.EditBahanBakuActivity;
import com.ta.yourhpns.R;
import com.ta.yourhpns.api.ApiHelper;
import com.ta.yourhpns.api.ApiInterface;
import com.ta.yourhpns.model.BahanBakuModel;
import com.ta.yourhpns.model.ListBahanBaku;
import com.ta.yourhpns.model.ListBahanBakuModel;
import com.ta.yourhpns.model.ListUserProfile;
import com.ta.yourhpns.model.ListUserProfileModel;
import com.ta.yourhpns.model.UserProfileModel;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link AdminUserFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class AdminUserFragment extends Fragment {
    ApiInterface mApiInterface;
    String iduser = "";
    String namauser = "";
    EditText ieNama, ieUsername, ieEmail;
    Button btnSimpanUser;
    TextView tvLupaPassword;

    SharedPreferences sharedPreferences;
    private static final String SHARED_PREF_NAME = "mypref";
    private static final String KEY_ID = "id";
    private static final String KEY_NAMA = "nama";
    private static final String KEY_USERNAME = "username";
    private static final String KEY_ROLE = "role";
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public AdminUserFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment AdminUserFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static AdminUserFragment newInstance(String param1, String param2) {
        AdminUserFragment fragment = new AdminUserFragment();
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

        View view = inflater.inflate(R.layout.fragment_admin_user, container, false);

        ieNama = view.findViewById(R.id.ieNama);
        ieUsername = view.findViewById(R.id.ieUsername);
        ieEmail = view.findViewById(R.id.ieEmail);
        tvLupaPassword = view.findViewById(R.id.tvLupaPassword);
        btnSimpanUser = view.findViewById(R.id.btnSimpanUser);
        sharedPreferences = this.getActivity().getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE);

        iduser = sharedPreferences.getString(KEY_ID,null);
        namauser = sharedPreferences.getString(KEY_NAMA,null);
        showProfile();

        btnSimpanUser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(ieNama.getText().toString().isEmpty()){
                    Toast.makeText(getActivity().getApplicationContext(), "Nama tidak boleh kosong", Toast.LENGTH_SHORT).show();
                }else{
                    if(ieUsername.getText().toString().isEmpty()){
                        Toast.makeText(getActivity().getApplicationContext(), "Username tidak boleh kosong", Toast.LENGTH_SHORT).show();
                    }else{
                        if(ieEmail.getText().toString().isEmpty()){
                            Toast.makeText(getActivity().getApplicationContext(), "Email tidak boleh kosong", Toast.LENGTH_SHORT).show();
                        }else{
                            updateProfile();
                        }
                    }
                }
            }
        });





        // Inflate the layout for this fragment
        return view;


    }
    public void showProfile(){
        ApiInterface mApiInterface = ApiHelper.getClient().create(ApiInterface.class);
        Call<ListUserProfileModel> call = mApiInterface.showUserProfile(iduser);
        call.enqueue(new Callback<ListUserProfileModel>() {
            @Override
            public void onResponse(Call<ListUserProfileModel> call, Response<ListUserProfileModel> response) {
                if (response.isSuccessful()){

                    List<ListUserProfile> list = response.body().getListUserProfile();
                    String nama = list.get(0).getNama().toString();
                    ieNama.setText(nama);
                    String username = list.get(0).getUsername().toString();
                    ieUsername.setText(username);
                    String email = list.get(0).getEmail().toString();
                    ieEmail.setText(email);

                }else{
                    Toast.makeText(getActivity(), ""+response.message(), Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<ListUserProfileModel> call, Throwable t) {

            }
        });

    }

    public void updateProfile(){
        ApiInterface mApiInterface = ApiHelper.getClient().create(ApiInterface.class);
        Call<UserProfileModel> call = mApiInterface.updateUserProfile(
                iduser,
                ieNama.getText().toString(),
                ieUsername.getText().toString(),
                ieEmail.getText().toString());

        call.enqueue(new Callback<UserProfileModel>() {
            @Override
            public void onResponse(Call<UserProfileModel> call, Response<UserProfileModel> response) {
                if(response.isSuccessful()){
                    Toast.makeText(getActivity(), "Berhasil update profile", Toast.LENGTH_LONG).show();
                    SharedPreferences.Editor editor = sharedPreferences.edit();
                    editor.putString(KEY_NAMA,ieNama.getText().toString());
                    editor.apply();
                    Intent intent = new Intent(getActivity(), AdminActivity.class);
                    startActivity(intent);
                }else{
                    Toast.makeText(getActivity().getApplicationContext(), "Gagal update profile", Toast.LENGTH_SHORT).show();
                }

            }

            @Override
            public void onFailure(Call<UserProfileModel> call, Throwable t) {

            }
        });
    }
}