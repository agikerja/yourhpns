package com.ta.yourhpns;

import android.os.Bundle;
import android.view.MenuItem;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.ta.yourhpns.fragment.AdminHomeFragment;
import com.ta.yourhpns.fragment.CustHomeFragment;
import com.ta.yourhpns.fragment.CustProfileFragment;
import com.ta.yourhpns.fragment.CustTransactionFragment;

public class CustActivity extends AppCompatActivity {
    BottomNavigationView navigationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_customer);
        navigationView = findViewById(R.id.bottombarcust);
        getSupportFragmentManager().beginTransaction().replace(R.id.bodyappcust,new CustHomeFragment()).commit();

        navigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {

                Fragment fragment = null;
                switch (item.getItemId()){
                    case R.id.nav_home_cust:
                        fragment = new CustHomeFragment();
                        break;
                    case R.id.nav_transaction_cust:
                        fragment = new CustTransactionFragment();
                        break;
                    case R.id.nav_user_cust:
                        fragment = new CustProfileFragment();
                        break;
                }
                getSupportFragmentManager().beginTransaction().replace(R.id.bodyappcust,fragment).commit();
                return true;
            }
        });


    }
}
