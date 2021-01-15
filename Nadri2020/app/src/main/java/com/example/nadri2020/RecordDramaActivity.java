package com.example.nadri2020;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

public class RecordDramaActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_record_drama);


        moveToReviewWrite();
        iv_back();

        //넘겨온거받음
        Intent intent = getIntent();
        byte[] arr = getIntent().getByteArrayExtra("image");
        Bitmap image = BitmapFactory.decodeByteArray(arr, 0, arr.length);
        ImageView BigImage = (ImageView)findViewById(R.id.record_act_drama_img);
        BigImage.setImageBitmap(image);


    }

    public void moveToReviewWrite(){
        Button button = findViewById(R.id.add_review_write);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), WriteDramaActivity.class);
                startActivity(intent);
            }
        });
    }

    //뒤로가기 버튼
    public void iv_back(){
        ImageView go_main = findViewById(R.id.iv_back_bt);

        go_main.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(),MainActivity.class);
                startActivity(intent);
                finish();
            }
        });
    }
}