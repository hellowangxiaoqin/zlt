package android.com.zlt;

import android.app.Activity;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by 王玉波 on 2018/5/24.
 */

public class DynamicListAdapter extends BaseAdapter {
    private Activity context;
    private List<Dynamic> bookList=new ArrayList<>();
    public DynamicListAdapter(Activity context, List<Dynamic> bookList){
        this.context=context;
        this.bookList=bookList;
    }
    @Override
    public int getCount() {
        return bookList.size();
    }

    @Override
    public Object getItem(int position) {
        return bookList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView==null){
            convertView=context.getLayoutInflater().inflate(
                    R.layout.dynamiclist_detail,null
            );}
        ImageView imageView=convertView.findViewById(R.id.image);
        TextView textViewName=convertView.findViewById(R.id.name);
        ImageView shuoView=convertView.findViewById(R.id.shuo);

        Glide.with(context)
                .load(Constant.BASE_URL+bookList.get(position).getImage())
                .into(imageView);
        Log.e("text",bookList.get(position).getName());
        textViewName.setText(bookList.get(position).getName());
       // shuoView.setText(bookList.get(position).getBookPrice());
        Glide.with(context)
                .load(Constant.BASE_URL+bookList.get(position).getDynamic())
                .into(shuoView);
        return convertView;
    }
}
