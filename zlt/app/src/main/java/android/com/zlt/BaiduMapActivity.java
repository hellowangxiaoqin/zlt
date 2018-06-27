package android.com.zlt;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;

import com.baidu.location.LocationClient;
import com.baidu.location.LocationClientOption;
import com.baidu.mapapi.SDKInitializer;
import com.baidu.mapapi.map.BaiduMap;
import com.baidu.mapapi.map.LogoPosition;
import com.baidu.mapapi.map.MapStatusUpdate;
import com.baidu.mapapi.map.MapStatusUpdateFactory;
import com.baidu.mapapi.map.MapView;
import com.baidu.mapapi.map.UiSettings;

/**
 * Created by dell on 2018/5/29.
 */

public class BaiduMapActivity extends Activity {

    private MapView mapView;
    private BaiduMap baiduMap;
    private LocationClient locationClient;
    private LocationClientOption locationClientOption;
    private boolean isFirstLocation = true;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //在使用SDK各组件之前初始化context对象，传入ApplicationContext
        SDKInitializer.initialize(getApplicationContext());
        setContentView(R.layout.layout_baidumap);

        //地图视图
        mapView = findViewById(R.id.mapview_1);
        mapView.setLogoPosition(LogoPosition.logoPostionCenterBottom);
        //地图控制器类
        baiduMap = mapView.getMap();
        //修改地图比例值
        MapStatusUpdate msu = MapStatusUpdateFactory.zoomTo(18.0f); //参数float类型
        baiduMap.setMapStatus(msu);

        //UI控制器
        UiSettings settings = baiduMap.getUiSettings();
        settings.setScrollGesturesEnabled(true);


    }

//    @Override
//    protected void onPause() {
//        super.onPause();
//        mapView.onPause();
//    }
//
//    @Override
//    protected void onDestroy() {
//        super.onDestroy();
//        mapView.onDestroy();
//    }
}
