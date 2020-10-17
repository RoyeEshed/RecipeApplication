package com.eshed.fork.Browse.view;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.eshed.fork.Browse.vm.browse.BrowseViewModel;
import com.eshed.fork.Recipe.vm.RecipeViewModel;
import com.eshed.fork.R;

public class BrowseRecyclerViewAdapter extends RecyclerView.Adapter implements RecipeHolder.RecipeCardCallback{

    public interface RecipeAdapterHandler {
        void selectRecipeCard(RecipeViewModel vm);
    }
    private final Context context;
    private BrowseViewModel vm;
    public BrowseRecyclerViewAdapter.RecipeAdapterHandler handler;

    public BrowseRecyclerViewAdapter(Context context, BrowseViewModel vm) {
        this.context = context;
        this.vm = vm;
    }

    @Override
    public void cardTappedOn(RecipeViewModel vm) {
        if (vm == null) {
            Log.d("RecyclerViewAdapter", "cardTappedOn --> vm is null");
        }
        handler.selectRecipeCard(vm);
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.recipe_card, parent, false);
        RecipeHolder viewHolder = new RecipeHolder(view);
        viewHolder.callback = this;
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        ((RecipeHolder) holder).setViewModel(vm.getRecipeList().get(position));
    }

    @Override
    public int getItemCount() {
        return vm.getRecipeList().size();
    }
}
