package com.example.xiaogengen.treasuremappractice;

import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.amap.api.maps.AMap;
import com.amap.api.maps.MapView;

import java.text.DateFormat;
import java.util.Date;

public class xunbao1Activity extends AppCompatActivity {
    MapView mMapView = null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_xunbao1);
        mMapView = (MapView) findViewById(R.id.map);
        mMapView.onCreate(savedInstanceState);// 此方法须覆写，虚拟机需要在很多情况下保存地图绘制的当前状态。
        AMap aMap=null;
        if (aMap == null) {
            aMap = mMapView.getMap(); }
        Button button1=(Button)findViewById(R.id.button1);
        Button button2=(Button)findViewById(R.id.button2);
        button1.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                AlertDialog.Builder dialog=new AlertDialog.Builder(xunbao1Activity.this);
                dialog.setTitle("确认：");
                dialog.setMessage("确定要打开宝箱吗？");
                dialog.setCancelable(true);
                dialog.setIcon(R.drawable.chest);
                dialog.setPositiveButton("下次吧", new DialogInterface.OnClickListener(){
                    @Override
                    public void onClick(DialogInterface dialogInterface, int which) {

                    }
                });
                dialog.setNeutralButton("打开（-10）",new DialogInterface.OnClickListener(){
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        //提示打开宝箱成功或失败界面
                        //真
                        String n1="宝石";
                        DateFormat n2=DateFormat.getDateTimeInstance();
                        DateFormat n3=DateFormat.getDateTimeInstance();
                        String n4="李四";
                        String n5="时长";
                        int n6=4;
                        int n7=6;
                        Date date=new Date();
                        String s1="物品名称："+n1;
                        String s2="找到时间："+n2.format(date);
                        String s3="藏匿时间："+n3.format(date);
                        String s4="藏宝人："+n4;
                        String s5="藏匿时长："+n5;
                        String s6="藏匿人所得积分："+n6;
                        String s7="自己所得积分："+n7;
                        new AlertDialog.Builder(xunbao1Activity.this)
                                .setTitle("宝箱详情")
                                .setIcon(R.drawable.gem3)
                                .setMessage(s1+"\n"+s2+"\n"+s3+"\n"+s4+"\n"+s5+"\n"+s6+"\n"+s7+"\n")
                                .setPositiveButton("确定", null)
                                .show();
                    }
                });
                dialog.show();
            }
        });
        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder dialog = new AlertDialog.Builder(xunbao1Activity.this);
                dialog.setTitle("通知：");
                dialog.setMessage("距离太远，无法打开宝箱");
                dialog.setCancelable(true);
                dialog.setIcon(R.drawable.chest);
                dialog.setPositiveButton("好的", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int which) {

                    }
                });
            }
        });
    }
}
