package com.eshed.fork.Recipe.view;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.eshed.fork.Recipe.vm.component.DirectionViewModel;
import com.eshed.fork.Recipe.vm.component.HeaderViewModel;
import com.eshed.fork.Recipe.vm.component.IngredientViewModel;
import com.eshed.fork.Recipe.vm.component.RecipeInformation;
import com.eshed.fork.Recipe.vm.RecipeViewModel;
import com.eshed.fork.Recipe.vm.component.TagViewModel;
import com.eshed.fork.R;

public class RecipeRecyclerViewAdapter extends RecyclerView.Adapter<RecipeRecyclerViewAdapter.RecipeViewHolder> implements RecipeViewModel.Listener {

    public static abstract class RecipeViewHolder extends RecyclerView.ViewHolder {
        public RecipeViewHolder(@NonNull View itemView) {
            super(itemView);
        }

        abstract void bind(RecipeInformation vm);
    }

    public static class IngredientViewHolder extends RecipeViewHolder {
        TextView amount;
        TextView ingredientText;

        public IngredientViewHolder(@NonNull View itemView) {
            super(itemView);
            amount = itemView.findViewById(R.id.ingredient_amount);
            ingredientText = itemView.findViewById(R.id.ingredient_text);
        }

        @Override public void bind(RecipeInformation vm) {
            IngredientViewModel ingredientViewModel = (IngredientViewModel)vm;
            amount.setText(ingredientViewModel.ingredient.getAmount());
            ingredientText.setText(ingredientViewModel.ingredient.getIngredientName());
        }
    }

    public static class DirectionViewHolder extends RecipeViewHolder {
        TextView directionNumber;
        EditText directionText;

        public DirectionViewHolder(@NonNull View itemView) {
            super(itemView);
            directionNumber = itemView.findViewById(R.id.direction_number);
            directionText = itemView.findViewById(R.id.direction_text);
        }

        @SuppressLint("SetTextI18n")
        @Override public void bind(RecipeInformation vm) {
            DirectionViewModel directionViewModel = (DirectionViewModel)vm;
            directionNumber.setText("" + directionViewModel.direction.getDirectionNumber());
            directionText.setText("" + directionViewModel.direction.getDirectionText());

            directionText.setEnabled(directionViewModel.isEditable());
        }
    }

    public static class HeaderViewHolder extends RecipeViewHolder {
        TextView header;

        public HeaderViewHolder(@NonNull View itemView) {
            super(itemView);
            header = itemView.findViewById(R.id.section_header);
        }

        @Override public void bind(RecipeInformation vm) {
            HeaderViewModel headerViewModel = (HeaderViewModel)vm;
            header.setText(headerViewModel.header);
        }
    }

    public static class FooterViewHolder extends RecipeViewHolder {
        ImageView addButton;

        public FooterViewHolder(@NonNull View itemView) {
            super(itemView);
            addButton = itemView.findViewById(R.id.add_button);
        }

        @Override
        void bind(RecipeInformation vm) {
            // TODO
        }
    }

    public static class TagsViewHolder extends RecipeViewHolder {
        TextView tags;

        public TagsViewHolder(@NonNull View itemView) {
            super(itemView);
            tags = itemView.findViewById(R.id.tags_text);
        }

        @Override public void bind(RecipeInformation vm) {
            TagViewModel tagViewModel = (TagViewModel)vm;
            tags.setText(tagViewModel.tag);
        }
    }

    private final Context context;
    private RecipeViewModel vm;

    public RecipeRecyclerViewAdapter(Context context, RecipeViewModel vm) {
        this.context = context;
        this.vm = vm;

        vm.listener = this;
    }

    @Override
    public void onDataChanged() {
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public RecipeViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());

        View view;
        RecipeInformation.Type type = RecipeInformation.Type.values()[viewType];
        switch (type) {
            case Direction:
                view = inflater.inflate(R.layout.item_direction, parent, false);
                return new DirectionViewHolder(view);
            case Header:
                view = inflater.inflate(R.layout.recipe_header, parent, false);
                return new HeaderViewHolder(view);
            case Ingredient:
                view = inflater.inflate(R.layout.item_ingredient, parent, false);
                return new IngredientViewHolder(view);
            case Footer:
                view = inflater.inflate(R.layout.recipe_footer, parent, false);
                throw new RuntimeException("Not Implemented"); // TODO
            case Tag:
                view = inflater.inflate(R.layout.item_tags, parent, false);
                return new TagsViewHolder(view);
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
}
