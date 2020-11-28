package com.eshed.fork.Recipe.ViewOptions.View;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.eshed.fork.R;
import com.eshed.fork.Recipe.ViewOptions.vm.ModificationCardViewModel;
import com.eshed.fork.Recipe.ViewOptions.vm.ModificationsViewModel;

import java.util.List;

import io.reactivex.rxjava3.disposables.Disposable;

public class ModificationsRecyclerViewAdapter extends RecyclerView.Adapter implements ModificationViewHolder.ModificationCardCallback {

    public interface ModificationsAdapterHandler {
        void selectRecipeCard(ModificationCardViewModel vm);
    }

    public ModificationsRecyclerViewAdapter.ModificationsAdapterHandler handler;
    private List<ModificationCardViewModel> modificationCardVms;
    private Disposable disposable;

    public ModificationsRecyclerViewAdapter(ModificationsViewModel vm) {
        disposable = vm.getRecipeList().subscribe(modificationCardViewModels -> {
            this.modificationCardVms = modificationCardViewModels;
            this.notifyDataSetChanged();
        });
    }

    @Override
    public void cardTappedOn(ModificationCardViewModel vm) {
        if (vm == null) {
            Log.d("RecyclerViewAdapter", "cardTappedOn --> vm is null");
        }
        handler.selectRecipeCard(vm);
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.simple_recipe_card, parent, false);
        ModificationViewHolder viewHolder = new ModificationViewHolder(view);
        viewHolder.callback = this;
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        ((ModificationViewHolder) holder).setViewModel(modificationCardVms.get(position));
    }

    @Override
    public int getItemCount() {
        return modificationCardVms.size();
    }
}