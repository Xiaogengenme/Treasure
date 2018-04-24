package com.example.xiaogengen.treasuremappractice;

import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import com.autonavi.ae.route.model.RerouteOption;

import org.json.JSONArray;
import org.json.JSONObject;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class SecondActivity extends AppCompatActivity {
    //练习接收数据

    TextView responseText;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);
        //去除标题栏
        ActionBar actionBar = getSupportActionBar();
        if(actionBar != null){
            actionBar.hide();
        }
        responseText = (TextView)findViewById(R.id.textView3);
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
                            .url("http://"+Values.IP+":8080/FeatureApp/FeaturePosition")
                            .build();
                    //调用OkHttpClient的newCall方法来创建一个call对象，并调用它的execute方法来
                    //发送请求并获取服务器返回的数据
                    Response response = client.newCall(request).execute();
                    String responseData =response.body().string();
                    parseJSONWithJSONObject(responseData);
                    showResponse(responseData);
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
                responseText.setText(response);
            }
        });
    }
}
