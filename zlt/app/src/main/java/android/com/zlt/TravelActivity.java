package android.com.zlt;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
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

public class TravelActivity extends Activity implements ViewPager.OnPageChangeListener{
    private ViewPager viewPager;
    private int[] imageResIds;
    private ArrayList<ImageView> imageViewList;
    private LinearLayout point_container;
    private String[] contentDecs;
    private TextView tv_desc;
    private int previousSelectedPosition=0;
    boolean isRunning=false;
    List<Place> placeList;
    private ListView listView;

    MyBaseAdapter baseAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_travel);
        listView=findViewById(R.id.travel_list);

        //图片轮播功能实现
        //初始化布局View视图
        initViews();

        //Model数据
        initData();

        //Controller控制器
        initAdapter();
        //开启轮询
        new Thread() {
            public void run() {
                isRunning = true;
                while (isRunning) {
                    try {
                        Thread.sleep(2000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    // 往下跳一位
                    runOnUiThread(new Runnable() {

                        @Override
                        public void run() {
                            System.out.println("设置当前位置: " + viewPager.getCurrentItem());
                            viewPager.setCurrentItem(viewPager.getCurrentItem() + 1);
                        }
                    });
                }
            };
        }.start();

        //做网络请求
        OkHttpClient okHttpClient = new OkHttpClient();
        Log.e("text3", "test");
        //请求对象
        Request request = new Request.Builder()
                .url(Constant.BASE_URL + "GetAllPlace")
                .build();
        String url = Constant.BASE_URL + "GetAllPlace";
        Log.e("text3", url);
        //发送请求对象
        final Call call = okHttpClient.newCall(request);
        final Handler handler = new Handler();
        // 构建Runnable对象，在runnable中更新界面
        final Runnable runnableUi = new Runnable() {
            @Override
            public void run() {
                //更新界面
                listView.setAdapter(baseAdapter);
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

                String placeListStr = response.body().string();//获取服务器端响应字符串
                Log.e("text", placeListStr);

                Gson gson = new Gson();
                Type type = new TypeToken<List<Place>>() {
                }.getType();
                placeList = gson.fromJson(placeListStr, type);
                for (Place place : placeList
                        ) {
                    Log.e("text", place.getName());
                }
                baseAdapter = new MyBaseAdapter(placeList);

                handler.post(runnableUi);

            }
        });
        //listview实例化
//        list=new ArrayList<Place>();
//        //准备数据
//
//        Place p1=new Place("辛集国际皮革城","石家庄市辛集区","qinifnaofnals",R.drawable.xinji);
//        list.add(p1);
//        Place p2=new Place("西柏坡旧址","石家庄市西柏坡风景区","西柏坡红色旅游区为国家级风景名胜区、国家AAAAA级旅游景区、红色旅游经典景区、爱国主义教育基地。",R.drawable.baipo);
//        list.add(p2);
//        Place p3=new Place("荣国府","石家庄市正定县兴荣路51号","地处正定大佛寺附近。它是为拍摄电视连续剧《红楼梦》而修建的一座大型仿清古建筑群。荣国府建成于1986年，占地面积为4万多平方米，有大小房间215间，游廊102间",R.drawable.rongguofu);
//        list.add(p3);
//        Place p4=new Place("赵州桥","石家庄市赵县大石桥村附近","\n" +
//                "安济桥座落在赵县城南的河之上,因赵县古时曾为赵州，所以一般称为赵州桥。 安济桥全长64.4米，拱顶宽9米，跨径37.02，拱矢7.23。桥的结构十分厅巧，从整体来看，是一座单孔弧形桥。",R.drawable.zhaozhouqiao);
//        list.add(p4);
//        Place p5=new Place("园博园","石家庄市石家庄市正定新区主轴线中心","\n" +
//                "园博园被划分为七大功能分区，分别为主入口广场、燕赵园、社会园、专类园、康体园、滨水景观区、山体休闲区。\n" +
//                "值得一提的是，位于主入口广场以西处，拥有世界最大、最有特色的喷泉，将喷出120米高、120米宽的水柱，可以用来播放水幕电影，并将创下世界之最。",R.drawable.yuanbo);
//        list.add(p5);
//        Place p6=new Place("天桂山风景区","河北省石家庄市平山县北冶乡天桂山","\n" +
//                "天桂山景区，距石市90公里，主峰海拔1270米，总面积60平方公里，分为青龙观、万佛岩、玄武峰、滴翠谷等八个小景区。这里峰险、石奇、洞幽、泉多、林木繁茂、云雾缭绕。",R.drawable.tianguishan);
//        list.add(p6);


        //准备BaseAdapter对象
//        MyBaseAdapter baseAdapter=new MyBaseAdapter();
//        listView.setAdapter(baseAdapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Place p=placeList.get(position);
                Intent intent=new Intent(TravelActivity.this,DetailPlaceActivity.class);
                intent.putExtra("place",p);
                startActivity(intent);

            }
        });

    }
    class MyBaseAdapter extends BaseAdapter{
        private List<Place> list = new ArrayList<>();
        public MyBaseAdapter(List<Place> list){

            this.list = list;
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
                convertView=View.inflate(TravelActivity.this,R.layout.layout_travel_item,null);
            }
            //根据position设置对应的数据
            Place place=list.get(position);
            ImageView imageView=convertView.findViewById(R.id.siple_iv);
            TextView tv1=convertView.findViewById(R.id.siple_tv1);
            TextView tv2=convertView.findViewById(R.id.siple_tv2);
            //设置资源
            Glide.with(getBaseContext())
                    .load(Constant.BASE_URL+place.getImage())
                    .into(imageView);
            tv1.setText(place.getName());
            tv2.setText(place.getAddress());
            return convertView;
        }

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        isRunning=false;
    }

    private void initAdapter() {
        point_container.getChildAt(0).setEnabled(true);
        tv_desc.setText(contentDecs[0]);
        previousSelectedPosition=0;
        //设置适配器
        viewPager.setAdapter(new MyAdapter());
        //默认设置到中间某个位置
        int pos=Integer.MAX_VALUE/2-(Integer.MAX_VALUE/2%imageViewList.size());
        viewPager.setCurrentItem(5000000);//设置到某个位置
    }
    class MyAdapter extends PagerAdapter{

        @Override
        public int getCount() {
            return Integer.MAX_VALUE;
        }
        // 3,指定复用的判断逻辑，固定写法
        @Override
        public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
            //当滑到新的条目，又返回来，view是否可以被复用
            //返回判断规则
            return view==object;
        }
        // 1. 返回要显示的条目内容, 创建条目
        @Override
        public Object instantiateItem(ViewGroup container, int position) {
            System.out.println("instantiateItem初始化: " + position);
            // container: 容器: ViewPager
            // position: 当前要显示条目的位置 0 -> 4

//          newPosition = position % 5
            int newPosition = position % imageViewList.size();

            ImageView imageView = imageViewList.get(newPosition);
            // a. 把View对象添加到container中
            container.addView(imageView);
            // b. 把View对象返回给框架, 适配器
            return imageView; // 必须重写, 否则报异常
        }

        // 2. 销毁条目
        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
            // object 要销毁的对象
            System.out.println("destroyItem销毁: " + position);
            container.removeView((View) object);
        }
    }

    /**
     * 初始化要显示的数据
     */
    private void initData() {
        //图片资源id数组
        imageResIds=new int[]{R.drawable.baipo,R.drawable.xinma,R.drawable.huhushui,
        R.drawable.zhaozhouqiao,R.drawable.rongguofu};
        //文本描述
        contentDecs=new String[]{
            "革命圣地-西柏坡",
                "辛玛王国",
                "沕沕水生态风景区",
                "赵州古桥",
                "荣国府"
        };
        //初始化要展示的5个ImageView
        imageViewList=new ArrayList<ImageView>();
        ImageView imageView;
        View pointView;
        LinearLayout.LayoutParams layoutParams;
        for (int i = 0; i < imageResIds.length; i++) {
            // 初始化要显示的图片对象
            imageView = new ImageView(this);
            imageView.setBackgroundResource(imageResIds[i]);
            imageViewList.add(imageView);

            // 加小白点, 指示器
            pointView = new View(this);
            pointView.setBackgroundResource(R.drawable.xiaoyuandian);
            layoutParams = new LinearLayout.LayoutParams(5, 5);
            if (i != 0)
                layoutParams.leftMargin = 10;
            // 设置默认所有都不可用
            pointView.setEnabled(false);
            point_container.addView(pointView, layoutParams);
        }


}

    private void initViews() {
        viewPager=(ViewPager)findViewById(R.id.viewpager);
        viewPager.setOnPageChangeListener(this);//设置页面更新监听
        point_container=(LinearLayout)findViewById(R.id.point_container);
        tv_desc=(TextView)findViewById(R.id.tv_desc);
    }

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
        //滚动时调用
    }

    @Override
    public void onPageSelected(int position) {
        //新的条目被选中时调用
        System.out.println("onPageSelected: " + position);
        int newPosition = position % imageViewList.size();

        //设置文本
        tv_desc.setText(contentDecs[newPosition]);

        //把之前的禁用，把最新的启用，更新指示器
        point_container.getChildAt(previousSelectedPosition).setEnabled(false);
        point_container.getChildAt(newPosition).setEnabled(true);
        //记录之前的位置
        previousSelectedPosition=newPosition;
    }

    @Override
    public void onPageScrollStateChanged(int state) {
        //滚动状态变化时调用
    }
}
