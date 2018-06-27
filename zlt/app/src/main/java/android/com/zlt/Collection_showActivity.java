package android.com.zlt;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

public class Collection_showActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_collection_show);
        getSupportActionBar().hide();
        Intent intent=getIntent();
        TextView collection_showNeirong=findViewById(R.id.collection_getNeirong);
        collection_showNeirong.setText(intent.getStringExtra("neirong"));
        ImageView fanhui=findViewById(R.id.back_coll_show);
        //点击返回
        fanhui.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }
}
