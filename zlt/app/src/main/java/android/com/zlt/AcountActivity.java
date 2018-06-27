package android.com.zlt;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

public class AcountActivity extends AppCompatActivity {

    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_acount);
        getSupportActionBar().hide();
        //获取信息
        final Intent intent = getIntent();
        final String phone = intent.getStringExtra("phone");
        final String password = intent.getStringExtra("password");
        final int userId = intent.getIntExtra("userId",0);
        TextView ac_ophone = findViewById(R.id.ac_ophone);
        ac_ophone.setText(phone);
        //点击返回按钮
        LinearLayout ac_fanhui = findViewById(R.id.ac_fanhui);
        ac_fanhui.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(AcountActivity.this,SetActivity.class);
                intent.putExtra("userId",userId);
                startActivity(intent);
                finish();
            }
        });
        //点击变更手机号按钮
        LinearLayout ac_changeOphone = findViewById(R.id.ac_changeOphone);
        ac_changeOphone.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent_changeOphone = new Intent(AcountActivity.this,ChangeOphone.class);
                intent_changeOphone.putExtra("phone",phone);
                intent_changeOphone.putExtra("userId",userId);
                intent_changeOphone.putExtra("password",password);
                startActivity(intent_changeOphone);
                finish();
            }
        });
        //点击密码管理
        LinearLayout ac_password = findViewById(R.id.ac_password);
        ac_password.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent_changePassword = new Intent(AcountActivity.this,ChangePassword.class);
                intent_changePassword.putExtra("phone",phone);
                intent_changePassword.putExtra("userId",userId);
                intent_changePassword.putExtra("password",password);
                startActivity(intent_changePassword);
                finish();
            }
        });
        //点击安全邮箱
        LinearLayout ac_securityMail = findViewById(R.id.ac_securityMail);
        ac_securityMail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent_securityMail = new Intent(AcountActivity.this,SecurityMail.class);
                intent_securityMail.putExtra("phone",phone);
                intent_securityMail.putExtra("userId",userId);
                intent_securityMail.putExtra("password",password);
                startActivity(intent_securityMail);
                finish();
            }
        });

    }
}
