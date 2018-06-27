package android.com.zlt;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;

public class ProblemActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_problem);
        getSupportActionBar().hide(); //隐藏标题栏
        ImageView back_problem=findViewById(R.id.back_problem);
        back_problem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        LinearLayout kefu=findViewById(R.id.kefu);
        kefu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent_kefu=new Intent(ProblemActivity.this,KefuActivity.class);
                startActivity(intent_kefu);
            }
        });
        LinearLayout how_message=findViewById(R.id.how_message);
        how_message.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent_message=new Intent(ProblemActivity.this,HowMessageActivity.class);
                startActivity(intent_message);
            }
        });
    }
}
