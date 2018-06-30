package com.runit.delhaizepoc.data.entity;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;

/**
 * Created by Sarma on 6/30/2018.
 */

@Entity(tableName = "shopping_list")
public class ShoppingList {
    @PrimaryKey
    @ColumnInfo(name = "list_id")
    public long id;
    public String title;
}
