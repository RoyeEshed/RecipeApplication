package com.eshed.fork.Browse.view;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.eshed.fork.Browse.vm.BrowseViewModel;
import com.eshed.fork.Browse.vm.RecipeCardViewModel;
import com.eshed.fork.R;

import java.util.List;

import io.reactivex.rxjava3.disposables.Disposable;

public class BrowseRecyclerViewAdapter extends RecyclerView.Adapter implements RecipeViewHolder.RecipeCardCallback {

    public interface BrowseAdapterHandler {
        void selectRecipeCard(RecipeCardViewModel vm);
    }

    public BrowseAdapterHandler handler;
    private List<RecipeCardViewModel> recipeCardVms;
    private Disposable disposable;

    public BrowseRecyclerViewAdapter(BrowseViewModel vm) {
        disposable = vm.getRecipeList().subscribe(recipeCardViewModels -> {
            this.recipeCardVms = recipeCardViewModels;
            this.notifyDataSetChanged();
        });
    }

    @Override
    public void cardTappedOn(RecipeCardViewModel vm) {
        if (vm == null) {
            Log.d("RecyclerViewAdapter", "cardTappedOn --> vm is null");
        }
        handler.selectRecipeCard(vm);
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.recipe_card, parent, false);
        RecipeViewHolder viewHolder = new RecipeViewHolder(view);
        viewHolder.callback = this;
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        ((RecipeViewHolder) holder).setViewModel(recipeCardVms.get(position));
    }

    @Override
    public int getItemCount() {
        return recipeCardVms.size();
    }
}
