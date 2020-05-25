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
    implementation 'com.github.Ahackerl:fishModel:1.1'
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
# 快速使用SQLite数据库教程
```
    //首次需要创建表 暂不支持数据库更新
    List<String> tableList=new ArrayList<>();
    tableList.add("create table bill (id integer primary key autoincrement,"+ //id主键
            "sum varchar(255)," +
            "type varchar(255))");
    FishDbTools fishDbTools=new FishDbTools(this,"test",tableList);
    fishDbTools.update("insert into bill (sum,type) values (?,?)",new Object[]{"test1","test2"});
    Cursor cursor=fishDbTools.search("select * from bill",null);
    fishDbTools.close();

    //非首次直接用数据库名访问
    FishDbTools dbTools=new FishDbTools(this,"test");
    Cursor cursor2=dbTools.search("select * from bill",null);
    cursor2.moveToNext();
    String sum=cursor2.getString(1);
    dbTools.close();
    
```

