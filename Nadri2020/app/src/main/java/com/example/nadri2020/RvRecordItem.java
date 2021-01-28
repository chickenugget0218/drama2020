package com.example.nadri2020;

import android.net.Uri;

public class RvRecordItem {
    //날짜 제목 내용 화수 item내용들
    private String rv_date;
    private String rv_drama;
    private String rv_text;
    private String rv_num;
    private Uri rv_image; //이미지 일단 임시로

    public RvRecordItem(String date, String drama, String text, String num, Uri image){
        this.rv_date = date;
        this.rv_drama = drama;
        this.rv_text = text;
        this.rv_num = num;
        this.rv_image = image;
    }
    public void setRv_date(String date){
        this.rv_date=date;
    }

    public void setRv_drama(String drama){
        this.rv_drama=drama;
    }

    public void setRv_text(String text){
        this.rv_text=text;
    }

    public void setRv_num(String num){
        this.rv_num=num;
    }

    public void setRv_image(Uri image) {
        this.rv_image = image;
    }

    public String getRv_date(){
        return rv_date;
    }

    public String getRv_drama(){
        return rv_drama;
    }

    public String getRv_text(){
        return rv_text;
    }

    public String getRv_num(){
        return rv_num;
    }

    public Uri getRv_image() {
        return rv_image;
    }

}
