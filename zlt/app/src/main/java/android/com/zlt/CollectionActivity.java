package android.com.zlt;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

/**
 * Created by yangwenmiao on 2018/5/23.
 */

public class CollectionActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().hide(); //隐藏标题栏
        setContentView(R.layout.activity_collection);
        LinearLayout collection_fanhui=findViewById(R.id.collection_fanhui);
        final Intent intent=getIntent();
        final String userName=intent.getStringExtra("userName");
        collection_fanhui.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        LinearLayout xibaipo=findViewById(R.id.xibaipo);
        xibaipo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent1=new Intent(CollectionActivity.this,TravelActivity.class);
                startActivityForResult(intent1,2);
            }
        });
        final TextView collection_id1=findViewById(R.id.collection_id1);
        final TextView collection_id2=findViewById(R.id.collection_id2);
        final TextView collection_id3=findViewById(R.id.collection_id3);
        final TextView collection_id4=findViewById(R.id.collection_id4);
        final TextView collection_id5=findViewById(R.id.collection_id5);
        final TextView collection_id6=findViewById(R.id.collection_id6);
        final TextView collection_id7=findViewById(R.id.collection_id7);
        final TextView collection_id8=findViewById(R.id.collection_id8);
        collection_id1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(CollectionActivity.this,Collection_showActivity.class);
                intent.putExtra("neirong",collection_id1.getText().toString());
                startActivity(intent);
            }
        });
        collection_id2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(CollectionActivity.this,Collection_showActivity.class);
                intent.putExtra("neirong",collection_id2.getText().toString());
                startActivity(intent);
            }
        });
        collection_id3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(CollectionActivity.this,Collection_showActivity.class);
                intent.putExtra("neirong",collection_id3.getText().toString());
                startActivity(intent);
            }
        });
        collection_id4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(CollectionActivity.this,Collection_showActivity.class);
                intent.putExtra("neirong",collection_id4.getText().toString());
                startActivity(intent);
            }
        });
        collection_id5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(CollectionActivity.this,Collection_showActivity.class);
                intent.putExtra("neirong",collection_id5.getText().toString());
                startActivity(intent);
            }
        });
        collection_id6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(CollectionActivity.this,Collection_showActivity.class);
                intent.putExtra("neirong",collection_id6.getText().toString());
                startActivity(intent);
            }
        });
        collection_id7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(CollectionActivity.this,Collection_showActivity.class);
                intent.putExtra("neirong",collection_id7.getText().toString());
                startActivity(intent);
            }
        });
        collection_id8.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(CollectionActivity.this,Collection_showActivity.class);
                intent.putExtra("neirong",collection_id8.getText().toString());
                startActivity(intent);
            }
        });



    }
}
