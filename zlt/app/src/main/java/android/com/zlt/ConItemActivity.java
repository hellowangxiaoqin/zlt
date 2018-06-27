package android.com.zlt;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public  class ConItemActivity extends Activity{

    ListAdapter adapter;
    List<Map<String,Object>> list;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.conitem_content);

        final Intent intent = getIntent();
        final int ido = intent.getIntExtra("userId",1);
        //final int id = Integer.parseInt(ido);
        final String userName = intent.getStringExtra("userName");
        final String phoneNum = intent.getStringExtra("phone");
        final String sex = intent.getStringExtra("sex");
        final String email = intent.getStringExtra("email");
        final String detail = intent.getStringExtra("detail");

        final TextView tvName = findViewById(R.id.item_name);
        final TextView tvPhoneNum = findViewById(R.id.item_phone);
        final TextView tvAddress = findViewById(R.id.item_address);
        final TextView tvEmail = findViewById(R.id.item_email);



        tvName.setText(userName);
        tvPhoneNum.setText(phoneNum);
        tvAddress.setText(detail);
        tvEmail.setText(email);

        final int position = intent.getIntExtra("position",0);

//        Log.e("name",name );
        Log.e("position", String.valueOf(position));

        Button btnQuit = findViewById(R.id.btn_conitem_quit);

        btnQuit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        Button btnEdit = findViewById(R.id.btn_conitem_modify);
        btnEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        Button btnSendMsg = findViewById(R.id.btn_con_sendmsg);
        btnSendMsg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent1 = new Intent();
                intent1.putExtra("userName",userName);
                intent1.putExtra("phoneNum",phoneNum);
                intent1.putExtra("sex",sex);
                intent1.putExtra("email",email);
                intent1.putExtra("detail",detail);
                intent1.setClass(ConItemActivity.this,ConSendMsgActivity.class);
                startActivityForResult(intent1,0);
            }
        });

    }


}
