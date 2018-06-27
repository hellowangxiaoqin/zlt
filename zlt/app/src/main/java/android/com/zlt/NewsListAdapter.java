package android.com.zlt;

import android.app.Activity;
import android.content.Intent;
import android.icu.text.SimpleDateFormat;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by user on 2018/5/15.
 */

public class NewsListAdapter extends BaseAdapter {
    private Activity context;
    private List<New> newsList = new ArrayList<>();
    public NewsListAdapter(Activity context, List<New> newsList){
        this.context = context;
        this.newsList = newsList;
    }
    @Override
    public int getCount() {
        return newsList.size()/3;
    }

    @Override
    public Object getItem(int position) {
        return newsList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView==null){
            convertView=context.getLayoutInflater().inflate(
                    R.layout.list_item,null
            );}
        int count = getCount();
//        int length = newsList.size();
//        for (int i = 1;i<=count;i++){
//
//        }
        TextView time = convertView.findViewById(R.id.time);
        SimpleDateFormat sd= null;
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.N) {
            sd = new SimpleDateFormat("yyyy-MM-dd HH:mm E");
        }
        Date curDate =  new Date(System.currentTimeMillis());
        String view_time= null;
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.N) {
            view_time = sd.format(curDate);
        }
        time.setText(view_time);
        int i = position*3;
        Log.e("text", String.valueOf(i));
        ImageView img= convertView.findViewById(R.id.News_Pic);
        Glide.with(context)
                .load(Constant.BASE_URL+newsList.get(position*3+0).getNewsImages())
                .into(img);
        TextView title = convertView.findViewById(R.id.News_Title);
        title.setText(newsList.get(position*3+0).getTitle());

        ImageView img1= convertView.findViewById(R.id.News_Pic1);
        Glide.with(context)
                .load(Constant.BASE_URL+newsList.get(position*3+1).getNewsImages())
                .into(img1);
        TextView title1 = convertView.findViewById(R.id.News_Title1);
        title1.setText(newsList.get(position*3+1).getTitle());

        ImageView img2= convertView.findViewById(R.id.News_Pic2);
        Glide.with(context)
                .load(Constant.BASE_URL+newsList.get(position*3+2).getNewsImages())
                .into(img2);
        TextView title2 = convertView.findViewById(R.id.News_Title2);
        title2.setText(newsList.get(position*3+2).getTitle());
        //点击进入详细页
        RelativeLayout layout1 = convertView.findViewById(R.id.layout1);
        layout1.setOnClickListener(new DetailListener(newsList.get(position*3+0)));
        LinearLayout layout2 = convertView.findViewById(R.id.layout2);
        layout2.setOnClickListener(new DetailListener(newsList.get(position*3+1)));
        LinearLayout layout3 = convertView.findViewById(R.id.layout3);
        layout3.setOnClickListener(new DetailListener(newsList.get(position*3+2)));
        return convertView;
    }
    protected class DetailListener implements View.OnClickListener{
        private New news;
        public DetailListener(New news){
            this.news = news;
        }
        @Override
        public void onClick(View v) {
            Intent intent = new Intent();
            Log.e("text",news.getTitle());
            Log.e("txt", String.valueOf(context));
            intent.setClass(context,DetailActivity.class);
            Log.e("text","test");
            intent.putExtra("title",news.getTitle());
            intent.putExtra("content",news.getContent());
            context.startActivityForResult(intent,0);
        }
    }
}
