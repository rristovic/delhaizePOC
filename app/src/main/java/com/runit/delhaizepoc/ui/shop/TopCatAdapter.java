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

public class TopCatAdapter extends RecyclerView.Adapter<TopCatAdapter.CatViewHolder> {

    private static final String[] data = new String[]{"Promo", "Baby Food",
            "Bakery",
            "Chocolate & Sweets",
            "Coffee, Tea & Drinks",
            "Frozen Food",
            "Fruit & Vegetables",
            "Healthy Food",
            "Kitchen",
            "Meat & Fish",
            "Milk & Egg",
            "Pet Food", "Baby Food",
            "Bakery",
            "Chocolate & Sweets",
            "Coffee, Tea & Drinks",
            "Frozen Food",
            "Fruit & Vegetables",
            "Healthy Food",
            "Kitchen",
            "Meat & Fish",
            "Milk & Egg",
            "Pet Food",
    };
    private static final int[] img = new int[]{R.drawable.promo, R.drawable.baby_food, R.drawable.bakery, R.drawable.chocolate,
            R.drawable.coffee_tea_drinks, R.drawable.frozen, R.drawable.fruit_vegetables, R.drawable.healthy_food,
            R.drawable.kitchen, R.drawable.meat_fish, R.drawable.milk_egg, R.drawable.pet_food, R.drawable.baby_food, R.drawable.bakery, R.drawable.chocolate,
            R.drawable.coffee_tea_drinks, R.drawable.frozen, R.drawable.fruit_vegetables, R.drawable.healthy_food,
            R.drawable.kitchen, R.drawable.meat_fish, R.drawable.milk_egg, R.drawable.pet_food};
    private final ItemClickListener<String> listener;

    public TopCatAdapter(ItemClickListener<String> listener) {
        this.listener = listener;
    }

    @Override
    public CatViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new CatViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.top_category_item, parent, false));
    }

    @Override
    public void onBindViewHolder(CatViewHolder holder, int position) {
        holder.label.setText(data[position]);
        holder.label.setCompoundDrawablesWithIntrinsicBounds(0, img[position], 0, 0);
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
            if (listener != null) {
                listener.onItemClicked(data[getAdapterPosition()]);
            }
        }
    }
}
