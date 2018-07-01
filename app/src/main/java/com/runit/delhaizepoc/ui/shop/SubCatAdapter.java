package com.runit.delhaizepoc.ui.shop;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.runit.delhaizepoc.R;
import com.runit.delhaizepoc.ui.ItemClickListener;

/**
 * Created by Sarma on 6/30/2018.
 */

public class SubCatAdapter extends RecyclerView.Adapter<SubCatAdapter.CatViewHolder> {

    private static final String[] data = new String[]{"Fresh Vegetables", "Organic Fruit & Vegetables",
    "Ready to Eat", "Fresh Fruit"};
    private final ItemClickListener<String> listener;

    public SubCatAdapter(ItemClickListener<String> listener) {
        this.listener = listener;
    }

    @Override
    public CatViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new CatViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.sub_category_item, parent, false));
    }

    @Override
    public void onBindViewHolder(CatViewHolder holder, int position) {
        holder.label.setText(data[position]);
    }

    @Override
    public int getItemCount() {
        return data.length;
    }

    public class CatViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        public TextView label;

        public CatViewHolder(View itemView) {
            super(itemView);
            label = (TextView) itemView;
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            if(listener != null) {
                listener.onItemClicked(data[getAdapterPosition()]);
            }
        }
    }
}
