package com.eshed.fork.Settings.view;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.eshed.fork.Browse.view.BrowseActivity;
import com.eshed.fork.R;
import com.eshed.fork.Util.Util;

public class SettingsActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_settings);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        if (this.getSupportActionBar() != null) {
            getSupportActionBar().setDisplayShowTitleEnabled(false);
        }

        TextView title = toolbar.findViewById(R.id.toolbar_title);
        title.setText("Settings");
        ImageView addButton = toolbar.findViewById(R.id.add_recipe);
        addButton.setVisibility(View.GONE);
        Util.setupTabBar(this);
    }
}
