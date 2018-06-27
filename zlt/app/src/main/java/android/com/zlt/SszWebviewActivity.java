package android.com.zlt;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.method.ScrollingMovementMethod;
import android.widget.LinearLayout;
import android.widget.TextView;

public class SszWebviewActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ssz_webview);
        getSupportActionBar().hide();
        TextView tv = findViewById(R.id.ssz_tv);
        Intent intent = getIntent();
        final String text = intent.getStringExtra("text");
        //实现滚动
        tv.setMovementMethod(ScrollingMovementMethod.getInstance());
        LinearLayout aa_fanhui = findViewById(R.id.aa_fanhui);

    }
}
