package android.com.zlt;

import android.app.Activity;
import android.content.Intent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by user on 2018/5/15.
 */

public class NewsHotAdapter extends BaseAdapter {
    private Activity context;
    private List<New> newsList = new ArrayList<>();
    public NewsHotAdapter(Activity context, List<New> newsList){
        this.context = context;
        this.newsList = newsList;
    }
    @Override
    public int getCount() {
        return newsList.size();
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
                    R.layout.hot_list_item,null
            );}

        ImageView img= convertView.findViewById(R.id.hot_img);
        Glide.with(context)
                .load(Constant.BASE_URL+newsList.get(position).getNewsImages())
                .into(img);
        TextView title = convertView.findViewById(R.id.hot_title);
        title.setText(newsList.get(position).getTitle());

        //点击进入详细页
        LinearLayout layout = convertView.findViewById(R.id.hot_layout);
        layout.setOnClickListener(new NewsHotAdapter.DetailListener(newsList.get(position)));
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
            intent.setClass(context,DetailActivity.class);
            intent.putExtra("title",news.getTitle());
            intent.putExtra("content",news.getContent());
            context.startActivityForResult(intent,0);
        }
    }
}
