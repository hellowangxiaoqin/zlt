package android.com.zlt;

import android.Manifest;
import android.annotation.TargetApi;
import android.content.ContentUris;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.media.Image;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.provider.DocumentsContract;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Type;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

/**
 * Created by yangwenmiao on 2018/5/23.
 */

public class PeopleActivity extends AppCompatActivity {
    private File file;
    private static final int REQUEST_CODE =2 ;
    private static final int REQUEST_PERMISSION =0 ;
    private static final int REQUEST_READ = 0;
    private static final String TAG ="OKHTTP" ;
    private OkHttpClient okHttpClient;
    private String imageUrl;
    private ImageView people_image;
    private TextView people_userName;
    private TextView people_number;
    private Handler handler;
    //用来存放更多活动页传来的信息
    private int user_id;
    private String password;
    private String name_more;
    private String sex_more;
    private String birth_more;
    private String city_more;
    private User userMessage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_people);
        getSupportActionBar().hide(); //隐藏标题栏
        Intent intent_people=getIntent();
        handler = new Handler();
        people_image=findViewById(R.id.people_image);
        people_userName=findViewById(R.id.people_userName);
        people_number=findViewById(R.id.people_number);
        final User user =new User();
        final Gson gson=new Gson();
        //得到了用户的id；
        int userId=intent_people.getIntExtra("userId",0);
        String userid=gson.toJson(userId);
        //创建请求体
        RequestBody requestBody = RequestBody.create(
                MediaType.parse("text/plain;charset=utf-8")
                ,userid
        );
        okHttpClient=new OkHttpClient();
        //创建请求
        Request request = new Request.Builder()
                .post(requestBody)
                .url(Constant.BASE_URL + "getUserMessage")
                .build();
        //创建call对象，接收服务器端返回消息
        final Call call=okHttpClient.newCall(request);
        new Thread(){//开启一个新的线程
            @Override
            public void run() {
                call.enqueue(new Callback() {
                    @Override
                    public void onFailure(Call call, IOException e) {
                        e.printStackTrace();
                        Log.e("getUserMessage","failure");
                    }
                    @Override
                    public void onResponse(Call call, final Response response) throws IOException {
                        handler.post(new Runnable() {
                            @Override
                            public void run() {
                                Log.e("getUserMessage","success");
                                String userListStr=null;
                                try{
                                    userListStr=response.body().string();
                                    Log.e("text",userListStr);
                                } catch (IOException e) {
                                    e.printStackTrace();
                                }
                                Type type=new TypeToken<User>(){}.getType();
                                //从服务器端得到用户的数据，存储到用户类中
                                 userMessage=gson.fromJson(userListStr,type);

                                Log.e("text",userMessage.getUserName());
                                Log.e("text",userMessage.getPassword());
                                Log.e("text", String.valueOf(userMessage.getUserId()));
                                Log.e("text", userMessage.getDetail());
                                if(userMessage.getDetail()==null){
                                    name_more="null";
                                }else{
                                    name_more=userMessage.getDetail();
                                }
                                if(userMessage.getBirth()==null){
                                    birth_more="null";
                                }else{
                                    birth_more=userMessage.getBirth();
                                }
                                if(userMessage.getSex()==null){
                                    sex_more="null";
                                }else{
                                    sex_more=userMessage.getSex();
                                }
                                if(userMessage.getAddress()==null){
                                    city_more="null";
                                }else{
                                    city_more=userMessage.getAddress();
                                }

                                password=userMessage.getPassword();

                                Log.e("text",name_more);
                                Log.e("text",sex_more);
                                Log.e("text",birth_more);
                                Log.e("text",city_more);
                                user_id=userMessage.getUserId();
                                //将用户的名字从数据库获取，放到用户名的text中
                                people_userName.setText(userMessage.getUserName());
                                //获取用户的名字
                                final String mingzi=people_userName.getText().toString();
                                //点击更换用户的用户名
                                people_userName.setOnClickListener(new View.OnClickListener() {
                                    @Override
                                    public void onClick(View v) {
                                        Intent intent_name=new Intent(PeopleActivity.this,editNameActivity.class);
                                        //将用户的名字传入编辑姓名的活动页面
                                        intent_name.putExtra("name",mingzi);
                                        startActivityForResult(intent_name,3);
                                    }
                                });
                                //获取用户头像的图片路径
                                imageUrl=userMessage.getImage();
                                if (imageUrl==null){
                                    imageUrl="null";
                                    people_image.setImageResource(0);
                                }else {
                                    Log.e("test",imageUrl);
                                    //将图片加载到界面显示
                                    Glide.with(PeopleActivity.this).load(Constant.BASE_URL
                                            +"images/"+imageUrl).into(people_image);
                                }
                                //将用户的电话号码从数据库获取放到电话视图中
                                people_number.setText(userMessage.getPhone());
                                //获取用户的手机号码
                                final String number=people_number.getText().toString();
                                //更改用户的手机号码
                                people_number.setOnClickListener(new View.OnClickListener() {
                                    @Override
                                    public void onClick(View v) {
                                        Intent intent_number=new Intent(PeopleActivity.this,editNumberActivity.class);
                                        //将用户的手机号码传入编辑手机号的活动页面
                                        intent_number.putExtra("number",number);
                                        startActivityForResult(intent_number,4);
                                    }
                                });

                                //获取更多的视图控件
                                TextView people_more=findViewById(R.id.people_more);
                                people_more.setOnClickListener(new View.OnClickListener() {
                                    @Override
                                    public void onClick(View v) {
                                        String gexing=userMessage.getDetail();
                                        String sex=userMessage.getSex();
                                        String birth=userMessage.getBirth();
                                        String address=userMessage.getAddress();
                                        if (gexing==null||sex==null||birth==null||address==null){
                                            gexing="null";
                                            sex="null";
                                            birth="null";
                                            address="null";
                                        }
                                        Log.e("test",gexing);
                                        Log.e("test",sex);
                                        Log.e("test",birth);
                                        Log.e("test",address);
                                        Intent intent_more=new Intent(PeopleActivity.this,editMoreActivity.class);
                                        intent_more.putExtra("gexing",gexing);
                                        intent_more.putExtra("sex",sex);
                                        intent_more.putExtra("birth",birth);
                                        intent_more.putExtra("address",address);
                                        startActivityForResult(intent_more,5);
                                    }
                                });

                            }
                        });
                    }
                });

            }

        }.start();


        //获取账号管理界面中的视图
        people_image=findViewById(R.id.people_image);
        people_userName=findViewById(R.id.people_userName);
        people_number=findViewById(R.id.people_number);
        //点击打开手机相册选择图片，并显示到页面上
        people_image.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ActivityCompat.requestPermissions(PeopleActivity.this,new
                String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE},REQUEST_PERMISSION);
            }
        });
        //点击箭头返回我的管理界面
        ImageView back_people=findViewById(R.id.back_people);
        back_people.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //获取所有修改过的用户的信息
                String usname=people_userName.getText().toString();
                String usnumber=people_number.getText().toString();
//                Log.e("test",name_more);
                Log.e("test",sex_more);
                Log.e("test",birth_more);
//                Log.e("test",city_more);
//                Log.e("test",usname);
//                Log.e("test",usnumber);
//                Log.e("test", String.valueOf(user_id));
                Request request = new Request.Builder()
//                .post(requestBody)
                        .url(Constant.BASE_URL+
                                "editUserMessage?username="+usname+
                                "&password="+password+
                                "&userId="+user_id+
                                "&sex="+sex_more+
                                "&phone="+usnumber+
                                "&name_more="+name_more+
                                "&birth_more="+birth_more+
                                "&city_more="+city_more+
                                "&imgurl="+usname+".jpg")
                        .build();
                //创建call对象，并执行请求
                final Call call = okHttpClient.newCall(request);
                new Thread() {//开启一个新的线程
                    @Override
                    public void run() {
                        //执行异步请求
                        call.enqueue(new Callback() {
                            @Override
                            public void onFailure(Call call, IOException e) {
                                Log.e("test", "failure");
                                e.printStackTrace();
                            }
                            @Override
                            public void onResponse(Call call, Response response) throws IOException {
                                Log.e("test","true");
                            }
                        });
                    }
                }.start();
                finish();
            }
        });
    }
    //相册界面返回之后的回调方法
    protected void onActivityResult(int requestCode,int resultCode,Intent data){
       super.onActivityResult(requestCode,resultCode,data);
        switch (requestCode){
            case REQUEST_CODE:
                if (resultCode==RESULT_OK){
                    //获取照片
                    Uri uri = data.getData();
                    Cursor cursor = getContentResolver().query(uri,null,null,null,null);
                    cursor.moveToFirst();
                    String column = MediaStore.Images.Media.DATA;
                    int columIndex = cursor.getColumnIndex(column);
                    String path = cursor.getString(columIndex);
                    file = new File(path);
                    Bitmap bitmap = BitmapFactory.decodeFile(path);
                    people_image.setImageDrawable(new BitmapDrawable(getResources(),bitmap));
                    doUploadFile(file);
                }
                break;
            //接收来自更改用户名的回调内容，并显示到页面，请求码为3
            case 3:
                if (resultCode==2){
                    people_userName.setText(data.getStringExtra("username").toString());
                }
                break;
            //接收来自更改用户的手机号码的回调内容，并显示到页面，请求码为4
            case 4:
                if (resultCode==3){
                    people_number.setText(data.getStringExtra("number").toString());
                }
                break;
            case 5:
                if(resultCode==4){
                    name_more=data.getStringExtra("name_more").toString();
                    sex_more=data.getStringExtra("sex_more").toString();
                    birth_more=data.getStringExtra("birth_more").toString();
                    city_more=data.getStringExtra("city_more").toString();
                    Log.e("fanhuisesx",sex_more);
                }
                break;
        }

    }
    //上传文件
    private void doUploadFile(File file){
        //创建请求体
        RequestBody requestBody = RequestBody.create(MediaType.parse("images/*"),file);
        Request request = new Request.Builder()
                .url(Constant.BASE_URL+"UploadFile?username="+people_userName.getText())
                .post(requestBody)
                .build();
        //创建call对象，并执行请求
        final Call call = okHttpClient.newCall(request);
        //执行异步请求
        call.enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                Log.e("Upload","false");
                e.printStackTrace();
            }
            @Override
            public void onResponse(Call call, Response response) throws IOException {
                Log.e(TAG,response.body().string());
            }
        });
    }
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        //打开手机相册
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        Intent intent = new Intent();
        intent.setAction(Intent.ACTION_PICK);
        intent.setType("image/*");
        startActivityForResult(intent,REQUEST_CODE);
    }

}
