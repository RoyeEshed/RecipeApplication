package com.eshed.fork.Modifications.view;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.eshed.fork.Browse.view.BrowseActivity;
import com.eshed.fork.Data.DbRecipeRepository;
import com.eshed.fork.Fork;
import com.eshed.fork.Modifications.vm.ModificationCardViewModel;
import com.eshed.fork.Modifications.vm.ModificationsViewModel;
import com.eshed.fork.R;
import com.eshed.fork.Recipe.view.RecipeActivity;
import com.eshed.fork.Settings.view.SettingsActivity;

public class ModificationsActivity extends AppCompatActivity implements ModificationsRecyclerViewAdapter.ModificationsAdapterHandler {
    private ModificationsViewModel vm;
    private ModificationsRecyclerViewAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_starred);
        setupTabBar();
        setupToolbar();
        DbRecipeRepository dbRepository = DbRecipeRepository.getInstance();
        Fork app = (Fork) getApplication();
        int parentRecipeID = getIntent().getExtras().getInt("recipe");
        vm = new ModificationsViewModel(dbRepository, parentRecipeID);
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

    public void setupTabBar() {
        Toolbar tabBar = this.findViewById(R.id.tab_bar);
        ImageView settingsButton = tabBar.findViewById(R.id.user_settings);
        ImageView homeButton = tabBar.findViewById(R.id.home);

        homeButton.setOnClickListener((View v)-> {
            Intent intent = new Intent(this, BrowseActivity.class);
            this.finish();
            this.startActivity(intent);
        });

        settingsButton.setOnClickListener((View v)-> {
            Intent intent = new Intent(this, SettingsActivity.class);
            this.startActivity(intent);
        });
    }

    private void initRecyclerView() {
        RecyclerView recyclerView = findViewById(R.id.recycler_view);
        recyclerView.setHasFixedSize(true);
        adapter = new ModificationsRecyclerViewAdapter(vm);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new GridLayoutManager(this, 1));
        ((ModificationsRecyclerViewAdapter) adapter).handler = this;
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
        title.setText("Available Modifications");
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
    public void selectRecipeCard(ModificationCardViewModel vm) {
        Intent intent = new Intent(this, RecipeActivity.class);
        intent.putExtra("recipe", vm.getRecipe().getRecipeID());
        this.startActivity(intent);
    }
}