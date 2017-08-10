package mouth.example.com.mvc.controller.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import mouth.example.com.mvc.MyApp;
import mouth.example.com.mvc.R;
import mouth.example.com.mvc.entity.User;
import mouth.example.com.mvc.entity.UserDao;
import mouth.example.com.mvc.entity.Weatherinfo;
import mouth.example.com.mvc.model.MyAdapter;
import mouth.example.com.mvc.model.okhttp.OnWeatherListener;
import mouth.example.com.mvc.model.okhttp.WeatherModel;
import mouth.example.com.mvc.model.okhttp.WeatherModelImpl;
import mouth.example.com.mvc.model.utils.Daosingleton;

public class MainActivity extends AppCompatActivity implements View.OnClickListener, OnWeatherListener {

    private EditText et_cityid;
    private Button btn_getweather;
    private TextView tv_city;
    private String path = "http://www.weather.com.cn/data/sk/";
    private TextView tv_cityid;
    private WeatherModel model;
    private String url;
    private ProgressBar pro;
    private UserDao getdao;
    private ListView listview;
    private List<User> users=new ArrayList<>();
    private List<User> list;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        MyApp.activity = this;
        setContentView(R.layout.activity_main);
        model = new WeatherModelImpl();
        getdao = Daosingleton.getDaosingleton().getdao(MainActivity.this);

        initView();
    }

    private void initView() {
        et_cityid = (EditText) findViewById(R.id.et_cityid);
        btn_getweather = (Button) findViewById(R.id.btn_getweather);
        tv_city = (TextView) findViewById(R.id.tv_city);
        tv_cityid = (TextView) findViewById(R.id.tv_cityid);
        pro = (ProgressBar) findViewById(R.id.pro);
        pro.setVisibility(View.GONE);
        btn_getweather.setOnClickListener(this);

        listview = (ListView) findViewById(R.id.listview);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_getweather:
                submit();
                model.getWeather(url, MainActivity.this);
                break;
        }
    }

    private void submit() {
        // validate
        String cityid = et_cityid.getText().toString().trim();
        if (TextUtils.isEmpty(cityid)) {
            Toast.makeText(this, "请输入城市序列号", Toast.LENGTH_SHORT).show();
            return;
        }
        url = path + cityid + ".html";

    }

    @Override
    public void onSuccess(Weatherinfo weatherinfo) {
        pro.setVisibility(View.VISIBLE);
        Weatherinfo.WeatherinfoBean weatherinfo1 = weatherinfo.getWeatherinfo();
        tv_city.setText(weatherinfo1.getCity());
        tv_cityid.setText(weatherinfo1.getCityid());
        getdao.insert(new User(null, weatherinfo.getWeatherinfo().getCity()));
        list = getdao.queryBuilder().build().list();
        users.addAll(list);
        MyAdapter myAdapter=new MyAdapter(MainActivity.this,users);
        listview.setAdapter(myAdapter);
        pro.setVisibility(View.GONE);
    }

    @Override
    public void OnError(String error) {
        Toast.makeText(this, "错误的原因" + error.toString(), Toast.LENGTH_SHORT).show();
    }
}
