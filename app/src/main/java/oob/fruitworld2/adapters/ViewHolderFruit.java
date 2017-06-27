package oob.fruitworld2.adapters;

import android.app.Activity;
import android.graphics.Typeface;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import com.squareup.picasso.Picasso;
import oob.fruitworld2.R;
import oob.fruitworld2.Utils.Utils;
import oob.fruitworld2.models.Fruit;

class ViewHolderFruit extends RecyclerView.ViewHolder {

    private ImageView fruitBackground;
    private TextView fruitName;
    private TextView fruitDescription;
    private TextView fruitAmount;


    ViewHolderFruit(View itemView) {
        super(itemView);

        this.fruitBackground = (ImageView) itemView.findViewById(R.id.fruitBackground);
        this.fruitName = (TextView) itemView.findViewById(R.id.fruitName);
        this.fruitDescription = (TextView) itemView.findViewById(R.id.fruitDescription);
        this.fruitAmount = (TextView) itemView.findViewById(R.id.fruitAmount);
    }

    void bind(final Fruit fruit, Activity activity, final RecyclerViewAdapter.onBackgroundClick listener) {
        Picasso.with(activity).load(fruit.getBackground()).fit().into(this.fruitBackground);
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
            this.fruitAmount.setTextColor(ContextCompat.getColor(activity, android.R.color.holo_red_dark));
            this.fruitAmount.setTypeface(null, Typeface.BOLD);
        } else {
            this.fruitAmount.setTextColor(ContextCompat.getColor(activity, android.R.color.holo_blue_light));
            this.fruitAmount.setTypeface(null, Typeface.NORMAL);
        }

        this.fruitAmount.setText(String.valueOf(amount));
    }
}
