package android.com.zlt;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;

public class AboutzltActivity extends AppCompatActivity {
    int userId;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_aboutzlt);
        getSupportActionBar().hide();
        Intent intent = getIntent();
        userId = intent.getIntExtra("userId",0);
        //点击返回
        LinearLayout azlt_fanhui = findViewById(R.id.azlt_fanhui);
        azlt_fanhui.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(AboutzltActivity.this,SetActivity.class);
                intent.putExtra("userId",userId);
                startActivity(intent);
                finish();
            }
        });
        //点击新功能介绍
        LinearLayout azlt_gongneng = findViewById(R.id.azlt_gongneng);
        azlt_gongneng.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent1 = new Intent(AboutzltActivity.this,NewFunction.class);
                startActivity(intent1);
            }
        });
        //点击用户协议
        LinearLayout azlt_agreement = findViewById(R.id.azlt_agreement);
        azlt_agreement.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent2 = new Intent(AboutzltActivity.this,Agreement.class);
                startActivity(intent2);
            }
        });
        LinearLayout azlt_serviceAgreement = findViewById(R.id.azlt_serviceAgreement);
        azlt_serviceAgreement.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent3 = new Intent(AboutzltActivity.this,ServiceAgreement.class);
                startActivity(intent3);
            }
        });
    }
}
