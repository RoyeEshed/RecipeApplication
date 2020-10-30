package com.eshed.fork.Recipe.view;

import android.app.DialogFragment;
import android.app.Fragment;
import android.app.FragmentManager;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.EditorInfo;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.eshed.fork.Fork;
import com.eshed.fork.R;
import com.eshed.fork.Recipe.view.Dialogs.NewRecipeDialogFragment;
import com.eshed.fork.Recipe.view.Dialogs.NewRecipeDialogFragment.NewRecipeDialogListener;
import com.eshed.fork.Recipe.view.RecipeRecyclerViewAdapter.RecipeAdapterHandler;
import com.eshed.fork.Recipe.vm.RecipeViewModel;
import com.eshed.fork.Util.Util;
import com.eshed.fork.Data.DebugRecipeRepository;
import com.eshed.fork.Data.model.Recipe;

import java.util.ArrayList;
import java.util.List;

import static androidx.recyclerview.widget.RecyclerView.OnScrollListener;

public class RecipeActivity extends AppCompatActivity implements RecipeAdapterHandler, NewRecipeDialogListener {
    private RecipeViewModel originalViewModel;
    private RecipeViewModel vm;
    private Toolbar toolbar;
    private ImageView saveButton;
    private ImageView addButton;
    private TextView title;
    private RecipeRecyclerViewAdapter adapter;
    private Spinner spinner;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recipe);
        Fork app = (Fork) getApplication();

        int recipeID = getIntent().getExtras().getInt("recipe");

        vm = new RecipeViewModel(
                app.getRepository(),
                app.getEdamamService(),
                recipeID
        );

        originalViewModel = vm;
        toolbar = findViewById(R.id.toolbar);
        setupToolbar();
        Util.setupTabBar(this);
        initRecyclerView();
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
        title.setText(vm.getRecipe().getName());
        addButton = toolbar.findViewById(R.id.add_recipe);
        saveButton = toolbar.findViewById(R.id.save_recipe);


        title.setOnClickListener((View v) -> {
            if (vm.isEditable()) {
                showNewRecipeDialog();
            }
        });
        addButton.setOnClickListener((View v) -> {
            showNewRecipeDialog();
        });
        saveButton.setOnClickListener((View v) -> {
            DebugRecipeRepository.getInstance().saveRecipe(vm.getRecipe());
            toggleEditing();
        });
        //initSpinner();
    }

//    private void initSpinner() {
//        List<String> options = new ArrayList<>();
//        options.add("");
//        options.add("Create new modification");
//        options.add("View modification history");
//
//        spinner = (Spinner) toolbar.findViewById(R.id.drop_down);
//        spinner.setVisibility(View.VISIBLE);
//        ArrayAdapter<String> adapter = new ArrayAdapter<String>(getApplicationContext(), android.R.layout.simple_spinner_item, options) {
//            @Override
//            public View getView(int position, View convertView, ViewGroup parent) {
//                View view = super.getView(position, convertView, parent);
//                view.setVisibility(View.GONE);
//
//                return view;
//            }
//        };
//        spinner.setAdapter(adapter);
//        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
//            @Override
//            public void onItemSelected(AdapterView<?> adapterView, View view, int position, long l) {
//                if (position == 1) {
//                    showNewRecipeDialog();
//                } else if (position == 2) {
//                    Toast.makeText(RecipeActivity.this, "TODO: Modification history", Toast.LENGTH_SHORT).show();
//                }
//            }
//
//            @Override
//            public void onNothingSelected(AdapterView<?> adapterView) {
//            }
//        });
//    }

    private void initRecyclerView() {
        RecyclerView recyclerView = findViewById(R.id.recycler_view);
        recyclerView.setHasFixedSize(true);

        adapter = new RecipeRecyclerViewAdapter(vm);
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
        vm.toggleEditable();
        if (!vm.isEditable()) {
            addButton.setVisibility(View.VISIBLE);
            saveButton.setVisibility(View.GONE);
        } else {
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
    public void cancelChanges(RecipeViewModel vm) {
        vm = originalViewModel;
        adapter.setViewModel(vm);
        title.setText(vm.getRecipe().getName());
        toggleEditing();
    }
    // endregion

    // region NewRecipeDialogFragment listener methods
    @Override
    public void onDialogPositiveClick(DialogFragment dialog, String recipeName) {
        title = toolbar.findViewById(R.id.toolbar_title);
        title.setText(recipeName);

        Recipe recipe = DebugRecipeRepository.getInstance().createNewRecipeFromRecipe(vm.getRecipe(), recipeName);
        vm = new RecipeViewModel(recipe);
        adapter.setViewModel(vm);
        toggleEditing();
    }

    @Override
    public void onDialogNegativeClick(DialogFragment dialog) {
        //
    }

    // endregion
}

