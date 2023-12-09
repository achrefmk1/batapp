package com.example.batapp.Model;

public class UserModel {
    private String id;
    private String user;
    private String email;
    private String phone;


    public UserModel(String id, String user, String email, String phone) {
        this.id = id;
        this.user = user;
        this.email = email;
        this.phone = phone;

    }

    public UserModel() {

    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }


}
