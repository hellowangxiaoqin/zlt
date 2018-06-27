package android.com.zlt;

import android.graphics.Color;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

/**
 * Created by 王玉波 on 2018/5/30.
 */

public class ComPayments extends AppCompatActivity {
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_payment);
        Button btn_cancel=findViewById(R.id.cancel);
        btn_cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
       final Button btn_pic1=findViewById(R.id.pic1);
        final Button btn_pic2=findViewById(R.id.pic2);
        final Button btn_pic3=findViewById(R.id.pic3);
        final Button btn_pic4=findViewById(R.id.pic4);
        final Button btn_pic5=findViewById(R.id.pic5);
        final Button btn_pic6=findViewById(R.id.pic6);
        View.OnClickListener cl = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (v.getId() == R.id.pic1) {//如果是myself按钮，则设置一种背景
                    btn_pic1.setBackgroundColor(Color.parseColor("#e6e6fa"));

                } else {//如果不是myself按钮，则设置回来。
                    btn_pic1.setBackgroundColor(Color.parseColor("#f5f5f5"));

                }
            }
        };
        btn_pic1.setOnClickListener(cl);
        btn_pic2.setOnClickListener(cl);
        btn_pic3.setOnClickListener(cl);
        btn_pic4.setOnClickListener(cl);
        btn_pic5.setOnClickListener(cl);
        btn_pic6.setOnClickListener(cl);


    }
}
