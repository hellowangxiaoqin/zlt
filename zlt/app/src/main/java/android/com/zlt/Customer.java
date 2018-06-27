package android.com.zlt;

/**
 * Created by 王玉波 on 2018/5/15.
 */
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;
import java.util.Map;

public class Customer extends BaseAdapter {
    private List<Map<String,Object>> data;
    private Context context;
    private int item_layout_id;

    public Customer(Context context,int item_layout_id,List<Map<String,Object>> data){
        this.context=context;
        this.item_layout_id=item_layout_id;
        this.data=data;
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
        if(convertView==null){
            LayoutInflater minflater=LayoutInflater.from(context);
            convertView=minflater.inflate(item_layout_id,null);
        }
        ImageView iv=convertView.findViewById(R.id.image);
        TextView tvName=convertView.findViewById(R.id.name);
        ImageView tvShuo=convertView.findViewById(R.id.shuo);

        Map<String,Object> map=data.get(position);

        iv.setImageResource((int)map.get("image"));
        tvName.setText(map.get("name").toString());
        iv.setImageResource((int)map.get("shuo"));


        return convertView;
    }
}