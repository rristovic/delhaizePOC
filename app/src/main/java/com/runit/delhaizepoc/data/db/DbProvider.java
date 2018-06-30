package com.runit.delhaizepoc.data.db;

import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.content.Context;

/**
 * Created by Sarma on 6/30/2018.
 */

public class DbProvider {
    private static DbProvider instance;
    static {
        instance = new DbProvider();
    }
    private DbProvider() {}

    public RoomDatabase getDatabase(Context context) {
        return Room.databaseBuilder(context, MaxiDB.class, "MaxiDB")
                .fallbackToDestructiveMigration().build();
    }

}
