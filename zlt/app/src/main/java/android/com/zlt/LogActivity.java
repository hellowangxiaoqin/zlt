package android.com.zlt;

import android.content.Intent;
import android.graphics.Color;
import android.os.Looper;
import android.provider.Settings;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.SpannedString;
import android.text.TextPaint;
import android.text.method.LinkMovementMethod;
import android.text.style.AbsoluteSizeSpan;
import android.text.style.ClickableSpan;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

import static android.widget.Toast.makeText;

public class LogActivity extends AppCompatActivity implements View.OnClickListener{
    //声明一个CheckBox 对象
    GlobalData userName;
    private CheckBox mycheckBox = null;
    private static int CODE =0 ;
    private OkHttpClient okHttpClient= new OkHttpClient();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_log);
        getSupportActionBar().hide(); //隐藏标题栏
        View v = findViewById(R.id.content);//找到你要设透明背景的layout 的id
        v.getBackground().setAlpha(240);//0~255透明度值
        TextView tvAgreement = findViewById(R.id.tv_agreement);
        SpannableString spanableInfo = new SpannableString(
                "我已阅读并同意《庄里通服务协议》");


        int firsStar = spanableInfo.toString().indexOf("《");
        int firstEnd = spanableInfo.toString().indexOf("》");
        int end = spanableInfo.length();
        //  1-管理服务协议页面； 2-金风险提示书页面
        spanableInfo.setSpan(new Clickable( 1), firsStar, firstEnd,
                Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);

        tvAgreement.setText(spanableInfo);
        int w= Color.WHITE;
        tvAgreement.setTextColor(w);
//        //setMovementMethod()该方法必须调用，否则点击事件不响应
        tvAgreement.setMovementMethod(LinkMovementMethod.getInstance());
        mycheckBox = findViewById(R.id.checkbox);
        //给CheckBox对象绑定单击监听事件
        mycheckBox.setOnClickListener(this);
        //将CheckBox默认设置为未选择状态
        mycheckBox.setChecked(true);

        Button btnLogin = findViewById(R.id.btn_login);
        btnLogin.setOnClickListener(this);
        TextView btnRegister = findViewById(R.id.btn_register);
        btnRegister.setOnClickListener(this);
        SpannableString ss = new SpannableString("用户名");
        AbsoluteSizeSpan ass = new AbsoluteSizeSpan(13,true);
        ss.setSpan(ass, 0, ss.length(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        SpannableString ss1 = new SpannableString("密码");
        ss1.setSpan(ass, 0, ss1.length(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        // 设置hint
        EditText et_password=findViewById(R.id.et_password);
        EditText et_userName=findViewById(R.id.et_userName);
        et_password.setHint(new SpannedString(ss1));
        et_userName.setHint(new SpannedString(ss));
    }
    //监听事件
    @Override
    public void onClick(View v) {
        //如果CheckBox选中的话
        if (mycheckBox.isChecked()){
            CODE = 1;
        }else {
            //如果CheckBox没有选中的话
        }
        switch (v.getId()){
            case R.id.btn_login:
                doLogin(CODE);
                break;
            case R.id.btn_register:
                doRegister();
                break;
        }
    }
    private void doLogin(int code){
        if(code == 1){
            final EditText etUsername = findViewById(R.id.et_userName);
            EditText etPassword = findViewById(R.id.et_password);
            Request request = new Request.Builder()
//                .post(requestBody)
                    .url(Constant.BASE_URL+"LoginServlet?userName="+etUsername.getText()+"&password="+etPassword.getText())
                    .build();

//        Log.e(TAG,BASE_URL+"LoginServlet?username="+etUsername.getText()+"&password="+etPassword.getText());
            //创建call对象，并执行请求
            final Call call = okHttpClient.newCall(request);
            //执行异步请求
            call.enqueue(new Callback() {
                @Override
                public void onFailure(Call call, IOException e) {

                    //解决在子线程中调用Toast的异常情况处理
//                    Looper.prepare();

//                    Looper.loop();
                }
                @Override
                public void onResponse(Call call, Response response) throws IOException {
//                Log.e(TAG,response.body().string());
                    String value = response.body().string();
                    if(value.equals("成功")){
                        GlobalData.setUserName(String.valueOf(etUsername.getText()));
                        Intent intent = new Intent(LogActivity.this,MainActivity.class);
                        Log.e("text1", String.valueOf(etUsername.getText()));
                        intent.putExtra("userName",etUsername.getText().toString());
                        Log.e("if can get userName",etUsername.getText().toString());
                        startActivity(intent);
                    }else{
                        Looper.prepare();
                        Toast toast= makeText(LogActivity.this, "用户名或密码错误", Toast.LENGTH_SHORT);
                        toast.setGravity(Gravity.CENTER, 0, 0);
                        toast.show();
                        Looper.loop();
                    }
                }
            });
        }

    }
    private void doRegister(){
        Intent intent = new Intent(LogActivity.this,RegisterActivity.class);
        startActivity(intent);
        overridePendingTransition(R.anim.anim_right_in,R.anim.anim_left_out);

    }

    class Clickable extends ClickableSpan {
        // 1-跳转到投资咨询及管理服务协议页面；
        private int type;

        public Clickable(int type) {
            super();
            this.type = type;
        }

        @Override
        public void updateDrawState(TextPaint ds) {
            super.updateDrawState(ds);
            ds.setColor(getResources().getColor(R.color.colorGray));
            ds.setUnderlineText(true);
        }

        @Override
        public void onClick(View v) {
            //            String userId = ConfigTools.getConfigValue(SyncStateContract.Constants.USER_ID, "");
            //            String token = PageUtils.getTokenUtf8();
            Intent intent = new Intent();
            intent.setClass(LogActivity.this,
                    SszWebviewActivity.class);
            startActivity(intent);
            overridePendingTransition(R.anim.anim_up_in,R.anim.anim_down_out);
            if (type == 1) {
                //处理管理服务协议的点击事件
                intent.putExtra("text", "hahhaah");
            } else {
                // 处理风险提示书的点击事件
            }
            startActivityForResult(intent, 0);
        }
    }
}
