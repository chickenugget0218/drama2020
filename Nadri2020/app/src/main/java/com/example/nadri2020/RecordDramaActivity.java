package com.example.nadri2020;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.os.Parcelable;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;

import java.util.ArrayList;

public class RecordDramaActivity extends AppCompatActivity {

    TextView dramaTitle;
    String title;
    String image;
    private DBhelper DBhelper; //db설정

    //rv 설정
    RecyclerView mrecyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_record_drama);

        moveToReviewWrite();
        iv_back();

        //넘겨온거받음
        Intent intent = getIntent();
//        byte[] arr = getIntent().getByteArrayExtra("image");
  //      Bitmap image = BitmapFactory.decodeByteArray(arr, 0, arr.length);
    //    ImageView BigImage = (ImageView)findViewById(R.id.record_act_drama_img);
      //  BigImage.setImageBitmap(image);
        Uri uri = getIntent().getParcelableExtra("uri");
        ImageView BigImage = (ImageView)findViewById(R.id.record_act_drama_img);
        BigImage.setImageURI(uri);

        //제목, 장르 받음 - 장르는 사용자에게 보여주지x
        String receiveTitle = intent.getExtras().getString("title");
        String gerne = intent.getExtras().getString("gerne");

        dramaTitle = findViewById(R.id.search_act_drama_name_text);
        dramaTitle.setText(receiveTitle);

        /*DB에 제목, 이미지 저장
        title = dramaTitle.getText().toString();
        image = uri.toString();
        Review review = new Review();
        review.setTitle(title);
        review.setImage(image);
        //DBhelper.insertInfo(review);

        //DBhelper.loadReviewINFO();
        //review.setTitle(dramaTitle);
       // review.setImage();*/

        //리사이클러뷰 부분
        LinearLayoutManager mLayoutManager= new LinearLayoutManager(this);

        mrecyclerView = findViewById(R.id.rv_item_record_list);
        mrecyclerView.setHasFixedSize(true); //크기일정

        //리사이클러뷰에 뿌려줄 데이터를 담을 arraylist초기화
        ArrayList<RvRecordItem> recordItemArrayList = new ArrayList<>();
        RecordAdapter recordAdapter = new RecordAdapter(recordItemArrayList);
        mrecyclerView.setAdapter(recordAdapter);
        mrecyclerView.setLayoutManager(mLayoutManager);

        recordItemArrayList.add(new RvRecordItem("2020.01.27","제목","내용","1화",uri));
        recordAdapter.notifyDataSetChanged(); //값 변경 알려줌

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