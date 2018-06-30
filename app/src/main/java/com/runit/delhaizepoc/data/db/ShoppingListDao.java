package com.runit.delhaizepoc.data.db;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;

import com.runit.delhaizepoc.data.dto.ShoppingListResult;
import com.runit.delhaizepoc.data.entity.Article;
import com.runit.delhaizepoc.data.entity.ShoppingList;

import io.reactivex.Flowable;

/**
 * Created by Sarma on 6/30/2018.
 */

@Dao
public interface ShoppingListDao {
    @Query("SELECT * FROM shopping_list WHERE shopping_list.list_id = :id")
    Flowable<ShoppingListResult> getShoppingListAsync(long id);

    @Query("SELECT * FROM shopping_list WHERE shopping_list.list_id = :id")
    ShoppingListResult getShoppingList(long id);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insert(ShoppingList shoppingList);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insert(Article article);

    @Delete
    void delete(Article article);

    @Query("DELETE FROM article WHERE article.listId = :listId")
    void deleteShoppingList(long listId);
}
