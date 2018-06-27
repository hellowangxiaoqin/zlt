package android.com.zlt;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

public class TcontentActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tcontent);
        getSupportActionBar().hide();
        TextView tittle=findViewById(R.id.tittle);
        TextView content=findViewById(R.id.content);
        ImageView imageView=findViewById(R.id.imageview);
        Intent intent=getIntent();
        TouTiao touTiao= (TouTiao) intent.getSerializableExtra("touTiao");
        tittle.setText(touTiao.getTitle());
        Glide.with(this)
                .load(Constant.BASE_URL+touTiao.getNewsImages())
                .into(imageView);
        content.setText("\u3000\u3000"+touTiao.getContent());
    }
}
