package com.by5388.xw.searchframe;

import android.app.Application;

/**
 * @author by5388  on 2018/11/7.
 */
public class App extends Application {
    private static App instance;

    @Override
    public void onCreate() {
        super.onCreate();
        instance = this;
    }

    public static App getInstance() {
        return instance;
    }
}
