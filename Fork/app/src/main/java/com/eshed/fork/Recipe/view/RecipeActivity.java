package com.eshed.fork.Recipe.view;

import android.app.DialogFragment;
import android.app.Fragment;
import android.app.FragmentManager;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.eshed.fork.Browse.view.BrowseActivity;
import com.eshed.fork.Recipe.view.Dialogs.NewRecipeDialogFragment;
import com.eshed.fork.Recipe.view.Dialogs.NewRecipeDialogFragment.NewRecipeDialogListener;
import com.eshed.fork.Recipe.view.RecipeRecyclerViewAdapter.RecipeAdapterHandler;
import com.eshed.fork.Recipe.vm.RecipeViewModel;
import com.eshed.fork.R;
import com.eshed.fork.Settings.SettingsActivity;

import static androidx.recyclerview.widget.RecyclerView.*;

public class RecipeActivity extends AppCompatActivity implements RecipeAdapterHandler, NewRecipeDialogListener {
    private RecipeViewModel vm;
    private Toolbar tabBar;
    private Toolbar toolbar;
    private ImageView saveButton;
    private ImageView addButton;
    private TextView title;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recipe);

        int recipeID = getIntent().getExtras().getInt("recipe");
        vm = new RecipeViewModel(recipeID);

        toolbar = findViewById(R.id.toolbar);
        tabBar = findViewById(R.id.tab_bar);
        setupToolbar();
        setupTabBar();

        initRecyclerView();
    }

    private void setupToolbar() {
        setSupportActionBar(toolbar);
        if (this.getSupportActionBar() != null) {
            getSupportActionBar().setDisplayShowTitleEnabled(false);
        }

        title = toolbar.findViewById(R.id.toolbar_title);
        title.setText(vm.getRecipe().getName());
        ImageView backButton = toolbar.findViewById(R.id.back_arrow);
        addButton = toolbar.findViewById(R.id.add_recipe);
        saveButton = toolbar.findViewById(R.id.save_recipe);

        title.setOnClickListener((View v)-> {
            if (vm.isEditable()) {
                showNewRecipeDialog();
            }
        });
        addButton.setOnClickListener((View v)-> {
            showNewRecipeDialog();
            toggleEditing();
        });
        backButton.setOnClickListener((View v) -> {
            this.finish();
        });
        saveButton.setOnClickListener((View v) -> {
            vm.createNewRecipe(title.getText().toString());
            Toast.makeText(this, "TODO: save recipe", Toast.LENGTH_SHORT).show();
            toggleEditing();
        });
    }

    private void setupTabBar() {
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
        RecyclerView recyclerView = findViewById(R.id.recycler_view);
        recyclerView.setHasFixedSize(true);

        Adapter adapter = new RecipeRecyclerViewAdapter(vm);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new GridLayoutManager(this, 1));
        ((RecipeRecyclerViewAdapter) adapter).handler = this;

        recyclerView.addOnScrollListener(new OnScrollListener() {
            @Override
            public void onScrolled(@NonNull RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                View view = recyclerView.getChildAt(0);
                if (view != null && recyclerView.getChildAdapterPosition(view) == 0) {
                    ImageView imageView = view.findViewById(R.id.recipe_image);
                    imageView.setTranslationY(-view.getTop() / 2f);
                }
            }
        });
    }

    private void showNewRecipeDialog() {
        FragmentManager manager = getFragmentManager();
        Fragment frag = manager.findFragmentByTag("fragment_new_recipe");
        if (frag != null) {
            manager.beginTransaction().remove(frag).commit();
        }
        NewRecipeDialogFragment dialog = new NewRecipeDialogFragment();
        dialog.show(manager, "fragment_new_recipe");
    }

    private void toggleEditing() {
        if (vm.isEditable()) {
            vm.toggleEditable();
            addButton.setVisibility(View.VISIBLE);
            saveButton.setVisibility(View.GONE);
        } else {
            vm.toggleEditable();
            addButton.setVisibility(View.GONE);
            saveButton.setVisibility(View.VISIBLE);
        }
    }

    // region RecipeAdapterHandler methods
    @Override
    public void addIngredientComponent(RecipeViewModel vm) {
        vm.addIngredientComponent();
    }

    @Override
    public void addDirectionComponent(RecipeViewModel vm) {
        vm.addDirectionComponent();
    }

    @Override
    public void undoChanges(RecipeViewModel vm) {
        vm.reset();
        title.setText(vm.getRecipe().getName());
        toggleEditing();
    }
    // endregion

    // region NewRecipeDialogFragment listener methods
    @Override
    public void onDialogPositiveClick(DialogFragment dialog, String recipeName) {
        title = toolbar.findViewById(R.id.toolbar_title);
        title.setText(recipeName);
    }

    @Override
    public void onDialogNegativeClick(DialogFragment dialog) {
        toggleEditing();
    }
    // endregion
}

