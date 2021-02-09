package com.example.nadri2020;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;
import android.widget.Toast;

import java.util.ArrayList;

public class DBhelper extends SQLiteOpenHelper {

    Context context;
    public static final int DB_VERSION = 1;


    //IN
    public DBhelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
        this.context = context;
    }

    //IN DB생성
    public void onCreate(SQLiteDatabase db) {
        String s;
        s = " CREATE TABLE IF NOT EXISTS DRAMAREVIEWDB ("
                + " _ID INTEGER PRIMARY KEY AUTOINCREMENT, "
                + " DATE INTEGER NOT NULL, "
                + " NUMBER INTEGER NOT NULL, "
                + " TITLE STRING NOT NULL, "
                + " IMAGE STRING NOT NULL, "
                + " WATCHED INTEGER NOT NULL, "
                + " GENRE STRING, "
                + " REVIEW STRING )";

        db.execSQL(s);
        Toast.makeText(context, "Drama Table Created", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        Toast.makeText(context, "버전이 올라갔습니다", Toast.LENGTH_SHORT).show();

    }

    public void testDB() {
        SQLiteDatabase db = getReadableDatabase();
    }


    //드라마 내용 저장
    public void insertReview(Review review) {
        SQLiteDatabase db = getWritableDatabase();
        String s;
        s = "INSERT INTO DRAMAREVIEWDB ( "
                + " DATE, NUMBER, TITLE, IMAGE, WATCHED, GENRE, REVIEW )"
                + " VALUES ( ?, ?, ?, ?, ?, ?, ? )";

        db.execSQL(s, new Object[]{
                review.getDate(),
                review.getNumber(),
                review.getTitle(),
                review.getImage(),
                review.getWatched(),
                review.getGenre(),
                review.getReview()});
        Log.i("REVIEW DATA INSERT : ", "SUCCESS");
        Toast.makeText(context, "Review Insert 완료", Toast.LENGTH_SHORT).show();
    }

    public void dropTable() {
        SQLiteDatabase db = getWritableDatabase();
        db.execSQL("DROP TABLE DRAMAREVIEWDB");
    }

    //해당 제목에 해당하는 데이터를 리사이클러뷰에 뿌린다
    public ArrayList<Review> loadReview(String title) {
        //String s = " SELECT DATE, NUMBER, TITLE, IMAGE, WATCHED, GENRE, REVIEW FROM DRAMAREVIEWDB ORDER BY TITLE ";
        //title만 같은 값 서치

        String s = " SELECT * FROM DRAMAREVIEWDB WHERE TITLE = '"+ title + "'" + " ORDER BY NUMBER";
        Log.d("타이틀",s);

        SQLiteDatabase db = getReadableDatabase();
        Cursor cursor = db.rawQuery(s, null);
        ArrayList<Review> reviewList = new ArrayList<>();
        Review review = null;

        while(cursor.moveToNext()){
            review = new Review();
            review.setDate(cursor.getString(1));
            review.setNumber(cursor.getString(2));
            review.setTitle(cursor.getString(3));
            review.setImage(cursor.getString(4));
            review.setWatched(cursor.getInt(5));
            review.setReview(cursor.getString(7));

            reviewList.add(review);
        }
        cursor.close();
        Log.i("GET review DATA: ","SUCCESS");
        return reviewList;
    }

    //드라마 제목, 이미지 보기 - 메인
    public ArrayList<DataView> showDB(){
//        String s = " SELECT * FROM DRAMAREVIEWDB ";
        String s = " SELECT * FROM DRAMAREVIEWDB GROUP BY TITLE";


        SQLiteDatabase db = getReadableDatabase();
        Cursor cursor = db.rawQuery(s, null);

        ArrayList<DataView> reviewList = new ArrayList<>();

        DataView data = null;

        //cursor.moveToFirst();
        while(cursor.moveToNext()){
            data = new DataView(); //TODO:커스텀 모델 생성
            data.setTitle(cursor.getString(3));
            data.setImage(cursor.getString(4));

            reviewList.add(data); //리스트에 넣기
        }
        cursor.close();
        Log.i("GET review DRAMA: ","SUCCESS");

        return reviewList;
    }


    @Override
    public void onConfigure(SQLiteDatabase db) {
        super.onConfigure(db);
        db.disableWriteAheadLogging();
    }


    //삭제
    public Cursor deleteDB(){
        SQLiteDatabase db = this.getWritableDatabase();
        ArrayList<DataView> reviewList = new ArrayList<>();

        DataView data = null;

        // Select All Query
        String selectQuery = "SELECT TITLE, IMAGE FROM DRAMAREVIEWDB";
        Cursor cursor = null;
        try {
            cursor = db.rawQuery(selectQuery, null);
            db.setTransactionSuccessful();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            db.endTransaction();
        }
        return cursor;
    }

    //삭제
    public void delete(Review review) {
        //리뷰값 찾으면 속해있는 열 다 삭제되게
        String r = review.toString();
        SQLiteDatabase db = getWritableDatabase();

        db.execSQL("DELETE FROM DRAMAREVIEW WHERE REVIEW = '" + r + "';");

    }


}


