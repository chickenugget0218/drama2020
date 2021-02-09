package com.example.nadri2020;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
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

import java.net.URLEncoder;
import java.util.ArrayList;

public class RecordDramaActivity extends AppCompatActivity {

    TextView dramaTitle;
    ImageView dramaimage;

    String dramaString;
    String dramaimg;

    DBhelper dBhelper; //db설정

    ArrayList<Review> reviewList;
    ArrayList<DataView> dataList;

    MainRecordAdapter mainRecordAdapter;

    Uri uri;

    //rv 설정
    RecyclerView mrecyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_record_drama);

        /*뒤로가기, 드라마 리뷰작성*/
        moveToReviewWrite();
        iv_back();

        //null이면 db생성
        if(dBhelper == null){
            dBhelper = new DBhelper(getApplicationContext(),"TEST",null,dBhelper.DB_VERSION);
            dBhelper.testDB();
        }

        dramaTitle = findViewById(R.id.search_act_drama_name_text);
        dramaimage = findViewById(R.id.record_act_drama_img);

        //get title
        Intent intent = getIntent();

        dramaString = intent.getExtras().getString("title");
        dramaTitle.setText(dramaString);
        String name = dramaTitle.getText().toString();

        dramaimg = intent.getExtras().getString("imguri");
        uri = Uri.parse(dramaimg);
        dramaimage.setImageURI(uri);

        /*  보류  */
        //dataList = dBhelper.showDB();


        /*  리사이클러뷰 부분   */
        reviewList =  dBhelper.loadReview(name); //여기에 타이틀 값을 념겨줘야함
        Log.d("dramalist",reviewList.toString());

        if(reviewList.size()==0){
            Log.i("REVIEW DATA : ","EMPTY!");
            Toast.makeText(getApplicationContext(),"There is no data!",Toast.LENGTH_SHORT).show();
        }
        else{
            Log.i("REVIEW DATA : ","NOT EMPTY!");
            Toast.makeText(getApplicationContext(),"data list set!",Toast.LENGTH_SHORT).show();
        }


        //리사이클러뷰 부분
        LinearLayoutManager mLayoutManager= new LinearLayoutManager(this);

        mrecyclerView = findViewById(R.id.rv_item_record_list);
        mrecyclerView.setHasFixedSize(true); //크기일정
        Log.d("rv","리사이클러뷰2 체크");

        //리사이클러뷰에 뿌려줄 데이터를 담을 arraylist
        mainRecordAdapter = new MainRecordAdapter(reviewList);
        mrecyclerView.addItemDecoration(new DividerItemDecoration(getApplicationContext(), 1));
        mrecyclerView.setAdapter(mainRecordAdapter);
        mrecyclerView.setLayoutManager(mLayoutManager);

        mainRecordAdapter.notifyDataSetChanged(); //값 변경 알려줌



    }

    //회차추가하기 버튼
    public void moveToReviewWrite(){
        Button button = findViewById(R.id.add_review_write);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //회차추가 writeDramaActivit2로 넘긴다
                Intent intent2 = new Intent(getApplicationContext(), WriteDramaActivity2.class);

                //이미지uri,제목 넘기기
                intent2.putExtra("title",dramaString);
                intent2.putExtra("img",dramaimg);

                Log.d("제목찍히나확인",dramaString);
                Log.d("임지찍히나확인",dramaimg);
                startActivity(intent2);
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