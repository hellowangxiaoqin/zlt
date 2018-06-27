package android.com.zlt;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.IOException;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Created by 王玉波 on 2018/5/15.
 */

public class DynamicState extends AppCompatActivity {
    private ListView listView;
    DynamicListAdapter dynamicListAdapter;
   // private String username;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_dynamiclist);
        getSupportActionBar().hide();
        Button btn_cancel=findViewById(R.id.cancel);
        btn_cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        listView = findViewById(R.id.lv);
        //做网络请求
        OkHttpClient okHttpClient = new OkHttpClient();
        //请求对象
        Request request = new Request.Builder()
                .url(Constant.BASE_URL + "GetDynamic")
                .build();
        Log.e("text",Constant.BASE_URL);
        //发送请求对象
        final Call call = okHttpClient.newCall(request);
        final Handler handler = new Handler();
        // 构建Runnable对象，在runnable中更新界面
        final Runnable runnableUi = new Runnable() {
            @Override
            public void run() {
                //更新界面
                listView.setAdapter(dynamicListAdapter);
            }

        };
        Log.e("text",Constant.BASE_URL);
         call.enqueue(new Callback() {
                    @Override
                    public void onFailure(Call call, IOException e) {
                        e.printStackTrace();
                        Log.e("text",Constant.BASE_URL
                        +"失败");
                    }

                    @Override
                    public void onResponse(Call call, Response response) throws IOException {

                        String dynamiclist = response.body().string();//获取服务器端响应字符串
                        Log.e("text",dynamiclist);
                        Gson gson = new Gson();
                        Type type = new TypeToken<List<Dynamic>>(){}.getType();
                        List<Dynamic> bookList = gson.fromJson(dynamiclist, type);
                        for (Dynamic book : bookList) {
                            Log.e("text",book.getName());
                        }
//                        dynamicListAdapter = new DynamicListAdapter(
//                                DynamicState.this, bookList);
                       // new DynamicListAdapter(DynamicState.this,bookList)=dynamicListAdapter;
                        dynamicListAdapter=new DynamicListAdapter(DynamicState.this,bookList);
                        handler.post(runnableUi);

                    }
                });





    }
    public class DynamicListAdapter extends BaseAdapter {
        private Activity context;
        private List<Dynamic> bookList=new ArrayList<>();
        public DynamicListAdapter(Activity context, List<Dynamic> bookList){
            this.context=context;
            this.bookList=bookList;
        }
        @Override
        public int getCount() {
            return bookList.size();
        }

        @Override
        public Object getItem(int position) {
            return bookList.get(position);
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            if (convertView==null){
                convertView=context.getLayoutInflater().inflate(
                        R.layout.dynamiclist_detail,null
                );}
            ImageView imageView=convertView.findViewById(R.id.image);
            TextView textViewName=convertView.findViewById(R.id.name);
            ImageView shuoView=convertView.findViewById(R.id.shuo)

           ;

            // Button btnDetail = convertView.findViewById(R.id.btn_detail);
           // Button btnBuy = convertView.findViewById(R.id.btn_buy);
            Glide.with(context)
                    .load(Constant.BASE_URL+bookList.get(position).getImage())
                    .into(imageView);
            Log.e("text",bookList.get(position).getName());
            textViewName.setText(bookList.get(position).getName());
            // shuoView.setText(bookList.get(position).getBookPrice());
            Glide.with(context)
                    .load(Constant.BASE_URL+bookList.get(position).getDynamic())
                    .into(shuoView);
            return convertView;
        }
    }

    }




