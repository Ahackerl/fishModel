package com.example.fishmodel;

import androidx.appcompat.app.AppCompatActivity;

import android.database.Cursor;
import android.os.Bundle;
import android.widget.TextView;

import com.example.fishaq.activity.FishBaseActivity;
import com.example.fishaq.annotation.ViewInject;
import com.example.fishaq.db.FishDbHelper;
import com.example.fishaq.db.FishDbTools;
import com.example.fishaq.internet.FishHttp;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends FishBaseActivity {

    @ViewInject(R.id.test)
    TextView test;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //注入id示例
        test.setText("test");

        //数据库示例
        //首次需要创建表
        List<String> tableList=new ArrayList<>();
        tableList.add("create table bill (id integer primary key autoincrement,"+ //id主键
                "sum varchar(255)," +
                "type varchar(255))");
        FishDbTools fishDbTools=new FishDbTools(this,"test",tableList);
        fishDbTools.update("insert into bill (sum,type) values (?,?)",new Object[]{"test1","test2"});
        Cursor cursor=fishDbTools.search("select * from bill",null);
        fishDbTools.close();

        //非首次直接用数据库名访问
        FishDbTools dbTools=new FishDbTools(this,"test");
        Cursor cursor2=dbTools.search("select * from bill",null);
        cursor2.moveToNext();
        String sum=cursor2.getString(1);
        dbTools.close();


        //快速异步主线程http示例
        new FishHttp() {//get
            @Override
            public void OnCallBack(String result) {
                //Get the result here
                //在这里处理结果
            }
        }.okGet("http://www.baidu.com");

        Bundle bundle=new Bundle();
        bundle.putString("test","test");
        new FishHttp() {//post
            @Override
            public void OnCallBack(String result) {
                //Get the result here
                //在这里处理结果
            }
        }.okPost("http://www.baidu.com",bundle);

        new FishHttp() {//upload file
            @Override
            public void OnCallBack(String result) {
                //Get the result here
                //在这里处理结果//
            }
        }.uploadFile("http://path","/sdcard/file","test");
    }

    @Override
    public int initLayoutId() {
        return R.layout.activity_main;
    }
}
