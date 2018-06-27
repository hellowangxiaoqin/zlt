package android.com.zlt;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.IOException;
import java.lang.reflect.Type;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

/**
 * Created by user on 2018/5/11.
 */

public class My extends Fragment {
    private OkHttpClient okHttpClient=new OkHttpClient();
    public int userId;
    private Handler handler;
    public String userName;
    private ImageView img_round;
    private String imageUrl;
    private User userMessage;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        final View view=inflater.inflate(R.layout.layout_my,container,false);
        handler=new Handler();
        User user=new User();
        final Gson gson=new Gson();
        //得到了用户的用户名
        final String userName = ((MainActivity)getActivity()).getUserName();
        Log.e("test",userName);
        //获取登录用户的信息
        RequestBody requestBody = RequestBody.create(
                MediaType.parse("text/plain;charset=utf-8")
                ,userName
        );
        okHttpClient=new OkHttpClient();
        //创建请求
        Request request = new Request.Builder()
                .post(requestBody)
                .url(Constant.BASE_URL + "getUserMessage1")
                .build();
        //创建call对象，接收服务器端返回消息
        final Call call=okHttpClient.newCall(request);
        new Thread(){//开启一个新的线程
            @Override
            public void run() {
                call.enqueue(new Callback() {
                    @Override
                    public void onFailure(Call call, IOException e) {
                        e.printStackTrace();
                    }
                    @Override
                    public void onResponse(Call call, final Response response) throws IOException {
                        handler.post(new Runnable() {
                             @Override
                             public void run() {
                                 String userListStr = null;
                                 try {
                                     userListStr = response.body().string();
                                     Log.e("text", userListStr);
                                 }catch (Exception e){
                                     e.printStackTrace();
                                 }
                                 Type type=new TypeToken<User>(){}.getType();
                                 //从服务器端得到用户的数据，存储到用户类中
                                 userMessage=gson.fromJson(userListStr,type);
                                 Log.e("ttttttt",userMessage.toString());
                                 userId=userMessage.getUserId();
                                 //获取用户头像的图片路径
                                 imageUrl=userMessage.getImage();
                                 if (imageUrl==null){
                                     imageUrl="null";
                                     img_round.setImageResource(R.drawable.picture);
                                 }else {
                                     Log.e("test", imageUrl);
                                     img_round =view.findViewById(R.id.img_round);
                                     //将图片加载到界面显示
                                     Glide.with(My.this).load(Constant.BASE_URL +"images/"+imageUrl).into(img_round);
                                 }
                                 TextView name_show = view.findViewById(R.id.name_show);
                                 name_show.setText(userMessage.getUserName());
                                 TextView sex_show = view.findViewById(R.id.sex_show);
                                 sex_show.setText(userMessage.getSex());
                                 TextView phone_show = view.findViewById(R.id.phone_show);

                                 phone_show.setText(userMessage.getPhone().substring(0,5));
                                 TextView detail_show = view.findViewById(R.id.detail_show);
                                 detail_show.setText(userMessage.getDetail());
                             }

                        });
                    }
                });
            }
        }.start();
        //设置背景图的透明度
        View v = view.findViewById(R.id.content);//找到你要设透明背景的layout 的id
        v.getBackground().setAlpha(100);//0~255透明度值 ，0为完全透明，255为不透明
        //获取账号管理的视图
        LinearLayout iTunes=view.findViewById(R.id.iTunes);
        //点击进入账号的设置
        iTunes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent_people=new Intent();
                intent_people.setClass(getActivity(),PeopleActivity.class);
                intent_people.putExtra("userId",userId);
                startActivity(intent_people);
            }
        });
        //点击进入收藏的设置
        LinearLayout collection=view.findViewById(R.id.collection);
        collection.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent_collection=new Intent(getActivity(),CollectionActivity.class);
                intent_collection.putExtra("userName",userName);
                startActivity(intent_collection);
            }
        });
        //点击进入签到页面
        LinearLayout sign_in=view.findViewById(R.id.sign_in);
        sign_in.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent_sign_in=new Intent(getActivity(),SignActivity.class);
                startActivity(intent_sign_in);
            }
        });
        //点击进入设置页面
        ImageView set=view.findViewById(R.id.people_set);
        set.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent_set=new Intent(getActivity(),SetActivity.class);
                intent_set.putExtra("userId",userId);
                startActivity(intent_set);
            }
        });
        //点击进入常见问题界面
        LinearLayout people_problem=view.findViewById(R.id.people_problem);
        people_problem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent_problem=new Intent(getActivity(),ProblemActivity.class);
                startActivity(intent_problem);
            }
        });
        //点击进入意见反馈界面
        LinearLayout people_advice=view.findViewById(R.id.people_advice);
        people_advice.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent_advice=new Intent(getActivity(),AdviceActivity.class);
                startActivity(intent_advice);
            }
        });
//        //点击显示二维码
//        ImageView erweima=view.findViewById(R.id.erweima);
//        erweima.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent intent_erweima=new Intent(getActivity(),ErweimaActivity.class);
//                intent_erweima.putExtra("userName",userName);
//                startActivity(intent_erweima);
//            }
//        });
        return view;
    }
}
