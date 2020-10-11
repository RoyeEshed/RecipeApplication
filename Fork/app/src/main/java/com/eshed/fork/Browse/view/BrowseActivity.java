package com.eshed.fork.Browse.view;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.eshed.fork.Browse.vm.BrowseViewModel;
import com.eshed.fork.Browse.vm.RecipeViewModel;
import com.eshed.fork.R;
import com.eshed.fork.Recipe.view.NewRecipeActivity;
import com.eshed.fork.Recipe.view.RecipeActivity;
import com.eshed.fork.Settings.SettingsActivity;
import com.eshed.fork.data.DebugRecipeRepository;
import com.eshed.fork.data.RecipeRepository;

import static androidx.recyclerview.widget.RecyclerView.*;

public class BrowseActivity extends AppCompatActivity implements RecipeRecyclerViewAdapter.RecipeAdapterHandler{
    private BrowseViewModel vm;
    private ConstraintLayout searchBar;

    private RecipeRepository recipeRepository = DebugRecipeRepository.getInstance();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_browse);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        if (this.getSupportActionBar() != null) {
            getSupportActionBar().setDisplayShowTitleEnabled(false);
        }

        Toolbar tabBar = findViewById(R.id.tab_bar);
        TextView title = toolbar.findViewById(R.id.toolbar_title);
        title.setText("Browse");

        ImageView backButton = toolbar.findViewById(R.id.back_arrow);
        ImageView addButton = toolbar.findViewById(R.id.add_recipe);
        ImageView settingsButton = tabBar.findViewById(R.id.user_settings);
        ImageView starredRecipesButton = tabBar.findViewById(R.id.star);
        searchBar = findViewById(R.id.search_bar);
        EditText searchInput = searchBar.findViewById(R.id.edit_search);

        backButton.setOnClickListener((View v)-> {
            Toast.makeText(this, "TODO: back button", Toast.LENGTH_SHORT).show();
        });
        settingsButton.setOnClickListener((View v)-> {
            Intent intent = new Intent(this, SettingsActivity.class);
            this.startActivity(intent);
        });
        addButton.setOnClickListener((View v)-> {
            Intent intent = new Intent(this, NewRecipeActivity.class);
            this.startActivity(intent);
        });
        starredRecipesButton.setOnClickListener((View v)-> {
            Toast.makeText(this, "TODO: starred recipes button", Toast.LENGTH_SHORT).show();
        });

        searchInput.setOnClickListener(new SearchBarClickListener());

        vm = new BrowseViewModel(recipeRepository);
        initRecyclerView();
    }

    private void initRecyclerView() {
        RecyclerView recyclerView = findViewById(R.id.recycler_view);
        recyclerView.setHasFixedSize(true);
        Adapter adapter = new RecipeRecyclerViewAdapter(this, vm);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new GridLayoutManager(this, 1));

        ((RecipeRecyclerViewAdapter) adapter).handler = this;
    }

    @Override
    public void selectRecipeCard(RecipeViewModel vm) {
        Intent intent = new Intent(this, RecipeActivity.class);
        intent.putExtra("recipe", vm.getRecipe().getRecipeID());
        this.startActivity(intent);
    }

    private class SearchBarClickListener implements OnClickListener {

        @Override
        public void onClick(View view) {
            LinearLayout sortOptions = searchBar.findViewById(R.id.sort_options);
            if (sortOptions.getVisibility() == View.VISIBLE) {
                sortOptions.setVisibility(View.GONE);
            } else {
                sortOptions.setVisibility(View.VISIBLE);
            }
        }
    }
}

