//package android.com.zlt;
//
//import android.content.Intent;
//import android.support.v7.app.AppCompatActivity;
//import android.os.Bundle;
//import android.view.View;
//import android.widget.ImageView;
//import android.widget.TextView;
//
//import okhttp3.MediaType;
//import okhttp3.OkHttpClient;
//import okhttp3.Request;
//import okhttp3.RequestBody;
//
//public class ErweimaActivity extends AppCompatActivity {
//    private OkHttpClient okHttpClient;
//
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_erweima);
//        getSupportActionBar().hide();
//        Intent intent=getIntent();
//        String name=intent.getStringExtra("userName");
//        RequestBody requestBody = RequestBody.create(
//                MediaType.parse("text/plain;charset=utf-8")
//                ,name
//        );
//        okHttpClient=new OkHttpClient();
//        //创建请求
//        Request request = new Request.Builder()
//                .post(requestBody)
//                .url(Constant.BASE_URL + "getUserMessage1")
//                .build();
//        ImageView erweima_fanhui=findViewById(R.id.erweima_fanhui);
//        //点击返回
//        erweima_fanhui.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                finish();
//            }
//        });
//        ImageView user_erweima=findViewById(R.id.user_erweima);
//        TextView erweima_name=findViewById(R.id.erweima_name);
//        ImageView erweima_image=findViewById(R.id.erweima_image);
//
//    }
//}
