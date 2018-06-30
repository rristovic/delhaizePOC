package com.runit.delhaizepoc.data;

import com.runit.delhaizepoc.data.dto.ShoppingListResult;
import com.runit.delhaizepoc.data.entity.Article;

import io.reactivex.Observable;

/**
 * Created by Sarma on 6/30/2018.
 */

public class ShopingListRepositoryImpl implements ShopingListRepository {

    @Override
    public Observable<ShoppingListResult> getCurrentShoppingList() {
        return null;
    }

    @Override
    public void addToCurrentShoppingList(Article articles) {

    }

    @Override
    public void removeFromCurrentShoppingList(Article articles) {

    }
}
