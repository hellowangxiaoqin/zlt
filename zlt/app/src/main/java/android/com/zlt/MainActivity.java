package android.com.zlt;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentTabHost;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TabHost;
import android.widget.TextView;

import java.util.HashMap;
import java.util.Map;

public class MainActivity extends FragmentActivity {
    private FragmentTabHost myTabHost;
    private Map<String,View> map;
    private Intent intent1;
    private String userName;

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Intent intent = getIntent();
        userName = intent.getStringExtra("userName");
        Log.e("text2",userName);
        intent1 = new Intent(MainActivity.this,Shequ.class);
        intent1.putExtra("userName",userName);

//        Intent intent_people=new Intent(MainActivity.this,My.class);
//        intent_people.putExtra("userName",userName);
//        startActivity(intent_people);
        map=new HashMap<>();
        //初始化
        initTabhost();
        //当前点击切换到哪，会传入Id
        myTabHost.setOnTabChangedListener(new TabHost.OnTabChangeListener() {
            @Override
            public void onTabChanged(String tabId) {
                changeTabSpec(0,R.drawable.shouye,"#000000");
                changeTabSpec(1,R.drawable.news,"#000000");
                changeTabSpec(2,R.drawable.shequ,"#000000");
                changeTabSpec(3,R.drawable.my,"#000000");
                switch (tabId){
                    case "tab1":
                            changeTabSpec(0,R.drawable.shouye_fill,"#1296db");
                            return;
                    case "tab2":
                        changeTabSpec(1,R.drawable.news_fill,"#1296db");
                        return;
                    case "tab3":
                        changeTabSpec(2,R.drawable.shequ_fill,"#1296db");
                        return;
                    case "tab4":
                        changeTabSpec(3,R.drawable.my_fill,"#1296db");
                        return;
                }
            }
        });
    }

    /**
     * Tab改变
     * @param id
     * @param drawable
     * @param color
     */
    private void changeTabSpec(int id,int drawable,String color) {
        ImageView imageView=myTabHost.getTabWidget().getChildAt(id).findViewById(R.id.imageId);
        imageView.setImageResource(drawable);
        TextView textView=myTabHost.getTabWidget().getChildAt(id).findViewById(R.id.textId);
        textView.setTextColor(Color.parseColor(color));
    }

    /**
     * 初始化TabHost
     */
    private void initTabhost() {
       myTabHost=findViewById(android.R.id.tabhost);
       myTabHost.setup(this,getSupportFragmentManager(),android.R.id.tabhost);
       //第一个
        addTabSpec("tab1","首页",R.drawable.shouye_fill,Index.class);
        //第二个
        addTabSpec("tab2","新闻",R.drawable.news,News.class);
        //第三个
        addTabSpec("tab3","社区",R.drawable.shequ,CommunityActivity.class);
        //第四个
        addTabSpec("tab4","我的",R.drawable.my,My.class);
        //设置默认显示的页面
        myTabHost.setCurrentTab(0);

    }

    /**
     * 添加TabSpec
     * @param id     标签
     * @param text   显示文本
     * @param drawable 显示的图片
     * @param fragment 对应的Fragment类
     */
    private void addTabSpec(String id, String text, int drawable, Class<?> fragment) {
        View viewTab=getTabView(text,drawable);
        TabHost.TabSpec tabSpec=myTabHost.newTabSpec(id).setIndicator(viewTab);
        myTabHost.addTab(tabSpec,fragment,null);
        map.put(id,viewTab);
    }

    /**
     * 创建选项视图
     * @param text
     * @param drawable
     * @return
     */
    private View getTabView(String text, int drawable) {
        View view=getLayoutInflater().inflate(R.layout.fragment_tab,null);
        ImageView imageView=view.findViewById(R.id.imageId);
        imageView.setImageResource(drawable);
        TextView textView=view.findViewById(R.id.textId);
        textView.setText(text);
        return view;
    }
}
