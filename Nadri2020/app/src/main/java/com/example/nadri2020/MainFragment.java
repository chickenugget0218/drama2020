package com.example.nadri2020;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.HorizontalScrollView;
import android.widget.ScrollView;

import com.example.nadri2020.data.MainRecordData;

import java.util.ArrayList;


public class MainFragment extends Fragment {

    private ArrayList<MainRecordData> arrayList;
    private MainAdapter mainAdapter;
    private RecyclerView recyclerView;//그리드형식으로 표시
    private GridLayoutManager mGridLayoutManager;
    private Context mContext;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        mContext = getContext();

        arrayList = new ArrayList<>();

        //어댑터 생성 arraylist추가
        mainAdapter = new MainAdapter(arrayList);

        View rootview = inflater.inflate(R.layout.fragment_main, container, false);

        Log.d("1탭 리사이클러뷰 체크","1탭 리사이클러뷰 체크");
        /*
        //스크롤뷰
        final ScrollView scrollView = (ScrollView) rootview.findViewById(R.id.main_scrollView_my);
        scrollView.post(new Runnable() {
            @Override
            public void run() {
                scrollView.fullScroll(ScrollView.FOCUS_UP);
            }
        });

         */


        //그리드 컬럼추가 - 4, 한줄에 4개 표시되게함
        int numberofColumns = 4;
        mGridLayoutManager = new GridLayoutManager(mContext, numberofColumns, mGridLayoutManager.VERTICAL, false);

        //리사이클러뷰 추가-지영
        recyclerView = (RecyclerView) rootview.findViewById(R.id.rv_item_main_record);
        Log.d("1탭 리사이클러뷰2 체크","1탭 리사이클러뷰2 체크");

        recyclerView.setAdapter(mainAdapter);
        recyclerView.setLayoutManager(mGridLayoutManager);

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