package mouth.example.com.mvc.controller.base;

import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import mouth.example.com.mvc.MyApp;

/**
 * Created by lenovo on 2017/8/10.
 */

public abstract class BaseActivity extends AppCompatActivity {

    protected <T extends View> T findView(int viewId) {
        return (T) findViewById(viewId);
    }

    @Override
    public void onCreate(Bundle savedInstanceState, PersistableBundle persistentState) {
        super.onCreate(savedInstanceState, persistentState);
        setContentView(getLayoutId());
        MyApp.activity=this;
        initView();
        initData();
        initListener();
    }

    @Override
    protected void onResume() {
        super.onResume();
        MyApp.activity=this;
    }

    //加载布局
    protected abstract int getLayoutId();
    //初始化数据

    protected abstract void initListener();

    protected abstract void initView();

    protected abstract void initData();


}

