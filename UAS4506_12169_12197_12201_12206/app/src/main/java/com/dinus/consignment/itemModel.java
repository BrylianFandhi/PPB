package com.dinus.consignment;

public class itemModel {

    public int getName;
    String name;
    int price;
    String type;
    int image;
    String desc;

    public int getGetName() {
        return getName;
    }

    public void setGetName(int getName) {
        this.getName = getName;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getPrice() {
        return price;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public int getImage() {
        return image;
    }

    public void setImage(int image) {
        this.image = image;
    }



    public itemModel(String name, int price, String type, int image, String desc) {
        this.name = name;
        this.price = price;
        this.type = type;
        this.image = image;
        this.desc = desc;
    }


}
