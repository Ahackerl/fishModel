package com.example.fishmodel;

import android.os.Bundle;

import com.example.fishaq.activity.login.FishLoginActivity;

public class MainActivity extends FishLoginActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
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


}
