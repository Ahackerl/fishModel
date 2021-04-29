package com.example.fishmodel;

import android.os.Bundle;
import com.example.fishaq.activity.login.FishLoginActivity;
import com.example.fishaq.util.EntityUtils;
import org.json.JSONObject;


public class MainActivity extends FishLoginActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);



        JSONObject jsonObject=new JSONObject();
        try {
            jsonObject.put("id",2);
            jsonObject.put("test","qwe");

            TestEntity testEntity1 = (TestEntity) EntityUtils.jsonObject2Obj(jsonObject, TestEntity.class);

            TestEntity testEntity=new TestEntity(1,"123");
            JSONObject jsonObject1 = EntityUtils.convert2JsonObject(testEntity);


        } catch (Exception e) {
            e.printStackTrace();
        }


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
