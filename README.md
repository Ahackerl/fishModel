# fishModel
##### Android convenient development, including a variety of shortcut components.
##### 安卓便捷开发，包含多种组件。
##### The minimum API version is 21.
##### 最低API版本为21。

## Instructions（使用方法）

#### 1.Add maven repository(添加maven仓库)
```
allprojects {
    repositories {
        ...
        maven { url 'https://jitpack.io' }
    }
}
```
#### 2.add Gradle Dependency(添加Gradle依赖)
```
dependencies {
    implementation 'com.github.Ahackerl:fishModel:1.1'
}
```

#### Function 1: Annotate ID injection tutorial.(功能1：注解方式注入id教程)
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
#### Function 2: Quickly use the SQLite database tutorial.(功能2：快速使用SQLite数据库教程)
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
#### More functions are under development, welcome to join, QQ941131649.
#### 更多功能正在开发中，欢迎加入，QQ941131649。