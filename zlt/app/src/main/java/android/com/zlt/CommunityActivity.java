package android.com.zlt;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Created by dell on 2018/5/11.
 */

public class CommunityActivity extends Fragment {
    String userName = GlobalData.getUserName();
    ListView listViewComlist;
    ListAdapter adapter;
    View view;
    View view1;
    String nums = "0";
    TextView tvNewMsgsNums;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view=inflater.inflate(R.layout.layout_community,container,false);

        EditText et1 = view.findViewById(R.id.et_commu_search);
        et1.clearFocus();

//        //newmsg通知
//        tvNewMsgsNums = view1.findViewById(R.id.tv_comitem_newMsgNoti);
//        tvNewMsgsNums.setBackgroundColor(Color.parseColor("#FFFFFF"));

        //BaiduMap
        Button btnMap = view.findViewById(R.id.btn_commu_map);
        btnMap.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(),BaiduMapActivity.class);
                startActivityForResult(intent,0);
            }
        });

        //通知
        Button btnNotice = view.findViewById(R.id.btn_commu_notice);
        btnNotice.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(),ComNotiContentActivity.class);
                startActivityForResult(intent,0);
            }
        });

        //签到
        Button btnSign = view.findViewById(R.id.btn_commu_sign);
        btnSign.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(),SignActivity1.class);
                startActivityForResult(intent,0);
            }
        });


        final Intent intent0 = getActivity().getIntent();
        Log.e("Constact",userName+"");

        listViewComlist = view.findViewById(R.id.lv_comlist);
        final List<Map<String,Object>> comlist = new ArrayList<>();

        //
        Map<String,Object> map0 = new HashMap<>();
        map0.put("comId","dynamic");
        map0.put("comImg",R.drawable.dynamic);
        map0.put("comTitle","动态");
        comlist.add(map0);
        Map<String,Object> map1 = new HashMap<>();
        map1.put("comId","constact");
        map1.put("comImg",R.drawable.contact);
        map1.put("comTitle","通讯录");
        comlist.add(map1);
        Map<String,Object> map2 = new HashMap<>();
        map2.put("comId","livingpayment");
        map2.put("comImg",R.drawable.living);
        map2.put("comTitle","生活缴费");
        comlist.add(map2);
        Map<String,Object> map3 = new HashMap<>();
        map3.put("comId","carservice");
        map3.put("comImg",R.drawable.car);
        map3.put("comTitle","车主服务");
        comlist.add(map3);

        adapter = new ListAdapter(R.layout.layout_comlist_item,
                getActivity(),comlist);
        listViewComlist.setAdapter(adapter);

        //做网络请求
        OkHttpClient okHttpClient = new OkHttpClient();
        //请求对象
        Request request = new Request.Builder()
                .url(Constant.BASE_URL + "GetMessagesServlet?loginName="+userName)
                .build();
        //发送请求对象
        final Call call = okHttpClient.newCall(request);
        final Handler handler = new Handler();
        // 构建Runnable对象，在runnable中更新界面
        final Runnable runnableUi = new Runnable() {
            @Override
            public void run() {

            }

        };
        new Thread() {

            @Override
            public void run() {

                call.enqueue(new Callback() {
                    @Override
                    public void onFailure(Call call, IOException e) {
                        e.printStackTrace();
                        Log.e("Constact","Com.failure");
                    }

                    @Override
                    public void onResponse(Call call, Response response) throws IOException {
                        Object num = response.body().string();
                        nums = String.valueOf(num);
                        Log.e("Constact","success");
                        Log.e("text",""+nums);
                        handler.post(runnableUi);

                    }
                });

            }
        }.start();
        return view;
    }

    private class ListAdapter extends BaseAdapter{
        private int comItemId;
        private Context context;
        private List<Map<String,Object>> list;
        public ListAdapter(int comItemId,Context context,List<Map<String,Object>> list){
            this.comItemId = comItemId;
            this.context = context;
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
            return position;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            if(convertView == null){
                LayoutInflater inflater = LayoutInflater.from(context);
                convertView = inflater.inflate(comItemId,null);
            }
            listViewComlist = view.findViewById(R.id.lv_comlist);
            ImageView ivCom = convertView.findViewById(R.id.comitem_img);
            TextView tvCom = convertView.findViewById(R.id.comitem_tv);
            Map<String,Object> map = list.get(position);
            ivCom.setImageResource((Integer) map.get("comImg"));
            tvCom.setText((String)map.get("comTitle"));
            tvNewMsgsNums = convertView.findViewById(R.id.tv_comitem_newMsgNoti);
            if(!nums.equals("0")){
                String comId = (String)map.get("comId");
                Log.e("ComId", ""+comId );
                if(comId == "constact"){
                    tvNewMsgsNums.setBackgroundResource(R.drawable.newmsgred);
                }
            }

            //单击
            listViewComlist.setOnItemClickListener(new ItemListener(list));

            return convertView;
        }
    }

    //item单击事件监听
    private class ItemListener implements AdapterView.OnItemClickListener {
        private List<Map<String, Object>> list;

        public ItemListener(List<Map<String, Object>> list) {
            this.list = list;
        }

        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
            Map<String, Object> map = list.get(position);
            Intent intent = new Intent();
            String comId = map.get("comId").toString();
            switch (comId){
                case "dynamic":
                    if(userName.equals("null")){
                        Intent intent0 = new Intent(getActivity(),LogActivity.class);
                    }
//                    tvNewMsgsNums = view.findViewById(R.id.tv_comitem_newMsgNoti);
//                    tvNewMsgsNums.setBackgroundColor(Color.parseColor("#E0E0E0"));
                    intent.setClass(getActivity(), DynamicState.class);
                    intent.putExtra("comId", map.get("comId").toString());
                    intent.putExtra("comTitle", map.get("comTitle").toString());
                    intent.putExtra("userName",userName);
                    startActivityForResult(intent, 0);
                    break;
                case "constact":
                    if(userName.equals("null")){
                        Intent intent0 = new Intent(getActivity(),LogActivity.class);
                    }
                    intent.setClass(getActivity(), ComConstactActivity.class);
                    intent.putExtra("comId", map.get("comId").toString());
                    intent.putExtra("comTitle", map.get("comTitle").toString());
                    intent.putExtra("userName",userName);
                    startActivityForResult(intent, 0);
                    break;
                case "livingpayment":
                    if(userName.equals("null")){
                        Intent intent0 = new Intent(getActivity(),LogActivity.class);
                    }
//                    tvNewMsgsNums = view.findViewById(R.id.tv_comitem_newMsgNoti);
//                    tvNewMsgsNums.setBackgroundColor(Color.parseColor("#E0E0E0"));
                    intent.setClass(getActivity(), ComDynamicActivity.class);
                    intent.putExtra("comId", map.get("comId").toString());
                    intent.putExtra("comTitle", map.get("comTitle").toString());
                    intent.putExtra("userName",userName);
                    startActivityForResult(intent, 0);
                    break;
                case "carservice":
                    if(userName.equals("null")){
                        Intent intent0 = new Intent(getActivity(),LogActivity.class);
                    }
//                    tvNewMsgsNums = view.findViewById(R.id.tv_comitem_newMsgNoti);
//                    tvNewMsgsNums.setBackgroundColor(Color.parseColor("#E0E0E0"));
                    intent.setClass(getActivity(), ComDynamicActivity.class);
                    intent.putExtra("comId", map.get("comId").toString());
                    intent.putExtra("comTitle", map.get("comTitle").toString());
                    intent.putExtra("userName",userName);
                    startActivityForResult(intent, 0);
                    break;
            }

        }

    }



}
