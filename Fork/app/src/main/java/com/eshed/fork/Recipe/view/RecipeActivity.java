package com.eshed.fork.Recipe.view;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import com.eshed.fork.R;

public class RecipeActivity extends AppCompatActivity {
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
        String recipeName = getIntent().getExtras().getString("recipe");
        title.setText(recipeName);

        ImageView backArrow = toolbar.findViewById(R.id.back_arrow);
        ImageView userSetting = toolbar.findViewById(R.id.user_settings);
        ImageView starredRecipes = tabBar.findViewById(R.id.star);
        ImageView search = tabBar.findViewById(R.id.search);

        backArrow.setOnClickListener((View v)-> {
            this.finish();
        });
        userSetting.setOnClickListener((View v)-> {
            Toast.makeText(this, "TODO: settings button", Toast.LENGTH_SHORT).show();
        });
        search.setOnClickListener((View v)-> {
            Toast.makeText(this, "TODO: search button", Toast.LENGTH_SHORT).show();
        });
        starredRecipes.setOnClickListener((View v)-> {
            Toast.makeText(this, "TODO: starred recipes button", Toast.LENGTH_SHORT).show();
        });
    }
}
