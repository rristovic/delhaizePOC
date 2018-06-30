package com.runit.delhaizepoc.ui.shop;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.support.annotation.NonNull;

import com.runit.delhaizepoc.data.ShoppingListRepository;
import com.runit.delhaizepoc.data.ShoppingListRepositoryImpl;
import com.runit.delhaizepoc.data.dto.ShoppingListResult;

import io.reactivex.Observable;

/**
 * Created by Sarma on 6/30/2018.
 */

public class ShopScreenViewModel extends AndroidViewModel {
    ShoppingListRepository repository;

    public ShopScreenViewModel(@NonNull Application application) {
        super(application);
        repository = new ShoppingListRepositoryImpl();
    }

    public Observable<ShoppingListResult> getCurrentShoppingList() {
        return repository.getCurrentShoppingList();
    }
}
