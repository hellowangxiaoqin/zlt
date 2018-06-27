package android.com.zlt;

import android.Manifest;
import android.content.Context;
import android.content.pm.PackageManager;
import android.location.Criteria;
import android.location.Location;
import android.location.LocationManager;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;

public class NewsActivity extends AppCompatActivity {
    private Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_news);
    }

    //第一步，获取经纬度，android自带有获取经纬度的类方法
    private void getLocation() {
        LocationManager locationManager;
        String contextService = Context.LOCATION_SERVICE;
        //通过系统服务，取得LocationManager对象
        locationManager = (LocationManager) context.getSystemService(contextService);
        String provider = LocationManager.GPS_PROVIDER;
            if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
                // TODO: Consider calling
                //    ActivityCompat#requestPermissions
                // here to request the missing permissions, and then overriding
                //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
                //                                          int[] grantResults)
                // to handle the case where the user grants the permission. See the documentation
                // for ActivityCompat#requestPermissions for more details.
                return;
            }
            Location location = locationManager.getLastKnownLocation(provider);
        Criteria criteria=new Criteria();
        criteria.setAccuracy(Criteria.ACCURACY_FINE);//高精度
        criteria.setAltitudeRequired(false);//不要求海拔
        criteria.setBearingRequired(false);//不要求方位
        criteria.setCostAllowed(true);//允许有花费
        criteria.setPowerRequirement(Criteria.POWER_LOW);//低功耗

        //从可用的位置提供器中，匹配以上标准的最佳提供器
        provider=locationManager.getBestProvider(criteria,true);
        //获得最后一次变化的位置
        location = locationManager.getLastKnownLocation(provider);
        if (location!=null) {
            double latitude = location.getLatitude();
            double longitude = location.getLongitude();
        }
        //locationManager.requestLocationUpdates(provider, 2000, 10, locationListener);
    }
   // 第二步,获取设置webview
//    WebView wv=findViewById(R.id.wvDD);
//    wv.getSettings().setJavaScriptEnabled(true);
//    // 加载微博的登录页面
//        wv.getSettings().setJavaScriptEnabled(true);
//        wvDD.setScrollBarStyle(0);
//    WebSettings webSettings = wvDD.getSettings();
//        webSettings.setAllowFileAccess(true);
//        webSettings.setBuiltInZoomControls(true);
//        webSettings.setGeolocationEnabled(true);
//        webSettings.setDomStorageEnabled(true);
//        wvDD.loadUrl(getUrl());
    //第三步,拼接url
//    private String getUrl() {
//        return "http://webapp.diditaxi.com.cn/?channel=xxxx&maptype=wgs&lat="+latitude+"&lng="+longitude;
//    }
    }

