package com.runit.delhaizepoc.ui;

import android.arch.lifecycle.ViewModelProviders;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;

import com.runit.delhaizepoc.R;

import io.reactivex.disposables.CompositeDisposable;

public class HomeActivity extends AppCompatActivity implements View.OnClickListener {
    private ShopScreenViewModel viewModel;
    private CompositeDisposable compositeDisposable;

    private static final String PICKUP_FRAG = "pick_up_fragment";
    private View btnShop, btnPickUp, btnLists, btnProfile;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // remove title
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_home);
        compositeDisposable = new CompositeDisposable();
        viewModel = ViewModelProviders.of(this).get(ShopScreenViewModel.class);

        btnShop = findViewById(R.id.btn_shop);
        btnShop.setOnClickListener(this);
        btnPickUp = findViewById(R.id.btn_pickup);
        btnPickUp.setOnClickListener(this);
        btnLists = findViewById(R.id.btn_lists);
        btnLists.setOnClickListener(this);
        btnProfile = findViewById(R.id.btn_profile);
        btnProfile.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_shop: {
                Intent i = new Intent(HomeActivity.this, ShopActivity.class);
                startActivity(i);
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
        if (consumeBackButton()) {
        } else {
            super.onBackPressed();
        }
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
