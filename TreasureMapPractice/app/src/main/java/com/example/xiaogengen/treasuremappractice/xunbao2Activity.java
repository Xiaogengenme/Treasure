package com.example.xiaogengen.treasuremappractice;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.amap.api.maps.AMap;
import com.amap.api.maps.MapView;
import com.amap.api.maps.model.MyLocationStyle;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class xunbao2Activity extends AppCompatActivity {
    MapView mMapView = null;
    private List<Treasure> treasureList=new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_xunbao2);
        mMapView = (MapView) findViewById(R.id.map);
        mMapView.onCreate(savedInstanceState);// 此方法须覆写，虚拟机需要在很多情况下保存地图绘制的当前状态。
        AMap aMap=null;
        if (aMap == null) {
            aMap = mMapView.getMap(); }
        MyLocationStyle myLocationStyle=null;
        myLocationStyle = new MyLocationStyle();//初始化定位蓝点样式类myLocationStyle.myLocationType(MyLocationStyle.LOCATION_TYPE_LOCATION_ROTATE);//连续定位、且将视角移动到地图中心点，定位点依照设备方向旋转，并且会跟随设备移动。（1秒1次定位）如果不设置myLocationType，默认也会执行此种模式。
        myLocationStyle.interval(2000); //设置连续定位模式下的定位间隔，只在连续定位模式下生效，单次定位模式下不会生效。单位为毫秒。
        aMap.setMyLocationStyle(myLocationStyle);//设置定位蓝点的Style
//aMap.getUiSettings().setMyLocationButtonEnabled(true);设置默认定位按钮是否显示，非必需设置。
        aMap.setMyLocationEnabled(true);// 设置为true表示启动显示定位蓝点，false表示隐藏定位蓝点并不进行定位，默认是false。
        myLocationStyle.myLocationType(MyLocationStyle.LOCATION_TYPE_FOLLOW) ;//连续定位、且将视角移动到地图中心点，定位蓝点跟随设备移动。（1秒1次定位）
        //关于下面的列表
        initTreasures();
        TreasureAdapter adapter=new TreasureAdapter(xunbao2Activity.this,R.layout.treasure_item,treasureList);
        ListView listView=(ListView)findViewById(R.id.list_view);
        listView.setAdapter(adapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener(){
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Treasure treasure = treasureList.get(i);
                AlertDialog.Builder dialog=new AlertDialog.Builder(xunbao2Activity.this);
                dialog.setTitle("确认：");
                dialog.setMessage("确定存放此宝物吗？");
                dialog.setCancelable(true);
                dialog.setIcon(R.drawable.chest);
                dialog.setPositiveButton("取消", new DialogInterface.OnClickListener(){
                    @Override
                    public void onClick(DialogInterface dialogInterface, int which) {

                    }
                });
                dialog.setNegativeButton("确认",new DialogInterface.OnClickListener(){
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        Log.d("xiaogengen click","hahaha"+i);
                        Intent intent = new Intent(xunbao2Activity.this,LocationActivity.class);
                      startActivity(intent);

                    }
                });
                dialog.show();
            }
        });
    }
    private void initTreasures(){
        sendRequestWithOkHttp();
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
                            .url("http://"+Values.IP+":8080/FeatureApp/TreasureInfoServlet")
                            .build();
                    //调用OkHttpClient的newCall方法来创建一个call对象，并调用它的execute方法来
                    //发送请求并获取服务器返回的数据
                    Response response = client.newCall(request).execute();
                    String responseData =response.body().string();
                    parseJSONWithJSONObject(responseData);
                    //showResponse(responseData);
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
                Treasure treasure = new  Treasure(jsonObject.getInt("idTreasure"),jsonObject.getString("nameTreasure"),jsonObject.getInt("valueTreasure"));
                treasureList.add(treasure);
            }
        }catch(Exception e){
            e.printStackTrace();
        }
    }

//    private void showResponse(final String response){
//        runOnUiThread(new Runnable() {
//            @Override
//            public void run() {
//                //UI
//                //responseText.setText(response);
//            }
//        });
//    }
}
