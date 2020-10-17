package com.eshed.fork.Recipe.view;

import android.content.Context;
import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.eshed.fork.Browse.view.BrowseActivity;
import com.eshed.fork.Recipe.vm.RecipeViewModel;
import com.eshed.fork.R;
import com.eshed.fork.Settings.SettingsActivity;
import com.eshed.fork.data.DebugRecipeRepository;
import com.eshed.fork.data.RecipeRepository;
import com.eshed.fork.data.model.Direction;
import com.eshed.fork.data.model.Ingredient;

import java.util.List;

import static android.widget.LinearLayout.HORIZONTAL;

public class ModifyRecipeActivity extends AppCompatActivity {
    private RecipeViewModel recipeVm;
    private View recipeForm;
    private Context context;
    private RecipeRepository recipeRepository = DebugRecipeRepository.getInstance();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_modify_recipe);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        if (this.getSupportActionBar() != null) {
            getSupportActionBar().setDisplayShowTitleEnabled(false);
        }

        Toolbar tabBar = findViewById(R.id.tab_bar);
        recipeForm = findViewById(R.id.recipe_form);
        context = this;

        TextView title = toolbar.findViewById(R.id.toolbar_title);
        int recipeID = getIntent().getExtras().getInt("recipe");
        recipeVm = new RecipeViewModel(recipeID);
        title.setText(recipeVm.getRecipe().getName());

        ImageView backButton = toolbar.findViewById(R.id.back_arrow);
        ImageView addButton = toolbar.findViewById(R.id.add_recipe);
        addButton.setVisibility(View.INVISIBLE);
        ImageView settingsButton = tabBar.findViewById(R.id.user_settings);
        ImageView starredRecipesButton = tabBar.findViewById(R.id.star);
        Button submitButton = findViewById(R.id.button);
        ImageView homeButton = tabBar.findViewById(R.id.home);

        ImageView addIngredientButton = recipeForm.findViewById(R.id.add_ingredient);
        ImageView addDirectionsButton = recipeForm.findViewById(R.id.add_directions);
        addIngredientButton.setOnClickListener(new ModifyRecipeActivity.AddIngredientClickListener());
        addDirectionsButton.setOnClickListener(new ModifyRecipeActivity.AddDirectionsClickListener());

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

        starredRecipesButton.setOnClickListener((View v)-> {
            Toast.makeText(this, "TODO: starred recipes button", Toast.LENGTH_SHORT).show();
        });
        submitButton.setOnClickListener((View v)-> {
            EditText ingredients = findViewById(R.id.ingredients_input);
            Log.d("Ingredients:", ingredients.getText().toString());
            EditText directions = findViewById(R.id.directions_input);
            Log.d("Directions:", directions.getText().toString());
            EditText tags = findViewById(R.id.tags_input);
            Log.d("Tags:", tags.getText().toString());
            Toast.makeText(this, "Recipe Submitted", Toast.LENGTH_SHORT).show();
            Intent intent = new Intent(this, BrowseActivity.class);
            this.startActivity(intent);
            this.finish();
        });
        initDirections();
        initIngredients();
        initTags();
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

            EditText ingredientAmount = new EditText(this);
            ingredientAmount.setLayoutParams(lp);
            ingredientAmount.setText(stepNumber);
            ingredientAmount.setTextSize(20);
            ingredientAmount.setTypeface(ingredientAmount.getTypeface(), Typeface.BOLD);
            ingredientAmount.setTextColor(this.getResources().getColor(R.color.black));
            ingredientAmount.setText(currentIngredient.getAmount() + " \t");

            EditText ingredientName = new EditText(this);
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
        List<Direction> directions = recipeVm.getRecipe().getDirections();
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

            EditText directionText = new EditText(this);
            directionText.setSingleLine(false);
            directionText.setImeOptions(EditorInfo.IME_FLAG_NO_ENTER_ACTION);
            directionText.setLayoutParams(lp);
            directionText.setText(directions.get(i).getDirectionText());
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
    }

    private class AddIngredientClickListener implements View.OnClickListener {
        LinearLayout ingredientsLayout = recipeForm.findViewById(R.id.list_of_ingredients);
        LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);
        @Override
        public void onClick(View view) {
            LinearLayout layout = new LinearLayout(context);
            layout.setLayoutParams(lp);
            layout.setOrientation(HORIZONTAL);
            lp.setMargins(0,0,0, 10);

            EditText measurement = new EditText(context);
            measurement.setLayoutParams(lp);
            measurement.setHint("1 cup");
            measurement.setTextSize(20);

            EditText ingredient = new EditText(context);
            ingredient.setLayoutParams(lp);
            ingredient.setHint("Enter Ingredient");
            ingredient.setTextSize(20);
            layout.addView(measurement);
            layout.addView(ingredient);

            ingredientsLayout.addView(layout);
        }
    }

    private class AddDirectionsClickListener implements View.OnClickListener {
        LinearLayout directionsLayout = recipeForm.findViewById(R.id.list_of_directions);
        LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);
        @Override
        public void onClick(View view) {
            LinearLayout layout = new LinearLayout(context);
            layout.setLayoutParams(lp);
            layout.setOrientation(HORIZONTAL);
            lp.setMargins(0,0,0, 10);

            String stepNumber = (directionsLayout.getChildCount() + 1) + ". ";

            TextView step = new TextView(context);
            step.setLayoutParams(lp);
            step.setText(stepNumber);
            step.setTextSize(20);
            step.setTextColor(context.getResources().getColor(R.color.colorPrimaryDark));

            EditText ingredient = new EditText(context);
            ingredient.setSingleLine(false);
            ingredient.setImeOptions(EditorInfo.IME_FLAG_NO_ENTER_ACTION);
            ingredient.setLayoutParams(lp);
            ingredient.setHint("Enter Next Instruction");
            ingredient.setTextSize(20);
            layout.addView(step);
            layout.addView(ingredient);

            directionsLayout.addView(layout);
        }
    }
}
