package android.com.zlt;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.Button;
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
 * Created by user on 2018/5/11.
 */

public class Index extends Fragment implements View.OnClickListener{
    private ListView listView;
    List<TouTiao> touTiaoList;
    TouTiaoAdapter touTiaoAdapter;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view= inflater.inflate(R.layout.layout_index,container,false);
        Button b1=view.findViewById(R.id.btn1);
        b1.setOnClickListener(this);
        Button b2=view.findViewById(R.id.btn2);
        b2.setOnClickListener(this);
        Button b3=view.findViewById(R.id.btn3);
        b3.setOnClickListener(this);
        Button b4=view.findViewById(R.id.btn4);
        b4.setOnClickListener(this);
        Button button5=view.findViewById(R.id.btn5);
        button5.setOnClickListener(this);
        Button button6=view.findViewById(R.id.btn6);
        button6.setOnClickListener(this);
        Button b5=view.findViewById(R.id.btn_tuisong1);
        b5.setOnClickListener(this);
        Button b6=view.findViewById(R.id.btn_tuisong2);
        b6.setOnClickListener(this);

        listView=view.findViewById(R.id.news_list);
        //做网络请求
        OkHttpClient okHttpClient = new OkHttpClient();
        Log.e("textTouTiao", "test");
        //请求对象
        Request request = new Request.Builder()
                .url(Constant.BASE_URL + "GetAllToutiao")
                .build();
        String url = Constant.BASE_URL + "GetAllToutiao";
        Log.e("texturl", url);
        //发送请求对象
        final Call call = okHttpClient.newCall(request);
        final Handler handler = new Handler();
        // 构建Runnable对象，在runnable中更新界面
        final Runnable runnableUi = new Runnable() {
            @Override
            public void run() {
                //更新界面
                listView.setAdapter(touTiaoAdapter);
            }

        };
        call.enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                e.printStackTrace();
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                String touTiaoListStr = response.body().string();//获取服务器端响应字符串
                Log.e("text", touTiaoListStr);

                Gson gson = new Gson();
                Type type = new TypeToken<List<TouTiao>>() {
                }.getType();
                touTiaoList = gson.fromJson(touTiaoListStr, type);
                for (TouTiao news : touTiaoList
                        ) {
                    Log.e("text", news.getTitle());
                }
                touTiaoAdapter = new TouTiaoAdapter(touTiaoList);

                handler.post(runnableUi);
            }
        });
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                TouTiao touTiao=touTiaoList.get(position);
                Intent intent=new Intent(getActivity(),TcontentActivity.class);
                intent.putExtra("touTiao",touTiao);
                startActivity(intent);

            }
        });

        return view;
    }
    class TouTiaoAdapter extends BaseAdapter{
        private List<TouTiao> list=new ArrayList<>();
        public TouTiaoAdapter(List<TouTiao> list){
            this.list=list;
        }

        @Override
        public int getCount() {
            return list.size();
        }

        @Override
        public Object getItem(int position) {
            return list.get(position);
        }

        @Override
        public long getItemId(int position) {
            return 0;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            if (convertView==null){
                convertView=View.inflate(getContext(),R.layout.layout_news_item,null);
            }
            TouTiao touTiao=list.get(position);
            TextView tittle=convertView.findViewById(R.id.tittle);
            TextView tag=convertView.findViewById(R.id.address);
            tittle.setText(touTiao.getTitle());
            tag.setText(touTiao.getTag());
            return convertView;
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btn1:
                //跳转到旅游景点
                Intent intent1=new Intent(getActivity(),TravelActivity.class);
                startActivity(intent1);
                break;
                //跳转到生活缴费
            case R.id.btn2:
                Intent intent2=new Intent(getActivity(),DynamicState.class);
                startActivity(intent2);
                break;
                //跳转到快递查询
            case R.id.btn3:
                Intent intent3=new Intent(getActivity(), PostActivity.class);
                startActivity(intent3);
                break;
                //跳转到预约挂号
            case R.id.btn4:
                Intent intent4 = new Intent();
                intent4.setData(Uri.parse("https://www.eztcn.com/Home/Index/index/cityid/3.html"));//Url 就是你要打开的网址
                intent4.setAction(Intent.ACTION_VIEW);
                this.startActivity(intent4); //启动浏览器
                break;
                //跳到好友动态
            case R.id.btn5:
                Intent intent5=new Intent(getActivity(),DynamicState.class);
                startActivity(intent5);
                break;
                //跳到全部
            case R.id.btn6:
                Intent intent6=new Intent(getActivity(),AllActivity.class);
                startActivity(intent6);
                break;
            //跳转到养生
            case R.id.btn_tuisong1:
                Intent i1=new Intent();
                i1.setData(Uri.parse("http://www.cnys.com/"));
                i1.setAction(Intent.ACTION_VIEW);
                startActivity(i1);
                break;
                //跳转到健身
            case R.id.btn_tuisong2:
                Intent i2=new Intent();
                i2.setData(Uri.parse("http://www.jianshen8.com/"));
                i2.setAction(Intent.ACTION_VIEW);
                startActivity(i2);
                break;

        }
    }
}
