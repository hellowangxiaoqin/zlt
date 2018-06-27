package android.com.zlt;

import android.app.DatePickerDialog;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CalendarView;
import android.widget.DatePicker;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.IOException;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

/**
 * Created by yangwenmiao on 2018/5/23.
 */

public class SignActivity extends AppCompatActivity {
    private OkHttpClient okHttpClient= new OkHttpClient();;
    private Handler handler=new Handler();
    final private Gson gson = new Gson();
    private Button btn_sign;//签到的按钮

    private CalendarView calendarView;
    private String date;
    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_in);
        getSupportActionBar().hide(); //隐藏标题栏
        //获取当前时间
        final Calendar nowdate = Calendar.getInstance();
        final int mYear = nowdate.get(Calendar.YEAR);
        final int mMonth = nowdate.get(Calendar.MONTH) + 1;
        final int mDay = nowdate.get(Calendar.DAY_OF_MONTH);
        Log.e("year", String.valueOf(mYear));
        Log.e("month", String.valueOf(mMonth));
        Log.e("day", String.valueOf(mDay));
        calendarView = findViewById(R.id.calendarViewId);
        //设置获得焦点的月份的日期文字的颜色
        int color1 = Color.parseColor("#FF8C00");
        calendarView.setFocusedMonthDateColor(color1);
        //点击箭头返回上一页
        ImageView sign_fanhui = findViewById(R.id.sign_fanhui);
        sign_fanhui.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        //将用户的用户名传入服务器端，判断用户今日是否签到
        //获取用户的用户名和id
//        String userName = "lili";
//        String username=gson.toJson(userName);
//        //创建请求体
//        RequestBody requestBody = RequestBody.create(
//                MediaType.parse("text/plain;charset=utf-8")
//                ,username
//        );
//        //创建请求
//        Request request = new Request.Builder()
//                .post(requestBody)
//                .url(Constant.BASE_URL + "getSignMessage")
//                .build();
//        //创建call对象，接收服务器端返回消息
//        final Call call =okHttpClient.newCall(request);
//        new Thread(){//开启一个新的线程
//            @Override
//            public void run() {
//                call.enqueue(new Callback() {
//                    @Override
//                    public void onFailure(Call call, IOException e) {
//
//                        e.printStackTrace();
//                        Log.e("test","flase");
//                    }
//                    @Override
//                    public void onResponse(Call call, final Response response) throws IOException {
//                        handler.post(new Runnable() {
//                            @Override
//                            public void run() {
//                                Log.e("getSignMessage", "success");
//                                String signListStr=null;
//                                try{
//                                    signListStr=response.body().string();
//                                    Log.e("test",signListStr);
//
//                                } catch (IOException e) {
//                                    e.printStackTrace();
//                                }
//                                Type type=new TypeToken<List<Sign>>(){}.getType();
//                                final List<Sign> signList=gson.fromJson(signListStr,type);
////                                Log.e("test", String.valueOf(signList));
//                                List<String> dateList=new ArrayList<String>();
//                                ArrayList<String> list=new ArrayList<String>();
//                                for (Sign sign:signList){
//                                    list.add(sign.getDate());
//                                }
//                                Log.e("data",date);
//                                Log.e("list", String.valueOf(list.size()));
//
//
//                                boolean ceshi=true;
//                                for (int i=0; i<list.size(); i++){
//                                    Log.e("list",list.get(i));
//                                    if (list.get(i)==date){
//                                        ceshi=false;
//                                        break;
//                                    }else {
//                                        ceshi=true;
//                                    }
//                                }
////                                for(String s:dateList){
////                                    Log.e("tw",s);
////                                    if (s==date){
////                                        ceshi=false;
////                                        break;
////                                    }else {
////                                        continue;
////                                    }
////                                }
//                                Log.e("ceshi", String.valueOf(ceshi));
//                                if(ceshi==false){
//                                    //说明今日已经签到过，显示签到按钮为不可点击状态
//                                    btn_sign.setText("今日已签到");
//                                    int color = Color.parseColor("#E2E2E2");
//                                    btn_sign.setBackgroundColor(color);
//                                    btn_sign.setClickable(false);
//                                }else{
//                                    //今日没有签到过，可以进行点击签到，将信息传入数据库中
//                                    btn_sign.setClickable(true);
//                                    btn_sign.setOnClickListener(new View.OnClickListener() {
//                                        @Override
//                                        public void onClick(View v) {
//                                            btn_sign.setText("今日已签到");
//                                            int color = Color.parseColor("#E2E2E2");
//                                            btn_sign.setBackgroundColor(color);
//                                            btn_sign.setClickable(false);
//                                            String userName = "lili";
//                                            int userId = 1;
//                                            //获取当前时间
//                                            String date = String.valueOf(mYear) + "-" + String.valueOf(mMonth) + "-" + String.valueOf(mDay);
//                                            okHttpClient = new OkHttpClient();
//                                            Sign sign=new Sign();
//                                            sign.setDate(date);
//                                            sign.setUserId(userId);
//                                            sign.setUserName(userName);
//                                            String signStr=gson.toJson(sign);
//                                            //创建请求体
//                                            RequestBody requestBody = RequestBody.create(
//                                                    MediaType.parse("text/plain;charset=utf-8")
//                                                    ,signStr
//                                            );
//                                            //创建请求
//                                            Request request = new Request.Builder()
//                                                    .post(requestBody)
//                                                    .url(Constant.BASE_URL + "insertSignMessage")
//                                                    .build();
//                                            Log.e("insertSign", "request");
//                                            //创建call对象，接收服务器端返回消息
//                                            final Call call = okHttpClient.newCall(request);
//                                            new Thread(){//开启一个新的线程
//                                                @Override
//                                                public void run() {
//                                                    Log.e("getUserMessage","new thread");
//                                                    call.enqueue(new Callback() {
//                                                        @Override
//                                                        public void onFailure(Call call, IOException e) {
//                                                            e.printStackTrace();
//                                                            Log.e("getUserMessage","failure");
//                                                        }
//                                                        @Override
//                                                        public void onResponse(Call call, final Response response) throws IOException {
//                                                            handler.post(new Runnable() {
//                                                                @Override
//                                                                public void run() {
//                                                                    Log.e("getUserMessage", "success");
//                                                                }
//                                                            });
//                                                        }
//                                                    });
//                                                }
//                                            }.start();
//                                        }
//                                    });
//                                }
//
//                            }
//                        });
//                    }
//                });
//            }
//        }.start();
        date = String.valueOf(mYear) + "-" + String.valueOf(mMonth) + "-" + String.valueOf(mDay);
        btn_sign = findViewById(R.id.btn_sign);
        btn_sign.setText("今日已签到");
        int color = Color.parseColor("#E2E2E2");
        btn_sign.setBackgroundColor(color);
        btn_sign.setClickable(false);
//        btn_sign.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                btn_sign.setText("今日已签到");
//                int color = Color.parseColor("#E2E2E2");
//                btn_sign.setBackgroundColor(color);
//                btn_sign.setClickable(false);
//            }
//        });

    }
//
//            //获取当月的天数
//            public int getCurrentMonthDay() {
//                Calendar a = Calendar.getInstance();
//                a.set(Calendar.DATE, 1);
//                a.roll(Calendar.DATE, -1);
//                int maxDate = a.get(Calendar.DATE);
//                return maxDate;
//            }

}
