package mouth.example.com.mvc.controller.base;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by lenovo on 2017/8/10.
 */

public abstract class BaseFragment extends Fragment {

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(getLayoutId(), null);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initView(view);
        initData();
        initListener();
    }
    //同一加载布局
    protected abstract int getLayoutId();


    protected abstract void initListener();

    protected abstract void initData();

    protected abstract void initView(View view);



    @Override
    public void onHiddenChanged(boolean hidden) {
        super.onHiddenChanged(hidden);
        if (hidden) {
            //隐藏状态，释放资源
            onHidden();
        } else {
            //显示状态，刷新资源
            onShow();
        }
    }

    protected abstract void onShow();

    protected abstract void onHidden();

}
