package com.example.nadri2020;

import android.net.Uri;

public class MainRecordItem {

    //제목 이미지 item내용
    String title;
    Uri image;

    //생성자
    public MainRecordItem(String title, Uri image) {
        this.title = title;
        this.image = image;
    }


    public Uri getimage(){
        return image;
    }

    public String getitle(){
        return title;
    }

    public void setImage(Uri image){
        this.image = image;
    }

    public void setName(String title) {
        this.title = title;
    }


}
