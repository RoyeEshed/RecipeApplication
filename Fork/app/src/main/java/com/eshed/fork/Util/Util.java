package com.eshed.fork.Util;

import android.content.Intent;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.eshed.fork.Browse.view.BrowseActivity;
import com.eshed.fork.R;
import com.eshed.fork.Recipe.view.NewRecipeActivity;
import com.eshed.fork.Settings.view.SettingsActivity;

import static android.view.View.GONE;
import static android.view.View.VISIBLE;

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
            Toast.makeText(activity, "TODO: starred recipes button", Toast.LENGTH_SHORT).show();
        });
    }

    public static void setupToolbar(AppCompatActivity activity, String toolarTitle) {
        Toolbar toolbar = activity.findViewById(R.id.toolbar);

        activity.setSupportActionBar(toolbar);
        if (activity.getSupportActionBar() != null) {
            activity.getSupportActionBar().setDisplayShowTitleEnabled(false);
        }

        TextView title = toolbar.findViewById(R.id.toolbar_title);
        title.setText(toolarTitle);

        ImageView backButton = toolbar.findViewById(R.id.back_arrow);
        ImageView addButton = toolbar.findViewById(R.id.add_recipe);
        //TextView logout = toolbar.findViewById(R.id.log_out);

        backButton.setVisibility(GONE);
//        logout.setVisibility(VISIBLE);
//        logout.setOnClickListener((View v)-> {
//            Toast.makeText(activity, "TODO: logout", Toast.LENGTH_SHORT).show();
//
//        });
        addButton.setOnClickListener((View v)-> {
            Intent intent = new Intent(activity, NewRecipeActivity.class);
            activity.startActivity(intent);
        });
    }

}
