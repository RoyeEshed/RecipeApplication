package com.eshed.fork.Browse.view;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.appcompat.widget.SearchView;

import com.eshed.fork.Browse.vm.BrowseViewModel;
import com.eshed.fork.Browse.vm.RecipeCardViewModel;
import com.eshed.fork.R;
import com.eshed.fork.Recipe.view.NewRecipeActivity;
import com.eshed.fork.Recipe.view.RecipeActivity;
import com.eshed.fork.Util.Util;

import static androidx.recyclerview.widget.RecyclerView.*;

public class BrowseActivity extends AppCompatActivity implements BrowseRecyclerViewAdapter.BrowseAdapterHandler, SearchView.OnQueryTextListener {
    private BrowseViewModel vm;
    private ConstraintLayout searchBar;
    private Adapter adapter;
    private SearchView searchView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_browse);
        Util.setupTabBar(this);
        setupToolbar();
        searchView = findViewById(R.id.search_view);

        vm = new BrowseViewModel();
        initRecyclerView();
        //initSearchView();
    }

    private void initRecyclerView() {
        RecyclerView recyclerView = findViewById(R.id.recycler_view);
        recyclerView.setHasFixedSize(true);
        adapter = new BrowseRecyclerViewAdapter(vm);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new GridLayoutManager(this, 1));

        ((BrowseRecyclerViewAdapter) adapter).handler = this;
    }

//    private void initSearchView() {
//        searchBar = findViewById(R.id.search_bar);
//        EditText searchInput = searchBar.findViewById(R.id.edit_search);
//        searchInput.setOnClickListener(new SearchBarClickListener());
//    }

    private void setupToolbar() {
        Toolbar toolbar = findViewById(R.id.toolbar);

        setSupportActionBar(toolbar);
        if (this.getSupportActionBar() != null) {
            getSupportActionBar().setDisplayShowTitleEnabled(false);
        }

        TextView title = toolbar.findViewById(R.id.toolbar_title);
        title.setText("Browse");

        ImageView backButton = toolbar.findViewById(R.id.back_arrow);
        ImageView addButton = toolbar.findViewById(R.id.add_recipe);
        TextView logout = toolbar.findViewById(R.id.log_out);

        backButton.setVisibility(GONE);
        logout.setVisibility(VISIBLE);
        logout.setOnClickListener((View v)-> {
            Toast.makeText(this, "TODO: logout", Toast.LENGTH_SHORT).show();

        });
        addButton.setOnClickListener((View v)-> {
            Intent intent = new Intent(this, NewRecipeActivity.class);
            this.startActivity(intent);
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.d("TAG", "onResume: called");
        adapter.notifyDataSetChanged();
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        Log.d("TAG", "onRestart: called");
        adapter.notifyDataSetChanged();
    }

    // region BrowseAdapterHandler
    @Override
    public void selectRecipeCard(RecipeCardViewModel vm) {
        Intent intent = new Intent(this, RecipeActivity.class);
        intent.putExtra("recipe", vm.getRecipe().getRecipeID());
        this.startActivity(intent);
    }
    // endregion

    // region searchview
//    @Override
//    public boolean onCreateOptionsMenu(Menu menu) {
//        getMenuInflater().inflate(R.menu.toolbar, menu);
//
//        final MenuItem searchItem = menu.findItem(R.id.search_view);
//        final SearchView searchView = (SearchView) searchItem.getActionView();
//        searchView.setOnQueryTextListener(this);
//
//        return true;
//    }

    @Override
    public boolean onQueryTextChange(String query) {
        // Here is where we are going to implement the filter logic
        return false;
    }

    @Override
    public boolean onQueryTextSubmit(String query) {
        return false;
    }
    // endregion
//    private class SearchBarClickListener implements OnClickListener {
//
//        @Override
//        public void onClick(View view) {
//            LinearLayout sortOptions = searchBar.findViewById(R.id.sort_options);
//            if (sortOptions.getVisibility() == View.VISIBLE) {
//                sortOptions.setVisibility(View.GONE);
//            } else {
//                sortOptions.setVisibility(View.VISIBLE);
//            }
//        }
//    }
}

