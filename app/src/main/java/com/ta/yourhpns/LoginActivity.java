package com.ta.yourhpns;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.ta.yourhpns.api.ApiHelper;
import com.ta.yourhpns.api.ApiInterface;
import com.ta.yourhpns.model.LoginModel;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginActivity extends AppCompatActivity {
    ApiInterface mApiInterface;
    EditText ieUsername, iePassword;
    TextView tbRegister;
    Button btnLogin;
    SharedPreferences sharedPreferences;
    private static final String SHARED_PREF_NAME = "mypref";
    private static final String KEY_ID = "id";
    private static final String KEY_USERNAME = "username";
    private static final String KEY_NAMA = "nama";
    private static final String KEY_ROLE = "role";
    String iduser = "";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        ieUsername = findViewById(R.id.ieUsername);
        iePassword = findViewById(R.id.iePassword);
        btnLogin = findViewById(R.id.btnLogin);
        tbRegister = findViewById(R.id.tbRegister);
        sharedPreferences = getSharedPreferences(SHARED_PREF_NAME,MODE_PRIVATE);

        mApiInterface = ApiHelper.getClient().create(ApiInterface.class);
        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Login();
            }
        });

        tbRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(LoginActivity.this, RegisterActivity.class);
                startActivity(intent);
            }
        });
    }
    public void Login(){
        ApiInterface mApiInterface = ApiHelper.getClient().create(ApiInterface.class);
        Call<LoginModel> call = mApiInterface.login(
            ieUsername.getText().toString(),
            iePassword.getText().toString());
        call.enqueue(new Callback<LoginModel>() {
            @Override
            public void onResponse(Call<LoginModel> call, Response<LoginModel> response) {
                if(response.isSuccessful()){
                    SharedPreferences.Editor editor = sharedPreferences.edit();
                    editor.putString(KEY_ID,response.body().getResult().get(0).getId().toString());
                    editor.putString(KEY_NAMA,response.body().getResult().get(0).getNama().toString());
                    editor.putString(KEY_USERNAME,ieUsername.getText().toString());
                    editor.putString(KEY_ROLE,response.body().getResult().get(0).getRole().toString());
                    editor.apply();
                    String role = response.body().getResult().get(0).getRole();
                    if (role.equals("4")) {
                        Intent intent = new Intent(LoginActivity.this, CustActivity.class);
                        startActivity(intent);
                    }else{
                        Intent intent = new Intent(LoginActivity.this, AdminActivity.class);
                        startActivity(intent);
                    }

                }else{
                    Log.d("RETRO", "ON FAIL : " + response.message());
                }

            }

            @Override
            public void onFailure(Call<LoginModel> call, Throwable t) {
                Log.d("RETRO", "ON FAIL : " + t.getMessage());
                Toast.makeText(getApplicationContext(), "Gagal update user", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
