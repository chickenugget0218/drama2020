package com.example.nadri2020;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import org.w3c.dom.Text;

import java.net.ProtocolFamily;
import java.util.ArrayList;
import java.util.List;

public class RecordAdapter extends RecyclerView.Adapter<RecordAdapter.ViewHolder> {
    private ArrayList<RvRecordItem> RvRecordItemList;

    public RecordAdapter(ArrayList<RvRecordItem> RvRecordItemList){
        this.RvRecordItemList = RvRecordItemList;
    }

    //뷰홀더 상속 및 구현
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(context.LAYOUT_INFLATER_SERVICE);
        View view = inflater.inflate(R.layout.rv_item_record_list,parent,false);
        RecordAdapter.ViewHolder vh = new RecordAdapter.ViewHolder(view);
        return vh;
    }

    //onBindeViewHolder :position에 해당하는 데이터를 뷰홀더의 아이템뷰에 표시
    @Override
    public void onBindViewHolder(@NonNull RecordAdapter.ViewHolder holder, int position) {
        RvRecordItem itemView = RvRecordItemList.get(position);

        holder.iv.setImageURI(itemView.getRv_image());
        holder.drama.setText(itemView.getRv_drama());
        holder.text.setText(itemView.getRv_text());
        holder.num.setText(itemView.getRv_num());
        holder.date.setText(itemView.getRv_date());

    }

    //전체 데이터 개수 리턴
    public int getItemCount() { return (null != RvRecordItemList ? RvRecordItemList.size() : 0); }


    //아이템 뷰를 저장하는 뷰홀더 클래스
    public class ViewHolder extends RecyclerView.ViewHolder{
        TextView drama;
        TextView text;
        TextView date;
        TextView num;
        ImageView iv;
        ImageButton share;

        public ViewHolder(View itemView){
            super(itemView);

            //뷰 객체에 대한 참조
            iv = itemView.findViewById(R.id.rv_item_record_img);
            drama = itemView.findViewById(R.id.rv_title_drama);
            text = itemView.findViewById(R.id.rv_drama_text);
            date = itemView.findViewById(R.id.rv_drama_date);
            num = itemView.findViewById(R.id.rv_drama_num);
            share = itemView.findViewById(R.id.btn_share);



        }

    }

}
