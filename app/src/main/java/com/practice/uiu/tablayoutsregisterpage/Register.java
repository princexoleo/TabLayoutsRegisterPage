package com.practice.uiu.tablayoutsregisterpage;

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

public class Register extends Fragment implements IMainInterface{
    private static final String TAG = "LoginFragment";
    private DBHelper database;


    private EditText new_name, new_password,new_phone;
    private Button register_button;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.register,container,false);

        database=new DBHelper(getContext());

        new_name =view.findViewById(R.id.new_name_et_id);
        new_password =view.findViewById(R.id.new_pass_et_id);
        new_phone =view.findViewById(R.id.new_phone_et_id);
        register_button =view.findViewById(R.id.register_button_id);

        register_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name=new_name.getText().toString();
                String pass=new_password.getText().toString();
                String phone=new_phone.getText().toString();

               if(!name.equals("") && !pass.equals("") && !phone.equals(""))
               {
                  UserModel tempUser=new UserModel(name,pass,phone);
                  if(database.addUserInfo(tempUser))
                  {
                      Toast.makeText(getContext(), "Register successfully !", Toast.LENGTH_SHORT).show();
                  }
                  else{
                      Toast.makeText(getContext(), "Register failed !!", Toast.LENGTH_SHORT).show();
                  }

               }
            }
        });

        return view;
    }


}
