package com.eshed.fork.Util;

import android.content.Intent;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.eshed.fork.Browse.view.BrowseActivity;
import com.eshed.fork.Favorites.view.FavoritesActivity;
import com.eshed.fork.R;
import com.eshed.fork.Recipe.view.NewRecipeActivity;
import com.eshed.fork.Settings.view.SettingsActivity;

import static android.view.View.GONE;

public class Util {
    public static void setupTabBar(AppCompatActivity activity) {
        Toolbar tabBar = activity.findViewById(R.id.tab_bar);
        ImageView settingsButton = tabBar.findViewById(R.id.user_settings);
        ImageView starredRecipesButton = tabBar.findViewById(R.id.star);
        ImageView homeButton = tabBar.findViewById(R.id.home);

        homeButton.setOnClickListener((View v)-> {
            Intent intent = new Intent(activity, BrowseActivity.class);
                activity.finish();
                activity.startActivity(intent);
        });

        settingsButton.setOnClickListener((View v)-> {
            Intent intent = new Intent(activity, SettingsActivity.class);
            activity.startActivity(intent);
        });

        starredRecipesButton.setOnClickListener((View v)-> {
            Intent intent = new Intent(activity, FavoritesActivity.class);
            activity.startActivity(intent);
        });
    }
}
