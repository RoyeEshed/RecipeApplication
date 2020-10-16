package com.eshed.fork.Recipe.view;

import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.constraintlayout.widget.ConstraintLayout;

import com.eshed.fork.Browse.view.BrowseActivity;
import com.eshed.fork.Browse.vm.RecipeViewModel;
import com.eshed.fork.R;
import com.eshed.fork.Settings.SettingsActivity;
import com.eshed.fork.data.DebugRecipeRepository;
import com.eshed.fork.data.RecipeRepository;
import com.eshed.fork.data.model.Ingredient;

import java.util.List;

import static android.widget.LinearLayout.HORIZONTAL;

public class RecipeActivity extends AppCompatActivity {
    private RecipeViewModel recipeVm;
    private RecipeRepository recipeRepository = DebugRecipeRepository.getInstance();
    private View recipeForm;

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

        recipeForm = findViewById(R.id.recipe_form);
        recipeForm.findViewById(R.id.add_ingredient).setVisibility(View.GONE);
        recipeForm.findViewById(R.id.add_directions).setVisibility(View.GONE);

        ImageView backButton = toolbar.findViewById(R.id.back_arrow);
        ImageView addButton = toolbar.findViewById(R.id.add_recipe);
        ImageView settingsButton = tabBar.findViewById(R.id.user_settings);
        ImageView starredRecipesButton = tabBar.findViewById(R.id.star);
        ImageView homeButton = tabBar.findViewById(R.id.home);
        initRecipeData();

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
        initContributor();
        initDirections();
        initIngredients();
        initTags();
    }

    private void initContributor() {
        EditText contributorTextView = recipeForm.findViewById(R.id.contributor_input);
        contributorTextView.setText(recipeVm.getRecipe().getContributor());
        lockEditText(contributorTextView);
    }

    private void initIngredients() {
        LinearLayout ingredientsLayout = recipeForm.findViewById(R.id.list_of_ingredients);
        LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);
        lp.setMargins(0,0,0, 10);
        String stepNumber = (ingredientsLayout.getChildCount() + 1) + ". ";
        List<Ingredient> ingredients = recipeVm.getRecipe().getIngredients();
        ingredientsLayout.getChildAt(0).setVisibility(View.GONE);

        for (int i = 0; i < ingredients.size(); i++) {
            Ingredient currentIngredient = ingredients.get(i);
            LinearLayout layout = new LinearLayout(this);
            layout.setLayoutParams(lp);
            layout.setOrientation(HORIZONTAL);

            TextView ingredientAmount = new TextView(this);
            ingredientAmount.setLayoutParams(lp);
            ingredientAmount.setText(stepNumber);
            ingredientAmount.setTextSize(20);
            ingredientAmount.setTypeface(ingredientAmount.getTypeface(), Typeface.BOLD);
            ingredientAmount.setTextColor(this.getResources().getColor(R.color.black));
            ingredientAmount.setText(currentIngredient.getAmount() + " \t");

            TextView ingredientName = new TextView(this);
            ingredientName.setSingleLine(false);
            ingredientName.setImeOptions(EditorInfo.IME_FLAG_NO_ENTER_ACTION);
            ingredientName.setLayoutParams(lp);
            ingredientName.setText(currentIngredient.getIngredientName());
            ingredientName.setTextColor(this.getResources().getColor(R.color.black));
            ingredientName.setTextSize(20);

            layout.addView(ingredientAmount);
            layout.addView(ingredientName);
            ingredientsLayout.addView(layout);
        }
    }

    private void initDirections() {
        LinearLayout directionsLayout = recipeForm.findViewById(R.id.list_of_directions);

        LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);


        lp.setMargins(0,0,0, 10);
        String stepNumber = (directionsLayout.getChildCount() + 1) + ". ";
        List<String> directions = recipeVm.getRecipe().getDirections();
        directionsLayout.getChildAt(0).setVisibility(View.GONE);
        for (int i = 0; i < directions.size(); i++) {
            LinearLayout layout = new LinearLayout(this);
            layout.setLayoutParams(lp);
            layout.setOrientation(HORIZONTAL);

            TextView directionNumber = new TextView(this);
            directionNumber.setLayoutParams(lp);
            directionNumber.setText(stepNumber);
            directionNumber.setTextSize(20);
            directionNumber.setTextColor(this.getResources().getColor(R.color.colorPrimaryDark));
            String n = i + 1 + ". ";
            directionNumber.setText(n);
            TextView directionText = new TextView(this);

            directionText.setSingleLine(false);
            directionText.setImeOptions(EditorInfo.IME_FLAG_NO_ENTER_ACTION);
            directionText.setLayoutParams(lp);
            directionText.setText(directions.get(i));
            directionText.setTextColor(this.getResources().getColor(R.color.black));
            directionText.setTextSize(20);

            layout.addView(directionNumber);
            layout.addView(directionText);
            directionsLayout.addView(layout);
        }
    }

    private void initTags() {
        EditText tagsTextView = recipeForm.findViewById(R.id.tags_input);
        List<String> tags = recipeVm.getRecipe().getTags();
        String str = "";

        for (int i = 0; i < tags.size() - 1; i++) {
            str += ", " + tags.get(i);
        }
        str += tags.get(tags.size() - 1);

        tagsTextView.setText(str);
        lockEditText(tagsTextView);
    }

    private void lockEditText(EditText editText) {
        editText.setTextColor(this.getResources().getColor(R.color.black));
        editText.setEnabled(false);
        editText.setFocusable(false);
        editText.setBackgroundResource(android.R.color.transparent);
    }
}
