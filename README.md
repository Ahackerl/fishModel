# fishModel
安卓便捷开发，包含多种快捷组件

# 添加maven仓库
```
allprojects {
    repositories {
        ...
        maven { url 'https://jitpack.io' }
    }
}
```
# 添加gradle依赖
```
dependencies {
    implementation 'com.github.Ahackerl:fishModel:1.0'
}
```

# 注解方式注入id教程
```
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
```
