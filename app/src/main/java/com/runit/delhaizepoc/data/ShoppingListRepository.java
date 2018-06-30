package com.runit.delhaizepoc.data;

import com.runit.delhaizepoc.data.dto.ShoppingListResult;
import com.runit.delhaizepoc.data.entity.Article;

import io.reactivex.Observable;

/**
 * Created by Sarma on 6/30/2018.
 */

public interface ShoppingListRepository {
    Observable<ShoppingListResult> getCurrentShoppingList();

    void addToCurrentShoppingList(Article articles);
    void removeFromCurrentShoppingList(Article articles);
    void resetCurrentShoppingList();
}
