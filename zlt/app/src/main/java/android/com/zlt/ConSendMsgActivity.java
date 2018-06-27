package android.com.zlt;

import android.app.Activity;
import android.app.Notification;
import android.app.NotificationManager;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.os.PersistableBundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.IOException;
import java.lang.reflect.Type;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Created by dell on 2018/5/30.
 */

public  class ConSendMsgActivity extends Activity  implements View.OnClickListener{


    private Button mBtnSend;// 发送btn
    private Button mBtnBack;// 返回btn
    private EditText mEditTextContent;
    private ListView mListView;
    private ConMsgViewAdapter mAdapter;// 消息视图的Adapter
    private List<ConMsgEntity> mDataArrays = new ArrayList<ConMsgEntity>();// 消息对象数组
    List<ConMsgEntity> msgList;
    String loginName = GlobalData.getUserName();
    String userName = null;
    EditText etMsgContent;
    String message;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.conitem_sendmsg);
        initView();// 初始化view
//        initData();// 初始化数据

        Intent intent = getIntent();
        userName = intent.getStringExtra("userName");
        final String phoneNum = intent.getStringExtra("phoneNum");
        final String sex = intent.getStringExtra("sex");
        final String email = intent.getStringExtra("email");
        final String detail = intent.getStringExtra("detail");

        TextView textView = findViewById(R.id.tv_msg_user);
        textView.setText(userName);

        Button btnMsgQuit = findViewById(R.id.btn_msg_quit);
        btnMsgQuit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ConSendMsgActivity.this,ConItemActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                intent.putExtra("userName",userName);
                intent.putExtra("phone",phoneNum);
                intent.putExtra("sex",sex);
                intent.putExtra("email",email);
                intent.putExtra("detail",detail);
                startActivity(intent);
                finish();
            }
        });

        Button btnMsgUser = findViewById(R.id.btn_msg_user);
        btnMsgUser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ConSendMsgActivity.this,ConItemActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                intent.putExtra("userName",userName);
                intent.putExtra("phone",phoneNum);
                intent.putExtra("sex",sex);
                intent.putExtra("email",email);
                intent.putExtra("detail",detail);
                startActivity(intent);
                finish();
            }
        });

        final Button btnMsgSend = findViewById(R.id.btn_msg_send);
        btnMsgSend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                switch (v.getId()) {
                    case R.id.btn_msg_send:// 发送按钮点击事件
                        send();
                        sendMsg();
                        break;
                    case R.id.btn_msg_quit:// 返回按钮点击事件
                        finish();// 结束,实际开发中，可以返回主界面
                        break;
                }
            }
        });

        etMsgContent = findViewById(R.id.et_msg_content);
        etMsgContent.setOnFocusChangeListener(new View.
                OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (hasFocus) {
                    // 此处为得到焦点时的处理内容
                    if (etMsgContent == null){
                        btnMsgSend.setBackgroundColor(Color.parseColor("#ced4d9"));
                    }else{
                        btnMsgSend.setBackgroundColor(Color.parseColor("#33B5E5"));
                    }
                } else {
                    // 此处为失去焦点时的处理内容
                    if (etMsgContent == null){
                        btnMsgSend.setBackgroundColor(Color.parseColor("#ced4d9"));
                    }else{
                        btnMsgSend.setBackgroundColor(Color.parseColor("#33B5E5"));
                    }
                }
            }
        });

        //做网络请求
        OkHttpClient okHttpClient = new OkHttpClient();
        //请求对象
        Request request = new Request.Builder()
                .url(Constant.BASE_URL + "GetMsgServlet?sendName="+userName+"&receiveName="+loginName)
                .build();
        //发送请求对象
        final Call call = okHttpClient.newCall(request);
        final Handler handler = new Handler();
        // 构建Runnable对象，在runnable中更新界面
        final Runnable runnableUi = new Runnable() {
            @Override
            public void run() {
                //更新界面
                initData();
                mListView.setSelection(mAdapter.getCount() - 1);
            }

        };
        new Thread() {

            @Override
            public void run() {

                call.enqueue(new Callback() {
                    @Override
                    public void onFailure(Call call, IOException e) {
                        e.printStackTrace();
                        Log.e("Sendmsg","failure");
                    }

                    @Override
                    public void onResponse(Call call, Response response) throws IOException {
                        Log.e("Sendmsg","success");
                        String msgListStr = response.body().string();//获取服务器端响应字符串
                        Log.e("text",msgListStr);
                        Gson gson = new Gson();
                        Type type = new TypeToken<List<ConMsgEntity>>(){}.getType();
                        if(msgListStr != null){
                            msgList = gson.fromJson(msgListStr, type);
                        }

                        for (ConMsgEntity ConMsg : msgList) {
                            Log.e("text",ConMsg.getName()+":"+ConMsg.getMessage());
                        }
                        handler.post(runnableUi);

                    }
                });

            }
        }.start();
    }

    /**
     * 初始化view
     */
    public void initView() {
        mListView = (ListView) findViewById(R.id.lv_conitem_msg);
        mListView.setDividerHeight(0);
        mBtnSend = (Button) findViewById(R.id.btn_msg_send);
        mBtnSend.setOnClickListener(this);
//        mBtnBack = (Button) findViewById(R.id.btn_back);
//        mBtnBack.setOnClickListener(this);
        mEditTextContent = (EditText) findViewById(R.id.et_msg_content);
    }
//    private String[] msgArray = new String[] { "123", "321", "123", "321",
//            "123", "321", "123", "321",
//            "123", "321", "123", "321" };
//    private String[] dataArray = new String[] { "2012-09-22 18:00:02",
//            "2012-09-22 18:10:22", "2012-09-22 18:11:24",
//            "2012-09-22 18:20:23", "2012-09-22 18:30:31",
//            "2012-09-22 18:35:37", "2012-09-22 18:40:13",
//            "2012-09-22 18:50:26", "2012-09-22 18:52:57",
//            "2012-09-22 18:55:11", "2012-09-22 18:56:45",
//            "2012-09-22 18:57:33", };
//    private final static int COUNT = 12;// 初始化数组总数
    /**
     * 模拟加载消息历史，实际开发可以从数据库中读出
     */
    public void initData() {
        for (int i = 0; i < msgList.size(); i++) {
            ConMsgEntity entity = new ConMsgEntity();
//            entity.setDate(dataArray[i]);
//            if (i % 2 == 0) {
//                entity.setName("admin");
//                entity.setMsgType(true);// 收到的消息
//            } else {
//                entity.setName("admin");
//                entity.setMsgType(false);// 自己发送的消息
//            }
            entity.setName(msgList.get(i).getName());
            entity.setDate(msgList.get(i).getDate());
            entity.setMessage(msgList.get(i).getMessage());
            entity.setMsgType(msgList.get(i).getMsgType());
            entity.setIsRead(msgList.get(i).getIsRead());
            entity.setNameReceive(msgList.get(i).getNameReceive());
            mDataArrays.add(entity);
        }
        mAdapter = new ConMsgViewAdapter(this, mDataArrays);
        mListView.setAdapter(mAdapter);
    }

    @Override
    public void onClick(View v) {
    }
    /**
     * 发送消息
     */
    private void send() {
        message = mEditTextContent.getText().toString();
        if (message.length() > 0) {
            ConMsgEntity entity = new ConMsgEntity();
            entity.setName("");
            entity.setDate(getDate());
            entity.setMessage(message);
            entity.setMsgType(false);
            mDataArrays.add(entity);
            mAdapter.notifyDataSetChanged();// 通知ListView，数据已发生改变
            mEditTextContent.setText("");// 清空编辑框数据
            mListView.setSelection(mListView.getCount() - 1);// 发送一条消息时，ListView显示选择最后一项
        }
    }
    /**
     * 发送消息时，获取当前事件
     *
     * @return 当前时间
     */
    private String getDate() {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
        return format.format(new Date());
    }

    private void sendMsg(){

        Log.e("text","sendMsg");
        //做网络请求
        OkHttpClient okHttpClient1 = new OkHttpClient();
        Request request = new Request.Builder()
//                .post(requestBody)
                .url(Constant.BASE_URL+"SendMsgServlet?sendName="+userName+"&receiveName="+loginName
                        +"&date="+getDate()+"&message="+message)
                .build();
        //创建call对象，并执行请求
        final Call call = okHttpClient1.newCall(request);
        new Thread() {
            @Override
            public void run() {
                Log.e("text","thread");
                //执行异步请求
                call.enqueue(new Callback() {
                    @Override
                    public void onFailure(Call call, IOException e) {
                        Log.e("text","failure");
                        e.printStackTrace();
                    }
                    @Override
                    public void onResponse(Call call, Response response) throws IOException {
//                Log.e(TAG,response.body().string());
                        String value = response.body().string();
                        Log.e("text","response");
                        if(value.equals("success")){
//                    Intent intent = new Intent(RegisterActivity.this,LoginActivity.class);
//                    startActivity(intent);
                            Log.e("text","success");
                        }
                    }
                });
            }
        }.start();
    }
}
