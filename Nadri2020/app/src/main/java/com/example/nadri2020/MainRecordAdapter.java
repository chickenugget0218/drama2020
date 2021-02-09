package com.example.nadri2020;

import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

//지영-rv 어댑터 추가
public class MainRecordAdapter extends RecyclerView.Adapter<MainRecordAdapter.ViewHolder> {

    ArrayList<Review> reviewList;
    Context context;

    public MainRecordAdapter(ArrayList<Review> reviewList) {this.reviewList = reviewList; }

    //리스트뷰 생명주기
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.rv_item_record_list, parent,false);
        ViewHolder vh = new ViewHolder(view);
        //viewholder리턴
        return vh;
    }

    //position에 해당하는 대이터를 뷰홀더의 아이템뷰에 표시
    @Override
    public void onBindViewHolder(@NonNull MainRecordAdapter.ViewHolder holder, int position) {
        /*이미지뷰 생성된거 가져옴
        Review itemView = reviewList.get(position);
        holder.dramaName.setText(reviewList.get(position).getTitle());
        holder.image.setImageURI(Uri.parse(reviewList.get(position).getImage()));
               // arrayList.get(position).getImage());*/

        ((ViewHolder)holder).onBind(reviewList.get(position));
}


    //전체 데이터 개수 return
    @Override
    public int getItemCount() { return (null != reviewList ? reviewList.size() : 0); }

    //데이터 목록 지우기
    public void clear(){
        int size = reviewList.size();
    }

    //아이템 뷰를 저장하는 뷰홀더 클래스
    public class ViewHolder extends RecyclerView.ViewHolder {
        public ImageView iv;
        public ImageView watch;
        public TextView title;
        public TextView num;
        public TextView text;
        public TextView date;
        ImageButton share;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            //뷰 객체에 대한 참조
            iv = itemView.findViewById(R.id.rv_item_record_img);
            watch = itemView.findViewById(R.id.rv_watch);
            title = itemView.findViewById(R.id.rv_title_drama);
            num = itemView.findViewById(R.id.rv_drama_num);
            text = itemView.findViewById(R.id.rv_drama_text);
            date = itemView.findViewById(R.id.rv_drama_date);
            share = itemView.findViewById(R.id.btn_share);

        }

        public void onBind(Review item) {
            Log.d("image uri:",item.getImage());
            Uri uri = Uri.parse(item.getImage()); //Glide쓸수있으면 쓰기

            /* watch 값 가져옴 */
            int w = item.getWatched();

            if(w == 1){ //WATCHED
                watch.setImageResource(R.drawable.ic_boardview_watched_checked);
            }else if(w ==2){ //WATCHING
                watch.setImageResource(R.drawable.ic_boardview_watching_checked);
            }else if(w ==3){ //UNWATCHED
                watch.setImageResource(R.drawable.ic_boardview_unwatched_checked);
            }else if(w ==4){ //STOP
                watch.setImageResource(R.drawable.ic_boardview_stop);
            }else{  }

            iv.setImageURI(uri);
            title.setText(item.getTitle());
            num.setText(item.getNumber());
            text.setText(item.getReview());
            date.setText(item.getDate());

            //공유버튼 눌렀을때
            share.setClickable(true);
            share.setOnClickListener((new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    try{
                        Intent share_intent = new Intent(Intent.ACTION_SEND);
                        share_intent.setType("text/plain");

                        Log.d("getText",text.getText().toString()); //textview내용 가져옴
                        String text_review =  text.getText().toString() + "\n#나드리 #나의드라마리뷰";

                        share_intent.putExtra(Intent.EXTRA_TEXT,text_review);

                        Intent sharing = Intent.createChooser(share_intent,"공유하기");

                        sharing.setFlags(share_intent.FLAG_ACTIVITY_NEW_TASK);
                        view.getContext().startActivity(sharing);
                    }
                    catch (Exception e){
                        e.printStackTrace();
                    }

                }
            }));

        }
    }
}
