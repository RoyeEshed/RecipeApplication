package com.eshed.fork.Recipe.view;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
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
import com.eshed.fork.R;
import com.eshed.fork.Settings.SettingsActivity;
import com.eshed.fork.data.model.Direction;
import com.eshed.fork.data.model.Ingredient;
import com.eshed.fork.data.model.Recipe;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static android.widget.LinearLayout.*;

public class NewRecipeActivity extends AppCompatActivity {
    private Context context;
    private View recipeForm;
    private LinearLayout ingredientsLayout;
    private LinearLayout directionsLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        context = this;

        setContentView(R.layout.activity_new_recipe);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        if (this.getSupportActionBar() != null) {
            getSupportActionBar().setDisplayShowTitleEnabled(false);
        }

        Toolbar tabBar = findViewById(R.id.tab_bar);
        recipeForm = findViewById(R.id.recipe_form);

        TextView title = toolbar.findViewById(R.id.toolbar_title);
        title.setText("Add New Recipe");
        ImageView backButton = toolbar.findViewById(R.id.back_arrow);
        ImageView addButton = toolbar.findViewById(R.id.add_recipe);
        addButton.setVisibility(View.INVISIBLE);
        Button submitButton = findViewById(R.id.button);
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

        });
        starredRecipesButton.setOnClickListener((View v)-> {
            Toast.makeText(this, "TODO: starred recipes button", Toast.LENGTH_SHORT).show();
        });
        submitButton.setOnClickListener((View v)-> {
            submitRecipe();
            Toast.makeText(this, "Recipe Submitted", Toast.LENGTH_SHORT).show();
            Intent intent = new Intent(this, BrowseActivity.class);
            this.finish();
            this.startActivity(intent);
        });

        ImageView addIngredientButton = recipeForm.findViewById(R.id.add_ingredient);
        ImageView addDirectionsButton = recipeForm.findViewById(R.id.add_directions);
        addIngredientButton.setOnClickListener(new AddIngredientClickListener());
        addDirectionsButton.setOnClickListener(new AddDirectionsClickListener());
    }

    private void submitRecipe() {
        EditText contributorTextView = recipeForm.findViewById(R.id.contributor_input);
        String contributor = contributorTextView.getText().toString();
        EditText tagsTextView = recipeForm.findViewById(R.id.tags_input);
        String[] tags = tagsTextView.getText().toString().split(",");

        LinearLayout ingredientsLayout = recipeForm.findViewById(R.id.list_of_ingredients);
        LinearLayout directionsLayout = recipeForm.findViewById(R.id.list_of_directions);
        List<Direction> directions = new ArrayList<>();
        List<Ingredient> ingredients = new ArrayList<>();
        // grab input from cooking directions textviews
        for(int index = 0; index < directionsLayout.getChildCount(); index++) {
            View nextChild = directionsLayout.getChildAt(index);
            String direction = ((EditText)((ViewGroup) nextChild).getChildAt(1)).getText().toString();
            directions.add(new Direction(index, direction));
        }
        // grab input from ingredients textviews
        for(int index = 0; index < ingredientsLayout.getChildCount(); index++) {
            View nextChild = ingredientsLayout.getChildAt(index);
            String amount = ((EditText)((ViewGroup) nextChild).getChildAt(0)).getText().toString();
            String ingredient = ((EditText)((ViewGroup) nextChild).getChildAt(1)).getText().toString();
            ingredients.add(new Ingredient(amount, ingredient));
        }

        Recipe recipe = new Recipe(-1, "", -1, contributor, ingredients, directions, Arrays.asList(tags));
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


