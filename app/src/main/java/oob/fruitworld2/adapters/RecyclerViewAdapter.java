package oob.fruitworld2.adapters;

import android.app.Activity;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import java.util.ArrayList;
import oob.fruitworld2.models.Fruit;

public class RecyclerViewAdapter extends RecyclerView.Adapter<ViewHolderFruit>{

    private ArrayList<Fruit> fruits;
    private int layout;
    private Activity activity;

    public RecyclerViewAdapter(ArrayList<Fruit> fruits, int layout, Activity activity) {
        this.fruits = fruits;
        this.layout = layout;
        this.activity = activity;
    }

    @Override
    public ViewHolderFruit onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(this.activity).inflate(this.layout, parent, false);
        return new ViewHolderFruit(v);
    }

    @Override
    public void onBindViewHolder(ViewHolderFruit holder, int position) {
        holder.bind(this.fruits.get(position));
    }

    @Override
    public int getItemCount() {
        return fruits.size();
    }
}
