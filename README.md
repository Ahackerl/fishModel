# fishModel
##### Android convenient development, including a variety of shortcut components.
##### 安卓便捷开发，包含多种组件。
##### The minimum API version is 21.
##### 最低API版本为21。
##### Part of the code referenced from the network.
##### 部分代码参考自网络。

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
#### 2.Add Gradle dependency(添加Gradle依赖)
```
dependencies {
    implementation 'com.github.Ahackerl.fishModel:fishaq:1.7'
    implementation 'com.github.Ahackerl.fishModel:permission:1.7'
}
```

#### Function 1: Annotate ID injection tutorial.(功能1：注解方式注入id教程)
* 无标题栏
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
* 有标题栏
```
public class MainActivity extends FishBaseAppCompatActivity {

    @ViewInject(R.id.test)
    TextView test;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //注入id示例
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
#### Function 3: Fast asynchronous main thread http tutorial.(功能3：快速异步主线程http教程)
* 添加gradle依赖okhttp：implementation 'com.squareup.okhttp3:okhttp:3.3.1'
* 添加gradle依赖androidx：implementation 'androidx.appcompat:appcompat:1.1.0'
```
new FishHttp() {//get
    @Override
    public void OnCallBack(String result) {
        //Get the result here.Has returned to the ui main thread.
        //在这里处理结果,已经回到ui主线程
    }
}.okGet("http://www.baidu.com");



Bundle bundle=new Bundle();
bundle.putString("test","test");
//Currently the post bundle only supports strings.(目前post bundle仅支持字符串)
new FishHttp() {//post
    @Override
    public void OnCallBack(String result) {
        //Get the result here.Has returned to the ui main thread.
        //在这里处理结果,已经回到ui主线程
    }
}.okPost("http://www.baidu.com",bundle);


JSONObject jsonObject=new JSONObject();
jsonObject.putString("test","test");
new FishHttp() {//post
    @Override
    public void OnCallBack(String result) {
        //Get the result here.Has returned to the ui main thread.
        //在这里处理结果,已经回到ui主线程
    }
}.okPostBody("http://www.baidu.com",jsonObject);


new FishHttp() {//upload file
    @Override
    public void OnCallBack(String result) {
        //Get the result here.Has returned to the ui main thread.
        //在这里处理结果,已经回到ui主线程
    }
}.uploadFile("http://path","/sdcard/file","test");

```
#### Function 4: Quickly implement the login page.(功能4：快速实现登录页面)
* 可以通过访问父类成员重新设置头像,设置按钮,编辑框和复选框
* 可以通过重写方法initLayoutId()来更换布局
* 账户account的id为account
* 密码password的id为password
* 头像head的id为head
* 记住密码checkbox的id为remember
```
public class MainActivity extends FishLoginActivity {
    
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
        //在这里设置icon 这里1.4版本之前为资源文件中获取 目前修改为代码中设置图片
        return R.drawable.ic_launcher_background;
    }
}
```
#### Function 5: Entity class to HashMap.(功能5：实体类转HashMap)
* 可以通过调用方法将实体类转为HashMap
* 生成map的key为实体类中属性的名称 通过get方法获取
* 必须生成get方法
```
public class TestEntity {

    int id;
    String test;

    public TestEntity(int id, String test) {
        this.id = id;
        this.test = test;
    }

    public int getId() {
        return id;
    }

    public String getTest() {
        return test;
    }

}

TestEntity testEntity=new TestEntity(1,"123");
Map map=EntityUtils.convert2Map(testEntity);

```
#### Function 6: Entity class to JSONObject.(功能6：实体类转JSONObject)
* 可以通过调用方法将实体类转为JSONObject
```
public class TestEntity {

    int id;
    String test;

    public TestEntity(int id, String test) {
        this.id = id;
        this.test = test;
    }

    public int getId() {
        return id;
    }

    public String getTest() {
        return test;
    }

}

TestEntity testEntity=new TestEntity(1,"123");
JSONObject jsonObject = EntityUtils.convert2JsonObject(testEntity);

```
#### Function 7: JSONObject class to Entity.(功能7：JSONObject转实体类)
* 可以通过调用方法将JSONObject转为实体类
* 实体类必须生成无参构造方法
* 添加gradle依赖 implementation('com.fasterxml.jackson.core:jackson-core:2.11.1')
* 添加gradle依赖 implementation('com.fasterxml.jackson.core:jackson-annotations:2.11.1')
* 添加gradle依赖 implementation('com.fasterxml.jackson.core:jackson-databind:2.11.1')
```
public class TestEntity {

    int id;
    String test;

    public TestEntity() {

    }

    public TestEntity(int id, String test) {
        this.id = id;
        this.test = test;
    }

    public int getId() {
        return id;
    }

    public String getTest() {
        return test;
    }

}
JSONObject jsonObject=new JSONObject();
jsonObject.put("id",2);
jsonObject.put("test","qwe");

TestEntity testEntity = (TestEntity) EntityUtils.jsonObject2Obj(jsonObject, TestEntity.class);

```
#### Function 8: Encapsulation of dynamic permission application.(功能8：动态权限申请封装)
* 郭霖博主第一行代码最后动态权限申请的封装
* 添加java方式
```
//java方式
new PermissonXOnJava() {
    @Override
    public void callBack(boolean allGranted, List<String> deniedList) {

    }
}.request(this,Manifest.permission.CALL_PHONE);

//kotlin方式
PermissionX.request(this, Manifest.permission.CALL_PHONE) { allGranted, deniedList ->
    if (allGranted) {
        call()
    } else {
        Toast.makeText(this, "You denied $deniedList", Toast.LENGTH_SHORT).show()
    }
}
//其中request为变长参数，可传入多个权限。

```
#### Function 9: Uri trans to real path.(功能9：Uri转真实路径)
```
String filePath = Uri2PathUtil.getRealPathFromUri(MainActivity.this, originalUri);

```

# QQ群：434282886 欢迎！！！
