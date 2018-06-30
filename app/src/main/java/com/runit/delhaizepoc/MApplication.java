package com.runit.delhaizepoc;

import android.app.Application;

import com.runit.delhaizepoc.data.db.DbProvider;

/**
 * Created by Sarma on 6/30/2018.
 */

public class MApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        DbProvider.getInstance().init(this);
    }
}