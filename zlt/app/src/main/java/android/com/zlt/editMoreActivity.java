package android.com.zlt;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.app.DatePickerDialog;
import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;

import java.util.Calendar;

import okhttp3.OkHttpClient;

public class editMoreActivity extends AppCompatActivity implements View.OnClickListener {
    private OkHttpClient okHttpClient;
    private Handler handler;
    //性别选择
    private String[] sexArry = new String[]{"girl", "boy"};
    private Button changebirth_button;
    private Button exitaccount_button;
    private Button changesex_button;
    private Button changename_button;
    private Button changeaddress_button;
    private TextView changesex_textview;
    private TextView changeaddress_textview;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_more);
        getSupportActionBar().hide();
        Intent intent_editMore=getIntent();
        String gexing=intent_editMore.getStringExtra("gexing");
        String sex=intent_editMore.getStringExtra("sex");
        String birth=intent_editMore.getStringExtra("birth");
        String address=intent_editMore.getStringExtra("address");
        TextView changename_textview=findViewById(R.id.changename_textview);
        changename_textview.setText(gexing);
        Log.e("testGexing",changename_textview.getText().toString());
        TextView changesex_textview=findViewById(R.id.changesex_textview);
        changesex_textview.setText(sex);
        TextView changebirth_textview=findViewById(R.id.changebirth_textview);
        changebirth_textview.setText(birth);
        TextView changeaddress_textview=findViewById(R.id.item_city_name_tv);
        changeaddress_textview.setText(address);
        changename_button = (Button) findViewById(R.id.changename_button);
        changebirth_button = (Button) findViewById(R.id.changebirth_button);
        changesex_button = (Button) findViewById(R.id.changesex_button);
        changeaddress_button = (Button) findViewById(R.id.changeaddress_button);
        InitView();
        ImageView jiaotou_more=findViewById(R.id.jiaotou_more);
        jiaotou_more.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TextView name1=findViewById(R.id.changename_textview);
                TextView sex=findViewById(R.id.changesex_textview);
                TextView birth=findViewById(R.id.changebirth_textview);
                TextView city=findViewById(R.id.item_city_name_tv);
                String name_more=name1.getText().toString();
                String sex_more=sex.getText().toString();
                String  birth_more=birth.getText().toString();
                String city_more=city.getText().toString();
                Intent intent_more=new Intent();
                Log.e("sex",sex_more);
                intent_more.putExtra("name_more",name_more);
                intent_more.putExtra("sex_more",sex_more);
                intent_more.putExtra("birth_more",birth_more);
                intent_more.putExtra("city_more",city_more);
                setResult(4,intent_more);
                finish();
            }
        });
    }
    private void InitView() {
        changename_button.setOnClickListener(this);
        changebirth_button.setOnClickListener(this);
        changesex_button.setOnClickListener(this);
        changeaddress_button.setOnClickListener(this);
    }
    /**
     * 日期选择器对话框监听
     */
    private DatePickerDialog.OnDateSetListener onDateSetListener = new DatePickerDialog.OnDateSetListener() {

        @Override
        public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
            int mYear = year;
            int mMonth = monthOfYear;
            int mDay = dayOfMonth;
            TextView date_textview = (TextView) findViewById(R.id.changebirth_textview);
            String days;
            days = new StringBuffer().append(mYear).append("年").append(mMonth).append("月").append(mDay).append("日").toString();
            date_textview.setText(days);
        }
    };
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.changebirth_button:
                Calendar nowdate = Calendar.getInstance();
                final int mYear = nowdate.get(Calendar.YEAR);
                final int mMonth = nowdate.get(Calendar.MONTH);
                final int mDay = nowdate.get(Calendar.DAY_OF_MONTH);
                //调用DatePickerDialog
                new DatePickerDialog(editMoreActivity.this, onDateSetListener, mYear, mMonth, mDay).show();
                break;
//            case R.id.exitaccount_button:
//                // Use the Builder class for convenient dialog construction
//                AlertDialog.Builder builder1 = new AlertDialog.Builder(editMoreActivity.this);
//                builder1.setMessage("确定退出账号?")
//                        .setPositiveButton("是", new DialogInterface.OnClickListener() {
//                            public void onClick(DialogInterface dialog, int id) {
//                                finish();  // 这里使用finish() 模拟下退出账号~
//                            }
//                        })
//                        .setNegativeButton("否", new DialogInterface.OnClickListener() {
//                            public void onClick(DialogInterface dialog, int id) {
//                                // User cancelled the dialog
//                            }
//                        });
//                // Create the AlertDialog object and return it
//                builder1.show();
//                break;
            case R.id.changesex_button:
                changesex_textview = (TextView) findViewById(R.id.changesex_textview);
                showSexChooseDialog();
                break;
            case R.id.changename_button:
                onCreateNameDialog();
                break;
            case R.id.changeaddress_button:
                changeaddress_textview=(TextView)findViewById(R.id.item_city_name_tv);
                onSetAddress();
            default:
                break;

        }
    }
    //设置地区
    private void onSetAddress() {
//        Intent intent=new Intent(editMoreActivity.this,MapbaiduActivity.class);
//        startActivity(intent);
        // 使用LayoutInflater来加载dialog_setname.xml布局
        LayoutInflater layoutInflater = LayoutInflater.from(this);
        View nameView = layoutInflater.inflate(R.layout.more_setaddress, null);
        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(
                this);
        // 使用setView()方法将布局显示到dialog
        alertDialogBuilder.setView(nameView);
        final EditText userInput = (EditText) nameView.findViewById(R.id.changeaddress_edit);
        final TextView name = (TextView) findViewById(R.id.item_city_name_tv);
        // 设置Dialog按钮
        alertDialogBuilder
                .setCancelable(false)
                .setPositiveButton("OK",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                // 获取edittext的内容,显示到textview
                                name.setText(userInput.getText());
                            }
                        })
                .setNegativeButton("Cancel",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                dialog.cancel();
                            }
                        });

        //创建alertDialog
        AlertDialog alertDialog = alertDialogBuilder.create();
        //显示
        alertDialog.show();
    }
    private void onCreateNameDialog() {
        // 使用LayoutInflater来加载dialog_setname.xml布局
        LayoutInflater layoutInflater = LayoutInflater.from(this);
        View nameView = layoutInflater.inflate(R.layout.more_setsignature, null);
        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(
                this);
        // 使用setView()方法将布局显示到dialog
        alertDialogBuilder.setView(nameView);
        final EditText userInput = (EditText) nameView.findViewById(R.id.changename_edit);
        final TextView name = (TextView) findViewById(R.id.changename_textview);
        // 设置Dialog按钮
        alertDialogBuilder
                .setCancelable(false)
                .setPositiveButton("OK",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                // 获取edittext的内容,显示到textview
                                name.setText(userInput.getText());
                            }
                        })
                .setNegativeButton("Cancel",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                dialog.cancel();
                            }
                        });

        //创建alertDialog
        AlertDialog alertDialog = alertDialogBuilder.create();
        //显示
        alertDialog.show();
    }
    private void showSexChooseDialog() {
        AlertDialog.Builder builder3 = new AlertDialog.Builder(this);// 自定义对话框
        builder3.setSingleChoiceItems(sexArry, 0, new DialogInterface.OnClickListener() {// 2默认的选中

            @Override
            public void onClick(DialogInterface dialog, int which) {// which是被选中的位置
                // showToast(which+"");
                changesex_textview.setText(sexArry[which]);
                dialog.dismiss();// 随便点击一个item消失对话框，不用点击确认取消
            }
        });
        builder3.show();// 让弹出框显示
    }
}



