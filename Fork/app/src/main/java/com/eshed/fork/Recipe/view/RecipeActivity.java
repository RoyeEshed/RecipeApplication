package com.eshed.fork.Recipe.view;

import android.app.Activity;
import android.app.DialogFragment;
import android.app.Fragment;
import android.app.FragmentManager;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.eshed.fork.Browse.view.BrowseActivity;
import com.eshed.fork.Data.DbRecipeRepository;
import com.eshed.fork.Data.model.Recipe;
import com.eshed.fork.Fork;
import com.eshed.fork.R;
import com.eshed.fork.Recipe.ViewOptions.view.HistoryActivity;
import com.eshed.fork.Recipe.ViewOptions.view.ModificationsActivity;
import com.eshed.fork.Recipe.view.Dialogs.NewRecipeDialogFragment;
import com.eshed.fork.Recipe.view.Dialogs.NewRecipeDialogFragment.NewRecipeDialogListener;
import com.eshed.fork.Recipe.view.Dialogs.RecipeOptionsDialogFragment;
import com.eshed.fork.Recipe.view.Dialogs.RecipeOptionsDialogFragment.RecipeOptionsDialogListener;
import com.eshed.fork.Recipe.view.RecipeRecyclerViewAdapter.RecipeAdapterHandler;
import com.eshed.fork.Recipe.vm.RecipeViewModel;
import com.eshed.fork.Settings.view.SettingsActivity;
import com.eshed.fork.StarredRecipes.view.StarredRecipesActivity;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.io.IOException;
import java.util.UUID;

import static androidx.recyclerview.widget.RecyclerView.OnScrollListener;

public class RecipeActivity extends AppCompatActivity implements RecipeAdapterHandler, NewRecipeDialogListener, RecipeOptionsDialogListener {
    private RecipeViewModel originalViewModel;
    private RecipeViewModel vm;
    private Toolbar toolbar;
    private ImageView saveButton;
    private ImageView addButton;
    private ImageView forkButton;
    private TextView title;
    private RecipeRecyclerViewAdapter adapter;
    private Spinner spinner;
    private Fork app;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_recipe);
        app = (Fork) getApplication();

        int recipeID = getIntent().getExtras().getInt("recipe");

        vm = new RecipeViewModel(
                DbRecipeRepository.getInstance(),
                app.getEdamamService(),
                recipeID,
                app.getUid()
        );

        originalViewModel = vm;
        toolbar = findViewById(R.id.toolbar);
        setupToolbar();
        setupTabBar();
        initRecyclerView();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_recipe, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                this.finish();
                return true;
            case R.id.mode_options_icon:
                showViewOptionsDialog();
            return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    public void setupTabBar() {
        Toolbar tabBar = this.findViewById(R.id.tab_bar);
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
            Intent intent = new Intent(this, StarredRecipesActivity.class);
            this.startActivity(intent);
        });
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
        addButton.setVisibility(View.GONE);
        forkButton = toolbar.findViewById(R.id.fork_recipe);
        saveButton = toolbar.findViewById(R.id.save_recipe);


        title.setOnClickListener((View v) -> {
            if (vm.isEditable()) {
                showNewRecipeDialog();
            }
        });
        forkButton.setOnClickListener((View v) -> {
            showNewRecipeDialog();
        });
        saveButton.setOnClickListener((View v) -> {
            DbRecipeRepository.getInstance().saveRecipe(vm.getRecipe(), app.getUid());
            toggleEditing();
        });
    }

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

    private void showViewOptionsDialog() {
        FragmentManager manager = getFragmentManager();
        Fragment frag = manager.findFragmentByTag("fragment_view_options");
        if (frag != null) {
            manager.beginTransaction().remove(frag).commit();
        }
        RecipeOptionsDialogFragment dialog = new RecipeOptionsDialogFragment();
        dialog.show(manager, "fragment_view_options");
    }

    private void toggleEditing() {
        vm.toggleEditable();
        if (!vm.isEditable()) {
            forkButton.setVisibility(View.VISIBLE);
            saveButton.setVisibility(View.GONE);
        } else {
            forkButton.setVisibility(View.GONE);
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

    @Override
    public void changeRecipeImage(String imageURL) {
        Intent intent = new Intent(Intent.ACTION_PICK);
        intent.setType("image/*");
        startActivityForResult(intent, 0);
    }

    @Override
    public void recipeStarred() {
        vm.starRecipe();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 0 && resultCode == Activity.RESULT_OK && data != null) {
            Uri selectedPhotoUri = data.getData();
            try {
                @SuppressWarnings("deprecation") Bitmap bitmap = MediaStore.Images.Media.getBitmap(getContentResolver(), selectedPhotoUri);
                @SuppressWarnings("deprecation") Drawable drawable = new BitmapDrawable(bitmap);
                // TODO: update image on screen
                ImageView recipeImage = findViewById(R.id.recipe_image);
                Glide.with(this).load(drawable).centerCrop().into(recipeImage);
                //recipeImage.setImageDrawable(drawable);

                uploadImageToStorage(selectedPhotoUri);

            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private void uploadImageToStorage(Uri uri) {
        String filename = "/images/" + UUID.randomUUID().toString();
        StorageReference ref = FirebaseStorage.getInstance().getReference(filename);
        ref.putFile(uri).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
            @Override
            public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                ref.getDownloadUrl().addOnSuccessListener(uri1 -> {
                    vm.getRecipe().setImageURL(uri1.toString());
                    Log.d("TAG", "onSuccess: image url" + uri1.toString());
                });
            }
        });
    }

    // endregion

    // region NewRecipeDialogFragment listener methods
    @Override
    public void onDialogPositiveClick(DialogFragment dialog, String recipeName) {
        title = toolbar.findViewById(R.id.toolbar_title);
        title.setText(recipeName);

//        Recipe recipe = DebugRecipeRepository.getInstance().createNewRecipeFromRecipe(vm.getRecipe(), recipeName);
        Recipe recipe = DbRecipeRepository.getInstance().createNewRecipeFromRecipe(vm.getRecipe(), recipeName);
        vm = new RecipeViewModel(recipe);
        adapter.setViewModel(vm);
        toggleEditing();
    }

    @Override
    public void onDialogNegativeClick(DialogFragment dialog) {
        //
    }

    @Override
    public void onViewModifiedVersionsTapped(DialogFragment dialog) {
        Intent intent = new Intent(this, ModificationsActivity.class);
        intent.putExtra("recipe", vm.getRecipe().getRecipeID());
        this.startActivity(intent);
    }

    @Override
    public void onViewRecipeHistoryTapped(DialogFragment dialog) {
        Intent intent = new Intent(this, HistoryActivity.class);
        intent.putExtra("recipe", vm.getRecipe().getRecipeID());
        this.startActivity(intent);
    }
    // endregion
}

