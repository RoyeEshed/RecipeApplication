package com.eshed.fork.Favorites.view;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.eshed.fork.Data.DbRecipeRepository;
import com.eshed.fork.Favorites.vm.FavoritesCardViewModel;
import com.eshed.fork.Favorites.vm.FavoritesViewModel;
import com.eshed.fork.Fork;
import com.eshed.fork.R;
import com.eshed.fork.Recipe.view.RecipeActivity;
import com.eshed.fork.Util.Util;

public class FavoritesActivity extends AppCompatActivity implements FavoritesRecyclerViewAdapter.FavoritesAdapterHandler {
    private FavoritesViewModel vm;
    private FavoritesRecyclerViewAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_favorites);
        Util.setupTabBar(this);
        setupToolbar();
        DbRecipeRepository dbRepository = DbRecipeRepository.getInstance();
        Fork app = (Fork) getApplication();
        vm = new FavoritesViewModel(app.getUid());
        dbRepository.load();
        initRecyclerView();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                this.finish();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    private void initRecyclerView() {
        RecyclerView recyclerView = findViewById(R.id.recycler_view);
        recyclerView.setHasFixedSize(true);
        adapter = new FavoritesRecyclerViewAdapter(vm);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new GridLayoutManager(this, 1));
        ((FavoritesRecyclerViewAdapter) adapter).handler = this;
    }

    private void setupToolbar() {
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        if (this.getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setHomeAsUpIndicator(R.drawable.ic_baseline_arrow_back_24);
            getSupportActionBar().setDisplayShowTitleEnabled(false);
        }
        TextView title = toolbar.findViewById(R.id.toolbar_title);
        title.setText("Favorites");
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.d("TAG", "onResume: CALLED");
        adapter.notifyDataSetChanged();
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        adapter.notifyDataSetChanged();
    }

    @Override
    public void selectFavoritesCard(FavoritesCardViewModel vm) {
        Intent intent = new Intent(this, RecipeActivity.class);
        intent.putExtra("recipe", vm.getRecipe().getRecipeID());
        this.startActivity(intent);
    }
}

