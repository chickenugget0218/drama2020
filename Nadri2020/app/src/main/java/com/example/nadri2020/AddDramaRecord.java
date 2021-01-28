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
import android.util.Log;
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
    Uri uri;
    EditText drama_gerne; //드라마 장르

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
        drama_gerne = findViewById(R.id.drama_genre);

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
                    uri = data.getData();
                    // 선택한 이미지에서 비트맵 생성
                    //InputStream in = getContentResolver().openInputStream(data.getData());
                    InputStream in = getContentResolver().openInputStream(uri);
                    img = BitmapFactory.decodeStream(in);
                    in.close();
                    // 이미지 표시
                    iv.setImageBitmap(img);

                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
    }



    private void showcwriteMessage() {
        final AlertDialog.Builder builder = new AlertDialog.Builder(this);

        builder.setMessage("리뷰를 등록하시겠습니까?\n");


        builder.setPositiveButton("예", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialogInterface, int which) {
                //드라마 제목, 장르, 이미지뷰가 비었을때
                if (drama_name.getText().toString().equals("") || drama_gerne.getText().toString().equals("") || iv.getDrawable()==null) {
                    //토스트 띄움
                    Toast.makeText(getApplicationContext(),"제목과 장르, 이미지를 등록해주세요.",Toast.LENGTH_SHORT).show();
                }
                else if (drama_name.getText().toString().length() > 0 && drama_name.getText().length() > 0 && iv.getDrawable()!=null){
                    //넘어가기 전에 다이얼로그로 입력한 정보 보여주면 좋을것같음 - 0128자영

                    Intent intent = new Intent(getApplicationContext(), RecordDramaActivity.class);
                    ByteArrayOutputStream stream = new ByteArrayOutputStream();
                    img.compress(Bitmap.CompressFormat.JPEG, 10, stream);
                    byte[] byteArray = stream.toByteArray();

                    //이미지 uri 전달, 제목 전달, 장르 전달
                    Bundle bundle = new Bundle();
                    intent.putExtra("uri", uri);
                    intent.putExtra("title", drama_name.getText().toString());
                    intent.putExtra("gerne", drama_gerne.getText().toString());
                    startActivity(intent);
                    finish();
                }
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
                drama_name.setText(""); //텍스트뷰 내용 삭제
                drama_gerne.setText("");
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
