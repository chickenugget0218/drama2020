package com.example.nadri2020;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.DatePicker;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ToggleButton;

import java.util.Calendar;

public class WriteDramaActivity extends AppCompatActivity {

    private TextView textView_Date;
    private TextView comment;
    private int id_button;
    private int flag1 = 0;

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

                //position 0,1,2에 값을 가져와 item변수에 저장후 toast로확인가능?
                //Toast.makeText(WriteDramaActivity.this,Integer.toString(position),Toast.LENGTH_SHORT).show();
                String item_num = String.valueOf(parent.getItemAtPosition(position));
                Toast.makeText(WriteDramaActivity.this, item_num, Toast.LENGTH_SHORT).show(); //9화로 뜸
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
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
                showcwriteMessage();
            }
        });

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
        comment = (TextView) findViewById(R.id.comment_wrt); //리뷰

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

      //  iv_back();
    }

    private void showcwriteMessage() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);

        builder.setMessage("리뷰를 등록하시겠습니까?\n");


        builder.setPositiveButton("예", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialogInterface, int which) {
                //넘겨주기 - 날짜, 리뷰텍스트, 회차(num), watch(flag-1234)
                Intent intent = new Intent();




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
               // comment = (TextView) findViewById(R.id.comment_wrt); //리뷰
               // comment.setText("");
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

    //new intent
    //넘겨주기 - 날짜, 리뷰텍스트, 회차(num), watch(flag-1234)
    //디비에 넘겨준다음 -> 리스트뷰에 불러오기!
    public void gotoDB(){
        
    }


    //날짜
    DatePickerDialog.OnDateSetListener mDateSetListener = new DatePickerDialog.OnDateSetListener() {
                @Override
                public void onDateSet(DatePicker datePicker, int yy, int mm, int dd) {
                    //datepicker에서 선택한 날짜를 textview에 설정
                    textView_Date.setText(String.format("%d년 %d월 %d일", yy, mm + 1, dd));
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

}