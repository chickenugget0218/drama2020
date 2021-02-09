package com.example.nadri2020;

import android.net.Uri;

public class Review {
    //pk
    private int _id;

//제목 날짜 회차 리뷰내용 이미지 장르 - 리사이클러뷰
    private String title;
    private String date;
    private String number;
    private int watched;
    private String review;
    private String image;
    private String genre;

    public int get_id() {return _id;}
    public String getTitle(){ return title; }
    public String getDate() { return date; }
    public String getNumber() {return number; }
    public int getWatched() {return watched;}
    public String getReview(){ return review;}
    public String getImage(){ return image;}
    public String getGenre(){ return genre;}

    public void set_id(int _id){this._id = _id;}
    public void setTitle(String title){ this.title = title; }
    public void setDate(String date) { this.date = date; }
    public void setNumber(String number) {this.number = number; }
    public void setWatched(int watched) {this.watched = watched;}
    public void setReview(String review){ this.review = review;}
    public void setGenre(String genre) {this.genre = genre;}
    public void setImage(String image){ this.image = image;}

}
