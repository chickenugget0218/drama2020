package com.example.nadri2020;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;



import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.HorizontalScrollView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.ScrollView;
import android.widget.Toast;

import com.example.nadri2020.data.MainRecordData;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.ArrayList;

//민승

public class MainActivity extends AppCompatActivity {

    //추가
    private ArrayList<MainRecordData> arrayList;
    private MainAdapter mainAdapter;
    private RecyclerView recyclerView;//그리드형식으로 표시
    private GridLayoutManager mGridLayoutManager;

    private FragmentManager fragmentManager = getSupportFragmentManager();
    private MainFragment mainFragment = new MainFragment();
    private BoardFragment boardFragment = new BoardFragment();
    private MypageFragment mypageFragment = new MypageFragment();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //moveToBoard();
        //moveToMypage();
        //moveToReview();
        //moveToSearch();

        setTabFragment();

        //민승-이하 주석들 Fragment로 이동 및 수정
        //지영추가
        //AddDrama();

        /*
        //리사이클러뷰 추가-지영
        recyclerView = (RecyclerView) findViewById(R.id.rv_item_main_record);
        //그리드 컬럼추가 - 4, 한줄에 4개 표시되게함
        int numberofColumns = 4;
        mGridLayoutManager = new GridLayoutManager(this,numberofColumns);
        recyclerView.setLayoutManager(mGridLayoutManager);

        arrayList = new ArrayList<>();

        //어댑터 생성 arraylist추가
        mainAdapter = new MainAdapter(arrayList);
        recyclerView.setAdapter(mainAdapter);

         */

        //버튼클릭시 리사이클러뷰 리뷰추가  - 임시로 연결
//        Button review_add = (Button)findViewById(R.id.review_add);
        //      review_add.setOnClickListener(new View.OnClickListener() {
        //        @Override
//            public void onClick(View v) {

        //메인데이터 가져옴- dramaname, int image
        // MainRecordData mainData = new MainRecordData("봄밤",R.drawable.img_thumbnail);
        //메인데이터에 어레이리스트 추가
        //arrayList.add(mainData);
        //새로고침 될것
        //mainAdapter.notifyDataSetChanged();
        //          }
        //    });

    }


    private void setTabFragment() {
        BottomNavigationView bottomNavigationView = findViewById(R.id.bottom_navigation_view);
        // 첫 화면 지정
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        transaction.replace(R.id.frame_layout, mainFragment).commitAllowingStateLoss();

        // bottomNavigationView의 아이템이 선택될 때 호출될 리스너 등록
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                FragmentTransaction transaction = fragmentManager.beginTransaction();
                switch (item.getItemId()) {
                    case R.id.navigation_menu1: {
                        transaction.replace(R.id.frame_layout, mainFragment).commitAllowingStateLoss();
                        break;
                    }
                    case R.id.navigation_menu2: {
                        transaction.replace(R.id.frame_layout, boardFragment).commitAllowingStateLoss();
                        break;
                    }
                    case R.id.navigation_menu3: {
                        transaction.replace(R.id.frame_layout, mypageFragment).commitAllowingStateLoss();
                        break;
                    }
                }

                return true;
            }
        });

    }

/*
    public void setScrollViewFocus(){
        final HorizontalScrollView scrollView = findViewById(R.id.main_scrollView_my);
        scrollView.post(new Runnable() {
            @Override
            public void run() {
                scrollView.fullScroll(ScrollView.FOCUS_UP);
            }
        });
    }

 */


    /*
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

     */
    public void moveToSearch(){
        RelativeLayout button = findViewById(R.id.main_act_search_btn);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), SearchDramaActivity.class);
                startActivity(intent);
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

    /*
    //드라마 추가하기 버튼
    public void AddDrama() {
        Button button = findViewById(R.id.review_add); //임시 연결
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(),AddDramaRecord.class);
                startActivity(intent);
            }
        });
    }

     */

    /*
    public void moveToReview(){
        Button button = findViewById(R.id.main_act_search_review);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), WriteDramaActivity.class);
                startActivity(intent);
            }
        });
    }

     */

}
