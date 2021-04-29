package com.example.fishmodel;

import android.Manifest;
import android.os.Bundle;
import android.widget.Toast;

import com.example.fishaq.activity.login.FishLoginActivity;
import com.example.fishaq.util.EntityUtils;
import com.permissionx.guolindev.PermissionX;
import com.permissionx.guolindev.PermissonXOnJava;

import org.json.JSONObject;

import java.util.List;


public class MainActivity extends FishLoginActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        new PermissonXOnJava() {
            @Override
            public void callBack(boolean allGranted, List<String> deniedList) {

            }
        }.request(this,Manifest.permission.CALL_PHONE);


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
