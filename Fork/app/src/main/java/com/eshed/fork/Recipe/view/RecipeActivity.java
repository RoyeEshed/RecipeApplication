package com.eshed.fork.Recipe.view;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.eshed.fork.Browse.view.BrowseActivity;
import com.eshed.fork.Browse.view.BrowseRecyclerViewAdapter;
import com.eshed.fork.Recipe.vm.component.RecipeComponentViewModel;
import com.eshed.fork.Recipe.vm.RecipeViewModel;
import com.eshed.fork.R;
import com.eshed.fork.Settings.SettingsActivity;

import java.time.Duration;
import java.util.List;

public class RecipeActivity extends AppCompatActivity implements RecipeRecyclerViewAdapter.RecipeAdapterHandler {
    private RecipeViewModel vm;
    private Toolbar tabBar;
    private Toolbar toolbar;
    private RecyclerView.Adapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recipe);

        int recipeID = getIntent().getExtras().getInt("recipe");
        vm = new RecipeViewModel(recipeID);

        toolbar = findViewById(R.id.toolbar);
        setupToolbar();

        tabBar = findViewById(R.id.tab_bar);
        setupTabBar();
        initRecyclerView();
    }

    private void setupToolbar() {
        setSupportActionBar(toolbar);
        if (this.getSupportActionBar() != null) {
            getSupportActionBar().setDisplayShowTitleEnabled(false);
        }

        TextView title = toolbar.findViewById(R.id.toolbar_title);
        title.setText(vm.getRecipe().getName());
        ImageView backButton = toolbar.findViewById(R.id.back_arrow);
        ImageView addButton = toolbar.findViewById(R.id.add_recipe);
        ImageView saveButton = toolbar.findViewById(R.id.save_recipe);

        addButton.setOnClickListener((View v)-> {
            vm.toggleEditable();
            addButton.setVisibility(View.GONE);
            saveButton.setVisibility(View.VISIBLE);
        });
        backButton.setOnClickListener((View v) -> {
            this.finish();
        });
        saveButton.setOnClickListener((View v) -> {
            Toast.makeText(this, "TODO: Save recipe", Toast.LENGTH_SHORT).show();
        });
    }

    private void setupTabBar() {
        ImageView settingsButton = tabBar.findViewById(R.id.user_settings);
        ImageView starredRecipesButton = tabBar.findViewById(R.id.star);
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
        starredRecipesButton.setOnClickListener((View v)-> {
            Toast.makeText(this, "TODO: starred recipes button", Toast.LENGTH_SHORT).show();
        });
    }

    private void initRecyclerView() {
        RecyclerView recyclerView = findViewById(R.id.recycler_view);
        recyclerView.setHasFixedSize(true);

        adapter = new RecipeRecyclerViewAdapter(this, vm);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new GridLayoutManager(this, 1));
        ((RecipeRecyclerViewAdapter) adapter).handler = this;
    }

    @Override
    public void addIngredientComponent(RecipeViewModel vm) {
        vm.addIngredientComponent();
    }

    @Override
    public void addDirectionComponent(RecipeViewModel vm) {
        vm.addDirectionComponent();
    }
}
