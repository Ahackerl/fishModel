package com.example.fishaq.internet;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;

import java.io.File;
import java.io.IOException;
import java.util.Set;
import java.util.UUID;


import androidx.annotation.NonNull;
import okhttp3.FormBody;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public abstract class FishHttp {

    private Handler handler;

    private final int TYPE_POST=1;
    private final int TYPE_GET=2;
    private final int TYPE_UPDATE_FILE=3;

    @SuppressWarnings("HandlerLeak")
    public FishHttp() {
        handler=new Handler(){
            @Override
            public void handleMessage(@NonNull Message msg) {
                super.handleMessage(msg);

                if(msg.what==TYPE_POST||msg.what==TYPE_GET||msg.what==TYPE_UPDATE_FILE){
                    OnCallBack((String) msg.obj);
                }
            }
        };

    }

    public abstract void OnCallBack(String result);

    //post请求
    public void okPost(final String url, final Bundle bundle) {

        new Thread(){
            @Override
            public void run() {
                super.run();

                FormBody.Builder bodyBuilder = new FormBody.Builder();

                Set<String> keySet = bundle.keySet();  //获取所有的Key,

                for(String key : keySet){  //bundle.get(key);来获取对应的value
                    bodyBuilder.add(key,bundle.getString(key));
                }

                FormBody body = bodyBuilder.build();

                OkHttpClient client=new OkHttpClient();
                Request request = new Request.Builder()
                        .url(url)
                        .post(body)
                        .build();
                Response response = null;
                try {
                    response = client.newCall(request).execute();
                    String res=response.body().string();

                    Message message=new Message();
                    message.what=TYPE_POST;
                    message.obj=res;
                    handler.sendMessage(message);

                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }.start();

    }

    //get请求
    public void okGet(final String url){

        new Thread(){
            @Override
            public void run() {
                super.run();
                OkHttpClient client=new OkHttpClient();
                Request request = new Request.Builder()
                        .url(url)
                        .get()
                        .build();
                Response response = null;
                try {
                    response = client.newCall(request).execute();
                    String res=response.body().string();

                    Message message=new Message();
                    message.what=TYPE_GET;
                    message.obj=res;
                    handler.sendMessage(message);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }.start();

    }

    //上传文件
    public void uploadFile(final String url, final String filePath, final String fileName)
    {

        new Thread(){
            @Override
            public void run() {
                super.run();
                OkHttpClient client = new OkHttpClient();
                RequestBody requestBody = new MultipartBody.Builder()
                        .setType(MultipartBody.FORM)
                        .addFormDataPart("file", fileName,
                                RequestBody.create(MediaType.parse("multipart/form-data"), new File(filePath)))
                        .build();

                Request request = new Request.Builder()
                        .header("Authorization", "Client-ID " + UUID.randomUUID())
                        .url(url)
                        .post(requestBody)
                        .build();

                Response response = null;
                try {
                    response = client.newCall(request).execute();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                if (!response.isSuccessful()) try {
                    throw new IOException("Unexpected code " + response);
                } catch (IOException e) {
                    e.printStackTrace();
                }

                Message message=new Message();
                message.what=TYPE_UPDATE_FILE;


                try {
                    message.obj=response.body().string();
                    handler.sendMessage(message);
                } catch (IOException e) {
                    message.obj="error";
                    handler.sendMessage(message);
                }
            }
        }.start();

    }

}
