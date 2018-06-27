package android.com.zlt;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

public class Agreement extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_agreement);
        getSupportActionBar().hide();
        TextView tv = findViewById(R.id.aa_tv);
        //实现滚动
        tv.setMovementMethod(ScrollingMovementMethod.getInstance());
        LinearLayout aa_fanhui = findViewById(R.id.aa_fanhui);
        aa_fanhui.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Agreement.this,AboutzltActivity.class);
                startActivity(intent);
            }
        });
    }
}
