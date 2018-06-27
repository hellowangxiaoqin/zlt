package android.com.zlt;

import android.content.Context;
import android.content.Intent;
import android.os.Handler;
import android.os.Looper;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

import java.io.IOException;
import java.util.HashMap;

import cn.smssdk.EventHandler;
import cn.smssdk.SMSSDK;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class SendCode extends AppCompatActivity {
    private int userId;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_send_code);
        Intent intent = getIntent();
        final String phone = intent.getStringExtra("phone");
        final String phone1 = intent.getStringExtra("phone1");
        final String password = intent.getStringExtra("password");
        userId = intent.getIntExtra("userId",0);
        final EditText sc_phone = findViewById(R.id.sc_phone);
        sc_phone.setText(phone);
        //点击返回
        LinearLayout sc_fanhui = findViewById(R.id.sc_fanhui);
        sc_fanhui.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(SendCode.this,ChangeOphone.class);
                intent.putExtra("userId",userId);
                intent.putExtra("phone",phone1);
                intent.putExtra("password",password);
                startActivity(intent);
                finish();
            }
        });
        final ImageView edit_cancel_number=findViewById(R.id.edit_cancel_number);
        //点击×的图片，会删除文本框中的名字
        edit_cancel_number.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sc_phone.setText(null);
            }
        });
        final Button sendCode = findViewById(R.id.sendCode);
        Button sc_change = findViewById(R.id.sc_change);
        sendCode.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sendCode("86",phone);
            }
        });
        sc_change.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EditText code = findViewById(R.id.code);
                submitCode("86",phone, code.getText().toString());
            }
        });
    }
    // 请求验证码，其中country表示国家代码，如“86”；phone表示手机号码，如“13800138000”
    public void sendCode(String country, String phone) {
        // 注册一个事件回调，用于处理发送验证码操作的结果
        SMSSDK.registerEventHandler(new EventHandler() {
            public void afterEvent(int event, int result, Object data) {
                if (result == SMSSDK.RESULT_COMPLETE) {
                    // TODO 处理成功得到验证码的结果
                    // 请注意，此时只是完成了发送验证码的请求，验证码短信还需要几秒钟之后才送达
                } else{
                    // TODO 处理错误的结果
                }

            }
        });
        // 触发操作
        SMSSDK.getVerificationCode(country, phone);
    }

    // 提交验证码，其中的code表示验证码，如“1357”
    public void submitCode(String country, final String phone, final String code) {
        // 注册一个事件回调，用于处理提交验证码操作的结果
        SMSSDK.registerEventHandler(new EventHandler() {
            public void afterEvent(int event, int result, Object data) {
                if (result == SMSSDK.RESULT_COMPLETE) {
                    // TODO 处理验证成功的结果
                    //做网络请求
                    OkHttpClient okHttpClient = new OkHttpClient();
                    //请求对象
                    Request request = new Request.Builder()
                            .url(Constant.BASE_URL + "ChangePhone?userId="+userId+"&phone="+phone)
                            .build();
                    String url = Constant.BASE_URL + "ChangePhone?userId="+userId+"&phone="+phone;
                    Log.e("text", url);
                    //发送请求对象
                    final Call call = okHttpClient.newCall(request);
                    call.enqueue(new Callback() {
                        @Override
                        public void onFailure(Call call, IOException e) {
                            Log.e("t","fail");
                            Toast toast= Toast.makeText(SendCode.this, "修改失败", Toast.LENGTH_SHORT);
                            toast.setGravity(Gravity.CENTER, 0, 0);
                            toast.show();
                        }

                        @Override
                        public void onResponse(Call call, Response response) throws IOException {
                            String value = response.body().string();
                            Log.e("t",value);
                            if(value.equals("成功")){
                                Looper.prepare();
                                Toast toast= Toast.makeText(SendCode.this, "修改成功", Toast.LENGTH_SHORT);
                                toast.setGravity(Gravity.CENTER, 0, 0);
                                toast.show();
                                Intent intent = new Intent(SendCode.this,AcountActivity.class);
                                intent.putExtra("phone",phone);
                                intent.putExtra("userId",userId);
                                startActivity(intent);
                                Looper.loop();
                            }else {
                                Looper.prepare();
                                Log.e("t","fail1");
                                Toast toast= Toast.makeText(SendCode.this, "修改失败", Toast.LENGTH_SHORT);
                                toast.setGravity(Gravity.CENTER, 0, 0);
                                toast.show();
                                Looper.loop();
                            }

                        }
                    });

                } else{
                    // TODO 处理错误的结果
                }

            }
        });
        // 触发操作
        SMSSDK.submitVerificationCode(country, phone, code);
    }

    protected void onDestroy() {
        super.onDestroy();
        //用完回调要注销掉，否则可能会出现内存泄露
        SMSSDK.unregisterAllEventHandler();
    };
}
