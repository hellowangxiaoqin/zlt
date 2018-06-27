package android.com.zlt;

import android.content.Intent;
import android.os.Looper;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Toast;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class ChangePassword extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_change_password);
        getSupportActionBar().hide();
        Intent intent = getIntent();
        final String phone = intent.getStringExtra("phone");
        final int userId = intent.getIntExtra("userId",0);
        final String password = intent.getStringExtra("password");
        Log.e("p",password);
        final EditText original_password = findViewById(R.id.original_password);
        final EditText new_password = findViewById(R.id.new_password);
        final EditText new_password1 = findViewById(R.id.new_password1);
//        final String newPassword = new_password.getText().toString();
//        final String newPassword1 = new_password1.getText().toString();
//        final String originalPassword =  original_password.getText().toString();
        Button cp_change = findViewById(R.id.cp_change);
        LinearLayout cp_fanhui = findViewById(R.id.cp_fanhui);
        //点击返回
        cp_fanhui.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ChangePassword.this,AcountActivity.class);
                intent.putExtra("userId",userId);
                intent.putExtra("phone",phone);
                intent.putExtra("password",password);
                startActivity(intent);
                finish();
            }
        });
        //在输入原始密码失去焦点后
        original_password.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if(!hasFocus){
                    Log.e("o", original_password.getText().toString());
                    if (!password.equals(original_password.getText().toString()) ){
                        Toast toast= Toast.makeText(ChangePassword.this, "密码不正确", Toast.LENGTH_SHORT);
                        toast.setGravity(Gravity.CENTER, 0, 0);
                        toast.show();
                    }
                }
            }
        });
        //在输入新密码失去焦点后
        new_password.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if(!hasFocus){
                    if (!new_password.getText().toString().matches("^(?![0-9]+$)(?![a-zA-Z]+$)[0-9A-Za-z]{6,20}$") ){
                        Toast toast= Toast.makeText(ChangePassword.this, "密码格式不正确", Toast.LENGTH_SHORT);
                        toast.setGravity(Gravity.CENTER, 0, 0);
                        toast.show();
                    }
                }
            }
        });
        //在输入确认密码失去焦点后
        new_password1.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if(!hasFocus){
                    if (!new_password.getText().toString().equals(new_password1.getText().toString()) ){
                        Toast toast= Toast.makeText(ChangePassword.this, "密码不正确", Toast.LENGTH_SHORT);
                        toast.setGravity(Gravity.CENTER, 0, 0);
                        toast.show();
                    }
                }
            }
        });
        //点击修改按钮
        cp_change.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //做网络请求
                OkHttpClient okHttpClient = new OkHttpClient();
                //请求对象
                Request request = new Request.Builder()
                        .url(Constant.BASE_URL + "ChangePassword?userId="+userId+"&newPassword="+new_password.getText().toString())
                        .build();
                final Call call = okHttpClient.newCall(request);
                call.enqueue(new Callback() {
                    @Override
                    public void onFailure(Call call, IOException e) {
                        Log.e("t","t1");
                        Toast toast= Toast.makeText(ChangePassword.this, "修改失败", Toast.LENGTH_SHORT);
                        toast.setGravity(Gravity.CENTER, 0, 0);
                        toast.show();
                    }

                    @Override
                    public void onResponse(Call call, Response response) throws IOException {
                        String value = response.body().string();
                        Log.e("t",value);
                        if(value.equals("成功")){
                            Looper.prepare();
                            Toast toast= Toast.makeText(ChangePassword.this, "修改成功", Toast.LENGTH_SHORT);
                            toast.setGravity(Gravity.CENTER, 0, 0);
                            toast.show();
                            Intent intent = new Intent(ChangePassword.this,AcountActivity.class);
                            intent.putExtra("phone",phone);
                            intent.putExtra("password",new_password.getText().toString());
                            intent.putExtra("userId",userId);
                            startActivity(intent);
                            Looper.loop();
                        }else {
                            Looper.prepare();
                            Log.e("t","fail1");
                            Toast toast= Toast.makeText(ChangePassword.this, "修改失败", Toast.LENGTH_SHORT);
                            toast.setGravity(Gravity.CENTER, 0, 0);
                            toast.show();
                            Looper.loop();
                        }
                    }
                });
            }
        });
    }
}
