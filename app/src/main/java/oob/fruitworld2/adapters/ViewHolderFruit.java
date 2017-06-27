package oob.fruitworld2.adapters;

import android.graphics.Typeface;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.RecyclerView;
import android.view.ContextMenu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import com.squareup.picasso.Picasso;
import java.util.ArrayList;
import oob.fruitworld2.R;
import oob.fruitworld2.Utils.Utils;
import oob.fruitworld2.models.Fruit;

class ViewHolderFruit extends RecyclerView.ViewHolder implements View.OnCreateContextMenuListener, MenuItem.OnMenuItemClickListener {

    private ImageView fruitBackground;
    private TextView fruitName;
    private TextView fruitDescription;
    private TextView fruitAmount;
    private RecyclerViewAdapter adapter;

    ViewHolderFruit(View itemView, RecyclerViewAdapter adapter) {
        super(itemView);

        this.fruitBackground = (ImageView) itemView.findViewById(R.id.fruitBackground);
        this.fruitName = (TextView) itemView.findViewById(R.id.fruitName);
        this.fruitDescription = (TextView) itemView.findViewById(R.id.fruitDescription);
        this.fruitAmount = (TextView) itemView.findViewById(R.id.fruitAmount);
        this.adapter = adapter;

        itemView.setOnCreateContextMenuListener(this);
    }

    void bind(final Fruit fruit, final RecyclerViewAdapter.onBackgroundClick listener) {
        Picasso.with(this.adapter.getActivity()).load(fruit.getBackground()).fit().into(this.fruitBackground);
        this.fruitBackground.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listener.onClick(fruit, getAdapterPosition());
            }
        });

        this.fruitName.setText(fruit.getName());
        this.fruitDescription.setText(fruit.getDescription());

        int amount = fruit.getAmount();

        if (amount == Utils.MAXIMUM_AMOUNT) {
            this.fruitAmount.setTextColor(ContextCompat.getColor(this.adapter.getActivity(), android.R.color.holo_red_dark));
            this.fruitAmount.setTypeface(null, Typeface.BOLD);
        } else {
            this.fruitAmount.setTextColor(ContextCompat.getColor(this.adapter.getActivity(), android.R.color.holo_blue_light));
            this.fruitAmount.setTypeface(null, Typeface.NORMAL);
        }

        this.fruitAmount.setText(String.valueOf(amount));
    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        ArrayList<Fruit> fruits = this.adapter.getFruits();

        Fruit fruit = fruits.get(this.getAdapterPosition());

        menu.setHeaderTitle(fruit.getName());
        menu.setHeaderIcon(fruit.getIcon());
        MenuInflater inflater = this.adapter.getActivity().getMenuInflater();

        inflater.inflate(R.menu.context_fruit_menu, menu);

        for (int i = 0; i < menu.size(); i++) {
            menu.getItem(i).setOnMenuItemClickListener(this);
        }
    }

    @Override
    public boolean onMenuItemClick(MenuItem item) {
        ArrayList<Fruit> fruits = this.adapter.getFruits();
        int position = this.getAdapterPosition();
        switch (item.getItemId()) {
            case R.id.delete_fruit:
                fruits.remove(position);
                this.adapter.notifyItemRemoved(position);
                break;
            case R.id.reset_amount_fruit:
                fruits.get(position).resetAmount();
                this.adapter.notifyItemChanged(position);
                break;
            default:
                return false;
        }

        return true;
    }
}
