package com.runit.delhaizepoc.ui.shop;

import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.TextView;

import com.runit.delhaizepoc.R;
import com.runit.delhaizepoc.data.dto.ShoppingListResult;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;

public class ShopActivity extends AppCompatActivity implements View.OnClickListener {
    private DrawerLayout drawer;
    private CompositeDisposable compositeDisposable;
    private View btnCart;
    private TextView tvCartLabel;
    private ShopScreenViewModel viewModel;

    private final static String CAT_FRAG = "category_frag";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shop);
        compositeDisposable = new CompositeDisposable();
        viewModel = ViewModelProviders.of(this).get(ShopScreenViewModel.class);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        tvCartLabel = findViewById(R.id.tv_cart_label);
        btnCart = findViewById(R.id.btn_cart);
        btnCart.setOnClickListener(this);
        drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        closeDrawer();

        getSupportFragmentManager().beginTransaction()
                .replace(R.id.main_container, new TopCategoryFragment(), CAT_FRAG)
                .commit();

        viewModel.getCurrentShoppingList()
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<ShoppingListResult>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                        getCompositeDisposable().add(d);
                    }

                    @Override
                    public void onNext(ShoppingListResult shoppingListResult) {
                        if (shoppingListResult.articles.size() == 0) {
                            ((View)tvCartLabel.getParent()).setVisibility(View.GONE);
                        } else {
                            ((View)tvCartLabel.getParent()).setVisibility(View.VISIBLE);
                            tvCartLabel.setText(Integer.toString(shoppingListResult.articles.size()));
                        }
                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_cart: {
                if (isDrawerOpen()) {
                    closeDrawer();
                } else {
                    openDrawer();
                }
                break;
            }
        }
    }

    @Override
    public void onBackPressed() {
        if (isDrawerOpen()) {
            closeDrawer();
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }

    private void openDrawer() {
        drawer.openDrawer(GravityCompat.END);
    }

    private void closeDrawer() {
        drawer.closeDrawer(GravityCompat.END);
    }

    private boolean isDrawerOpen() {
        return drawer.isDrawerOpen(GravityCompat.END);
    }

    public void showFragment(Fragment fragment, String fragTag) {
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.main_container, fragment, fragTag)
                .addToBackStack(null)
                .commit();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (!compositeDisposable.isDisposed())
            compositeDisposable.dispose();
    }

    public void onFragmentResumed() {

    }

    public CompositeDisposable getCompositeDisposable() {
        return compositeDisposable;
    }
}
