package com.practice.uiu.tablayoutsregisterpage;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.TextView;

import com.practice.uiu.tablayoutsregisterpage.database.UserDetails;

public class HomeAppActivity extends AppCompatActivity {

    TextView userNameTV,userPhoneTV;
    BottomNavigationView bottomNav;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_app);

        init();

        bottomNav.setOnNavigationItemSelectedListener(navListener);
        getSupportFragmentManager().beginTransaction().replace(R.id.fragmentContainer_main,
                new HomeFragment()).commit();

    }

    private void init() {


        bottomNav=findViewById(R.id.bottom_navigation);
    }

    private BottomNavigationView.OnNavigationItemSelectedListener navListener =
            new BottomNavigationView.OnNavigationItemSelectedListener() {
                @Override
                public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                    Fragment selectedFragment = null;

                    switch (item.getItemId()){

                        case  R.id.home_navbar:
                            selectedFragment=new HomeFragment();
                            break;
                        case R.id.friends_navbar:
                            selectedFragment = new FriendsFragment();
                            break;
                        case R.id.search_navbar:
                            selectedFragment =new SearchFragment(); break;
                    }
                    getSupportFragmentManager().beginTransaction().replace(R.id.fragmentContainer_main,selectedFragment)
                            .commit();

                    return true;
                }
            };
}
