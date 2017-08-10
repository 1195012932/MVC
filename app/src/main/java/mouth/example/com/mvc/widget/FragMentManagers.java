package mouth.example.com.mvc.widget;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;

import mouth.example.com.mvc.MyApp;
import mouth.example.com.mvc.controller.base.BaseFragment;

/**
 * Created by lenovo on 2017/8/10.
 */

public class FragMentManagers {

    private static BaseFragment lastFragment;

    public static Fragment startFragMent(Class<? extends BaseFragment> fragment, int containerId, Bundle params, boolean isBack, boolean isHidden) {
        FragmentManager manager = MyApp.activity.getSupportFragmentManager();
        FragmentTransaction transaction = manager.beginTransaction();
        String tagName = fragment.getSimpleName();
        BaseFragment currentfragment = (BaseFragment) manager.findFragmentByTag(tagName);
        if (currentfragment == null) {

            try {
                currentfragment = fragment.newInstance();
                transaction.add(containerId, currentfragment, tagName);
            } catch (InstantiationException e) {
                e.printStackTrace();
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
        }
        if (isHidden) {
            if (lastFragment != null) {
                transaction.hide(lastFragment);
                transaction.show(currentfragment);
            }else {
                transaction.replace(containerId,currentfragment,tagName);
            }
        }
        if (params != null) {
//            currentfragment.setParams(params);
        }
        return currentfragment;

    }
}
