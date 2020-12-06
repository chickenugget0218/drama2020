package com.example.nadri2020;

import androidx.appcompat.app.AppCompatActivity;


import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.HorizontalScrollView;
import android.widget.RelativeLayout;
import android.widget.ScrollView;
import android.widget.Toast;

//민승

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        moveToBoard();
        moveToMypage();
    }

    public void setScrollViewFocus(){
        final HorizontalScrollView scrollView = findViewById(R.id.main_scrollView_my);
        scrollView.post(new Runnable() {
            @Override
            public void run() {
                scrollView.fullScroll(ScrollView.FOCUS_UP);
            }
        });
    }


    public void moveToMain(){
        RelativeLayout button = findViewById(R.id.main_act_home_btn);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(intent);
                overridePendingTransition(R.anim.slide_out_right, R.anim.slide_out_left);
                finish();
            }
        });
    }

    public void moveToBoard(){
        RelativeLayout button = findViewById(R.id.main_act_board_btn);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), BoardActivity.class);
                startActivity(intent);
                overridePendingTransition(R.anim.slide_out_left, R.anim.slide_in_right);
            }
        });
    }


    public void moveToMypage(){
        RelativeLayout button = findViewById(R.id.main_act_mypage_btn);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), MypageActivity.class);
                startActivity(intent);
                overridePendingTransition(R.anim.slide_out_left, R.anim.slide_in_right);
            }
        });
    }


    // 뒤로 가기 버튼 두 번 눌러야 종료되게
    private long time = 0;
    @Override
    public void onBackPressed() {
        //super.onBackPressed();

        if (System.currentTimeMillis() >= 2000 + time) {
            time = System.currentTimeMillis();
            Toast.makeText(getApplicationContext(), "뒤로 가기 버튼을 한 번 더 누르면 종료합니다.", Toast.LENGTH_SHORT).show();
        } else if (System.currentTimeMillis() < 2000 + time) {
            finish();
        }
    }


}
