package com.example.fishmodel;

import androidx.appcompat.app.AppCompatActivity;

import android.database.Cursor;
import android.os.Bundle;
import android.widget.TextView;

import com.example.fishaq.activity.FishBaseActivity;
import com.example.fishaq.activity.FishBaseAppCompatActivity;
import com.example.fishaq.annotation.ViewInject;
import com.example.fishaq.db.FishDbHelper;
import com.example.fishaq.db.FishDbTools;
import com.example.fishaq.internet.FishHttp;


import java.util.ArrayList;
import java.util.List;

public class MainActivity extends FishBaseAppCompatActivity {

    @ViewInject(R.id.test)
    TextView test;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //注入id示例
        test.setText("test");
    }

    @Override
    public int initLayoutId() {
        return R.layout.activity_main;
    }
}
