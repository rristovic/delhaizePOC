package com.runit.delhaizepoc.data.entity;

import io.realm.RealmObject;

/**
 * Created by Sarma on 6/30/2018.
 */

public class Article extends RealmObject {
    private String name;
    private int weight;

    public Article() {}

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getWeight() {
        return weight;
    }

    public void setWeight(int weight) {
        this.weight = weight;
    }
}
