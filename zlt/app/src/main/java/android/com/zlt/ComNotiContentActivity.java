package android.com.zlt;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Adapter;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.PopupMenu;
import android.widget.TextView;
import android.widget.Toast;

import java.sql.Time;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by dell on 2018/3/27.
 */

public class ComNotiContentActivity extends Activity {
    ListAdapter adapter;
    List<Map<String,Object>> list;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_comnoti_listview_content);


        Button btnQuit = findViewById(R.id.btn_comnoti_list_quit);

        btnQuit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        final Intent intent = getIntent();
        final int position = intent.getIntExtra("position",0);

        Log.e("position", String.valueOf(position));

        list = new ArrayList<>();


                Map<String,Object> map1 = new HashMap<>();
                map1.put("con_msg_time","12:12");
                map1.put("btn_main_bg",R.drawable.con_01_1);
                map1.put("btn_main_name","一组关于理想和现实的照片，拉到底……");
                map1.put("btn_main_color","#FFFFFF");
                map1.put("btn_02_name","最可怕的是比你优秀的人还努力");
                map1.put("btn_03_name","那个微商的女同学，把我拉黑了");
                map1.put("btn_04_name","课堂|不要让厌学毁了你孩子的未来");
                map1.put("img_02",R.drawable.con_02);
                map1.put("img_03",R.drawable.con_03);
                map1.put("img_04",R.drawable.con_04);
                list.add(map1);

                Map<String,Object> map2 = new HashMap<>();
                map2.put("con_msg_time","23:23");
                map2.put("btn_main_bg",R.drawable.con_01_2);
                map2.put("btn_main_name","这样背单词，一个月轻松掌握2000+词汇");
                map2.put("btn_main_color","#FFFFFF");
                map2.put("btn_02_name","我害怕那些考证的人");
                map2.put("btn_03_name","朋友圈正在慢慢删除你的生活");
                map2.put("btn_04_name","课堂|如何快速纠正发音");
                map2.put("img_02",R.drawable.con_06);
                map2.put("img_03",R.drawable.con_05);
                map2.put("img_04",R.drawable.con_07);
                list.add(map2);

                Map<String,Object> map3 = new HashMap<>();
                map3.put("con_msg_time","09:09");
                map3.put("btn_main_bg",R.drawable.con_02_1);
                map3.put("btn_main_name"," ");
                map3.put("btn_main_color","#000000");
                map3.put("btn_02_name","榜单|全国普通高校团委微信公众号…");
                map3.put("btn_03_name","习近平心中的“中国人民”");
                map3.put("btn_04_name","考研还是找工作，看完再决定…");
                map3.put("img_02",R.drawable.con_02);
                map3.put("img_03",R.drawable.con_03);
                map3.put("img_04",R.drawable.con_04);
                list.add(map3);

                Map<String,Object> map4 = new HashMap<>();
                map4.put("con_msg_time","12:12");
                map4.put("btn_main_bg",R.drawable.con_02_2);
                map4.put("btn_main_name","哪个瞬间让你忽然意识到自己长大了？");
                map4.put("btn_main_color","#FFFFFF");
                map4.put("btn_02_name","青语|你那么年轻，一定有很多小确幸");
                map4.put("btn_03_name","第十六届“挑战杯”竞赛召开");
                map4.put("btn_04_name","习近平：两会“画中画”");
                map4.put("img_02",R.drawable.con_06);
                map4.put("img_03",R.drawable.con_05);
                map4.put("img_04",R.drawable.con_07);
                list.add(map4);
//                Map<String,Object> map4s = new HashMap<>();
//                map4s.put("con_msg_time","23:23");
//                map4s.put("btn_main_bg",R.drawable.con_02_3);
//                map4s.put("btn_main_name"," ");
//                map4s.put("btn_main_color","#000000");
//                map4s.put("btn_02_name","榜单|全国普通高校团委微信公众号…");
//                map4s.put("btn_03_name","习近平心中的“中国人民”");
//                map4s.put("btn_04_name","考研还是找工作，看完再决定…");
//                map4s.put("img_02",R.drawable.con_02);
//                map4s.put("img_03",R.drawable.con_03);
//                map4s.put("img_04",R.drawable.con_04);
//                list.add(map4s);



                Map<String,Object> map5 = new HashMap<>();
                map5.put("con_msg_time","09:09");
                map5.put("btn_main_bg",R.drawable.con_03_1);
                map5.put("btn_main_name","2017年新增博士、硕士学位授权点名单出炉");
                map5.put("btn_main_color","#000000");
                map5.put("btn_02_name","榜单|全国普通高校团委微信公众号…");
                map5.put("btn_03_name","习近平心中的“中国人民”");
                map5.put("btn_04_name","考研还是找工作，看完再决定…");
                map5.put("img_02",R.drawable.con_02);
                map5.put("img_03",R.drawable.con_03);
                map5.put("img_04",R.drawable.con_04);
                list.add(map5);

                Map<String,Object> map6 = new HashMap<>();
                map6.put("con_msg_time","12:12");
                map6.put("btn_main_bg",R.drawable.con_03_2);
                map6.put("btn_main_name"," ");
                map6.put("btn_main_color","#000000");
                map6.put("btn_02_name","青语|你那么年轻，一定有很多小确幸");
                map6.put("btn_03_name","第十六届“挑战杯”竞赛召开");
                map6.put("btn_04_name","习近平：两会“画中画”");
                map6.put("img_02",R.drawable.con_06);
                map6.put("img_03",R.drawable.con_05);
                map6.put("img_04",R.drawable.con_07);
                list.add(map6);


        adapter = new ListAdapter(this,R.layout.layout_items_content,list);
        ListView lv = findViewById(R.id.lv_content);
        lv.setAdapter(adapter);

        Button btnpopupmenu1 = findViewById(R.id.btn_bottom_2);
        btnpopupmenu1.setOnClickListener(new btnOnClickListener0());

        //按钮点击后弹出popupmenu
        Button btnpopupmenu2 = findViewById(R.id.btn_bottom_3);
        btnpopupmenu2.setOnClickListener(new btnOnClickListener());

        Button btnpopupmenu3 = findViewById(R.id.btn_bottom_4);
        btnpopupmenu3.setOnClickListener(new btnOnClickListener1());
    }

    //定义一个内部类ListAdapter
    private class ListAdapter extends BaseAdapter {
        private List<Map<String,Object>> data;
        private Context context;
        private int list_item_id;

        public ListAdapter(Context context,int list_item_id,List<Map<String,Object>> data){
            this.context = context;
            this.list_item_id = list_item_id;
            this.data = data;
        }

        @Override
        public int getCount() {
            return data.size();
        }

        @Override
        public Object getItem(int position) {
            return data.get(position);
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            if(convertView == null){
                LayoutInflater myInflater = LayoutInflater.from(context);
                convertView = myInflater.inflate(list_item_id,null);
                Log.e("name","desp");
            }
            TextView tv_time = convertView.findViewById(R.id.con_msg_time);
            Button btn_main = convertView.findViewById(R.id.btn_content_main);
            Button btn_02 = convertView.findViewById(R.id.btn_contnet_2);
            Button btn_03 = convertView.findViewById(R.id.btn_contnet_3);
            Button btn_04 = convertView.findViewById(R.id.btn_contnet_4);
            ImageView iv_02 = convertView.findViewById(R.id.img_content_2);
            ImageView iv_03 = convertView.findViewById(R.id.img_content_3);
            ImageView iv_04 = convertView.findViewById(R.id.img_content_4);

            Map<String,Object> map = data.get(position);

            tv_time.setText((String)map.get("con_msg_time"));
            btn_main.setBackgroundResource((Integer) map.get("btn_main_bg"));
            btn_main.setText((String)map.get("btn_main_name"));
            btn_main.setTextColor(Color.parseColor((String)map.get("btn_main_color")));
            btn_02.setText((String)map.get("btn_02_name"));
            btn_03.setText((String)map.get("btn_03_name"));
            btn_04.setText((String)map.get("btn_04_name"));
            iv_02.setImageResource((Integer) map.get("img_02"));
            iv_03.setImageResource((Integer) map.get("img_03"));
            iv_04.setImageResource((Integer) map.get("img_04"));



            return convertView;
        }
    }

    private int dip2px(Context context, float dpValue) {
        final float scale = context.getResources()
                .getDisplayMetrics().density;
        return (int)(dpValue * scale + 0.5f);
    }

    private class btnOnClickListener implements View.OnClickListener{
        @Override
        public void onClick(View v) {
            PopupMenu menu = new PopupMenu(getApplicationContext(),v);
            menu.getMenuInflater().inflate(R.menu.menu_popumenu,menu.getMenu());
            menu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                @Override
                public boolean onMenuItemClick(MenuItem item) {
                    switch (item.getItemId()){
                        case R.id.popupmenu_item_1:
                            Toast.makeText(getApplicationContext(),
                                    "获取消息…",Toast.LENGTH_SHORT).show();
                            new Handler().postDelayed(new Runnable() {
                                public void run() {
                                    Map<String, Object> map4 = new HashMap<>();
                                    map4.put("con_msg_time","12:12");
                                    map4.put("btn_main_bg",R.drawable.con_02_2);
                                    map4.put("btn_main_name","哪个瞬间让你忽然意识到自己长大了？");
                                    map4.put("btn_main_color","#FFFFFF");
                                    map4.put("btn_02_name","青语|你那么年轻，一定有很多小确幸");
                                    map4.put("btn_03_name","第十六届“挑战杯”竞赛召开");
                                    map4.put("btn_04_name","习近平：两会“画中画”");
                                    map4.put("img_02",R.drawable.con_06);
                                    map4.put("img_03",R.drawable.con_05);
                                    map4.put("img_04",R.drawable.con_07);
                                    list.add(map4);
                                    ListView lv = findViewById(R.id.lv_content);
                                    lv.setAdapter(adapter);
                                }
                            },3000);
                                    return true;
                                    case R.id.popupmenu_item_2:
                                    Toast.makeText(getApplicationContext(),
                                            "获取消息…", Toast.LENGTH_SHORT).show();
                                    new Handler().postDelayed(new Runnable(){
                                        public void run() {
                                            Map<String, Object> map3 = new HashMap<>();
                                            map3.put("con_msg_time", "09:09");
                                            map3.put("btn_main_bg", R.drawable.con_02_1);
                                            map3.put("btn_main_name", " ");
                                            map3.put("btn_main_color", "#000000");
                                            map3.put("btn_02_name", "榜单|全国普通高校团委微信公众号…");
                                            map3.put("btn_03_name", "习近平心中的“中国人民”");
                                            map3.put("btn_04_name", "考研还是找工作，看完再决定…");
                                            map3.put("img_02", R.drawable.con_02);
                                            map3.put("img_03", R.drawable.con_03);
                                            map3.put("img_04", R.drawable.con_04);
                                            list.add(map3);
                                            ListView lv = findViewById(R.id.lv_content);
                                            lv.setAdapter(adapter);
                                        }
                                    },3000);
                            return true;
                        case R.id.popupmenu_item_3:
                            Toast.makeText(getApplicationContext(),
                                    "获取消息…",Toast.LENGTH_SHORT).show();
                            new Handler().postDelayed(new Runnable(){
                                public void run() {
                                    Map<String,Object> map2 = new HashMap<>();
                                    map2.put("con_msg_time","23:23");
                                    map2.put("btn_main_bg",R.drawable.con_01_2);
                                    map2.put("btn_main_name","这样背单词，一个月轻松掌握2000+词汇");
                                    map2.put("btn_main_color","#FFFFFF");
                                    map2.put("btn_02_name","我害怕那些考证的人");
                                    map2.put("btn_03_name","朋友圈正在慢慢删除你的生活");
                                    map2.put("btn_04_name","课堂|如何快速纠正发音");
                                    map2.put("img_02",R.drawable.con_06);
                                    map2.put("img_03",R.drawable.con_05);
                                    map2.put("img_04",R.drawable.con_07);
                                    list.add(map2);
                                    ListView lv = findViewById(R.id.lv_content);
                                    lv.setAdapter(adapter);
                                }
                            },3000);
                            return true;
                    }
                    return false;
                }
            });
            menu.show();
        }
    }

    private class btnOnClickListener0 implements View.OnClickListener{
        @Override
        public void onClick(View v) {
            Toast.makeText(getApplicationContext(),
                    "获取消息…",Toast.LENGTH_SHORT).show();
            new Handler().postDelayed(new Runnable(){
                public void run() {
                    Map<String,Object> map4 = new HashMap<>();
                    map4.put("con_msg_time","12:12");
                    map4.put("btn_main_bg",R.drawable.con_02_2);
                    map4.put("btn_main_name","哪个瞬间让你忽然意识到自己长大了？");
                    map4.put("btn_main_color","#FFFFFF");
                    map4.put("btn_02_name","青语|你那么年轻，一定有很多小确幸");
                    map4.put("btn_03_name","第十六届“挑战杯”竞赛召开");
                    map4.put("btn_04_name","习近平：两会“画中画”");
                    map4.put("img_02",R.drawable.con_06);
                    map4.put("img_03",R.drawable.con_05);
                    map4.put("img_04",R.drawable.con_07);
                    list.add(map4);
                    ListView lv = findViewById(R.id.lv_content);
                    lv.setAdapter(adapter);
                }
            }, 3000);



        }
    }

    private class btnOnClickListener1 implements View.OnClickListener{
        @Override
        public void onClick(View v) {
            Toast.makeText(getApplicationContext(),
                    "获取消息…",Toast.LENGTH_SHORT).show();
            new Handler().postDelayed(new Runnable(){
                public void run() {
                    Map<String,Object> map6 = new HashMap<>();
                    map6.put("con_msg_time","12:12");
                    map6.put("btn_main_bg",R.drawable.con_03_2);
                    map6.put("btn_main_name"," ");
                    map6.put("btn_main_color","#000000");
                    map6.put("btn_02_name","青语|你那么年轻，一定有很多小确幸");
                    map6.put("btn_03_name","第十六届“挑战杯”竞赛召开");
                    map6.put("btn_04_name","习近平：两会“画中画”");
                    map6.put("img_02",R.drawable.con_06);
                    map6.put("img_03",R.drawable.con_05);
                    map6.put("img_04",R.drawable.con_07);
                    list.add(map6);
                    ListView lv = findViewById(R.id.lv_content);
                    lv.setAdapter(adapter);
                }
            }, 3000);



        }
    }
}
