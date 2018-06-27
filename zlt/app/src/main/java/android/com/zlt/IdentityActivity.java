package android.com.zlt;

import android.content.Intent;
import android.os.Looper;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.TextPaint;
import android.text.method.LinkMovementMethod;
import android.text.style.ClickableSpan;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

import static android.widget.Toast.makeText;

public class IdentityActivity extends AppCompatActivity implements View.OnClickListener{
    //声明一个CheckBox 对象
    GlobalData userName;
    private CheckBox mycheckBox = null;
    private static int CODE =0 ;
    private OkHttpClient okHttpClient= new OkHttpClient();
    int userId;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_identity);
        getSupportActionBar().hide(); //隐藏标题栏
        Intent intent = getIntent();
        userId = intent.getIntExtra("userId",0);
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
//        //setMovementMethod()该方法必须调用，否则点击事件不响应
        tvAgreement.setMovementMethod(LinkMovementMethod.getInstance());
        mycheckBox = findViewById(R.id.checkbox);
        //给CheckBox对象绑定单击监听事件
        mycheckBox.setOnClickListener(this);
        //将CheckBox默认设置为未选择状态
        mycheckBox.setChecked(true);

        Button id_certain = findViewById(R.id.id_certain);
        id_certain.setOnClickListener(this);
        LinearLayout id_fanhui = findViewById(R.id.id_fanhui);
        id_fanhui.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(IdentityActivity.this,SetActivity.class);
                intent.putExtra("userId",userId);
                startActivity(intent);
                finish();
            }
        });
    }

    @Override
    public void onClick(View v) {
        //如果CheckBox选中的话
        if (mycheckBox.isChecked()){
            CODE = 1;
        }else {
            //如果CheckBox没有选中的话
        }
        switch (v.getId()){
            case R.id.id_certain:
                doCertain(CODE);
                break;

        }
    }

    private void doCertain(int code) {
        if(code == 1){
            EditText realName = findViewById(R.id.id_realName);
            EditText number = findViewById(R.id.id_number);
            Request request = new Request.Builder()
                    .url(Constant.BASE_URL+"SaveIdentity?realName="+realName.getText()+"&number="+number.getText()+"&userId="+userId)
                    .build();
            //创建call对象，并执行请求
            final Call call = okHttpClient.newCall(request);
            //执行异步请求
            call.enqueue(new Callback() {
                @Override
                public void onFailure(Call call, IOException e) {

                    Log.e("t","fail");
                    Toast toast= Toast.makeText(IdentityActivity.this, "保存失败", Toast.LENGTH_SHORT);
                    toast.setGravity(Gravity.CENTER, 0, 0);
                    toast.show();
                }
                @Override
                public void onResponse(Call call, Response response) throws IOException {
                    String value = response.body().string();
                    if(value.equals("成功")){
                        Looper.prepare();
                        Toast toast= Toast.makeText(IdentityActivity.this, "保存成功", Toast.LENGTH_SHORT);
                        toast.setGravity(Gravity.CENTER, 0, 0);
                        toast.show();
                        Intent intent = new Intent(IdentityActivity.this,SetActivity.class);
                        intent.putExtra("userId",userId);
                        startActivity(intent);
                        Looper.loop();
                    }else{
                        Looper.prepare();
                        Toast toast= makeText(IdentityActivity.this, "保存失败", Toast.LENGTH_SHORT);
                        toast.setGravity(Gravity.CENTER, 0, 0);
                        toast.show();
                        Looper.loop();
                    }
                }
            });
        }
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
            intent.setClass(IdentityActivity.this,
                    SszWebviewActivity.class);
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
