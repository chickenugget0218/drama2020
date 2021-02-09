package com.example.nadri2020;

import android.content.ContentResolver;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.icu.text.AlphabeticIndex;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;
import java.util.List;

public class MainAdapter extends RecyclerView.Adapter<MainAdapter.ViewHolder> {

    ArrayList<DataView> reviewList;
    DBhelper DBhelper;
    Context context;


    public MainAdapter(ArrayList<DataView> reviewList) {
        this.reviewList = reviewList;
    }

    //리스너 객체 참조를 저장하는 변수
    private AdapterView.OnItemClickListener mListener = null;


    //뷰홀더 상속 및 구현
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        context = parent.getContext();
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(context.LAYOUT_INFLATER_SERVICE);
        View view = inflater.inflate(R.layout.rv_item_board_drama, parent, false);
        MainAdapter.ViewHolder vh = new MainAdapter.ViewHolder(view);
        return vh;
    }

    //position에 해당하는 데이터를 뷰홀더의 아이템뷰에 표시
    @Override
    public void onBindViewHolder(@NonNull MainAdapter.ViewHolder holder, int position) {
        //Review itemView = reviewList.get(position);
        //holder.iv.setImageURI(Uri.parse(itemView.getImage()));
       // holder.title.setText(itemView.getTitle());

        ((ViewHolder) holder).onBind(reviewList.get(position));
    }

    //전체 데이터 개수 리턴
    public int getItemCount() {
        return (null != reviewList ? reviewList.size() : 0);
    }

    //데이터 목록 지우기
    public void clear(){
        int size = reviewList.size();
    }

    // OnItemClickListener 리스너 객체 참조를 어댑터에 전달하는 메서드
    public void setOnItemClickListener(AdapterView.OnItemClickListener listener) {
        this.mListener = listener ;
    }

    //아이템 뷰를 저장하는 뷰홀더 클래스
    public class ViewHolder extends RecyclerView.ViewHolder {
        public TextView title;
        public ImageView iv;
        public Uri uri;

        public ViewHolder(View itemView) {
            super(itemView);

            //뷰 객체에 대한 참조
            iv = itemView.findViewById(R.id.rv_item_board_thumbnail);
            title = itemView.findViewById(R.id.rv_item_board_thumbnail_text);

            itemView.setClickable(true);
            //디폴트 클릭리스너
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    int pos = getAdapterPosition();
                    if (pos != RecyclerView.NO_POSITION) {
                        //데이터 리스트로부터 아이템 데이터 참조
                        if (pos != RecyclerView.NO_POSITION) {
                            Intent intent = new Intent(context, RecordDramaActivity.class);

                            //값 넘겨주기
                            String title2=title.getText().toString();
                            String img_uri = uri.toString(); //uri를 string으로 변환시켜서 전달

                            intent.putExtra("title",title2);
                            intent.putExtra("imguri",img_uri);
                            Log.d("넘김 타이틀", title2);
                            Log.d("넘김 이미지1",img_uri);
                            context.startActivity(intent);
                        }
                    }
                }
            });

        }

        public void onBind(DataView item) {
            Log.d("uri:",item.getImage());
            uri = Uri.parse(item.getImage());

            iv.setImageURI(uri);
            title.setText(item.getTitle());
        }
    }


}
