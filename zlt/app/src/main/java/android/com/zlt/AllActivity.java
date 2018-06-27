package android.com.zlt;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;

public class AllActivity extends AppCompatActivity implements View.OnClickListener{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_all);
        LinearLayout l1=findViewById(R.id.layout_lvxing);
        l1.setOnClickListener(this);
        LinearLayout l2=findViewById(R.id.layout_jiaofei);
        l2.setOnClickListener(this);
        LinearLayout l3=findViewById(R.id.layout_weather);
        l3.setOnClickListener(this);
        LinearLayout l4=findViewById(R.id.layout_dongtai);
        l4.setOnClickListener(this);
        LinearLayout l5=findViewById(R.id.layout_geren);
        l5.setOnClickListener(this);
        LinearLayout l6=findViewById(R.id.layout_jianshen);
        l6.setOnClickListener(this);
        LinearLayout l7=findViewById(R.id.layout_news);
        l7.setOnClickListener(this);
        LinearLayout l8=findViewById(R.id.layout_yangsheng);
        l8.setOnClickListener(this);
        LinearLayout l9=findViewById(R.id.layout_yuyue);
        l9.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.layout_lvxing:
                //跳转到旅游景点
                Intent intent1=new Intent(AllActivity.this,TravelActivity.class);
                startActivity(intent1);
                break;
            //跳转到生活缴费
            case R.id.layout_jiaofei:
                Intent intent2=new Intent(AllActivity.this,ComDynamicActivity.class);
                startActivity(intent2);
                break;
            //跳转到天气
            case R.id.layout_weather:
                Intent intent3=new Intent(AllActivity.this, WeatherActivity.class);
                startActivity(intent3);
                break;
            //跳转到预约挂号
            case R.id.layout_yuyue:
                Intent intent4 = new Intent();
                intent4.setData(Uri.parse("https://www.eztcn.com/Home/Index/index/cityid/3.html"));//Url 就是你要打开的网址
                intent4.setAction(Intent.ACTION_VIEW);
                this.startActivity(intent4); //启动浏览器
                break;
            //跳到动态
            case R.id.layout_dongtai:
                Intent intent6=new Intent(AllActivity.this,DynamicState.class);
                startActivity(intent6);
                break;
            //跳转到养生
            case R.id.layout_yangsheng:
                Intent i1=new Intent();
                i1.setData(Uri.parse("http://www.cnys.com/"));
                i1.setAction(Intent.ACTION_VIEW);
                startActivity(i1);
                break;
            //跳转到健身
            case R.id.layout_jianshen:
                Intent i2=new Intent();
                i2.setData(Uri.parse("http://www.jianshen8.com/"));
                i2.setAction(Intent.ACTION_VIEW);
                startActivity(i2);
                break;
            case R.id.layout_geren:
                Intent i3=new Intent(AllActivity.this,My.class);
                startActivity(i3);
                break;
            case R.id.layout_news:
                Intent i4=new Intent(AllActivity.this,News.class);
                startActivity(i4);
                break;
        }
    }
}
