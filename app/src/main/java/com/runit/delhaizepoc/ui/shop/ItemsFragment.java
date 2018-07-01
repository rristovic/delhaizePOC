package com.runit.delhaizepoc.ui.shop;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.runit.delhaizepoc.R;

/**
 * Created by Sarma on 6/30/2018.
 */

public class ItemsFragment extends Fragment {

    RecyclerView list;

    public ItemsFragment() {}

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.list_frag, container, false);
        list = root.findViewById(R.id.list);
        list.setAdapter(new ItemsAdapter(item -> {

        }));
        list.setLayoutManager(new LinearLayoutManager(getActivity()));
        return root;
    }

    @Override
    public void onResume() {
        super.onResume();
        getActivity().setTitle("Fresh Fruit");
    }
}
