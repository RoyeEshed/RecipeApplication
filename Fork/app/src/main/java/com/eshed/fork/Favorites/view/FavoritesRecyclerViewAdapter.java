package com.eshed.fork.Favorites.view;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.eshed.fork.Favorites.vm.FavoritesCardViewModel;
import com.eshed.fork.Favorites.vm.FavoritesViewModel;
import com.eshed.fork.R;

import java.util.List;

import io.reactivex.rxjava3.disposables.Disposable;

public class FavoritesRecyclerViewAdapter extends RecyclerView.Adapter implements FavoritesViewHolder.FavoritesCardCallback {

    public interface FavoritesAdapterHandler {
        void selectFavoritesCard(FavoritesCardViewModel vm);
    }

    public FavoritesAdapterHandler handler;
    private List<FavoritesCardViewModel> favoritesCardVms;
    private Disposable disposable;

    public FavoritesRecyclerViewAdapter(FavoritesViewModel vm) {
        disposable = vm.getRecipeList().subscribe(favoritesCardViewModels -> {
            this.favoritesCardVms = favoritesCardViewModels;
            this.notifyDataSetChanged();
        });
    }

    @Override
    public void cardTappedOn(FavoritesCardViewModel vm) {
        if (vm == null) {
            Log.d("RecyclerViewAdapter", "cardTappedOn --> vm is null");
        }
        handler.selectFavoritesCard(vm);
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.recipe_card, parent, false);
        FavoritesViewHolder viewHolder = new FavoritesViewHolder(view);
        viewHolder.callback = this;
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        ((FavoritesViewHolder) holder).setViewModel(favoritesCardVms.get(position));
    }

    @Override
    public int getItemCount() {
        return favoritesCardVms.size();
    }
}
