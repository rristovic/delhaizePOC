package com.runit.delhaizepoc.ui;

import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.runit.delhaizepoc.R;

import io.reactivex.disposables.CompositeDisposable;

public class ShopActivity extends AppCompatActivity implements View.OnClickListener {
    private DrawerLayout drawer;
    private CompositeDisposable compositeDisposable;
    private View btnCurrentList;
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
        btnCurrentList = findViewById(R.id.btn_current_list);
        btnCurrentList.setOnClickListener(this);
        drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        closeDrawer();

        getSupportFragmentManager().beginTransaction()
                .replace(R.id.main_container, new TopCategoryFragment(), CAT_FRAG)
                .commit();
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_current_list: {
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
