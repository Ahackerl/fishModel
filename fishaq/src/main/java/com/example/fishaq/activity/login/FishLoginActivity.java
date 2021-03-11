package com.example.fishaq.activity.login;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;

import androidx.core.content.ContextCompat;

import com.example.fishaq.R;
import com.example.fishaq.activity.FishBaseAppCompatActivity;


/**
 * @author fish
 * 功能：登录activity
 */
public abstract class FishLoginActivity extends FishBaseAppCompatActivity {

    public EditText account;
    public EditText password;
    public ImageView head;
    public CheckBox remember;
    public SharedPreferences sharedPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        account=findViewById(R.id.account);
        password=findViewById(R.id.password);
        head=findViewById(R.id.head);
        remember=findViewById(R.id.remember);

        head.setImageDrawable(ContextCompat.getDrawable(getApplicationContext(),setIcon()));
        sharedPreferences=getSharedPreferences("login",MODE_PRIVATE);

        remember.setChecked(sharedPreferences.getBoolean("remember",false));
        account.setText(sharedPreferences.getString("account",""));
        password.setText(sharedPreferences.getString("password",""));
    }

    @Override
    public int initLayoutId() {
        return R.layout.login_activity;
    }

    public void retrieve(View view) {
        retrieveToDo();
    }

    public void register(View view) {
        registerToDo();
    }

    public void login(View view) {
        if(remember.isChecked()){
            SharedPreferences.Editor editor=sharedPreferences.edit();
            editor.putString("account",account.getText().toString());
            editor.putString("password",password.getText().toString());
            editor.putBoolean("remember",true);
            editor.apply();
        }else {
            SharedPreferences.Editor editor=sharedPreferences.edit();
            editor.putString("account","");
            editor.putString("password","");
            editor.putBoolean("remember",false);
            editor.apply();
        }
        loginToDo(account.getText().toString(),password.getText().toString());
    }

    protected abstract void loginToDo(String account, String password);
    protected abstract void registerToDo();
    protected abstract void retrieveToDo();
    protected abstract int setIcon();
}
