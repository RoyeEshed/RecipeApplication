package com.eshed.fork.Recipe.view;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.eshed.fork.Browse.view.BrowseActivity;
import com.eshed.fork.Browse.vm.RecipeViewModel;
import com.eshed.fork.R;
import com.eshed.fork.Settings.SettingsActivity;
import com.eshed.fork.data.DebugRecipeRepository;
import com.eshed.fork.data.RecipeRepository;

public class RecipeActivity extends AppCompatActivity {
    private RecipeViewModel recipeVm;
    private RecipeRepository recipeRepository = DebugRecipeRepository.getInstance();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_recipe);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        if (this.getSupportActionBar() != null) {
            getSupportActionBar().setDisplayShowTitleEnabled(false);
        }

        Toolbar tabBar = findViewById(R.id.tab_bar);

        TextView title = toolbar.findViewById(R.id.toolbar_title);
        int recipeID = getIntent().getExtras().getInt("recipe");
        recipeVm = new RecipeViewModel(recipeID, recipeRepository);
        title.setText(recipeVm.getRecipe().getName());
        initRecipeData();

        ImageView backButton = toolbar.findViewById(R.id.back_arrow);
        ImageView addButton = toolbar.findViewById(R.id.add_recipe);
        ImageView settingsButton = tabBar.findViewById(R.id.user_settings);
        ImageView starredRecipesButton = tabBar.findViewById(R.id.star);
        ImageView homeButton = tabBar.findViewById(R.id.home);

        homeButton.setOnClickListener((View v)-> {
            Intent intent = new Intent(this, BrowseActivity.class);
            this.finish();
            this.startActivity(intent);
        });
        backButton.setOnClickListener((View v)-> {
            this.finish();
        });
        settingsButton.setOnClickListener((View v)-> {
            Intent intent = new Intent(this, SettingsActivity.class);
            this.startActivity(intent);
        });
        addButton.setOnClickListener((View v)-> {
            Intent intent = new Intent(this, ModifyRecipeActivity.class);
            intent.putExtra("recipe", recipeVm.getRecipe().getRecipeID());
            this.startActivity(intent);
        });
        starredRecipesButton.setOnClickListener((View v)-> {
            Toast.makeText(this, "TODO: starred recipes button", Toast.LENGTH_SHORT).show();
        });
    }

    private void initRecipeData() {
        TextView ingredients = findViewById(R.id.ingredients_input);
        ingredients.setText(recipeVm.getRecipe().getIngredients());
        TextView directions = findViewById(R.id.directions_input);
        directions.setText(recipeVm.getRecipe().getDirections());
        TextView tags = findViewById(R.id.tags_input);
        tags.setText(recipeVm.getRecipe().getTags());
    }
}
