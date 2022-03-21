package com.example.lbsdemo;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import com.baidu.location.BDAbstractLocationListener;
import com.baidu.location.BDLocation;
import com.baidu.location.LocationClient;
import com.baidu.location.LocationClientOption;
import com.baidu.mapapi.SDKInitializer;
import com.baidu.mapapi.map.BaiduMap;
import com.baidu.mapapi.map.MapStatusUpdate;
import com.baidu.mapapi.map.MapStatusUpdateFactory;
import com.baidu.mapapi.map.MapView;
import com.baidu.mapapi.map.MyLocationData;
import com.baidu.mapapi.model.LatLng;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    TextView locationInfo;
    LocationClient mLocationClient;//定位对象
    MapView mMapView;
    BaiduMap mBaiduMap;
    boolean isFirstLocate = true;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        SDKInitializer.initialize(getApplicationContext());//对上下文的初始化
        setContentView(R.layout.activity_main);
        locationInfo = findViewById(R.id.locationInfo);
        mLocationClient = new LocationClient(getApplicationContext());//创建地理信息客户端
        mLocationClient.registerLocationListener(new MyLocationListener());//注册监听器
        mMapView = findViewById(R.id.bmapView);
        mBaiduMap = mMapView.getMap();
        mBaiduMap.setMapType(BaiduMap.MAP_TYPE_SATELLITE);
        mBaiduMap.setMyLocationEnabled(true);//设置当前定位位置

        List<String> permissionList = new ArrayList<String>();//把需要申请的权限加到集合里
        if (ContextCompat.checkSelfPermission(MainActivity.this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED){
            permissionList.add(Manifest.permission.ACCESS_FINE_LOCATION);
        }
        if (ContextCompat.checkSelfPermission(MainActivity.this, Manifest.permission.READ_PHONE_STATE) != PackageManager.PERMISSION_GRANTED){
            permissionList.add(Manifest.permission.READ_PHONE_STATE);
        }
        if (ContextCompat.checkSelfPermission(MainActivity.this, Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED){
            permissionList.add(Manifest.permission.WRITE_EXTERNAL_STORAGE);
        }
        if (!permissionList.isEmpty()){
            String[] permissions = permissionList.toArray(new String[permissionList.size()]);
            ActivityCompat.requestPermissions(MainActivity.this,permissions,1);
        } else {
            requestLocation();//请求定位
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        switch (requestCode){
            case 1:
                if (grantResults.length > 0){
                    for (int result : grantResults){
                        if (result != PackageManager.PERMISSION_GRANTED){
                            Toast.makeText(this, "必须同意所有的权限才能使用程序", Toast.LENGTH_SHORT).show();
                            finish();
                            return;
                        }
                    }
                    requestLocation();
                } else {
                    Toast.makeText(this, "发生未知错误", Toast.LENGTH_SHORT).show();
                    finish();
                }
                break;
        }

    }

    private void requestLocation() {
        initLocation();//初始化参数
        mLocationClient.start();
    }

    private void initLocation() {
        LocationClientOption option = new LocationClientOption();
        option.setLocationMode(LocationClientOption.LocationMode.Hight_Accuracy);
        //可选，设置定位模式，默认高精度
        //LocationMode.Hight_Accuracy:高精度
        //LocationMode.Battery_Saving:低功耗
        //LocationMode.Device_Sensors:仅使用设备
        option.setCoorType("bd0911");
        //可选，设置返回经纬度坐标类型，默认GCJ02
        //GCJ02 国测局坐标
        //BD09ll 百度经纬度坐标
        //BD09 百度墨卡托坐标
        //海外地区定位，无需设置坐标类型，统一返回WGS84坐标类型
        option.setScanSpan(1000);
        //可选，设置发起定位请求的间隔，int类型，单位ms
        //如果设置为0，则代表单次定位，即仅定位一次，默认为0
        //如果设置非0，需设置1000ms以上才有效
        option.setOpenGps(true);
        //设置是否使用GPS，默认false
        //使用高精度和仅用设备两种定位模式的，参数必须设置为true
        option.setLocationNotify(true);
        //设置是否当GPS有效时按照1s/1次频率输出GPS结果，默认false
        option.setIgnoreKillProcess(false);
        //定位SDK内部是一个service，并放到了独立进程
        //设置是否在stop的时候杀死这个进程，默认（建议）不杀死，即setIgnoreKillProcess(true)
        option.setWifiCacheTimeOut(5 * 60 * 1000);
        //如果设置了该接口，首次启动定位时，会先判断当前WIFI是否超出有效期，若超出有效期，会先重新扫描WIFI，然后定位
        option.setEnableSimulateGps(false);
        //设置是否需要过滤GPS仿真结果，默认需要，即参数false
        option.setIsNeedAddress(true);
        //显示具体地理位置信息，如果false则不显示
        mLocationClient.setLocOption(option);//把设定好的参数设置进去

    }

    private class MyLocationListener extends BDAbstractLocationListener{

        @Override
        public void onReceiveLocation(BDLocation bdLocation) {//收到定位信息要做什么，bdLocation包含了各种定位信息
            navigateTo(bdLocation);
//            StringBuilder currentPosition = new StringBuilder();
//            currentPosition.append("纬度:").append(bdLocation.getLatitude()).append("\n");
//            currentPosition.append("经度:").append(bdLocation.getLongitude()).append("\n");
//            currentPosition.append("国家:").append(bdLocation.getCountry()).append("\n");
//            currentPosition.append("省:").append(bdLocation.getProvince()).append("\n");
//            currentPosition.append("市:").append(bdLocation.getCity()).append("\n");
//            currentPosition.append("区:").append(bdLocation.getDistrict()).append("\n");
//            currentPosition.append("村镇:").append(bdLocation.getTown()).append("\n");
//            currentPosition.append("街道:").append(bdLocation.getStreet()).append("\n");
//            currentPosition.append("地址:").append(bdLocation.getAddrStr()).append("\n");
//            currentPosition.append("定位方式:");
//            if (bdLocation.getLocType() == BDLocation.TypeGpsLocation){
//                currentPosition.append("GPS");
//            } else if (bdLocation.getLocType() == BDLocation.TypeNetWorkLocation){
//                currentPosition.append("移动网络");
//            }
//            locationInfo.setText(currentPosition);
        }
    }
    private void navigateTo(BDLocation bdLocation){
        if (isFirstLocate) {
            LatLng ll = new LatLng(bdLocation.getLatitude(), bdLocation.getLongitude());//拿到经纬度
            MapStatusUpdate update = MapStatusUpdateFactory.newLatLng(ll);//把地图更新一下
            mBaiduMap.animateMapStatus(update);
            update = MapStatusUpdateFactory.zoomTo(16f);//把地图放大16倍
            mBaiduMap.animateMapStatus(update);
            isFirstLocate = false;
        }
        //把当前位置标记出来
        MyLocationData.Builder locationBuilder = new MyLocationData.Builder();
        locationBuilder.longitude(bdLocation.getLongitude());
        locationBuilder.latitude(bdLocation.getLatitude());
        MyLocationData locationData = locationBuilder.build();
        mBaiduMap.setMyLocationData(locationData);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mMapView.onDestroy();
        mBaiduMap.setMyLocationEnabled(false);
        mLocationClient.stop();
    }

    @Override
    protected void onResume() {
        super.onResume();
        mMapView.onResume();
    }

    @Override
    protected void onPause() {
        super.onPause();
        mMapView.onPause();
    }
}
























