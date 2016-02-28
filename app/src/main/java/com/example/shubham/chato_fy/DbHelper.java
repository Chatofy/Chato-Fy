package com.example.shubham.chato_fy;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DbHelper extends SQLiteOpenHelper {

    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "Details.db";
    private static final String TABLE_NAME = "Details";
    private static final String COLUMN_ID = "id";
    private static final String COLUMN_ROLL = "roll";
    private static final String COLUMN_PASS = "pass";
    SQLiteDatabase db;
    private static final String TABLE_CREATE = "create table Details (id integer primary key not null, "+
                                                "roll text not null, "+
                                                "pass text not null);";

    public DbHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        this.db = db;
        db.execSQL(TABLE_CREATE);
    }

    public boolean insertDetails(Contact c){

        if(!c.getRollNumber().equals("")&&!c.getPassword().equals("")) {
            db = getWritableDatabase();
            ContentValues values = new ContentValues();

            String q = "select * from " + TABLE_NAME;
            Cursor cursor = db.rawQuery(q, null);
            int count = cursor.getCount();

            values.put(COLUMN_ID, count);
            values.put(COLUMN_ROLL, c.getRollNumber());
            values.put(COLUMN_PASS, c.getPassword());

            db.insert(TABLE_NAME, null, values);
            cursor.close();
            db.close();
        }
        return true;

    }
    public boolean updateDetails(String rollnnumber, String passWord){
        boolean result = false;
        String query = "select * from "+TABLE_NAME+" where "+COLUMN_ROLL+" = "+rollnnumber+";";
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(query, null);
        Contact c = new Contact();
        if(cursor.moveToFirst()) {
            c.setId(Integer.parseInt(cursor.getString(0)));
            ContentValues values = new ContentValues();

            values.put(COLUMN_PASS, passWord);

            // updating row
            db.update(TABLE_NAME, values, COLUMN_ID + " = ?",
                    new String[]{String.valueOf(c.getId())});
            cursor.close();
            db.close();
            result = true;
        }
        return result;
    }

    public String searchPassword(String roll){
        db = this.getReadableDatabase();
        String query = "select roll,pass from "+TABLE_NAME;
        Cursor cursor = db.rawQuery(query, null);
        String rollnumber , matchedPassword = "Not Found!";
        if(cursor.moveToFirst()){
            do{
                rollnumber = cursor.getString(0);

                if(rollnumber.equals(roll)){
                    matchedPassword = cursor.getString(1);
                    break;
                }
            }while(cursor.moveToNext());

        }
        cursor.close();
        return matchedPassword;
    }
    public boolean searchRoll(String roll){
        boolean flag=false;
        db = this.getReadableDatabase();

        String query = "select * from "+TABLE_NAME;
        Cursor cursor = db.rawQuery(query, null);
        String forgotrollNumber ;
        if(cursor.moveToFirst()){
            do{
                forgotrollNumber = cursor.getString(1);

                if(forgotrollNumber.equals(roll)){
                    flag =  true;
                    break;
                }
            }while(cursor.moveToNext());

        }
        cursor.close();
        return flag;
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        this.db = db;
        String query = "DROP TABLE IF EXISTS "+TABLE_NAME;
        db.execSQL(query);
        this.onCreate(db);

    }
}
