package com.example.nadri2020;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ToggleButton;

import java.text.SimpleDateFormat;
import java.util.Calendar;

public class WriteDramaActivity extends AppCompatActivity {

    TextView textView_Date;

    int flag1 = 0;
    String date;
    public static String yy,mm,dd;
    String item_num;
    String comment1;
    EditText etcom;

    String getTitle, getGerne;
    Uri uri;
    String img;

    AlertDialog.Builder builder;

    private DBhelper DBhelper;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_write_drama);

        final TextView textView = (TextView) findViewById(R.id.write_act_drama_num_text);
        Spinner spinner = (Spinner) findViewById(R.id.drama_num_spinner);

        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                textView.setText(" 회차는 : " + parent.getItemAtPosition(position));

                //position 0,1,2에 값을 가져와 item변수에 저장후 toast로확인가능
                //Toast.makeText(WriteDramaActivity.this,Integer.toString(position),Toast.LENGTH_SHORT).show();
                item_num = String.valueOf(parent.getItemAtPosition(position)); //화수 db로 넘기는값
                Toast.makeText(WriteDramaActivity.this, item_num, Toast.LENGTH_SHORT).show(); //9화- 이런식으로 표시됨
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });

        //제목, 이미지 이전 액티비티에서 받음
        Intent intent = getIntent();
        uri = getIntent().getParcelableExtra("uri");
        getTitle = intent.getExtras().getString("title");
        getGerne = intent.getExtras().getString("genre");

        Log.d("title",getTitle);
        Log.d("genre",getGerne);
        Log.d("uri",uri.toString());

        //토글
        final ToggleButton watched;
        final ToggleButton watching;
        final ToggleButton unwatched;
        final ToggleButton stop;

        watched = (ToggleButton) findViewById(R.id.write_act_watched_btn);
        watching = (ToggleButton) findViewById(R.id.write_act_watching_btn);
        unwatched = (ToggleButton) findViewById(R.id.write_act_unwatched_btn);
        stop = (ToggleButton) findViewById(R.id.write_act_stop_btn);

        //날짜
        textView_Date = (TextView) findViewById(R.id.write_act_date_pickup_btn);
        ImageView date_button = (ImageView)findViewById(R.id.date_button); //날짜버튼이미지뷰
        etcom = (EditText)findViewById(R.id.write_com);

        builder = new AlertDialog.Builder(this);

        //WATCHED 1, WATCHING 2, UNWATCH 3, STOP 4
        //db에 숫자로 저장됨
        watched.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    watching.setChecked(false);
                    unwatched.setChecked(false);
                    stop.setChecked(false);
                    flag1 = 1;
                } else {
                    flag1 = 0;
                }
                Log.d("ddd", Integer.toString(flag1));
            }
        });

        watching.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    watched.setChecked(false);
                    unwatched.setChecked(false);
                    stop.setChecked(false);
                    flag1 = 2;
                } else {
                    flag1 = 0;
                }
                Log.d("ddd", Integer.toString(flag1));

            }
        });

        unwatched.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    watching.setChecked(false);
                    watched.setChecked(false);
                    stop.setChecked(false);
                    flag1 = 3;
                } else {
                    flag1 = 0;
                }
                Log.d("ddd", Integer.toString(flag1));

            }
        });

        stop.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    watching.setChecked(false);
                    unwatched.setChecked(false);
                    watched.setChecked(false);
                    flag1 = 4;
                } else {
                    flag1 = 0;
                }
                Log.d("ddd", Integer.toString(flag1));
            }

        });

        //날짜 버튼 클릭시 날짜 변경
        date_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mOnClick_DatePick();
            }
        });

        Button buttoncancel = findViewById(R.id.write_act_no_btn);
        buttoncancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showcancelMessage();
            }
        });

        Button buttonwrite = findViewById(R.id.write_act_yes_btn);
        buttonwrite.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //작성 버튼 클릭시 db에 저장
                img = uri.toString();
                comment1 = etcom.getText().toString();

                Log.d("img", img); //로그값 확인
                Log.d("date", date);
                Log.d("title", getTitle);
                Log.d("watch", Integer.toString(flag1));
                Log.d("genre", getGerne);
                Log.d("review", comment1);

                if(DBhelper == null){
                    DBhelper = new DBhelper(WriteDramaActivity.this,"TEST",null,DBhelper.DB_VERSION);
                }

                //db저장
                Review review = new Review();
                review.setDate(date);
                review.setNumber(item_num);
                review.setTitle(getTitle);
                review.setImage(img);
                review.setWatched(flag1);
                review.setGenre(getGerne);
                review.setReview(comment1);

                DBhelper.insertReview(review);

                //메인으로 이동
                go_main();
            }
        });

        //  iv_back();
    }

    private void showcwriteMessage() {
    /*
                    //넘겨주기 - 날짜, 리뷰텍스트, 회차(num), watch(flag-1234)
                    Intent intent = new Intent(getApplicationContext(),RecordDramaActivity.class);
                    Log.d("코멘트: ", comment1);

                    intent.putExtra("date",date); //날짜
                    intent.putExtra("comm",comment1); //리뷰텍스트
    // 이줄때문에 자꾸에러남
    //              intent.putExtra("comment",comment); //리뷰텍스트
                    intent.putExtra("num",item_num); //회차
                    intent.putExtra("flag",flag1); //플래그
    */
    };

    private void showcancelMessage() {
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

    //날짜
    DatePickerDialog.OnDateSetListener mDateSetListener = new DatePickerDialog.OnDateSetListener() {
                @Override
                public void onDateSet(DatePicker datePicker, int year, int month, int ddate) {
                    //datepicker에서 선택한 날짜를 textview에 설정
                    textView_Date.setText(String.format("%d년 %d월 %d일", year, month + 1, ddate));
                    //yymmdd하나 다 변수만들어서 tostring한다음 저장
                    //SimpleDateFormat format = new SimpleDateFormat("yyyy.mm.dd");
                    //Log.d("datetest",format.toString());

                    yy = Integer.toString(year);
                    mm = Integer.toString(month+1);
                    dd = Integer.toString(ddate);
                    date= yy+"."+mm+"."+dd; //20211130
                    Log.d("dateint",date);
                }
            };

    //오늘 날짜 받아옴
    public void mOnClick_DatePick()
    {
            Calendar cal = Calendar.getInstance();
            new DatePickerDialog(this,mDateSetListener,cal.get(Calendar.YEAR),
                    cal.get(Calendar.MONTH),cal.get(Calendar.DATE)).show();
    }

    //뒤로가기 버튼
    public void iv_back(){
        ImageView go_main = findViewById(R.id.iv_back_bt);

        go_main.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(),RecordDramaActivity.class);
                startActivity(intent);
                finish();
            }
        });
    }

    //메인으로 이동
    public void go_main(){
        Intent intent = new Intent(getApplicationContext(),MainActivity.class);
        startActivity(intent);
        finish();
    }

}