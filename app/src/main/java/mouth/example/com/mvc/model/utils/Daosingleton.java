package mouth.example.com.mvc.model.utils;

import android.content.Context;

import mouth.example.com.mvc.entity.DaoMaster;
import mouth.example.com.mvc.entity.DaoSession;
import mouth.example.com.mvc.entity.UserDao;

public class Daosingleton {

    private static Daosingleton daosingleton;

    private Daosingleton() {

    }
    public static Daosingleton getDaosingleton() {

        if (daosingleton == null) {
            synchronized (Daosingleton.class) {
                if (daosingleton == null) {
                    daosingleton = new Daosingleton();
                }
            }
        }

        return daosingleton;
    }
    public UserDao getdao(Context context) {

        DaoMaster.DevOpenHelper devOpenHelper = new DaoMaster.DevOpenHelper(context, "students.db", null);
        DaoMaster daoMaster = new DaoMaster(devOpenHelper.getReadableDb());
        DaoSession daoSession = daoMaster.newSession();
        UserDao userDao = daoSession.getUserDao();
        return userDao;

    }


}