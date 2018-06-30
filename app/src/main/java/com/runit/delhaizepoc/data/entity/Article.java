package com.runit.delhaizepoc.data.entity;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;

/**
 * Created by Sarma on 6/30/2018.
 */

@Entity(tableName = "article")
public class Article {
    @PrimaryKey
    @ColumnInfo(name = "article_id")
    public int _id;
    public String name, company, weight;
    public long listId;
    public float price;
    public int count = 0;
    public int img;
}
