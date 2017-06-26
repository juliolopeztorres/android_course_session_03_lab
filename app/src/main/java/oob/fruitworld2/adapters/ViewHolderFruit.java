package oob.fruitworld2.adapters;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import oob.fruitworld2.R;
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

    void bind(final Fruit fruit) {
        this.fruitBackground.setBackgroundResource(fruit.getBackground());
        this.fruitName.setText(fruit.getName());
        this.fruitDescription.setText(fruit.getDescription());
        this.fruitAmount.setText(String.valueOf(fruit.getAmount()));
    }
}
