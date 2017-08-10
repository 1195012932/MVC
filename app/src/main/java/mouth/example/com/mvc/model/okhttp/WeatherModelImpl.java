package mouth.example.com.mvc.model.okhttp;

import com.google.gson.Gson;

import java.io.IOException;

import mouth.example.com.mvc.MyApp;
import mouth.example.com.mvc.entity.Weatherinfo;
import mouth.example.com.mvc.model.utils.OkHttpManagers;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

/**
 * Created by lenovo on 2017/8/9.
 */

public class WeatherModelImpl implements WeatherModel {
    @Override
    public void getWeather(String url, final OnWeatherListener owl) {
        OkHttpManagers.getInstance().getDataFromNet(url, new Callback() {
            @Override
            public void onFailure(Call call, final IOException e) {
                owl.OnError(e.toString());
            }

            @Override
            public void onResponse(Call call, final Response response) throws IOException {
                Gson gson = new Gson();
                final Weatherinfo weatherinfo = gson.fromJson(response.body().string(), Weatherinfo.class);
                MyApp.activity.runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        owl.onSuccess(weatherinfo);
                    }
                });
            }
        });
    }
}
