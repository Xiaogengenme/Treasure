package com.example.xiaogengen.treasuremappractice;

import android.content.Intent;
import android.graphics.BitmapFactory;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.TextView;

import com.amap.api.location.AMapLocation;
import com.amap.api.location.AMapLocationClient;
import com.amap.api.location.AMapLocationClientOption;
import com.amap.api.location.AMapLocationListener;
import com.example.xiaogengen.treasuremappractice.Box;
import com.amap.api.maps.AMap;
import com.amap.api.maps.CameraUpdateFactory;
import com.amap.api.maps.MapView;
import com.amap.api.maps.model.BitmapDescriptor;
import com.amap.api.maps.model.BitmapDescriptorFactory;
import com.amap.api.maps.model.LatLng;
import com.amap.api.maps.model.Marker;
import com.amap.api.maps.model.MarkerOptions;
import com.amap.api.maps.model.MyLocationStyle;

import org.json.JSONArray;
import org.json.JSONObject;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.logging.Logger;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

import static com.autonavi.ae.pos.LocManager.init;

public class MainActivity extends AppCompatActivity {


    //此Activity为app主页


    MapView gMapView = null;
    AMap amap = null;
    //实现定位蓝点
    MyLocationStyle myLocationStyle;

    //声明AMapLocationClient类对象
    public AMapLocationClient mLocationClient = null;
    //声明定位回调监听器
    public AMapLocationListener mLocationListener = null;
    //声明AMapLocationClientOption对象
    public AMapLocationClientOption mLocationOption = null;

    ArrayList<Box> boxes = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //去除标题栏
        ActionBar actionBar = getSupportActionBar();
        if(actionBar != null){
            actionBar.hide();
        }
        //获取地图控件引用
        gMapView = (MapView) findViewById(R.id.map);
        //创建地图
        gMapView.onCreate(savedInstanceState);
        //初始化地图控制器对象
        if (amap == null) {
            amap = gMapView.getMap();
        }
        //初始化定位蓝点样式类
        myLocationStyle = new MyLocationStyle();
        //连续定位、且将视角移动到地图中心点，定位点依照设备方向旋转，并且会跟随设备移动。
        //(1秒1次定位）如果不设置myLocationType，默认也会执行此种模式。
        myLocationStyle.myLocationType(MyLocationStyle.LOCATION_TYPE_LOCATION_ROTATE);
        //设置连续定位模式下的定位间隔，只在连续定位模式下生效，单次定位模式下不会生效。单位为毫秒。
        myLocationStyle.interval(20000);//以后要加点某一按键回到屏幕中心
        //设置定位蓝点的style
        amap.setMyLocationStyle(myLocationStyle);
        // 设置为true表示启动显示定位蓝点，false表示隐藏定位蓝点并不进行定位，默认是false。
        amap.setMyLocationEnabled(true);
        //设置缩放级别（缩放级别为4-20级）
        //amap.getScalePerPixel();
        amap.moveCamera(CameraUpdateFactory.zoomTo(10));//地图初始显示比例‘

        sendRequestWithOkHttp();



        //实例化宝箱对象
//        Box mybox1 = new Box(39.906901,116.397972);
//        Box mybox2 = new Box(39.916901,116.397972);
//        Box mybox3 = new Box(39.926901,116.797972);
//        Box mybox4 = new Box(39.936901,116.697972);
//        Box mybox5 = new Box(39.946901,116.297972);
//        Box mybox6 = new Box(39.956901,116.597972);
//        Box mybox7 = new Box(39.968901,116.697972);
//        Box mybox8 = new Box(39.979901,116.797972);
//        Box mybox9 = new Box(39.989901,116.897972);
//        Box mybox0 = new Box(39.999901,116.997972);

        //以下为宝箱绘制地图标记点
//        MarkTreasure(mybox0);
//        MarkTreasure(mybox1);
//        MarkTreasure(mybox2);
//        MarkTreasure(mybox3);
//        MarkTreasure(mybox4);
//        MarkTreasure(mybox5);
//        MarkTreasure(mybox6);
//        MarkTreasure(mybox7);
//        MarkTreasure(mybox8);
//        MarkTreasure(mybox9);

        //点击 Marker 时会回调AMap.OnMarkerClickListener，监听器的实现
        AMap.OnMarkerClickListener markerClickListener = new AMap.OnMarkerClickListener(){
            //marker 对象被点击时候回调接口
            //返回true表示以点击，否则返回false
            @Override
            public boolean onMarkerClick(Marker marker){
                Intent intent = new Intent(MainActivity.this,xunbao2Activity.class);
                startActivity(intent);
                return false;
            }
        };
        //绑定 marker 被点击事件
        amap.setOnMarkerClickListener(markerClickListener);
        //定位问题
        mLocationClient = new AMapLocationClient(this);
        //Button button_locate = (Button)findViewById(R.id.button);
        Button button_hide = (Button)findViewById(R.id.button);
        button_hide.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this,xunbao2Activity.class);
                startActivity(intent);
            }
        });


        mLocationClient = new AMapLocationClient(this);
        //初始化
        mLocationListener = new AMapLocationListener() {
            @Override
            public void onLocationChanged(AMapLocation amapLocation) {
                if (amapLocation != null) {
                    if (amapLocation.getErrorCode() == 0) {
                        //可在其中解析amapLocation获取相应内容。
//                        amapLocation.getLocationType();//获取当前定位结果来源，如网络定位结果，详见定位类型表
                        amapLocation.getLatitude();//获取纬度
                        amapLocation.getLongitude();//获取经度
//                        amapLocation.getAccuracy();//获取精度信息
//                        amapLocation.getAddress();//地址，如果option中设置isNeedAddress为false，则没有此结果，网络定位结果中会有地址信息，GPS定位不返回地址信息。
//                        amapLocation.getCountry();//国家信息
//                        amapLocation.getProvince();//省信息
//                        amapLocation.getCity();//城市信息
//                        amapLocation.getDistrict();//城区信息
//                        amapLocation.getStreet();//街道信息
//                        amapLocation.getStreetNum();//街道门牌号信息
//                        amapLocation.getCityCode();//城市编码
//                        amapLocation.getAdCode();//地区编码
//                        amapLocation.getAoiName();//获取当前定位点的AOI信息
//                        amapLocation.getBuildingId();//获取当前室内定位的建筑物Id
//                        amapLocation.getFloor();//获取当前室内定位的楼层
//                        amapLocation.getGpsAccuracyStatus();//获取GPS的当前状态
                        //获取定位时间
                        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                        Date date = new Date(amapLocation.getTime());
                        df.format(date);
//                        TextView textView = (TextView)findViewById(R.id.textView2);
//                        textView.setText(String.valueOf(amapLocation.getLatitude()));

                    }else {
                        //定位失败时，可通过ErrCode（错误码）信息来确定失败的原因，errInfo是错误信息，详见错误码表。
                        Log.e("AmapError","location Error, ErrCode:"
                                + amapLocation.getErrorCode() + ", errInfo:"
                                + amapLocation.getErrorInfo());
                    }
                }
            }
        };

        //初始化AMapLocationClientOption对象
        mLocationOption = new AMapLocationClientOption();
        //设置定位回调监听
        mLocationClient.setLocationListener(mLocationListener);

        AMapLocationClientOption option = new AMapLocationClientOption();
        /**
         * 设置定位场景，目前支持三种场景（签到、出行、运动，默认无场景）
         */
        option.setLocationPurpose(AMapLocationClientOption.AMapLocationPurpose.SignIn);
        if(null != mLocationClient) {
            mLocationClient.setLocationOption(option);
            //设置场景模式后最好调用一次stop，再调用start以保证场景模式生效
            mLocationClient.stopLocation();
            mLocationClient.startLocation();

            //设置定位模式为AMapLocationMode.Hight_Accuracy，高精度模式。
            mLocationOption.setLocationMode(AMapLocationClientOption.AMapLocationMode.Hight_Accuracy);

            //设置定位间隔,单位毫秒,默认为2000ms，最低1000ms。
            mLocationOption.setInterval(1000);

            //设置是否返回地址信息（默认返回地址信息）
            mLocationOption.setNeedAddress(true);

            //给定位客户端对象设置定位参数
            mLocationClient.setLocationOption(mLocationOption);
            //启动定位
            mLocationClient.startLocation();
        }




        }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        gMapView.onDestroy();
    }

    @Override
    protected void onResume() {
        super.onResume();
        //在activity执行onResume时执行mMapView.onResume ()，重新绘制加载地图
        gMapView.onResume();
    }

    @Override
    protected void onPause() {
        super.onPause();
        //在activity执行onPause时执行mMapView.onPause ()，暂停地图的绘制
        gMapView.onPause();
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        //在activity执行onSaveInstanceState时执行mMapView.onSaveInstanceState (outState)，保存地图当前的状态
        gMapView.onSaveInstanceState(outState);
    }


    public void MarkTreasure(Box box){
        //MarkerOptions 是设置 Marker 参数变量的类，自定义 Marker 时会经常用到。
        MarkerOptions markerOption = new MarkerOptions();
        LatLng latLng = new LatLng(box.getLatitudeBox(), box.getLongitudeBox());
        Log.d("纬度"+box.getLatitudeBox(),"经度"+box.getLongitudeBox());
        final Marker marker = amap.addMarker(markerOption.position(latLng).title("Treasure").snippet("宝箱"));
        markerOption.draggable(true);//设置Marker可拖动
        markerOption.icon(BitmapDescriptorFactory.fromBitmap(BitmapFactory
                .decodeResource(getResources(),R.drawable.treasurebox)));
        markerOption.setFlat(true);
    }



    private void sendRequestWithOkHttp(){

        new Thread(new Runnable() {
            @Override
            public void run() {
                try{
                    //创建一个OKHTTPClient实例
                    OkHttpClient client = new OkHttpClient();
                    //创建一个request对象，用于发起http请求
                    Request request = new Request.Builder()
                            .url("http://"+Values.IP+":8080/FeatureApp/TreasurePositionServlet")
                            .build();
                    //调用OkHttpClient的newCall方法来创建一个call对象，并调用它的execute方法来
                    //发送请求并获取服务器返回的数据
                    Response response = client.newCall(request).execute();
                    String responseData =response.body().string();
                    parseJSONWithJSONObject(responseData);
//                    showResponse(responseData);
                }catch(Exception e){
                    e.printStackTrace();
                }
            }
        }).start();
    }


    private void parseJSONWithJSONObject(String jsonData){
        try{
            JSONArray jsonArray = new JSONArray(jsonData);
            for(int i = 0; i < jsonArray.length(); i++){
                JSONObject jsonObject = jsonArray.getJSONObject(i);
                MarkTreasure(new Box(jsonObject.getInt("idBox"),jsonObject.getDouble("latitudeBox"),jsonObject.getDouble("longitudeBox")));
            }

        }catch(Exception e){
            e.printStackTrace();
        }
    }

    private void showResponse(final String response){
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                //UI
                //responseText.setText(response);
            }
        });
    }
}


