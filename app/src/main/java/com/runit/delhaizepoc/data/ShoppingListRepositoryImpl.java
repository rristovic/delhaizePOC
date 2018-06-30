package com.runit.delhaizepoc.data;

import com.runit.delhaizepoc.data.db.DbProvider;
import com.runit.delhaizepoc.data.db.MaxiDB;
import com.runit.delhaizepoc.data.dto.ShoppingListResult;
import com.runit.delhaizepoc.data.entity.Article;
import com.runit.delhaizepoc.data.entity.ShoppingList;

import io.reactivex.Completable;
import io.reactivex.Observable;
import io.reactivex.schedulers.Schedulers;

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
    public void addToCurrentShoppingList(Article article) {
        Completable.fromAction(() -> {
            ShoppingList current = db.shoppingListDao().getShoppingList(CUR_SHOPING_LIST_ID).shoppingList;
            article.listId = current.id;
            db.shoppingListDao().insert(article);
        }).subscribeOn(Schedulers.io()).onErrorComplete().subscribe();
    }

    @Override
    public void removeFromCurrentShoppingList(Article article) {
        Completable.fromAction(() -> {
            ShoppingList current = db.shoppingListDao().getShoppingList(CUR_SHOPING_LIST_ID).shoppingList;
            article.listId = current.id;
            if (article.count == 0)
                db.shoppingListDao().delete(article);
            else
                db.shoppingListDao().insert(article);
        }).subscribeOn(Schedulers.io()).onErrorComplete().subscribe();

    }
}
