package com.example.nadri2020;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;
import androidx.core.app.ActivityCompat;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.bumptech.glide.Glide;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;

public class AddDramaRecord extends AppCompatActivity {
    private static final int REQUEST_CODE = 1;

    //이미지뷰
    ImageView iv;
    ImageView iv_result;
    Button gallery_btn;
    Button drama_new; //확인 버튼
    Button drama_back;
    Bitmap img;
    EditText drama_name; //드라마 제목

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_drama_record);

        //이미지뷰
        iv = (ImageView) findViewById(R.id.iv_dramaGallery);

        //버튼
        gallery_btn = findViewById(R.id.add_gallery_btn);
        drama_new = findViewById(R.id.add_drama_new);
        drama_back = findViewById(R.id.add_drama_back);
        drama_name = findViewById(R.id.add_drama_name);


        //이미지뷰 클릭시
        gallery_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent();
                intent.setType("image/*");
                intent.setAction(Intent.ACTION_GET_CONTENT);
                startActivityForResult(intent, REQUEST_CODE);
            }
        });

        Button buttoncancel = findViewById(R.id.add_drama_back);
        buttoncancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showcancelMessage();
            }
        });

        Button buttonwrite = findViewById(R.id.add_drama_new);
        buttonwrite.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showcwriteMessage();
            }
        });

        //뒤로가기 버튼
        iv_back();

    }

    //뒤로가기 버튼
    public void iv_back(){
        ImageView go_main = findViewById(R.id.iv_back_bt);

        go_main.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(),MainActivity.class);
                startActivity(intent);
                finish();
            }
        });
    }


    //이미지 결과
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        // Check which request we're responding to
        if (requestCode == 1) {
            // Make sure the request was successful
            if (resultCode == RESULT_OK) {
                try {
                    // 선택한 이미지에서 비트맵 생성
                    InputStream in = getContentResolver().openInputStream(data.getData());
                    img = BitmapFactory.decodeStream(in);
                    in.close();
                    // 이미지 표시
                    iv.setImageBitmap(img);
                    //Uri uri = data.getData();    //사진 경로, 라이브러리 사용
                    //Glide.with(this).load(uri.toString()).into(iv);

                    //임시

                    //showcwriteMessage();
                    //액티비티 전환: 제목,이미지넘김


                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
    }



    private void showcwriteMessage() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);

        builder.setMessage("리뷰를 등록하시겠습니까?\n");


        builder.setPositiveButton("예", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialogInterface, int which) {

                Intent intent = new Intent(getApplicationContext(),RecordDramaActivity.class);
                ByteArrayOutputStream stream = new ByteArrayOutputStream();
                img.compress(Bitmap.CompressFormat.PNG,40,stream);
                byte[] byteArray = stream.toByteArray();
                intent.putExtra("image",byteArray);
                startActivity(intent);

            }
        });

        builder.setNeutralButton("취소", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialogInterface, int which) {

            }
        });

        builder.setNegativeButton("아니오", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialogInterface, int which) {

            }
        });

        AlertDialog dialog = builder.create();
        dialog.show();

    }

    private void showcancelMessage() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("안내");
        builder.setMessage("작성을 취소하시겠습니까?");
        builder.setIcon(android.R.drawable.ic_dialog_alert);

        builder.setPositiveButton("예", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialogInterface, int which) {
                //0114추가부분
                drama_name.setText(""); //텍스트뷰 내용 삭제
                iv.setImageResource(R.drawable.img_recordview_drama); //이미지뷰 내용 삭제
            }
        });

        builder.setNeutralButton("취소", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialogInterface, int which) {

            }
        });

        builder.setNegativeButton("아니오", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialogInterface, int which) {

            }
        });

        AlertDialog dialog = builder.create();
        dialog.show();

    }
}
