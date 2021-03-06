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
    private onBackgroundClick listener;

    public RecyclerViewAdapter(ArrayList<Fruit> fruits, int layout, Activity activity, onBackgroundClick listener) {
        this.fruits = fruits;
        this.layout = layout;
        this.activity = activity;
        this.listener = listener;
    }

    @Override
    public ViewHolderFruit onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(this.activity).inflate(this.layout, parent, false);
        return new ViewHolderFruit(v, this);
    }

    @Override
    public void onBindViewHolder(ViewHolderFruit holder, int position) {
        holder.bind(this.fruits.get(position), this.listener);
    }

    @Override
    public int getItemCount() {
        return fruits.size();
    }

    public interface onBackgroundClick {
        void onClick(Fruit fruit, int position);
    }

    Activity getActivity() {
        return activity;
    }

    ArrayList<Fruit> getFruits() {
        return fruits;
    }
}
