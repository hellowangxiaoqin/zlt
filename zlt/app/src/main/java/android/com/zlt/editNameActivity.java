package android.com.zlt;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.gson.Gson;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Created by yangwenmiao on 2018/5/23.
 */

public class editNameActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_editname);
        getSupportActionBar().hide();
        Intent intent_editname=getIntent();
        final EditText edit_name=findViewById(R.id.edit_name);
        final ImageView edit_cancel=findViewById(R.id.edit_cancel);
        TextView cancel_edit=findViewById(R.id.cancel_edit);
        final TextView finish_edit=findViewById(R.id.finish_edit);
        edit_name.setText(intent_editname.getStringExtra("name"));
        //点击×的图片，会删除文本框中的名字
        edit_cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                edit_name.setText(null);
                int i = Color.parseColor("#00FF00");
                finish_edit.setTextColor(i);
            }
        });
        //设置文本输入变化监听
        edit_name.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }
            @Override
            public void afterTextChanged(Editable s) {
                if (s.length() > 0) {
                    edit_cancel.setVisibility(View.VISIBLE);
                } else {
                    edit_cancel.setVisibility(View.GONE);
                }
            }
        });
        //点击取消更改名称，会返回上个页面
        cancel_edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        final OkHttpClient okHttpClient=new OkHttpClient();
        Handler handler=new Handler();
        //点击完成，用户名更改并返回上一个页面
        finish_edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent();
                String username=edit_name.getText().toString();
                Request request = new Request.Builder()
                        .url(Constant.BASE_URL + "editUserName?userName="+username)
                        .build();
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


//                Intent intent_name=new Intent(editNameActivity.this,PeopleActivity.class);
//                startActivity(intent_name);


                intent.putExtra("username",username);
                // 设置Intent回调 并设置回调内容
                setResult(2,intent);
                //结束当前页面
                finish();
            }
        });

    }
}
