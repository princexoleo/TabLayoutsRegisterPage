package com.practice.uiu.tablayoutsregisterpage;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import com.practice.uiu.tablayoutsregisterpage.database.UserDetails;

public class HomeAppActivity extends AppCompatActivity {

    TextView userNameTV,userPhoneTV;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_app);

        init();


        userNameTV.setText(UserDetails.name);
        userPhoneTV.setText(UserDetails.phone);
    }

    private void init() {

        userNameTV=findViewById(R.id.name_tv_id);
        userPhoneTV= findViewById(R.id.phone_tv_id);
    }
}
