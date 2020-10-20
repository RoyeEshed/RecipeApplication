package com.eshed.fork.Recipe.view;

import android.content.Context;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.eshed.fork.Recipe.view.ViewHolders.CancelFooterViewHolder;
import com.eshed.fork.Recipe.view.ViewHolders.ContributorViewHolder;
import com.eshed.fork.Recipe.view.ViewHolders.DirectionFooterViewHolder;
import com.eshed.fork.Recipe.view.ViewHolders.DirectionViewHolder;
import com.eshed.fork.Recipe.view.ViewHolders.HeaderViewHolder;
import com.eshed.fork.Recipe.view.ViewHolders.ImageViewHolder;
import com.eshed.fork.Recipe.view.ViewHolders.IngredientFooterViewHolder;
import com.eshed.fork.Recipe.view.ViewHolders.IngredientViewHolder;
import com.eshed.fork.Recipe.view.ViewHolders.RecipeViewHolder;
import com.eshed.fork.Recipe.view.ViewHolders.TagsViewHolder;
import com.eshed.fork.Recipe.vm.RecipeViewModel;
import com.eshed.fork.Recipe.vm.component.RecipeComponentViewModel;
import com.eshed.fork.R;

public class RecipeRecyclerViewAdapter extends RecyclerView.Adapter<RecipeViewHolder> implements RecipeViewModel.Listener, FooterCallback {

    public interface RecipeAdapterHandler {
        void addIngredientComponent(RecipeViewModel vm);
        void addDirectionComponent(RecipeViewModel vm);
        void undoChanges(RecipeViewModel vm);
    }

    private RecipeViewModel vm;
    public RecipeAdapterHandler handler;

    public RecipeRecyclerViewAdapter(RecipeViewModel vm) {
        this.vm = vm;
        vm.listener = this;
    }

    @Override
    public void onDataChanged() {
        notifyDataSetChanged();
    }

    @Override
    public void addButtonTapped(RecipeComponentViewModel.Type type) {
        if (vm == null || handler == null) {
            Log.d("RecyclerViewAdapter", "addButtonTapped --> vm is null");
        }
        if (type == RecipeComponentViewModel.Type.Footer_Ingredient) {
            handler.addIngredientComponent(vm);
        } else {
            handler.addDirectionComponent(vm);
        }
        notifyDataSetChanged();
    }

    @Override
    public void cancelButtonTapped() {
        handler.undoChanges(vm);
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public RecipeViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());

        View view;
        RecipeComponentViewModel.Type type = RecipeComponentViewModel.Type.values()[viewType];
        switch (type) {
            case Direction:
                view = inflater.inflate(R.layout.item_direction, parent, false);
                return new DirectionViewHolder(view);
            case Header:
                view = inflater.inflate(R.layout.item_header, parent, false);
                return new HeaderViewHolder(view);
            case Ingredient:
                view = inflater.inflate(R.layout.item_ingredient, parent, false);
                return new IngredientViewHolder(view);
            case Footer_Ingredient:
                view = inflater.inflate(R.layout.item_footer, parent, false);
                IngredientFooterViewHolder ingredientFooterViewHolder = new IngredientFooterViewHolder(view);
                ingredientFooterViewHolder.callback = this;
                return ingredientFooterViewHolder;
            case Footer_Direction:
                view = inflater.inflate(R.layout.item_footer, parent, false);
                DirectionFooterViewHolder directionFooterViewHolder = new DirectionFooterViewHolder(view);
                directionFooterViewHolder.callback = this;
                return directionFooterViewHolder;
            case Tag:
                view = inflater.inflate(R.layout.item_tags, parent, false);
                return new TagsViewHolder(view);
            case Image:
                view = inflater.inflate(R.layout.item_image, parent, false);
                return new ImageViewHolder(view);
            case Contributor:
                view = inflater.inflate(R.layout.item_contribution, parent, false);
                return new ContributorViewHolder(view);
            case Footer_Cancel:
                view = inflater.inflate(R.layout.item_footer, parent, false);
                CancelFooterViewHolder cancelFooterViewHolder = new CancelFooterViewHolder(view);
                cancelFooterViewHolder.callback = this;
                return cancelFooterViewHolder;
            default:
                throw new RuntimeException("Invalid viewType: " + viewType);
        }
    }

    @Override
    public void onBindViewHolder(@NonNull RecipeViewHolder holder, int position) {
        holder.bind(vm.getComponents().get(position));
    }

    @Override
    public int getItemCount() {
        return vm.getComponents().size();
    }

    @Override
    public int getItemViewType(int position) {
        return vm.getComponents().get(position).getType().ordinal();
    }

    private class EditTextListener implements TextWatcher {
        private int position;

        public void updatePosition(int position) {
            this.position = position;
        }

        @Override
        public void beforeTextChanged(CharSequence charSequence, int i, int i2, int i3) {
        }

        @Override
        public void onTextChanged(CharSequence charSequence, int i, int i2, int i3) {
            RecipeComponentViewModel component = vm.getComponents().get(position);
            component.update(charSequence.toString());
        }

        @Override
        public void afterTextChanged(Editable editable) {
        }
    }

}
