package android.com.zlt;

import android.content.Intent;
import android.os.Looper;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Toast;

import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class SecurityMail extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_security_mail);
        getSupportActionBar().hide();
        Intent intent = getIntent();
        final String phone = intent.getStringExtra("phone");
        final int userId = intent.getIntExtra("userId",0);
        final String password = intent.getStringExtra("password");
        LinearLayout sm_fanhui = findViewById(R.id.sm_fanhui);
        sm_fanhui.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(SecurityMail.this,AcountActivity.class);
                intent.putExtra("userId",userId);
                intent.putExtra("phone",phone);
                intent.putExtra("password",password);
                startActivity(intent);
                finish();
            }
        });
        final EditText mail = findViewById(R.id.mail);
        Button sm_finish = findViewById(R.id.sm_finish);
        sm_finish.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(isEmail(mail.getText().toString())){
                    Log.e("text","t1");
                    //做网络请求
                    OkHttpClient okHttpClient = new OkHttpClient();
                    //请求对象
                    Request request = new Request.Builder()
                            .url(Constant.BASE_URL + "StoreEmail?userId="+userId+"&email="+mail.getText().toString())
                            .build();
                    Log.e("url",Constant.BASE_URL + "StoreEmail?userId="+userId+"&email="+mail.getText().toString());
                    final Call call = okHttpClient.newCall(request);
                    call.enqueue(new Callback() {
                        @Override
                        public void onFailure(Call call, IOException e) {
                            Log.e("t","t1");
                            Toast toast= Toast.makeText(SecurityMail.this, "设置失败", Toast.LENGTH_SHORT);
                            toast.setGravity(Gravity.CENTER, 0, 0);
                            toast.show();
                        }

                        @Override
                        public void onResponse(Call call, Response response) throws IOException {
                            String value = response.body().string();
                            Log.e("t",value);
                            if(value.equals("成功")){
                                Looper.prepare();
                                Toast toast= Toast.makeText(SecurityMail.this, "设置成功", Toast.LENGTH_SHORT);
                                toast.setGravity(Gravity.CENTER, 0, 0);
                                toast.show();
                                Intent intent = new Intent(SecurityMail.this,AcountActivity.class);
                                intent.putExtra("phone",phone);
                                intent.putExtra("password",password);
                                intent.putExtra("userId",userId);
                                startActivity(intent);
                                Looper.loop();
                            }else {
                                Looper.prepare();
                                Log.e("t","fail1");
                                Toast toast= Toast.makeText(SecurityMail.this, "设置失败", Toast.LENGTH_SHORT);
                                toast.setGravity(Gravity.CENTER, 0, 0);
                                toast.show();
                                Looper.loop();
                            }
                        }
                    });
                }

            }
        });
    }
        // 判断格式是否为email
        public boolean isEmail(String email) {
            String str = "^([a-zA-Z0-9_\\-\\.]+)@((\\[[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\.)|(([a-zA-Z0-9\\-]+\\.)+))([a-zA-Z]{2,4}|[0-9]{1,3})(\\]?)$";
            Pattern p = Pattern.compile(str);
            Matcher m = p.matcher(email);
            return m.matches();
        }

}
