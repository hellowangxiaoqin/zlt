package android.com.zlt;

import android.content.Intent;
import android.graphics.Color;
import android.os.Looper;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ChangeOphone extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_change_ophone);
        getSupportActionBar().hide();
        Intent intent = getIntent();
        final String phone = intent.getStringExtra("phone");
        Log.e("ph",phone);
        final int userId = intent.getIntExtra("userId",0);
        final String password = intent.getStringExtra("password");
        final EditText edit_number=findViewById(R.id.edit_number);
        final ImageView edit_cancel_number=findViewById(R.id.edit_cancel_number);
        LinearLayout co_fanhui = findViewById(R.id.co_fanhui);
        Button co_next = findViewById(R.id.co_next);
        //设置编辑框中的内容是传入的号码
        edit_number.setText(phone);
        //点击×的图片，会删除文本框中的名字
        edit_cancel_number.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                edit_number.setText(null);
            }
        });
        co_fanhui.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ChangeOphone.this,AcountActivity.class);
                intent.putExtra("userId",userId);
                intent.putExtra("phone",phone);
                intent.putExtra("password",password);
                startActivity(intent);
                finish();
            }
        });
        //点击下一步
        co_next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String  phone1 = String.valueOf(edit_number.getText());
                Pattern p = Pattern.compile("^((13[0-9])|(15[^4,\\D])|(18[0-9]))\\d{8}$");
                Matcher m = p.matcher(phone1);
                Log.e("ph",phone);
                Log.e("ph1",phone1);
                if(m.matches() && !phone.equals(phone1)){
                    Intent intent=new Intent(ChangeOphone.this,SendCode.class);
                    intent.putExtra("phone1",phone);
                    intent.putExtra("phone",phone1);
                    intent.putExtra("userId",userId);
                    intent.putExtra("password",password);
                    startActivity(intent);
                    finish();
                }else {
//                    Looper.prepare();
                    Toast toast= Toast.makeText(ChangeOphone.this, "手机号格式不正确或者没有改变,请重新输入", Toast.LENGTH_SHORT);
                    toast.setGravity(Gravity.CENTER, 0, 0);
                    toast.show();
//                    Looper.loop();
                }

            }
        });
    }
}
