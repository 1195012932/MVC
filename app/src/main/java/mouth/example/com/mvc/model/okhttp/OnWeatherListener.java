package mouth.example.com.mvc.model.okhttp;

import mouth.example.com.mvc.entity.Weatherinfo;

/**
 * Created by lenovo on 2017/8/9.
 */

public interface OnWeatherListener {
    void onSuccess(Weatherinfo weatherinfo);
    void OnError(String error);
}
