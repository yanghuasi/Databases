package com.ninetripods.mq.sqliteupdatedemo;

import android.app.Application;
import android.content.Context;

/**
 * Created by mq on 2017/10/22 上午11:58
 * mqcoder90@gmail.com
 */

public class MyApplication extends Application {

    private static Context context;

    @Override
    public void onCreate() {
        super.onCreate();
        context = getApplicationContext();
    }

    public static Context getContext() {
        return context;
    }
}
