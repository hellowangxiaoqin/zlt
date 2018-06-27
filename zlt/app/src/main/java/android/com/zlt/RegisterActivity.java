package android.com.zlt;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Looper;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.SpannedString;
import android.text.style.AbsoluteSizeSpan;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class RegisterActivity extends AppCompatActivity implements View.OnClickListener{
    private OkHttpClient okHttpClient= new OkHttpClient();
//    private String CODE =null;
//    private CheckBox[] checkBoxes = new CheckBox[2];
    private String selectText = "男";
    @SuppressLint("ResourceType")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        getSupportActionBar().hide(); //隐藏标题栏
        RadioGroup rg = findViewById(R.id.rg);
        EditText userName = findViewById(R.id.userName);
        EditText password = findViewById(R.id.password);
        EditText phone = findViewById(R.id.phone);
        EditText detail = findViewById(R.id.detail);
//        // 新建一个可以添加属性的文本对象
//        SpannableString ss = new SpannableString("请输入用户名");
//        // 新建一个属性对象,设置文字的大小
//        AbsoluteSizeSpan ass = new AbsoluteSizeSpan(15,true);
//        ss.setSpan(ass, 0, ss.length(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
//        SpannableString ss1 = new SpannableString("请输入密码");
//        SpannableString ss2 = new SpannableString("请输入电话号码");
//        SpannableString ss3 = new SpannableString("50-60的个人简介");
//        ss1.setSpan(ass, 0, ss1.length(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
//        ss2.setSpan(ass, 0, ss2.length(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
//        ss3.setSpan(ass, 0, ss3.length(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
//        // 设置hint
//        userName.setHint(new SpannedString(ss));
//        password.setHint(new SpannedString(ss1));
//        phone.setHint(new SpannedString(ss2));
//        detail.setHint(new SpannedString(ss3));
        Button register = findViewById(R.id.register);
        register.setOnClickListener(this);
        rg.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
//                tip[0] = (checkedId == R.id.boy )? "男" : "女";
//                Log.e("text", tip[0]);
//                if(checkedId == R.id.boy){
//                    tip[0] = "男";
//                }else {
//                    tip[0] = "女";
//                }
                RadioButton radioButton = findViewById(group.getCheckedRadioButtonId());
                selectText = (String) radioButton.getText();
                Log.e("text",selectText);
            }
        });
        //点击返回
        LinearLayout error_fanhui=findViewById(R.id.error_fanhui);
        error_fanhui.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(RegisterActivity.this,LogActivity.class);

                startActivity(intent);
                overridePendingTransition(R.anim.anim_left_in,R.anim.anim_right_out);
                finish();
            }
        });
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.register:
                doRegister();
                break;
        }
    }
    private void doRegister(){
        EditText userName = findViewById(R.id.userName);
        EditText password = findViewById(R.id.password);
        EditText phone = findViewById(R.id.phone);
        EditText detail = findViewById(R.id.detail);
        Log.e("text", selectText);
        Request request = new Request.Builder()
                .url(Constant.BASE_URL+"UserServlet?userName="+userName.getText()+"&password="+password.getText()+"&phone="+phone.getText()+"&sex="+ selectText +"&detail="+detail.getText())
                .build();
        //创建call对象，并执行请求
        final Call call = okHttpClient.newCall(request);
        //执行异步请求
        call.enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                e.printStackTrace();
            }
            @Override
            public void onResponse(Call call, Response response) throws IOException {
//                Log.e(TAG,response.body().string());
                String value = response.body().string();
                Log.e("text",value);
                Toast toast = null;
                if(value.equals("成功")){
                    try {
                        if(toast!=null){
                            toast.setText(value);
                        }else{
                            toast= Toast.makeText(RegisterActivity.this, value, Toast.LENGTH_SHORT);
                            toast.setGravity(Gravity.CENTER, 0, 0);
                        }
                        toast.show();
                    } catch (Exception e) {
                        //解决在子线程中调用Toast的异常情况处理
                        Looper.prepare();
                        Toast.makeText(RegisterActivity.this, value, Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(RegisterActivity.this,LogActivity.class);
                        startActivity(intent);
                        overridePendingTransition(R.anim.anim_up_in,R.anim.anim_down_out);
                        Looper.loop();
                    }

                }else{
                    String text = value.substring(2,value.length()-1);
                    try {
                        if(toast!=null){
                            toast.setText(text);
                        }else{
                            toast= Toast.makeText(RegisterActivity.this, text, Toast.LENGTH_SHORT);
                            toast.setGravity(Gravity.CENTER, 0, 0);
                        }
                        toast.show();
                    } catch (Exception e) {
                        //解决在子线程中调用Toast的异常情况处理
                        Looper.prepare();
                        Toast.makeText(RegisterActivity.this, text, Toast.LENGTH_SHORT).show();
                        Looper.loop();
                    }
                }
            }
        });
    }
//    private boolean isPhoneNumber(String phoneStr) {
//        //定义电话格式的正则表达式
//        String regex = "^((13[0-9])|(15[^4,\\D])|(18[0,5-9]))\\d{8}$";
//        //设定查看模式
//        Pattern p = Pattern.compile(regex);
//        //判断Str是否匹配，返回匹配结果
//        Matcher m = p.matcher(phoneStr);
//        return m.find();
//    }
//    protected class CheckListener implements CompoundButton.OnCheckedChangeListener{
//
//        @Override
//        public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
//            if(isChecked) {
//                for (int i = 0; i < checkBoxes.length; i++) {
//                    //不等于当前选中的就变成false
//                    if (checkBoxes[i].getText().toString().equals(buttonView.getText().toString())) {
//                        checkBoxes[i].setChecked(true);
//                        if(i == 0){
//                            CODE = "男";
//                        }else {
//                            CODE = "女";
//                        }
//                    } else {
//                        checkBoxes[i].setChecked(false);
//                    }
//                }
//            }
//        }
//    }
    private void selectRadioBtn(){

    }
    protected class CheckedListener implements RadioGroup.OnCheckedChangeListener{

    @Override
    public void onCheckedChanged(RadioGroup group, int checkedId) {

    }
}
}
