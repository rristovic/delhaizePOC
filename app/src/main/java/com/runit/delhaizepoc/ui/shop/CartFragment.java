package com.runit.delhaizepoc.ui.shop;

import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.runit.delhaizepoc.R;
import com.runit.delhaizepoc.data.dto.ShoppingListResult;
import com.runit.delhaizepoc.data.entity.Article;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;

/**
 * Created by Sarma on 6/30/2018.
 */

public class CartFragment extends Fragment {

    RecyclerView list;
    private ShopScreenViewModel viewModel;
    private CartAdapter adapter;
    private TextView tvCartTotal, tvNoItems;

    public CartFragment() {
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.cart_frag, container, false);
        tvCartTotal = root.findViewById(R.id.tv_cart_total);
        tvNoItems = root.findViewById(R.id.no_items);
        viewModel = ViewModelProviders.of(getActivity()).get(ShopScreenViewModel.class);
        list = root.findViewById(R.id.list);
        adapter = new CartAdapter(item -> {

        });
        list.setAdapter(adapter);
        list.setLayoutManager(new LinearLayoutManager(getActivity()));

        viewModel.getCurrentShoppingList()
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<ShoppingListResult>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                        ((ShopActivity) getActivity()).getCompositeDisposable().add(d);
                    }

                    @Override
                    public void onNext(ShoppingListResult shoppingListResult) {
                        adapter.setData(shoppingListResult.articles);

                        if (shoppingListResult.articles.size() == 0) {
                            tvNoItems.setVisibility(View.VISIBLE);
                        } else {
                            tvNoItems.setVisibility(View.GONE);
                        }

                        float total = 0;
                        for (Article a :
                                shoppingListResult.articles) {
                            total += a.count * a.price;
                        }
                        tvCartTotal.setText(total + " rsd");

                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onComplete() {

                    }
                });
        return root;
    }

    @Override
    public void onResume() {
        super.onResume();
        getActivity().setTitle("Shop");
    }
}
