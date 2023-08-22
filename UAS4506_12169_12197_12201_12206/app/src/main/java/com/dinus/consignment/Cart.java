package com.dinus.consignment;


import com.google.firebase.database.Exclude;

public class Cart {
    String title, key;
    Integer logo , harga, total;

    public Cart(){

    }

    public String getTitle(){
        return title;
    }

    public void setTitle(String title){
        this.title = title;
    }

    public Integer getHarga(){
        return harga;
    }

    public void setHarga(int harga){
        this.harga = harga;
    }

    public Integer getLogo(){
        return logo;
    }

    public void setLogo(int logo){
        this.logo = logo;
    }

    public Integer getTotal(){
        return total;
    }

    public void setTotal(int total){
        this.total = total;
    }

    @Exclude
    public String getKey(){
        return key;
    }

    @Exclude
    public void setKey(String key){
        this.key = key;
    }


}
