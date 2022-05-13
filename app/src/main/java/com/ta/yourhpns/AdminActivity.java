package com.ta.yourhpns;

import android.os.Bundle;
import android.view.MenuItem;
import android.view.Window;
import android.view.WindowManager;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.ta.yourhpns.fragment.AdminHistoryFragment;
import com.ta.yourhpns.fragment.AdminHomeFragment;
import com.ta.yourhpns.fragment.AdminUserFragment;

public class AdminActivity extends AppCompatActivity {

    BottomNavigationView navigationView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin);
        navigationView = findViewById(R.id.bottombar);
        getSupportFragmentManager().beginTransaction().replace(R.id.bodyapp,new AdminHomeFragment()).commit();
        navigationView.setSelectedItemId(R.id.nav_home);

        navigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                Fragment fragment = null;
                switch (item.getItemId()){
                    case R.id.nav_home:
                        fragment = new AdminHomeFragment();
                        break;
                    case R.id.nav_history:
                        fragment = new AdminHistoryFragment();
                        break;
                    case R.id.nav_user:
                        fragment = new AdminUserFragment();
                        break;
                }
                getSupportFragmentManager().beginTransaction().replace(R.id.bodyapp,fragment).commit();

                return true;
            }
        });


    }
}
