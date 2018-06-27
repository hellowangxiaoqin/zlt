package android.com.zlt;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.text.method.ScrollingMovementMethod;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.IOException;
import java.lang.reflect.Type;
import java.util.List;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Created by user on 2018/5/15.
 */

public class DetailActivity extends AppCompatActivity {
    private ListView listView;
    NewsHotAdapter newsHotAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        getSupportActionBar().hide();
        Log.e("text","test");
        Intent intent = getIntent();
        String title = intent.getStringExtra("title");
        String content = intent.getStringExtra("content");
        TextView textView1 = findViewById(R.id.de_title);
        TextView textView2 = findViewById(R.id.de_content);
        textView1.setText(title);
        textView2.setText(content);

        //实现滚动
        textView2.setMovementMethod(ScrollingMovementMethod.getInstance());
        Button de_renturn = findViewById(R.id.de_return);
        de_renturn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        listView = findViewById(R.id.de_lv);
        //做网络请求
        OkHttpClient okHttpClient = new OkHttpClient();
        Log.e("text","test");
        //请求对象
        Request request = new Request.Builder()
                .url(Constant.BASE_URL + "GetHotNewsServlet?title="+title)
                .build();
        String url = Constant.BASE_URL + "GetHotNewsServlet?title="+title;
        Log.e("text",url);
        //发送请求对象
        final Call call = okHttpClient.newCall(request);
        final Handler handler = new Handler();
        // 构建Runnable对象，在runnable中更新界面
        final Runnable runnableUi = new Runnable() {
            @Override
            public void run() {
                //更新界面
                listView.setAdapter(newsHotAdapter);
            }

        };
//        new Thread() {
//
//            @Override
//            public void run() {

                call.enqueue(new Callback() {
                    @Override
                    public void onFailure(Call call, IOException e) {
                        e.printStackTrace();
                    }

                    @Override
                    public void onResponse(Call call, Response response) throws IOException {

                        String newsListStr = response.body().string();//获取服务器端响应字符串
                        Log.e("text",newsListStr);

                        Gson gson = new Gson();
                        Type type = new TypeToken<List<New>>(){}.getType();
                        List<New> newsList = gson.fromJson(newsListStr, type);
                        for (New news:newsList
                                ) {
                            Log.e("text",news.getTitle());
                        }
                        newsHotAdapter = new NewsHotAdapter(DetailActivity.this,newsList);

                        handler.post(runnableUi);

                    }
                });

//            }
//        }.start();

    }
}
