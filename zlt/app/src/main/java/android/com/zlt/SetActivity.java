package android.com.zlt;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.LinearLayout;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.IOException;
import java.lang.reflect.Type;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class SetActivity extends AppCompatActivity {

    private User user;
    private OkHttpClient okHttpClient;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_set);
        getSupportActionBar().hide(); //隐藏标题栏
        //获得用户id
        Intent intent_set=getIntent();
        int userId=intent_set.getIntExtra("userId",0);
        Log.e("text", String.valueOf(userId));
        final Gson gson=new Gson();
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
                call.enqueue(new Callback() {
                    @Override
                    public void onFailure(Call call, IOException e) {
                        e.printStackTrace();
                        Log.e("getUserMessage","failure");
                    }

                    @Override
                    public void onResponse(Call call, Response response) throws IOException {
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
                        user=gson.fromJson(userListStr,type);
                        Log.e("user",user.getPhone());
                    }
                });
        //点击返回按钮
        LinearLayout set_fanhui=findViewById(R.id.set_fanhui);
        set_fanhui.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        //点击账号与安全按钮
        LinearLayout account_security = findViewById(R.id.Account_security);
        account_security.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(SetActivity.this,AcountActivity.class);
                intent.putExtra("phone",user.getPhone());
                intent.putExtra("password",user.getPassword());
                intent.putExtra("userId",user.getUserId());
                startActivity(intent);
                finish();
            }
        });
        //点击实名认证
        LinearLayout identity=findViewById(R.id.identity);
        identity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent_identity=new Intent(SetActivity.this,IdentityActivity.class);
                intent_identity.putExtra("userId",user.getUserId());
                startActivity(intent_identity);
            }
        });
        //点击退出时
        LinearLayout tuichu_login=findViewById(R.id.tuichu_login);
        tuichu_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(SetActivity.this,LogActivity.class);
                startActivity(intent);
                finish();
            }
        });
        //点击关于庄里通时
        LinearLayout for_zhuanglitong = findViewById(R.id.for_zhuanglitong);
        for_zhuanglitong.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(SetActivity.this,AboutzltActivity.class);
                intent.putExtra("userId",user.getUserId());
                startActivity(intent);
                finish();
            }
        });
    }
}
