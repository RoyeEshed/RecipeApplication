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
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.eshed.fork.Data.DbRecipeRepository;
import com.eshed.fork.Data.model.Recipe;
import com.eshed.fork.Fork;
import com.eshed.fork.R;
import com.eshed.fork.Recipe.view.Dialogs.NewRecipeDialogFragment;
import com.eshed.fork.Recipe.view.Dialogs.NewRecipeDialogFragment.NewRecipeDialogListener;
import com.eshed.fork.Recipe.view.RecipeRecyclerViewAdapter.RecipeAdapterHandler;
import com.eshed.fork.Recipe.vm.RecipeViewModel;
import com.eshed.fork.Util.Util;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.io.IOException;
import java.util.UUID;

import static androidx.recyclerview.widget.RecyclerView.OnScrollListener;

public class RecipeActivity extends AppCompatActivity implements RecipeAdapterHandler, NewRecipeDialogListener {
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
        Util.setupTabBar(this);
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
            case R.id.more_options:
                Toast.makeText(this, "TODO: Drop-down", Toast.LENGTH_SHORT).show();
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
//                View com.eshed.fork.Login.view = super.getView(position, convertView, parent);
//                com.eshed.fork.Login.view.setVisibility(View.GONE);
//
//                return com.eshed.fork.Login.view;
//            }
//        };
//        spinner.setAdapter(adapter);
//        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
//            @Override
//            public void onItemSelected(AdapterView<?> adapterView, View com.eshed.fork.Login.view, int position, long l) {
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

    // endregion
}

