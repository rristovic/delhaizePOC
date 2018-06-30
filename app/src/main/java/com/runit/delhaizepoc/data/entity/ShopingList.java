package com.runit.delhaizepoc.data.entity;

import io.realm.RealmList;
import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

/**
 * Created by Sarma on 6/30/2018.
 */

public class ShopingList extends RealmObject{
    @PrimaryKey
    private long id;
    private String title;
    private RealmList<Article> articles;

    public ShopingList() {}

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public RealmList<Article> getArticles() {
        return articles;
    }

    public void setArticles(RealmList<Article> articles) {
        this.articles = articles;
    }
}
