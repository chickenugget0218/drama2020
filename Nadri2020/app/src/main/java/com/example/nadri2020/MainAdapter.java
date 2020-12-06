package com.example.nadri2020;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.nadri2020.data.MainRecordData;

import java.util.ArrayList;

public class MainAdapter extends RecyclerView.Adapter<MainAdapter.MainViewHolder> implements OnMainRecordClickListener {

    ArrayList<MainRecordData> items;
    OnMainRecordClickListener listener;

    public MainAdapter(ArrayList<MainRecordData> items) {
        this.items = items;
    }


    @Override
    public MainViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(viewGroup.getContext());
        View itemView = inflater.inflate(R.layout.rv_item_main_record, viewGroup, false);
        return new MainViewHolder(itemView, this);
    }

    @Override
    public void onBindViewHolder(MainViewHolder viewHolder, int position) {
        MainRecordData item = items.get(position);

        Glide.with(viewHolder.itemView.getContext())
                .load(item.getImage())
                .into(viewHolder.dramaImage);
        viewHolder.dramaName.setText(item.getName());
    }

    public void setOnItemClickListener(OnMainRecordClickListener listener){
        this.listener = listener;
    }

    @Override
    public void onItemClick(MainViewHolder holder, View view, int position) {
        if (listener != null)
            listener.onItemClick(holder, view, position);
    }

    @Override
    public int getItemCount() {
        if (items == null)
            return 0;
        return items.size();
    }

    public void addItem(MainRecordData item){
        items.add(item);
    }

    public void setItems(ArrayList<MainRecordData> items){
        this.items = items;
    }

    public MainRecordData getItem(int position){
        return items.get(position);
    }

    public void setItem(int position, MainRecordData item){
        items.set(position, item);
    }


    static class MainViewHolder extends RecyclerView.ViewHolder {
        protected ImageView dramaImage;
        protected TextView dramaName;

        public MainViewHolder(final View itemView, final OnMainRecordClickListener listener) {
            super(itemView);

            dramaImage = itemView.findViewById(R.id.rv_item_main_thumbnail);
            dramaName = itemView.findViewById(R.id.rv_item_main_thumbnail_text);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    int position = getAdapterPosition();
                    if (listener != null)
                        listener.onItemClick(MainViewHolder.this, view, position);
                }
            });
        }
    }
}
