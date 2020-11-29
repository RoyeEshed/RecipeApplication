package com.eshed.fork.History.view;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.eshed.fork.History.vm.AncestorCardViewModel;
import com.eshed.fork.History.vm.HistoryViewModel;
import com.eshed.fork.R;

import java.util.List;

import io.reactivex.rxjava3.disposables.Disposable;

public class HistoryRecyclerViewAdapter extends RecyclerView.Adapter implements AncestorViewHolder.AncestorCardCallback {

    public interface HistoryAdapterHandler {
    void selectRecipeCard(AncestorCardViewModel vm);
}

    public HistoryRecyclerViewAdapter.HistoryAdapterHandler handler;
    private List<AncestorCardViewModel> ancestorCardVms;
    private Disposable disposable;

    public HistoryRecyclerViewAdapter(HistoryViewModel vm) {
        disposable = vm.getAncestors().subscribe(ancestorCardViewModels -> {
            this.ancestorCardVms = ancestorCardViewModels;
            this.notifyDataSetChanged();
        });
    }

    @Override
    public void cardTappedOn(AncestorCardViewModel vm) {
        if (vm == null) {
            Log.d("RecyclerViewAdapter", "cardTappedOn --> vm is null");
        }
        handler.selectRecipeCard(vm);
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.ancestor_recipe_card, parent, false);
        AncestorViewHolder viewHolder = new AncestorViewHolder(view);
        viewHolder.callback = this;
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        ((AncestorViewHolder) holder).setViewModel(ancestorCardVms.get(position));
    }

    @Override
    public int getItemCount() {
        return ancestorCardVms.size();
    }
}