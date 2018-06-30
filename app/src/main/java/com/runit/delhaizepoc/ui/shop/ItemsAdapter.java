package com.runit.delhaizepoc.ui.shop;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.runit.delhaizepoc.R;
import com.runit.delhaizepoc.data.ShoppingListRepositoryImpl;
import com.runit.delhaizepoc.data.entity.Article;
import com.runit.delhaizepoc.ui.ItemClickListener;

/**
 * Created by Sarma on 6/30/2018.
 */

public class ItemsAdapter extends RecyclerView.Adapter<ItemsAdapter.CatViewHolder> {

    private static final Article[] data = new Article[3];
    private static int[] img = new int[3];

    static {
        img = new int[]{R.drawable.home, R.drawable.home, R.drawable.home,};
        for (int i = 0; i < 3; i++) {
            Article a = new Article();
            a._id = i;
            a.company = "Cedevita";
            a.name = "Lorem Ipsum";
            a.price = 250;
            a.weight = "200g (249 rsd / 1kg";
            data[i] = a;
        }
    }

    private final ItemClickListener<String> listener;
    private final ShoppingListRepositoryImpl repo;

    public ItemsAdapter(ItemClickListener<String> listener) {
        this.listener = listener;
        this.repo = new ShoppingListRepositoryImpl();
    }

    @Override
    public CatViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new CatViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.shop_cart_item, parent, false));
    }

    @Override
    public void onBindViewHolder(CatViewHolder holder, int position) {
        Article a = data[position];
        holder.label.setText(a.name);
        holder.company.setText(a.company);
        holder.gram.setText(a.weight + "g");
        holder.cost.setText(a.count + " x " + a.price);
    }

    @Override
    public int getItemCount() {
        return data.length;
    }

    public class CatViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        public TextView label;
        public TextView company;
        public TextView cost;
        public TextView gram;
        public ImageView icon;
        public ImageView plus, minus;

        public CatViewHolder(View itemView) {
            super(itemView);
            label = itemView.findViewById(R.id.tv_label);
            company = itemView.findViewById(R.id.tv_company);
            cost = itemView.findViewById(R.id.tv_cost);
            gram = itemView.findViewById(R.id.tv_gram);
            icon = itemView.findViewById(R.id.iv_icon);
            plus = itemView.findViewById(R.id.iv_plus);
            minus = itemView.findViewById(R.id.iv_minus);
            plus.setOnClickListener(this);
            minus.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            int pos = getAdapterPosition();
            if (view.getId() == R.id.iv_plus) {
                data[pos].count++;
                notifyItemChanged(pos);
                repo.addToCurrentShoppingList(data[pos]);
            } else {
                if (data[pos].count > 0) {
                    data[pos].count--;
                    notifyItemChanged(pos);
                    repo.removeFromCurrentShoppingList(data[pos]);
                }
            }
        }
    }
}
