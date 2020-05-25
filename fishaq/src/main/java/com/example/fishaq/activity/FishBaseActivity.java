package com.example.fishaq.activity;

import android.app.Activity;
import android.os.Bundle;

import com.example.fishaq.annotation.ViewInject;

import java.lang.reflect.Field;

public abstract class FishBaseActivity extends Activity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(initLayoutId());
        injectViews();
    }

    /**
     * 通过反射的方式将id绑定到加上注解的View
     *
     * @author Yanbao_Wu
     */
    protected void injectViews(){
        Field[] fields = this.getClass().getDeclaredFields();
        for (Field field : fields) {
            if (field.isAnnotationPresent(ViewInject.class)) {
                ViewInject inject = field.getAnnotation(ViewInject.class);
                int id = inject.value();

                if (id > 0) {
                    field.setAccessible(true);
                    try {
                        field.set(this, this.findViewById(id));
                    } catch (IllegalArgumentException e) {
                        e.printStackTrace();
                    } catch (IllegalAccessException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
    }

    public abstract int initLayoutId();
}
