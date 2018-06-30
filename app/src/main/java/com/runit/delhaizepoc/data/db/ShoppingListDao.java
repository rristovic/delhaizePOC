package com.runit.delhaizepoc.data.db;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Query;

import com.runit.delhaizepoc.data.dto.ShoppingListResult;

import java.util.List;

import io.reactivex.Flowable;

/**
 * Created by Sarma on 6/30/2018.
 */

@Dao
public interface ShoppingListDao {
    @Query("SELECT * FROM shopping_list")
    Flowable<List<ShoppingListResult>> loadUsersWithPets();
}
