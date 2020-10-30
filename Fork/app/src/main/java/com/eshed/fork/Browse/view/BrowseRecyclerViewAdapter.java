package com.eshed.fork.Browse.view;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.eshed.fork.Browse.vm.BrowseViewModel;
import com.eshed.fork.Browse.vm.RecipeCardViewModel;
import com.eshed.fork.R;
import com.eshed.fork.data.model.Ingredient;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.rxjava3.disposables.Disposable;

public class BrowseRecyclerViewAdapter extends RecyclerView.Adapter implements RecipeViewHolder.RecipeCardCallback, Filterable {

    public interface BrowseAdapterHandler {
        void selectRecipeCard(RecipeCardViewModel vm);
    }

    public BrowseAdapterHandler handler;
    private List<RecipeCardViewModel> recipeCardVms;
    private List<RecipeCardViewModel> recipeCardVmsFull;
    private Disposable disposable;

    public BrowseRecyclerViewAdapter(BrowseViewModel vm) {
        disposable = vm.getRecipeList().subscribe(recipeCardViewModels -> {
            this.recipeCardVms = recipeCardViewModels;
            this.recipeCardVmsFull = new ArrayList<>(recipeCardVms);
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

    @Override
    public Filter getFilter() {
        return browseFilter;
    }
    private Filter browseFilter = new Filter() {
        @Override
        protected FilterResults performFiltering(CharSequence constraint) {
            List<RecipeCardViewModel> filteredList = new ArrayList<>();
            if (constraint == null || constraint.length() == 0 || constraint == " ") {
                filteredList.addAll(recipeCardVms);
            } else {
                // TODO: Fix this
                String filterPattern = constraint.toString().toLowerCase().trim();
                for (RecipeCardViewModel recipeCardVm : recipeCardVmsFull) {
                    if (recipeCardVm.getSearchTerms().contains(filterPattern)) {
                        filteredList.add(recipeCardVm);
                    }
                }
            }
            FilterResults results = new FilterResults();
            results.values = filteredList;
            return results;
        }
        @Override
        protected void publishResults(CharSequence constraint, FilterResults results) {
            recipeCardVms.clear();
            recipeCardVms.addAll((List) results.values);
            notifyDataSetChanged();
        }
    };
}
