package com.dinus.consignment;

public class model_team {
    private String name;
    private String type;
    private int image;

    public model_team(String name, String type, int image) {
        this.name = name;
        this.type = type;
        this.image = image;
    }

    public String getName() {
        return name;
    }

    public String getType() {
        return type;
    }

    public int getImage() {
        return image;
    }
}
