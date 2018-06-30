package com.runit.delhaizepoc.data.db;

import android.arch.persistence.room.Room;
import android.content.Context;
import android.support.annotation.NonNull;

/**
 * Created by Sarma on 6/30/2018.
 */

public class DbProvider {
    private static DbProvider instance;
    private MaxiDB database;

    static {
        instance = new DbProvider();
    }

    private DbProvider() {
    }

    public void init(Context context) {
        database = Room.databaseBuilder(context, MaxiDB.class, "MaxiDB")
                .allowMainThreadQueries()
                .fallbackToDestructiveMigration()
                .build();
    }

    public static DbProvider getInstance() {
        return instance;
    }

    public @NonNull
    MaxiDB getDatabase() {
        if (database == null) {
            throw new IllegalStateException("Must call DbProvider.init() method before using DbProvider class.");
        } else
            return database;
    }

}
