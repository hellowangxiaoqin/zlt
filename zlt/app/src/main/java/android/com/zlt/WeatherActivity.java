package android.com.zlt;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.webkit.WebViewClient;

public class WeatherActivity extends AppCompatActivity {
    private WebView webView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_weather);
        webView=findViewById(R.id.webview);
        webView.getSettings().setJavaScriptEnabled(true);//设置JavaScript可用
        webView.setWebChromeClient(new WebChromeClient());//处理JavaScript对话框
        webView.setWebViewClient(new WebViewClient());
        webView.loadUrl("http://m.weather.com.cn/mweather/101090101.shtml");
    }


}
