package com.runit.delhaizepoc.data;

import com.runit.delhaizepoc.data.db.DbProvider;
import com.runit.delhaizepoc.data.db.MaxiDB;
import com.runit.delhaizepoc.data.dto.ShoppingListResult;
import com.runit.delhaizepoc.data.entity.Article;
import com.runit.delhaizepoc.data.entity.ShoppingList;

import io.reactivex.Observable;

/**
 * Created by Sarma on 6/30/2018.
 */

public class ShoppingListRepositoryImpl implements ShoppingListRepository {

    private final MaxiDB db;
    private static final int CUR_SHOPING_LIST_ID = 1;

    public ShoppingListRepositoryImpl() {
        this.db = DbProvider.getInstance().getDatabase();
        if (this.db.shoppingListDao().getShoppingList(CUR_SHOPING_LIST_ID) == null) {
            ShoppingList current = new ShoppingList();
            current.title = "Current Shopping List";
            current.id = CUR_SHOPING_LIST_ID;
            this.db.shoppingListDao().insert(current);
        }
    }

    @Override
    public Observable<ShoppingListResult> getCurrentShoppingList() {
        return db.shoppingListDao().getShoppingListAsync(CUR_SHOPING_LIST_ID).toObservable();
    }

    @Override
    public void addToCurrentShoppingList(Article articles) {

    }

    @Override
    public void removeFromCurrentShoppingList(Article articles) {

    }
}
