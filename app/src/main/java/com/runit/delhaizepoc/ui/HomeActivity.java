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

public class HomeActivity extends AppCompatActivity implements View.OnClickListener {
    private MainScreenViewModel viewModel;
    private CompositeDisposable compositeDisposable;
    private DrawerLayout drawer;

    private static final String PICKUP_FRAG = "pick_up_fragment";
    private View btnCurrentList, btnShop, btnPickUp, btnLists, btnProfile;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        compositeDisposable = new CompositeDisposable();
        viewModel = ViewModelProviders.of(this).get(MainScreenViewModel.class);
        // init ui
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle(null);
        btnCurrentList = findViewById(R.id.btn_current_list);
        btnCurrentList.setOnClickListener(this);
        btnShop = findViewById(R.id.btn_shop);
        btnShop.setOnClickListener(this);
        btnPickUp = findViewById(R.id.btn_pickup);
        btnPickUp.setOnClickListener(this);
        btnLists = findViewById(R.id.btn_lists);
        btnLists.setOnClickListener(this);
        btnProfile = findViewById(R.id.btn_profile);
        btnProfile.setOnClickListener(this);
        drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        closeDrawer();
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
            case R.id.btn_shop: {
                break;
            }
            case R.id.btn_pickup: {
                showFragment(new PickUpFragment());
                break;
            }
            case R.id.btn_lists: {
                break;
            }
            case R.id.btn_profile: {
                break;
            }
        }
    }

    @Override
    public void onBackPressed() {
        if (isDrawerOpen()) {
            closeDrawer();
        } else if (consumeBackButton()) {
        } else {
            super.onBackPressed();
        }
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

    private void showFragment(Fragment fragment) {
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.main_container, fragment, PICKUP_FRAG)
                .addToBackStack(null)
                .commit();
    }

    private boolean consumeBackButton() {
        PickUpFragment pickUpFragment = (PickUpFragment) getSupportFragmentManager().findFragmentByTag(PICKUP_FRAG);
        if (pickUpFragment != null) {
            return pickUpFragment.onBackPressed();
        } else
            return false;
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
