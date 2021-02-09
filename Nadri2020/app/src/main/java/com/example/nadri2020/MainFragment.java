package com.example.nadri2020;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.text.Html;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.Toast;

import java.lang.reflect.Array;
import java.util.ArrayList;


public class MainFragment extends Fragment {

    ArrayList<DataView> dramaList;
    MainAdapter mainAdapter;
    RecyclerView recyclerView;//그리드형식으로 표시
    GridLayoutManager mGridLayoutManager;
    MainActivity activity;
    int numberCol; //그리드표시

    DBhelper dBhelper;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {


        View rootview = inflater.inflate(R.layout.fragment_main, container, false);

        activity =(MainActivity) getActivity();

        //null이면 db생성
        if(dBhelper == null){
            dBhelper = new DBhelper(getActivity(),"TEST",null,dBhelper.DB_VERSION);
            dBhelper.testDB();
        }


        dramaList =  dBhelper.showDB();
        Log.d("dramalist",dramaList.toString());
        //dramaList =  dBhelper.showDB();//error

        if(dramaList.size()==0){
            Log.i("DRAMA DATA : ","EMPTY!");
            Toast.makeText(getContext().getApplicationContext(),"There is no data!",Toast.LENGTH_SHORT).show();
        }
        else{
            Log.i("DRAMA DATA : ","NOT EMPTY!");
            Toast.makeText(getContext().getApplicationContext(),"data list set!",Toast.LENGTH_SHORT).show();
        }


        //리사이클러뷰
        recyclerView = (RecyclerView) rootview.findViewById(R.id.rv_item_main_record);
        recyclerView.setHasFixedSize(true);
        Log.d("rv","1탭 리사이클러뷰2 체크");


        //그리드 컬럼추가 - 4, 한줄에 4개 표시되게함
        numberCol = 3;
        mGridLayoutManager = new GridLayoutManager(getActivity(), numberCol, mGridLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(mGridLayoutManager); //그리드레이아웃
        recyclerView.setNestedScrollingEnabled(false); //스크롤 부드럽게


        mainAdapter = new MainAdapter(dramaList); //데이터 표시 어댑터 생성
        //mainAdapter.showDB();

        recyclerView.setAdapter(mainAdapter); //리사이클러뷰에 어댑터 연결
        //갱신
        mainAdapter.notifyDataSetChanged();


        //리뷰 작성 버튼
        Button button2 = rootview.findViewById(R.id.main_act_search_review);
        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), WriteDramaActivity.class);
                startActivity(intent);
            }
        });

        //드라마 추가하기 버튼
        Button button1 = rootview.findViewById(R.id.review_add); //임시 연결
        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), AddDramaRecord.class);
                startActivity(intent);
            }
        });



        return rootview;
    }


    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
    }


}