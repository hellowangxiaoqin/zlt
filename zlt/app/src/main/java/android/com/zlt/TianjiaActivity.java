package android.com.zlt;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.EditText;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class TianjiaActivity extends AppCompatActivity {
    private OkHttpClient okHttpClient;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tianjia);
        getSupportActionBar().hide();
        Intent intent=getIntent();
        final String userName=intent.getStringExtra("userName");
        EditText tianjia_wenzi=findViewById(R.id.tianjia_wenzi);
        final String tianjia=tianjia_wenzi.getText().toString();
        RequestBody requestBody = RequestBody.create(
                MediaType.parse("text/plain;charset=utf-8")
                ,userName
        );
        okHttpClient=new OkHttpClient();
        //创建请求
        Request request = new Request.Builder()
                .post(requestBody)
                .url(Constant.BASE_URL + "getUserMessage1")
                .build();
        final Call call=okHttpClient.newCall(request);
        call.enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                Log.e("test","添加收藏失败");
            }
            @Override
            public void onResponse(Call call, Response response) throws IOException {
                Log.e("test","添加成功");
            }
        });
    }
}
