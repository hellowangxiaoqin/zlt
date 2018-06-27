package android.com.zlt;

import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ListView;

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
 * Created by user on 2018/5/11.
 */

public class News extends Fragment {
    private ListView listView;
    NewsListAdapter newsListAdapter;
    List<New> newsList;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.layout_news, container, false);
//        Button renturn = view.findViewById(R.id.ma_return);
//
//        renturn.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                //  finish();
//            }
//        });
        //新闻列表
        listView = view.findViewById(R.id.lv_news);
        //做网络请求
        OkHttpClient okHttpClient = new OkHttpClient();
        Log.e("text", "test");
        //请求对象
        Request request = new Request.Builder()
                .url(Constant.BASE_URL + "GetPageNewsServlet")
                .build();
        String url = Constant.BASE_URL + "GetPageNewsServlet";
        Log.e("text", url);
        //发送请求对象
        final Call call = okHttpClient.newCall(request);
        final Handler handler = new Handler();
        // 构建Runnable对象，在runnable中更新界面
        final Runnable runnableUi = new Runnable() {
            @Override
            public void run() {
                //更新界面
                listView.setAdapter(newsListAdapter);
            }

        };
//        new Thread() {
//
//            @Override
//            public void run() {

                call.enqueue(new Callback() {//异步
                    @Override
                    public void onFailure(Call call, IOException e) {
                        e.printStackTrace();
                    }

                    @Override
                    public void onResponse(Call call, Response response) throws IOException {

                        String newsListStr = response.body().string();//获取服务器端响应字符串
                        Log.e("text", newsListStr);

                        Gson gson = new Gson();
                        Type type = new TypeToken<List<New>>() {
                        }.getType();
                        newsList = gson.fromJson(newsListStr, type);
                        for (New news : newsList
                                ) {
                            Log.e("text", news.getTitle());
                        }
                        newsListAdapter = new NewsListAdapter(getActivity(), newsList);

                        handler.post(runnableUi);

                    }
                });

//            }
//        }.start();
        //点击换一批按钮
        Button btnAgain = view.findViewById(R.id.btn_again);
        btnAgain.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                New news = newsList.get(2);
                String title = news.getTitle();
                //做网络请求
                OkHttpClient okHttpClient = new OkHttpClient();
                Log.e("text", "test");
                //请求对象
                Request request = new Request.Builder()
                        .url(Constant.BASE_URL + "GetNextPageNewsServlet?title=" + title)
                        .build();
                String url = Constant.BASE_URL + "GetNextPageNewsServlet?title=" + title;
                Log.e("text", url);
                //发送请求对象
                final Call call = okHttpClient.newCall(request);
                final Handler handler = new Handler();
                // 构建Runnable对象，在runnable中更新界面
                final Runnable runnableUi = new Runnable() {
                    @Override
                    public void run() {
                        //更新界面
                        listView.setAdapter(newsListAdapter);
                    }

                };
                new Thread() {

                    @Override
                    public void run() {

                        call.enqueue(new Callback() {
                            @Override
                            public void onFailure(Call call, IOException e) {
                                e.printStackTrace();
                            }

                            @Override
                            public void onResponse(Call call, Response response) throws IOException {

                                String newsListStr1 = response.body().string();//获取服务器端响应字符串
                                Log.e("text1", newsListStr1);

                                Gson gson = new Gson();
                                Type type = new TypeToken<List<New>>() {
                                }.getType();
                                List<New> list = gson.fromJson(newsListStr1, type);
                                for (New news : list
                                        ) {
                                    Log.e("text1", news.getTitle());
                                }
                                newsList.addAll(0,list);
//                                newsList.addAll(list);
                                for (New news : newsList
                                        ) {
                                    Log.e("text1", news.getTitle());
                                }
                                newsListAdapter = new NewsListAdapter(getActivity(), newsList);

                                handler.post(runnableUi);

                            }
                        });

                    }
                }.start();

            }
        });
        return view;
    }
}