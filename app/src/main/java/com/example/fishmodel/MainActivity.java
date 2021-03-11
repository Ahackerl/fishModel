package com.example.fishmodel;

import android.os.Bundle;
import android.widget.ImageView;

import com.example.fishaq.activity.login.FishLoginActivity;
import com.example.fishaq.util.EntityUtils;

import java.util.Map;

public class MainActivity extends FishLoginActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        TestEntity testEntity=new TestEntity(1,"123");
        Map map=EntityUtils.convert2Map(testEntity);

    }

    @Override
    protected void loginToDo(String account, String password) {
        //在这里实现登录
    }

    @Override
    protected void registerToDo() {
        //在这里实现注册跳转
    }

    @Override
    protected void retrieveToDo() {
        //在这里实现找回跳转
    }

    @Override
    protected int setIcon() {
        return R.drawable.ic_launcher_background;
    }


}
