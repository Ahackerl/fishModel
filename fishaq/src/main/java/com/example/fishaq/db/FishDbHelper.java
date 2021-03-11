package com.example.fishaq.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.List;

/**
 * @author fish
 * 功能：SQLite相关
 */
public class FishDbHelper extends SQLiteOpenHelper {


    private List<String> sqlS;


    public FishDbHelper(Context context,String dbName,List<String> tableList) {
        super(context, dbName, null, 1);     //创建一个名为dbName数据库
        this.sqlS=tableList;
    }


    public FishDbHelper(Context context,String dbName) {
        super(context, dbName, null, 1);     //创建一个名为dbName数据库
    }


    @Override
    public void onCreate(SQLiteDatabase db) {

        for (String sql : sqlS) {
            db.execSQL(sql);
        }
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
