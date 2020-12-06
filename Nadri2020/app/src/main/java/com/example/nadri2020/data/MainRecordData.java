package com.example.nadri2020.data;

public class MainRecordData {

    private String dramaName;
    private String image;

    public MainRecordData(String dramaName, String image) {
        this.dramaName = dramaName;
        this.image = image;
    }


    public String getImage() { return image; }

    public String getName() { return dramaName; }

    public void setImage(String image) { this.image = image; }

    public void setName(String proName) { this.dramaName = dramaName; }

}
