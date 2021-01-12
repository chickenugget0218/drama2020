package com.example.nadri2020;

import android.text.Layout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import androidx.versionedparcelable.CustomVersionedParcelable;

import com.example.nadri2020.data.MainRecordData;

import java.util.ArrayList;

//지영-rv 어댑터 추가
public class MainRecordAdapter extends RecyclerView.Adapter<MainRecordAdapter.CustomViewHolder> {

    private ArrayList<MainRecordData> arrayList;

    public MainRecordAdapter(ArrayList<MainRecordData> arrayList) {this.arrayList = arrayList; }


    //리스트뷰 생명주기
    @NonNull
    @Override
    public CustomViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.rv_item_main_record, parent,false);
        CustomViewHolder holder = new CustomViewHolder(view);
        //viewholder리턴
        return holder;
    }

    //추가될때 생명주기


    @Override
    public void onBindViewHolder(@NonNull CustomViewHolder holder, int position) {
        //이미지뷰 생성된거 가져옴
        holder.dramaName.setText(arrayList.get(position).getName());
        holder.image.setImageResource(arrayList.get(position).getImage());

    }

    @Override
    public int getItemCount() { return (null != arrayList ? arrayList.size() : 0); }

    //뷰홀더 추가
    public class CustomViewHolder extends RecyclerView.ViewHolder {

        protected ImageView image;
        protected TextView dramaName;

        public CustomViewHolder(@NonNull View itemView) {
            super(itemView);
            //이미지 추가
            this.image = (ImageView) itemView.findViewById(R.id.rv_item_main_thumbnail);
            this.dramaName = (TextView) itemView.findViewById(R.id.rv_item_main_thumbnail_text);
        }
    }
}
