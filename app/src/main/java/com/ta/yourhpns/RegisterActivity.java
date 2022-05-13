package com.ta.yourhpns;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.ta.yourhpns.api.ApiHelper;
import com.ta.yourhpns.api.ApiInterface;
import com.ta.yourhpns.model.RegisterModel;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RegisterActivity extends AppCompatActivity {

    ApiInterface mApiInterface;
    EditText ieNama, ieUsername, ieEmail, iePassword;
    Button btnRegister;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        ieNama = findViewById(R.id.ieNama);
        ieUsername = findViewById(R.id.ieUsername);
        ieEmail = findViewById(R.id.ieEmail);
        iePassword = findViewById(R.id.iePassword);
        btnRegister = findViewById(R.id.btnRegister);

        mApiInterface = ApiHelper.getClient().create(ApiInterface.class);
        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {register(); }
        });
    }
    public void register(){
        ApiInterface mApiInterface = ApiHelper.getClient().create(ApiInterface.class);
        Call<RegisterModel> call = mApiInterface.register(
                ieNama.getText().toString(),
                ieUsername.getText().toString(),
                ieEmail.getText().toString(),
                iePassword.getText().toString());
        call.enqueue(new Callback<RegisterModel>() {
            @Override
            public void onResponse(Call<RegisterModel> call, Response<RegisterModel> response) {
                if(response.isSuccessful()) {
                    Intent intent = new Intent(RegisterActivity.this, LoginActivity.class);
                    startActivity(intent);
                }
                else {
                    Toast.makeText(getApplicationContext(), "Gagal registrasi user", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<RegisterModel> call, Throwable t) {
                Toast.makeText(getApplicationContext(), "Gagal registrasi user", Toast.LENGTH_SHORT).show();
            }
        });


    }

}
