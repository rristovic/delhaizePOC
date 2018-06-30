package com.runit.delhaizepoc.data.db;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.RoomDatabase;

import com.runit.delhaizepoc.data.entity.Article;
import com.runit.delhaizepoc.data.entity.ShoppingList;

/**
 * Created by Sarma on 6/30/2018.
 */

@Database(version = 1, exportSchema = false, entities = {ShoppingList.class, Article.class})
public abstract class MaxiDB extends RoomDatabase {
    public abstract ShoppingListDao shoppingListDao();
}
