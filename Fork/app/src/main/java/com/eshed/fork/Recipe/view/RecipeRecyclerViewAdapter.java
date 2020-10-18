package com.eshed.fork.Recipe.view;

import android.annotation.SuppressLint;
import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.eshed.fork.Recipe.vm.RecipeViewModel;
import com.eshed.fork.Recipe.vm.component.DirectionViewModel;
import com.eshed.fork.Recipe.vm.component.Footer.DirectionFooterViewModel;
import com.eshed.fork.Recipe.vm.component.Footer.IngredientFooterViewModel;
import com.eshed.fork.Recipe.vm.component.HeaderViewModel;
import com.eshed.fork.Recipe.vm.component.ImageViewModel;
import com.eshed.fork.Recipe.vm.component.IngredientViewModel;
import com.eshed.fork.Recipe.vm.component.RecipeComponentViewModel;
import com.eshed.fork.Recipe.vm.component.TagViewModel;
import com.eshed.fork.R;

public class RecipeRecyclerViewAdapter extends RecyclerView.Adapter<RecipeRecyclerViewAdapter.RecipeViewHolder> implements RecipeViewModel.Listener, FooterCallback {

    public interface RecipeAdapterHandler {
        void addIngredientComponent(RecipeViewModel vm);
        void addDirectionComponent(RecipeViewModel vm);
    }

    public static abstract class RecipeViewHolder extends RecyclerView.ViewHolder {
        public RecipeViewHolder(@NonNull View itemView) {
            super(itemView);
        }

        abstract void bind(RecipeComponentViewModel vm);
    }

    public static class IngredientViewHolder extends RecipeViewHolder {
        EditText amount;
        EditText ingredientText;

        public IngredientViewHolder(@NonNull View itemView) {
            super(itemView);
            amount = itemView.findViewById(R.id.ingredient_amount);
            ingredientText = itemView.findViewById(R.id.ingredient_text);
        }

        @Override public void bind(RecipeComponentViewModel vm) {
            IngredientViewModel ingredientViewModel = (IngredientViewModel)vm;
            amount.setText(ingredientViewModel.ingredient.getAmount());
            ingredientText.setText(ingredientViewModel.ingredient.getIngredientName());

            amount.setEnabled(ingredientViewModel.isEditable());
            ingredientText.setEnabled(ingredientViewModel.isEditable());
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
        @Override public void bind(RecipeComponentViewModel vm) {
            DirectionViewModel directionViewModel = (DirectionViewModel)vm;
            directionNumber.setText("" + directionViewModel.direction.getDirectionNumber());
            directionText.setText("" + directionViewModel.direction.getDirectionText());

            directionText.setEnabled(directionViewModel.isEditable());
        }
    }

    public static class TagsViewHolder extends RecipeViewHolder {
        EditText tags;

        public TagsViewHolder(@NonNull View itemView) {
            super(itemView);
            tags = itemView.findViewById(R.id.tags_text);
        }

        @Override public void bind(RecipeComponentViewModel vm) {
            TagViewModel tagViewModel = (TagViewModel)vm;
            tags.setText(tagViewModel.tag);

            tags.setEnabled(tagViewModel.isEditable());
        }
    }

    public static class ImageViewHolder extends RecipeViewHolder {
        ImageView imageView;

        public ImageViewHolder(@NonNull View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.recipe_image);
        }

        @Override public void bind(RecipeComponentViewModel vm) {
            ImageViewModel imageViewModel = (ImageViewModel)vm;
            Glide.with(itemView).load(imageViewModel.imageURL).centerCrop().into(imageView);
            //imageView.setImageResource(imageViewModel.imageResource);
        }
    }

    public static class HeaderViewHolder extends RecipeViewHolder {
        TextView header;

        public HeaderViewHolder(@NonNull View itemView) {
            super(itemView);
            header = itemView.findViewById(R.id.section_header);
        }

        @Override public void bind(RecipeComponentViewModel vm) {
            HeaderViewModel headerViewModel = (HeaderViewModel)vm;
            header.setText(headerViewModel.header);
        }
    }

    public static class IngredientFooterViewHolder extends RecipeViewHolder {
        public FooterCallback callback;
        ImageView addButton;

        public IngredientFooterViewHolder(@NonNull View itemView) {
            super(itemView);
            addButton = itemView.findViewById(R.id.add_button);
        }

        @Override
        void bind(RecipeComponentViewModel vm) {
            IngredientFooterViewModel footerViewModel = (IngredientFooterViewModel) vm;
            addButton.setImageResource(footerViewModel.imageResource);
            if (footerViewModel.isEditable()) {
                addButton.setVisibility(View.VISIBLE);
            } else {
                addButton.setVisibility(View.GONE);
            }

            addButton.setOnClickListener(v -> {
                if (callback != null) {
                    Log.d("TAG", "bind: PRINTING TYPE" + vm.getType().toString());
                    callback.addButtonTapped(vm.getType());
                    Log.d("FooterViewHolder", "bind: add button tapped");
                }
            });
        }
    }
    
    public static class DirectionFooterViewHolder extends RecipeViewHolder {
        public FooterCallback callback;
        ImageView addButton;

        public DirectionFooterViewHolder(@NonNull View itemView) {
            super(itemView);
            addButton = itemView.findViewById(R.id.add_button);
        }

        @Override
        void bind(RecipeComponentViewModel vm) {
            DirectionFooterViewModel footerViewModel = (DirectionFooterViewModel) vm;
            addButton.setImageResource(footerViewModel.imageResource);
            if (footerViewModel.isEditable()) {
                addButton.setVisibility(View.VISIBLE);
            } else {
                addButton.setVisibility(View.GONE);
            }

            addButton.setOnClickListener(v -> {
                if (callback != null) {
                    Log.d("TAG", "bind: PRINTING TYPE" + vm.getType().toString());
                    callback.addButtonTapped(vm.getType());
                    Log.d("FooterViewHolder", "bind: add button tapped");
                }
            });
        }
    }



    private final Context context;
    private RecipeViewModel vm;
    public RecipeAdapterHandler handler;


    public RecipeRecyclerViewAdapter(Context context, RecipeViewModel vm) {
        this.context = context;
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
