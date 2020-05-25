package com.example.fishaq.db;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.List;

public class FishDbTools {

    private FishDbHelper helper;
    private SQLiteDatabase db;

    /**
     * 快速创建数据库操作对象
     * @param context
     * @param dbName
     * @param CreateTableSqlList
     */
    public FishDbTools(Context context, String dbName, List<String> CreateTableSqlList) {

        this.helper = new FishDbHelper(context,dbName,CreateTableSqlList);
        db=helper.getWritableDatabase();

    }


    /**
     * 快速使用已经创建的数据库
     * @param context
     * @param dbName
     */
    public FishDbTools(Context context, String dbName) {

        this.helper = new FishDbHelper(context,dbName);
        db=helper.getWritableDatabase();

    }

    /**
     * 插入删除或更新
     * @param sql
     * @param params
     */
    public void update(String sql,Object[] params){
        db.execSQL(sql,params);
    }

    /**
     * 查询
     * @param sql
     * @param params
     * @return
     */
    public Cursor search(String sql,String[] params){
       return db.rawQuery(sql,params);
    }

    /**
     * 关闭数据库访问
     */
    public void close()
    {
        db.close();
        helper.close();
    }

}
