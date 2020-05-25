package com.example.fishmodel;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

import com.example.fishaq.activity.FishBaseActivity;
import com.example.fishaq.annotation.ViewInject;

public class MainActivity extends FishBaseActivity {

    @ViewInject(R.id.test)
    TextView test;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        test.setText("test");
    }

    @Override
    public int initLayoutId() {
        return R.layout.activity_main;
    }
}
