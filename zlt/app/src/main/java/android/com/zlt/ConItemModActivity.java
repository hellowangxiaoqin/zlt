package android.com.zlt;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.IOException;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Created by DELL on 2018/6/25.
 */

public class ConItemModActivity extends AppCompatActivity {

    ListAdapter adapter ;
    ListView lv ;
    String userName;
    String num = "0";
    TextView tvNewMsgNum;
    String userListStr = null;
    String strSearch;
    //做网络请求
    private OkHttpClient okHttpClient = new OkHttpClient();
    EditText etSearch;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().hide(); //隐藏标题栏
        setContentView(R.layout.layout_conlist_addfriend);

        final Intent intent0 = getIntent();
        userName = intent0.getStringExtra("userName");
        Log.e("Constact", userName);
        if (userName.equals("")) {
            Intent intent = new Intent(ConItemModActivity.this, LogActivity.class);
        }

        lv = findViewById(R.id.lv_conlist_add);

        Button btnConQuit = findViewById(R.id.btn_conadd_quit);
        Button btnSearch = findViewById(R.id.btn_conadd_search);

        //后退
        btnConQuit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        //搜索
        btnSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                etSearch = findViewById(R.id.et_conadd_search);
                strSearch = etSearch.getText().toString();
                //请求对象
                Request request = new Request.Builder()
                        .url(Constant.BASE_URL + "FindNewFriendServlet?loginName="+userName+"&searchName="+strSearch)
                        .build();
                //发送请求对象
                final Call call = okHttpClient.newCall(request);
                final Handler handler = new Handler();
                // 构建Runnable对象，在runnable中更新界面
                final Runnable runnableUi = new Runnable() {
                    @Override
                    public void run() {
                        //更新界面
                        lv.setAdapter(adapter);
                    }

                };
                new Thread() {

                    @Override
                    public void run() {

                        call.enqueue(new Callback() {
                            @Override
                            public void onFailure(Call call, IOException e) {
                                e.printStackTrace();
                                Log.e("Constact","failure");
                            }

                            @Override
                            public void onResponse(Call call, Response response) throws IOException {
                                Log.e("Constact","success");
                                String someUserListStr = response.body().string();//获取服务器端响应字符串
                                Log.e("someUser",""+someUserListStr);
                                Gson gson = new Gson();
                                Type type = new TypeToken<List<User>>(){}.getType();
                                List<User> someUserList = gson.fromJson(someUserListStr, type);

                                if(someUserList.size() != 0){
                                    Log.e("someUser","not null");
                                    adapter = new UserListAdapter(R.layout.layout_conitem,
                                            ConItemModActivity.this, someUserList);
                                }else{
                                    Log.e("someUser","null");
                                    adapter = new NullListAdapter(R.layout.layout_conitem_null,
                                            ConItemModActivity.this,someUserList);
                                }

                                handler.post(runnableUi);

                            }
                        });

                    }
                }.start();
            }
        });
    }

    protected class UserListAdapter extends BaseAdapter {
        private int conItemId;
        private Activity context;
        private List<User> userList=new ArrayList<User>();
        public UserListAdapter(int conItemId,Activity context, List<User> userList){
            this.conItemId = conItemId;
            this.context=context;
            this.userList=userList;
        }
        @Override
        public int getCount() {
            return userList.size();
        }

        @Override
        public Object getItem(int position) {
            return userList.get(position);
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            if (convertView==null){
                convertView=context.getLayoutInflater().inflate(
                        conItemId,null
                );}

            //lv = convertView.findViewById(R.id.conlist);

            TextView name = convertView.findViewById(R.id.con_name);
            User user = userList.get(position);
            //Log.e("Comitem",user.getUserName());
            name.setText(user.getUserName());
            int num = user.getMsg();
            String numo = String.valueOf(num);
            tvNewMsgNum = convertView.findViewById(R.id.tv_conitem_newMsgNoti);
            if(num != 0){
                tvNewMsgNum.setBackgroundResource(R.drawable.newmsgnoti);
                tvNewMsgNum.setText(numo);
            }

            //单击
            lv.setOnItemClickListener(new ItemListener(userList));
            //长按
//            lv.setOnItemLongClickListener(new LongItemListener(userList));
            return convertView;

        }
    }

    protected class NullListAdapter extends BaseAdapter {
        private int conItemId;
        private Activity context;
        private List<User> userList=new ArrayList<User>();
        public NullListAdapter(int conItemId,Activity context,List<User> userList){
            this.conItemId = conItemId;
            this.context=context;
            this.userList=userList;
        }
        @Override
        public int getCount() {
            return 0;
        }

        @Override
        public Object getItem(int position) {
            return null;
        }

        @Override
        public long getItemId(int position) {
            return 0;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            if (convertView==null){
                convertView=context.getLayoutInflater().inflate(
                        conItemId,null
                );}
            TextView name = convertView.findViewById(R.id.con_name);
            User user = userList.get(position);
            //Log.e("Comitem",user.getUserName());
            name.setText("未找到联系人");
            name.setTextColor(Color.parseColor("#A9A9A9"));
            return convertView;

        }
    }

    private class ItemListener implements AdapterView.OnItemClickListener{
        private List<User> list;
        public ItemListener(List<User> list){
            this.list = list;
        }
        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
            User user = list.get(position);
            Intent intent = new Intent();
            intent.setClass(ConItemModActivity.this,ConItemAddActivity.class);
            intent.putExtra("userId",user.getUserId());
            intent.putExtra("userName",user.getUserName());
            intent.putExtra("phone",user.getPhone());
            intent.putExtra("sex",user.getSex());
            intent.putExtra("email",user.getEmail());
            intent.putExtra("detail",user.getDetail());
            intent.putExtra("msg",0);
            startActivityForResult(intent,0);
            //做网络请求
            OkHttpClient okHttpClient = new OkHttpClient();
            //请求对象
            Request request = new Request.Builder()
                    .url(Constant.BASE_URL + "ReadMsgServlet?name="+user.getUserName()+"&nameReceive="+userName)
                    .build();
            //发送请求对象
            final Call call = okHttpClient.newCall(request);
            final Handler handler = new Handler();
            // 构建Runnable对象，在runnable中更新界面
            final Runnable runnableUi = new Runnable() {
                @Override
                public void run() {

                }

            };
            new Thread() {

                @Override
                public void run() {

                    call.enqueue(new Callback() {
                        @Override
                        public void onFailure(Call call, IOException e) {
                            e.printStackTrace();
                            Log.e("Constact","failure");
                        }

                        @Override
                        public void onResponse(Call call, Response response) throws IOException {
                            Log.e("Constact","success");
                            String flag = response.body().string();//获取服务器端响应字符串
                            Log.e("text",flag);
                            if(flag.equals("success")){
                                Log.e("text","success");
                            }
                        }
                    });

                }
            }.start();
        }
    }
}
