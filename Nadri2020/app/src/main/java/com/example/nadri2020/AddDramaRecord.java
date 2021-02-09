package com.example.nadri2020;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;
import androidx.core.app.ActivityCompat;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.ContentResolver;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.Toast;

import com.bumptech.glide.Glide;

import java.io.BufferedOutputStream;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

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
    Spinner spinner; //드라마 장르

    Bitmap bitmap;
    String filePath;
    String absoultePath;
    String gerne;

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
        spinner = findViewById(R.id.drama_genre);

        ArrayAdapter adapter = ArrayAdapter.createFromResource(this,R.array.genre,R.layout.support_simple_spinner_dropdown_item);
        adapter.setDropDownViewResource(R.layout.support_simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);

        //스피너 선택시
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                gerne = String.valueOf(parent.getItemAtPosition(position)); //화수 db로 넘기는값
                Toast.makeText(AddDramaRecord.this, "선택하신 장르는 " + gerne + "입니다.", Toast.LENGTH_SHORT).show();
            } //이 오버라이드 메소드에서 position은 몇번째 값이 클릭됬는지 알 수 있습니다.
            //getItemAtPosition(position)를 통해서 해당 값을 받아올수있습니다.

            @Override
            public void onNothingSelected(AdapterView<?> parent) { }
        });


        //이미지뷰 클릭시
        gallery_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Intent.ACTION_PICK);
                intent.setType(MediaStore.Images.Media.CONTENT_TYPE);
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
                    Log.d("경로: ", uri.getPath().toString());
                    // 선택한 이미지에서 비트맵 생성
                    //InputStream in = getContentResolver().openInputStream(data.getData());

                    InputStream in = getContentResolver().openInputStream(uri);
                    img = BitmapFactory.decodeStream(in);
                    in.close();
                    // 이미지 표시
                    iv.setImageBitmap(img);
                    //iv.setImageURI(uri);

                }catch (Exception e) {
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
  //              drama_gerne.getText().toString().equals("") ||
                if (drama_name.getText().toString().equals("") ||  iv.getDrawable()==null) {
                    //토스트 띄움
                    Toast.makeText(getApplicationContext(),"제목과 장르, 이미지를 등록해주세요.",Toast.LENGTH_SHORT).show();
                }
                else if (drama_name.getText().toString().length() > 0 && drama_name.getText().length() > 0 && iv.getDrawable()!=null){
                    //넘어가기 전에 다이얼로그로 입력한 정보 보여주면 좋을것같음

                    /*확인 버튼 누를 시 폴더에 이미지 저장*/
                    // CROP된 이미지를 저장하기 위한 FILE 경로
                    String filePath = Environment.getExternalStorageDirectory().getAbsolutePath()+
                            "/NadriDrama/"+System.currentTimeMillis()+".jpg";

                    storeCropImage(img, filePath); // CROP된 이미지를 외부저장소, 앨범에 저장한다.
                    absoultePath = filePath; //절대경로값
                    Log.d("filepath: ",absoultePath); //찍히는지 확인 ->찍힘
                    uri = Uri.parse(absoultePath); //uri로 변환

                    Intent intent = new Intent(getApplicationContext(), WriteDramaActivity.class); //수정
                    ByteArrayOutputStream stream = new ByteArrayOutputStream();
                    img.compress(Bitmap.CompressFormat.JPEG, 10, stream);
                    byte[] byteArray = stream.toByteArray();

                    //이미지 uri 전달, 제목 전달, 장르 전달
                    Bundle bundle = new Bundle();
                    intent.putExtra("uri", uri);
                    intent.putExtra("title", drama_name.getText().toString());
                    intent.putExtra("genre",gerne);
                    //intent.putExtra("gerne", drama_gerne.getText().toString());
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
               // drama_gerne.setText("");
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

    private void storeCropImage(Bitmap bitmap,String filePath) {
        // NadriDrama 폴더를 생성하여 이미지를 저장하는 방식이다.
        String dirPath = Environment.getExternalStorageDirectory().getAbsolutePath()+"/NadriDrama";
        File directory_NadriDrama = new File(dirPath);

        if(!directory_NadriDrama.exists()) // SmartWheel 디렉터리에 폴더가 없다면 (새로 이미지를 저장할 경우에 속한다.)
            directory_NadriDrama.mkdir();

        File copyFile = new File(filePath);
        BufferedOutputStream out = null;

        try {
            copyFile.createNewFile();
            out = new BufferedOutputStream(new FileOutputStream(copyFile));
            bitmap.compress(Bitmap.CompressFormat.JPEG, 40, out);

            // sendBroadcast를 통해 Crop된 사진을 앨범에 보이도록 갱신한다.
            sendBroadcast(new Intent(Intent.ACTION_MEDIA_SCANNER_SCAN_FILE,
                    Uri.fromFile(copyFile)));

            out.flush();
            out.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
