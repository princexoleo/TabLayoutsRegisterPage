package com.practice.uiu.tablayoutsregisterpage;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.practice.uiu.tablayoutsregisterpage.database.DBHelper;
import com.practice.uiu.tablayoutsregisterpage.model.UserModel;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.Objects;

public class Login extends Fragment {

    private static final String TAG = "LoginFragment";
    private DBHelper database;
    private UserModel userModel;
    public SharedPreferences preferences;

    private EditText u_name, u_password;
    private Button login_button;

    @Nullable
    @Override
    public View onCreateView(@NonNull final LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        final View view=inflater.inflate(R.layout.login, container, false);
        u_name=view.findViewById(R.id.username_et_id);
        u_password = view.findViewById(R.id.userpass_et_id);
        login_button = view.findViewById(R.id.login_button_id);

        login_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!u_name.equals("") && !u_password.equals(""))
                {
                    database=new DBHelper(getContext());

                    ArrayList<UserModel>userArr =database.getAllUsers();
                    String userpassword=u_password.getText().toString();

                      if(userpassword.equals(database.getSinlgeEntry(u_name.getText().toString())))
                    {
                        Toast.makeText(getContext(), "Login succesfull", Toast.LENGTH_SHORT).show();
                        preferences=getContext().getSharedPreferences("userinfo", Context.MODE_PRIVATE);

                        SharedPreferences.Editor editor=preferences.edit();
                        editor.putString("username",u_name.getText().toString());
                        editor.putString("password",u_password.getText().toString());
                        editor.apply();

                        moveToNewActivity();

//                        Intent intent=new Intent(getContext().getApplicationContext(),AppHome.class);
//                        intent.putExtra("name",u_name.getText().toString());
//                        //intent.putExtra("phone",);
//                        startActivity(intent);

                       }
                    }
                    else{
                        Toast.makeText(getContext(), "Login failed", Toast.LENGTH_SHORT).show();
                    }

//                    if(checkData(userArr,u_name.getText().toString(),"name") && checkData(userArr,u_password.getText().toString(),"pass"))
//                    {
//                        //found in database login Succesfully
//                    }
//                    else {
//                        //Invalid password or username
//
//                    }
                }
        });
        return view;
    }
    private void moveToNewActivity() {
        Intent i = new Intent(getActivity(), HomeAppActivity.class);
        startActivity(i);
        ((Activity) Objects.requireNonNull(getActivity())).overridePendingTransition(0,0);

    }

    private boolean checkData(ArrayList<UserModel> userArr, String s, String data) {
        boolean found= false;
        Iterator<UserModel> iterator= userArr.iterator();
        UserModel tempUser;
        int pos =0;

        while (iterator.hasNext())
        {
            pos=pos+1;
            tempUser=iterator.next();
             if (s.equals("name"))
             {
                 if (data.equals(tempUser.getName()))
                 {
                     found=true;
                     break;
                 }
             }
             if (s.equals("pass"))
             {
                 if (data.equals(tempUser.getPassword()))
                 {
                     found=true;
                     break;
                 }
             }
        }

        return false;
    }

}
