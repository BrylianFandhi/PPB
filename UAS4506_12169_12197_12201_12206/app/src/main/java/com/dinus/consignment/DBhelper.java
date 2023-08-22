package com.dinus.consignment;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class DBhelper extends SQLiteOpenHelper {
    public static final String DBNAME="DB_login.db";

    public DBhelper(Context context) {
        super(context, "DB_login.db", null, 1);
    }


    @Override
    public void onCreate(SQLiteDatabase MyDB) {
        MyDB.execSQL("create Table users(teksEmail TEXT primary key,teksPass TEXT)");

    }

    @Override
    public void onUpgrade(SQLiteDatabase MyDB, int i, int i1) {
        MyDB.execSQL("drop table if exists users");


    }

    public boolean insertdata(String teksEmail,String teksPass){
        SQLiteDatabase MyDB=this.getWritableDatabase();
        ContentValues contentValues=new ContentValues();
        contentValues.put("teksEmail",teksEmail);
        contentValues.put("teksPass",teksPass);
        long result= MyDB.insert("users",null,contentValues);
        if (result==-1) return false;
        else
            return true;
    }
    public boolean updatepassword(String teksEmail,String teksPass){
        SQLiteDatabase MyDB=this.getWritableDatabase();
        ContentValues contentValues=new ContentValues();
        contentValues.put("teksPass",teksPass);
        long result= MyDB.update("users",contentValues,"teksEmail=?",new String[] {teksEmail} );
        if (result==-1) return false;
        else
            return true;
    }

    public Boolean checkusername(String teksEmail){
        SQLiteDatabase MyDB=this.getWritableDatabase();
        Cursor cursor=MyDB.rawQuery("select*from users where teksEmail=?",new String[]{teksEmail});
        if (cursor.getCount()>0){
            return true;
        }
        else{
            return false;
        }

    }

    public Boolean checkusernamepassword(String teksEmail,String teksPass){
        SQLiteDatabase MyDB=this.getWritableDatabase();
        Cursor cursor=MyDB.rawQuery("select*from users where teksEmail=? and teksPass=?",new String[]{teksEmail,teksPass});
        if(cursor.getCount()>0){
            return true;
        }
        else{
            return false;
        }
    }
}
