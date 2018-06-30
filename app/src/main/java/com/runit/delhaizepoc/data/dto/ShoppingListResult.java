package com.runit.delhaizepoc.data.dto;

import android.arch.persistence.room.Embedded;
import android.arch.persistence.room.Relation;

import com.runit.delhaizepoc.data.entity.Article;
import com.runit.delhaizepoc.data.entity.ShoppingList;

import java.util.List;

/**
 * Created by Sarma on 6/30/2018.
 */

public class ShoppingListResult {
    @Embedded
    public ShoppingList shoppingList;

    @Relation(parentColumn = "list_id", entityColumn = "article_id", entity = Article.class)
    public List<Article> articles;
}
