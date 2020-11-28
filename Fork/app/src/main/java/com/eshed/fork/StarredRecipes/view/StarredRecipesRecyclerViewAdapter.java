package com.eshed.fork.StarredRecipes.view;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.eshed.fork.R;
import com.eshed.fork.StarredRecipes.vm.StarredRecipeCardViewModel;
import com.eshed.fork.StarredRecipes.vm.StarredRecipesViewModel;

import java.util.List;

import io.reactivex.rxjava3.disposables.Disposable;

public class StarredRecipesRecyclerViewAdapter extends RecyclerView.Adapter implements StarredRecipeViewHolder.StarredRecipeCardCallback {

    public interface StarredRecipesAdapterHandler {
        void selectStarredRecipeCard(StarredRecipeCardViewModel vm);
    }

    public StarredRecipesAdapterHandler handler;
    private List<StarredRecipeCardViewModel> starredRecipeCardVms;
    private Disposable disposable;

    public StarredRecipesRecyclerViewAdapter(StarredRecipesViewModel vm) {
        disposable = vm.getRecipeList().subscribe(starredRecipeCardViewModels -> {
            this.starredRecipeCardVms = starredRecipeCardViewModels;
            this.notifyDataSetChanged();
        });
    }

    @Override
    public void cardTappedOn(StarredRecipeCardViewModel vm) {
        if (vm == null) {
            Log.d("RecyclerViewAdapter", "cardTappedOn --> vm is null");
        }
        handler.selectStarredRecipeCard(vm);
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.starred_recipe_card, parent, false);
        StarredRecipeViewHolder viewHolder = new StarredRecipeViewHolder(view);
        viewHolder.callback = this;
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        ((StarredRecipeViewHolder) holder).setViewModel(starredRecipeCardVms.get(position));
    }

    @Override
    public int getItemCount() {
        return starredRecipeCardVms.size();
    }
}
