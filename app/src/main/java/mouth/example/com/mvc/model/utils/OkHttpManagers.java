package mouth.example.com.mvc.model.utils;

import mouth.example.com.mvc.MyApp;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;

/**
 * Created by lenovo on 2017/8/9.
 */

public class OkHttpManagers {
    private static  OkHttpManagers manager ;
    private final OkHttpClient client;


    public OkHttpManagers() {
        client = new OkHttpClient.Builder().build();

    }
    public static OkHttpManagers getInstance(){

        if (manager == null) {
            synchronized (OkHttpManagers.class){
                manager=new OkHttpManagers();
            }
        }
        return manager;
    }
    public void getDataFromNet(final String url, final Callback callback){
        MyApp.activity.runOnUiThread(new Runnable() {
            @Override
            public void run() {
                Request request=new Request.Builder().url(url).build();
                client.newCall(request).enqueue(callback);
            }
        });

    }

//    public void post(final String url, Map<String,String>map , Callback callback){
//        MyApp.activity.runOnUiThread(new Runnable() {
//            @Override
//            public void run() {
//
//                Request request=new Request.Builder().url(url).post().build();
//            }
//        });
//    }
}
