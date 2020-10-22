package com.eshed.fork.Recipe.vm;

import com.eshed.fork.R;
import com.eshed.fork.Recipe.vm.component.ContributorViewModel;
import com.eshed.fork.Recipe.vm.component.DirectionViewModel;
import com.eshed.fork.Recipe.vm.component.Footer.CancelFooterViewModel;
import com.eshed.fork.Recipe.vm.component.Footer.DirectionFooterViewModel;
import com.eshed.fork.Recipe.vm.component.Footer.IngredientFooterViewModel;
import com.eshed.fork.Recipe.vm.component.HeaderViewModel;
import com.eshed.fork.Recipe.vm.component.ImageViewModel;
import com.eshed.fork.Recipe.vm.component.IngredientViewModel;
import com.eshed.fork.Recipe.vm.component.RecipeComponentIsEditable;
import com.eshed.fork.Recipe.vm.component.RecipeComponentViewModel;
import com.eshed.fork.Recipe.vm.component.TagViewModel;
import com.eshed.fork.data.DebugRecipeRepository;
import com.eshed.fork.data.RecipeRepository;
import com.eshed.fork.data.model.Direction;
import com.eshed.fork.data.model.Ingredient;
import com.eshed.fork.data.model.Recipe;

import java.util.ArrayList;
import java.util.List;

public class RecipeViewModel {

    public interface Listener {
        void onDataChanged();
    }

    private Recipe recipe;
    private List<RecipeComponentViewModel> recipeComponents;
    private Boolean isEditable = false;

    public Listener listener;

    public RecipeViewModel(Recipe recipe) {
        this.recipe = recipe;
        regenerateComponents();
    }

    public void addIngredientComponent() {
        recipe.getIngredients().add(new Ingredient("", ""));
        regenerateComponents();
        listener.onDataChanged();
    }

    public void addDirectionComponent() {
        int directionOrdinal = recipe.getDirections().size() + 1;
        recipe.getDirections().add(new Direction(directionOrdinal, ""));
        regenerateComponents();
        listener.onDataChanged();
    }

    public void toggleEditable() {
        isEditable = !isEditable;
        regenerateComponents();
        listener.onDataChanged();
    }

    public Recipe getRecipe() {
        return recipe;
    }

    public boolean isEditable() {
        return isEditable;
    }

    public List<RecipeComponentViewModel> getComponents() {
        return recipeComponents;
    }

    private void regenerateComponents() {
        recipeComponents = new ArrayList<>();
        recipeComponents.add(new ImageViewModel(recipe.getImageURL()));
        recipeComponents.add(new ContributorViewModel(recipe.getContributor(), isEditable));
        recipeComponents.add(new HeaderViewModel("Ingredients"));
        for (int i = 0; i < recipe.getIngredients().size(); i++) {
            Ingredient ingredient = recipe.getIngredients().get(i);
            recipeComponents.add(new IngredientViewModel(ingredient, isEditable));
        }
        recipeComponents.add(new IngredientFooterViewModel(R.drawable.ic_baseline_add_circle_24, isEditable));
        recipeComponents.add(new HeaderViewModel("Directions"));
        for (int i = 0; i < recipe.getDirections().size(); i++) {
            Direction direction = recipe.getDirections().get(i);
            recipeComponents.add(new DirectionViewModel(direction, isEditable));
        }
        recipeComponents.add(new DirectionFooterViewModel(R.drawable.ic_baseline_add_circle_24, isEditable));
        recipeComponents.add(new HeaderViewModel("Tags"));
        for (int i = 0; i < recipe.getTags().size(); i++) {
            String tag = recipe.getTags().get(i);
            recipeComponents.add(new TagViewModel(tag));
        }
        recipeComponents.add(new CancelFooterViewModel((isEditable)));
    }
}

