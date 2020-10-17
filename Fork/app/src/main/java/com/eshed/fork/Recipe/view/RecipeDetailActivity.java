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
import com.eshed.fork.Recipe.vm.component.RecipeInformation;
import com.eshed.fork.Recipe.vm.RecipeViewModel;
import com.eshed.fork.R;
import com.eshed.fork.Settings.SettingsActivity;
import com.eshed.fork.data.DebugRecipeRepository;
import com.eshed.fork.data.RecipeRepository;

import java.util.List;

public class RecipeDetailActivity extends AppCompatActivity {
    private RecipeViewModel vm;
    private RecipeRepository recipeRepository = DebugRecipeRepository.getInstance();
    private View recipeForm;
    private Toolbar tabBar;
    private RecyclerView.Adapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recipe);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        if (this.getSupportActionBar() != null) {
            getSupportActionBar().setDisplayShowTitleEnabled(false);
        }

        int recipeID = getIntent().getExtras().getInt("recipe");
        vm = new RecipeViewModel(recipeID);

        TextView title = toolbar.findViewById(R.id.toolbar_title);
        title.setText(vm.getRecipe().getName());
        ImageView backButton = toolbar.findViewById(R.id.back_arrow);
        ImageView addButton = toolbar.findViewById(R.id.add_recipe);
        backButton.setOnClickListener((View v)-> {
            this.finish();
        });
        addButton.setOnClickListener((View v)-> {
            vm.toggleEditable();
        });

        setupTabBar();
        initRecyclerView();
    }


    private void setupTabBar() {
        tabBar = findViewById(R.id.tab_bar);
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
        List<RecipeInformation> recipeComponents = vm.getComponents();
        RecyclerView recyclerView = findViewById(R.id.recycler_view);
        recyclerView.setHasFixedSize(true);

        adapter = new RecipeRecyclerViewAdapter(this, vm);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new GridLayoutManager(this, 1));
    }
}
