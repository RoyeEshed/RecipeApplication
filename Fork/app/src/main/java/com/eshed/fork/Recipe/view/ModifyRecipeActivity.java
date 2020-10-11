package com.eshed.fork.Recipe.view;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.eshed.fork.Browse.view.BrowseActivity;
import com.eshed.fork.R;

public class ModifyRecipeActivity extends AppCompatActivity {
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

        TextView title = toolbar.findViewById(R.id.toolbar_title);
        title.setText("Modify Recipe 1");

        ImageView backButton = toolbar.findViewById(R.id.back_arrow);
        ImageView addButton = toolbar.findViewById(R.id.add_recipe);
        addButton.setVisibility(View.INVISIBLE);
        ImageView settingsButton = tabBar.findViewById(R.id.user_settings);
        ImageView starredRecipesButton = tabBar.findViewById(R.id.star);
        Button submitButton = findViewById(R.id.button);
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
            Toast.makeText(this, "TODO: settings button", Toast.LENGTH_SHORT).show();
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

    }
}
