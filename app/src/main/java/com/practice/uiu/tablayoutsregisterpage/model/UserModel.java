package com.practice.uiu.tablayoutsregisterpage.model;

public class UserModel  {
    private  String id;
    private   String name;
    private  String password;
    private  String phone;



    public UserModel(String name, String pass, String phone) {
        this.name = name;
        this.password = pass;
        this.phone = phone;
    }

    public UserModel(String id, String name, String password, String phone) {
        this.id = id;
        this.name = name;
        this.password = password;
        this.phone = phone;
    }
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }
}
