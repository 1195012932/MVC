package mouth.example.com.mvc.model.okhttp;

/**
 * Created by lenovo on 2017/8/9.
 */

public interface WeatherModel {
    void getWeather(String url,OnWeatherListener owl);
}
