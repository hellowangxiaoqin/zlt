package android.com.zlt;

import android.app.Activity;
import android.content.ContentResolver;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.PopupMenu;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.IOException;
import java.lang.reflect.Type;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;


public class ComConstactActivity extends AppCompatActivity {
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
        setContentView(R.layout.layout_conlist);


        final Intent intent0 = getIntent();
        userName = intent0.getStringExtra("userName");
        Log.e("Constact",userName);
        if(userName.equals("")){
            Intent intent = new Intent(ComConstactActivity.this,LogActivity.class);
        }

        Button btnConQuit = findViewById(R.id.btn_conlist_quit);
        Button btnAdd = findViewById(R.id.btn_conlist_add);
        Button btnSearch = findViewById(R.id.btn_conlist_search);


//后退
        btnConQuit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

//添加
        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setClass(ComConstactActivity.this,ConItemModActivity.class);
                intent.putExtra("userName",userName);
//                intent.putExtra("phone","");
//                intent.putExtra("sex","");
//                intent.putExtra("email","");
                startActivityForResult(intent,0);
            }
        });

//查找全部好友列表
        //请求对象
        Request request = new Request.Builder()
                .url(Constant.BASE_URL + "FindFriendServlet?userName="+userName)
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
                        userListStr = response.body().string();//获取服务器端响应字符串
                        Log.e("text",userListStr);
                        Gson gson = new Gson();
                        Type type = new TypeToken<List<User>>(){}.getType();
                        List<User> userList = gson.fromJson(userListStr, type);
                        for (User user : userList) {
                            Log.e("text",user.getUserName());
                        }
                        adapter = new UserListAdapter(R.layout.layout_conitem,
                                ComConstactActivity.this, userList);
                        handler.post(runnableUi);

                    }
                });

            }
        }.start();

//搜索
        btnSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                etSearch = findViewById(R.id.et_conlist_search);
                strSearch = etSearch.getText().toString();
                //请求对象
                Request request = new Request.Builder()
                        .url(Constant.BASE_URL + "FindSomeFriendServlet?loginName="+userName+"&searchName="+strSearch)
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
                                            ComConstactActivity.this, someUserList);
                                }else{
                                    Log.e("someUser","null");
                                    adapter = new NullListAdapter(R.layout.layout_conitem_null,
                                            ComConstactActivity.this,someUserList);
                                }

                                handler.post(runnableUi);

                            }
                        });

                    }
                }.start();
            }
        });


        lv = findViewById(R.id.conlist);




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
            intent.setClass(ComConstactActivity.this,ConItemActivity.class);
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


//
//        //打开数据库
//        //Connection connection = DBConnection.getConnection();
//        //PreparedStatement ps = null;
//        SQLiteDatabase db = SQLiteDatabase
//                .openOrCreateDatabase("/data/data/com.example.dell.project/databases/mydb.db",
//                        null);
//
//        //查询方式1
//        Cursor cursor = db.query("CONTACTLIST",
//                null,null,null,
//                null,null,null,null);
//        if(cursor.moveToFirst()){ //成功移动到第一条，证明有数据
//            do{
//                //处理当前游标使用的数据
//                int id = cursor.getInt(cursor.getColumnIndex("ID"));
//                String name = cursor.getString(cursor.getColumnIndex("NAME"));
//                String phoneNum = cursor.getString(cursor.getColumnIndex("PHONENUM"));
//                String address = cursor.getString(cursor.getColumnIndex("ADDRESS"));
//                String email = cursor.getString(cursor.getColumnIndex("EMAIL"));
//                Log.e("mydb", id+"|"+name+"|"+phoneNum+"|"+address+"|"+email );
//
//                Map<String,Object> map = new HashMap<>();
//                map.put("id",id);
//                map.put("name",name);
//                map.put("phoneNum",phoneNum);
//                map.put("address",address);
//                map.put("email",email);
//                list.add(map);
//            }while(cursor.moveToNext());
//        }
//        //关闭数据库
//        db.close();
//        adapter = new ListAdapter(this,R.layout.layout_conitem,list);
//        lv = findViewById(R.id.conlist);
//        lv.setAdapter(adapter);
//    }
//
//    //定义一个内部类ListAdapter
//    private class ListAdapter extends BaseAdapter {
//        private List<Map<String,Object>> data;
//        private Context context;
//        private int list_item_id;
//
//        public ListAdapter(Context context,int list_item_id,List<Map<String,Object>> data){
//            this.context = context;
//            this.list_item_id = list_item_id;
//            this.data = data;
//        }
//
//        @Override
//        public int getCount() {
//            return data.size();
//        }
//
//        @Override
//        public Object getItem(int position) {
//            return data.get(position);
//        }
//
//        @Override
//        public long getItemId(int position) {
//            return position;
//        }
//
//        @Override
//        public View getView(int position, View convertView, ViewGroup parent) {
//            if(convertView == null){
//                LayoutInflater myInflater = LayoutInflater.from(context);
//                convertView = myInflater.inflate(list_item_id,null);
//                //Log.e("name","desp");
//            }
//            lv = findViewById(R.id.conlist);
//            //单击
//            lv.setOnItemClickListener(new ItemListener(data));
//            //长按
//            lv.setOnItemLongClickListener(new LongItemListener(data));
//            TextView name = convertView.findViewById(R.id.con_name);
//
//            Map<String,Object> map = data.get(position);
//
//            name.setText((String)map.get("name"));
//
//            return convertView;
//        }
//    }
//

//    private class LongItemListener implements AdapterView.OnItemLongClickListener{
//        private User user;
//        private String userName;
//        private int posi;
//        public LongItemListener(User User,String userName,int posi){
//            this.user = user;
//            this.userName = userName;
//            this.posi = posi;
//        }
//        @Override
//        public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
//            posi = position;
//            PopupMenu menu = new PopupMenu(getApplicationContext(),view);
//            menu.getMenuInflater().inflate(R.menu.menu_popumenu,menu.getMenu());
//            menu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
//                @Override
//                public boolean onMenuItemClick(MenuItem item) {
//                    switch (item.getItemId()){
//                        case R.id.popupmenu_item_1:
//                            //打开数据库
//                            SQLiteDatabase db = SQLiteDatabase
//                                    .openOrCreateDatabase("/data/data/com.example.dell.project/databases/mydb.db",
//                                            null);
//                            //删除数据
//                            Map<String,Object> map = list.get(posi);
//                            String []whereArgs = new String[]{map.get("name").toString()};
//                            db.delete("CONTACTLIST","NAME=?",whereArgs);
//                            //关闭数据库
//                            db.close();
//                            Intent intent = new Intent(ComConstactActivity.this,ComConstactActivity.class);
//                            startActivity(intent);
//
//                            Log.e("mydb","长按事件");
//                            return true;
//                    }
//                    return false;
//                }
//            });
//            menu.show();
//
//            return true;
//        }
//    }

}

