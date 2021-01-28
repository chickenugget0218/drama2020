package com.example.nadri2020;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;
import android.widget.Toast;

import java.util.ArrayList;

public class DBhelper extends SQLiteOpenHelper {

    private Context context;
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
                + " REVIEW STRING NOT NULL )";

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
                + " DATE, NUMBER, TITLE, IMAGE, WATCHED, REVIEW )"
                + " VALUES ( ?, ?, ?, ?, ?, ? )";

        db.execSQL(s, new Object[]{
                Integer.parseInt(review.getDate()),
                Integer.parseInt(review.getNumber()),
                review.getTitle(),
                review.getImage(),
                review.getWatched(),
                review.getReview()});
        Log.i("REVIEW DATA INSERT : ", "SUCCESS");
        Toast.makeText(context, "Review Insert 완료", Toast.LENGTH_SHORT).show();
    }

    /*
    public ArrayList<Review> getReview(int data){
        String s;
        String d = Integer.toString(data);
        s = " SELECT "
    }
*/
    public void dropTable() {
    }

    //해당 데이터()를 가져오는 메소드
    public ArrayList<Review> loadReview() {
        String s = " SELECT TITLE, IMAGE FROM TITLEDB WHERE _ID = 0";
        SQLiteDatabase db = getReadableDatabase();
        Cursor cursor = db.rawQuery(s, null);
        ArrayList<Review> reviewList = new ArrayList<>();
        Review review = null;

        while(cursor.moveToNext()){
            review = new Review();
            review.setTitle(cursor.getString(0));
            review.setImage(cursor.getString(1));

            reviewList.add(review);
            cursor.close();
        }
        Log.i("GET INFO DATA: ","SUCCESS");
        return reviewList;
    }
}


