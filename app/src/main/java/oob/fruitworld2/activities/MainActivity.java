package oob.fruitworld2.activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.Toast;
import java.util.ArrayList;
import oob.fruitworld2.R;
import oob.fruitworld2.Utils.Utils;
import oob.fruitworld2.adapters.RecyclerViewAdapter;
import oob.fruitworld2.models.Fruit;

public class MainActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private RecyclerView.LayoutManager layoutManager;
    private RecyclerViewAdapter recyclerViewAdapter;

    private ArrayList<Fruit> fruits;
    private int counter = Utils.INITIAL_AMOUNT;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        this.fruits = this.getAllFruits();

        this.recyclerView = (RecyclerView) findViewById(R.id.recyclerView);
        this.layoutManager = new LinearLayoutManager(this);
        this.recyclerViewAdapter = new RecyclerViewAdapter(this.fruits, R.layout.card_item_layout, this, new RecyclerViewAdapter.onBackgroundClick() {
            @Override
            public void onClick(Fruit fruit, int position) {
                if (fruit.addAmount()) {
                    recyclerViewAdapter.notifyItemChanged(position);
                } else {
                    showErrorUpdatingFruitAmount();
                }
            }
        });

        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(this.layoutManager);
        recyclerView.setAdapter(this.recyclerViewAdapter);
    }

    private void showErrorUpdatingFruitAmount() {
        Toast.makeText(this, getString(R.string.messageUpdateFruitMaximumAmount), Toast.LENGTH_SHORT).show();
    }

    public ArrayList<Fruit> getAllFruits() {
        return new ArrayList<Fruit>() {{
            add(new Fruit("Strawberry", "Strawberry's description", R.drawable.strawberry_bg, R.drawable.strawberry_ic, 0));
            add(new Fruit("Orange", "Orange's description", R.drawable.orange_bg, R.drawable.orange_ic, 0));
            add(new Fruit("Apple", "Apple's description", R.drawable.apple_bg, R.drawable.apple_ic, 0));
            add(new Fruit("Banana", "Banana's description", R.drawable.banana_bg, R.drawable.banana_ic, 0));
            add(new Fruit("Cherry", "Cherry's description", R.drawable.cherry_bg, R.drawable.cherry_ic, 0));
            add(new Fruit("Pear", "Pear's description", R.drawable.pear_bg, R.drawable.pear_ic, 0));
            add(new Fruit("Raspberry", "Raspberry's description", R.drawable.raspberry_bg, R.drawable.raspberry_ic, 0));
        }};
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = this.getMenuInflater();
        menuInflater.inflate(R.menu.option_menu, menu);

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.addFruit:
                this.addNewStandardFruit();
                break;
            default:
                return super.onOptionsItemSelected(item);
        }

        return true;
    }

    private void addNewStandardFruit() {
        Fruit fruit = new Fruit(
                getString(R.string.fruit_standard_name) + " " + (++this.counter),
                getString(R.string.fruit_standard_description),
                R.drawable.plum_bg,
                R.drawable.plum_ic,
                Utils.INITIAL_AMOUNT
        );

        int newFruitPosition = this.fruits.size();
        this.fruits.add(fruit);

        this.recyclerViewAdapter.notifyItemInserted(newFruitPosition);
        this.layoutManager.scrollToPosition(newFruitPosition);
    }
}
