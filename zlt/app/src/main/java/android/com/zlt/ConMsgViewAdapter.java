package android.com.zlt;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;

/**
 * Created by DELL on 2018/6/19.
 */

public class ConMsgViewAdapter extends BaseAdapter {

    public static interface IMsgViewType {
        int IMVT_COM_MSG = 0;// 收到对方的消息
        int IMVT_TO_MSG = 1;// 自己发送出去的消息
    }
    private static final int ITEMCOUNT = 2;// 消息类型的总数
    private List<ConMsgEntity> coll;// 消息对象数组
    private LayoutInflater mInflater;
    public ConMsgViewAdapter(Context context, List<ConMsgEntity> coll) {
        this.coll = coll;
        mInflater = LayoutInflater.from(context);
    }
    public int getCount() {
        return coll.size();
    }
    public Object getItem(int position) {
        return coll.get(position);
    }
    public long getItemId(int position) {
        return position;
    }
    /**
     * 得到Item的类型，是对方发过来的消息，还是自己发送出去的
     */
    public int getItemViewType(int position) {
        ConMsgEntity entity = coll.get(position);
        if (entity.getMsgType()) {//收到的消息
            return IMsgViewType.IMVT_COM_MSG;
        } else {//自己发送的消息
            return IMsgViewType.IMVT_TO_MSG;
        }
    }
    /**
     * Item类型的总数
     */
    public int getViewTypeCount() {
        return ITEMCOUNT;
    }
    public View getView(int position, View convertView, ViewGroup parent) {
        ConMsgEntity entity = coll.get(position);
        boolean isComMsg = entity.getMsgType();
        ViewHolder viewHolder = null;
        if (convertView == null) {
            if (isComMsg) {
                convertView = mInflater.inflate(
                        R.layout.conitem_msglist_item_left, null);
            } else {
                convertView = mInflater.inflate(
                        R.layout.conitem_msglist_item_right, null);
            }
            viewHolder = new ViewHolder();
            viewHolder.tvSendTime = (TextView) convertView
                    .findViewById(R.id.tv_sengmsg_time);
//            viewHolder.tvUserName = (TextView) convertView
//                    .findViewById(R.id.tv_username);
            viewHolder.tvContent = (TextView) convertView
                    .findViewById(R.id.tv_conitem_msg_content);
            viewHolder.isComMsg = isComMsg;
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }
        viewHolder.tvSendTime.setText(entity.getDate());
//        viewHolder.tvUserName.setText(entity.getName());
        viewHolder.tvContent.setText(entity.getMessage());
        return convertView;
    }
    static class ViewHolder {
        public TextView tvSendTime;
        public TextView tvUserName;
        public TextView tvContent;
        public boolean isComMsg = true;
    }
}
