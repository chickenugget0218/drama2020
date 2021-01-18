package com.example.nadri2020;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.ScrollView;

import com.example.nadri2020.data.MainRecordData;

import java.util.ArrayList;

public class BoardFragment extends Fragment {

    private ArrayList<MainRecordData> arrayList;
    private MainAdapter mainAdapter;
    private RecyclerView recyclerView;//그리드형식으로 표시
    private GridLayoutManager mGridLayoutManager;
    private Context mContext;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        Log.d("2탭 체크", "2탭 체크");
        mContext = getContext();

        View rootview = inflater.inflate(R.layout.fragment_board, container, false);

        Log.d("2탭 리사이클러뷰 체크","2탭 리사이클러뷰 체크");
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
        mGridLayoutManager = new GridLayoutManager(mContext, numberofColumns);


        //리사이클러뷰 추가-지영
        recyclerView = (RecyclerView) rootview.findViewById(R.id.rv_board_list);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

        arrayList = new ArrayList<>();

        Log.d("2탭 어댑터 체크","2탭 어댑터 체크");

        //어댑터 생성 arraylist추가
        mainAdapter = new MainAdapter(arrayList);
        recyclerView.setAdapter(mainAdapter);




        return rootview;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }


}