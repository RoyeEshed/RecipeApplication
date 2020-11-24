package com.eshed.fork.Recipe.view;

import android.app.DialogFragment;
import android.app.Fragment;
import android.app.FragmentManager;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.eshed.fork.Browse.view.BrowseActivity;
import com.eshed.fork.Data.DbRecipeRepository;
import com.eshed.fork.R;
import com.eshed.fork.Recipe.view.Dialogs.NewRecipeDialogFragment;
import com.eshed.fork.Recipe.view.Dialogs.NewRecipeDialogFragment.NewRecipeDialogListener;
import com.eshed.fork.Recipe.view.RecipeRecyclerViewAdapter.RecipeAdapterHandler;
import com.eshed.fork.Recipe.vm.RecipeViewModel;
import com.eshed.fork.Util.Util;
import com.eshed.fork.Data.DebugRecipeRepository;

import static android.widget.LinearLayout.GONE;
import static android.widget.LinearLayout.VISIBLE;

public class NewRecipeActivity extends AppCompatActivity implements RecipeAdapterHandler, NewRecipeDialogListener {
    private Toolbar toolbar;
    private ImageView addButton;
    private ImageView saveButton;
    private ImageView forkButton;
    private TextView title;
    private RecyclerView.Adapter adapter;
    private RecipeViewModel vm;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_new_recipe);

        toolbar = findViewById(R.id.toolbar);
        setupToolbar();
        Util.setupTabBar(this);

//        vm = new RecipeViewModel(DebugRecipeRepository.getInstance().createNewRecipe());
        vm = new RecipeViewModel(DbRecipeRepository.getInstance().createNewRecipe());

        initRecyclerView();
        vm.toggleEditable();
        showNewRecipeDialog();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                this.finish();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    private void setupToolbar() {
        setSupportActionBar(toolbar);
        if (this.getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setHomeAsUpIndicator(R.drawable.ic_baseline_arrow_back_24);
            getSupportActionBar().setDisplayShowTitleEnabled(false);
        }

        title = toolbar.findViewById(R.id.toolbar_title);
        title.setText("Add New Recipe");
        addButton = toolbar.findViewById(R.id.add_recipe);
        forkButton = toolbar.findViewById(R.id.fork_recipe);
        saveButton = toolbar.findViewById(R.id.save_recipe);
        forkButton.setVisibility(GONE);
        addButton.setVisibility(GONE);
        saveButton.setVisibility(VISIBLE);

        title.setOnClickListener((View v)-> {
            if (vm.isEditable()) {
                showNewRecipeDialog();
            }
        });
        saveButton.setOnClickListener((View v) -> {
            //vm.getRecipe().s
//            DebugRecipeRepository.getInstance().saveRecipe(vm.getRecipe());
            DbRecipeRepository.getInstance().saveRecipe(vm.getRecipe());
            this.finish();
        });
    }

    private void initRecyclerView() {
        RecyclerView recyclerView = findViewById(R.id.recycler_view);
        recyclerView.setHasFixedSize(true);

        adapter = new RecipeRecyclerViewAdapter(vm);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new GridLayoutManager(this, 1));
        ((RecipeRecyclerViewAdapter) adapter).handler = this;

        recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
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

    public void showNewRecipeDialog() {
        FragmentManager manager = getFragmentManager();
        Fragment frag = manager.findFragmentByTag("fragment_new_recipe");
        if (frag != null) {
            manager.beginTransaction().remove(frag).commit();
        }
        NewRecipeDialogFragment dialog = new NewRecipeDialogFragment();
        dialog.show(manager, "fragment_new_recipe");
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
    public void cancelChanges(RecipeViewModel vm) {
        Intent intent = new Intent(this, BrowseActivity.class);
        this.finish();
        this.startActivity(intent);
    }
    // endregion

    // region NewRecipeDialogFragment listener methods
    @Override
    public void onDialogPositiveClick(DialogFragment dialog, String recipeName) {
        title = toolbar.findViewById(R.id.toolbar_title);
        title.setText(recipeName);

        addButton.setVisibility(View.GONE);
        saveButton.setVisibility(View.VISIBLE);
    }

    @Override
    public void onDialogNegativeClick(DialogFragment dialog) {}
    // endregion
}