package com.dinus.consignment;

import com.google.firebase.database.Exclude;

public class History {
    String title, key;
    Integer logo , harga, total;

    public History(){

    }

    public String getTitle1(){
        return title;
    }

    public void setTitle1(String title){
        this.title = title;
    }

    public Integer getHarga1(){
        return harga;
    }

    public void setHarga1(int harga){
        this.harga = harga;
    }

    public Integer getLogo1(){
        return logo;
    }

    public void setLogo1(int logo){
        this.logo = logo;
    }

    public Integer getTotal1(){
        return total;
    }

    public void setTotal1(int total){
        this.total = total;
    }

    @Exclude
    public String getKey1(){
        return key;
    }

    @Exclude
    public void setKey1(String key){
        this.key = key;
    }
}
