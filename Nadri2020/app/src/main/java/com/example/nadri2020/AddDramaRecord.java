package com.example.nadri2020;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

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

import java.io.InputStream;

public class AddDramaRecord extends AppCompatActivity {
    private static final int REQUEST_CODE = 1;

    //다이얼로그
    Dialog dialog;

    //이미지뷰
    private ImageView iv;
    Button gallery_btn;
    Button drama_new; //확인 버튼
    Button drama_back;
    EditText add_drama_name; //드라마 제목

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

        //확인버튼 클릭시 커스텀 다이얼로그 띄우기
        drama_new.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showDialog();
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
                    Bitmap img = BitmapFactory.decodeStream(in);
                    in.close();
                    // 이미지 표시
                    iv.setImageBitmap(img);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
    }

    //dialog함수
    public void showDialog(){
   //     dialog.show();
    }


    private void showcwriteMessage() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);

        builder.setMessage("리뷰를 등록하시겠습니까?\n");


        builder.setPositiveButton("예", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialogInterface, int which) {
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
