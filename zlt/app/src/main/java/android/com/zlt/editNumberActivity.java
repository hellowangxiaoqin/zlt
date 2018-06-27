package android.com.zlt;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

/**
 * Created by yangwenmiao on 2018/5/23.
 */

public class editNumberActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_editnumber);
        getSupportActionBar().hide();
        Intent intent_editnumber=getIntent();
        final EditText edit_number=findViewById(R.id.edit_number);
        final ImageView edit_cancel_number=findViewById(R.id.edit_cancel_number);
        //设置取消操作
        final TextView cancel_edit=findViewById(R.id.cancel_edit_number);
        //设置完成操作
        final TextView finish_edit=findViewById(R.id.finish_edit_number);
        //设置编辑框中的内容是传入的号码
        edit_number.setText(intent_editnumber.getStringExtra("number"));
        //点击×的图片，会删除文本框中的名字
        edit_cancel_number.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                edit_number.setText(null);
                //当编辑框中文字消失，完成的颜色变成绿色
                int i1=Color.parseColor("#00FF00");
                finish_edit.setTextColor(i1);
            }
        });
        //设置文本输入变化监听
        edit_number.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }
            @Override
            public void afterTextChanged(Editable s) {
                if (s.length() > 0) {
                    edit_cancel_number.setVisibility(View.VISIBLE);
                } else {
                    edit_cancel_number.setVisibility(View.GONE);
                }
            }
        });
        //点击取消更改名称，会返回上个页面
        cancel_edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        //点击完成，用户名更改并返回上一个页面
        finish_edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent();
                String number=edit_number.getText().toString();
                intent.putExtra("number",number);
                setResult(3,intent);
                finish();
            }
        });
    }
}
