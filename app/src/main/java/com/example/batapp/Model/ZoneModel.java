package com.example.batapp.Model;

public class ZoneModel {
    private String user;
    private String adress;
    private String statu;
    private String date;
    private String id;


    public ZoneModel(String id,String user, String adress, String statu, String date) {
        this.id = id;
        this.user = user;
        this.adress = adress;
        this.statu = statu;
        this.date = date;
    }


    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getAdress() {
        return adress;
    }

    public void setAdress(String adress) {
        this.adress = adress;
    }

    public String getStatu() {
        return statu;
    }

    public void setStatu(String statu) {
        this.statu = statu;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }
}
