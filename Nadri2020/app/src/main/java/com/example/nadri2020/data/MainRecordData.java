package com.example.nadri2020.data;

public class MainRecordData {

    private String dramaName;
    private int image;

    public MainRecordData(String dramaName, int image) {
        this.dramaName = dramaName;
        this.image = image;
    }


    public int getImage() { return image; }

    public String getName() { return dramaName; }

    public void setImage(int image) { this.image = image; }

    public void setName(String dramaName) { this.dramaName = dramaName; }

}
