package com.eshed.fork.Recipe.vm;

import com.eshed.fork.Recipe.vm.component.DirectionViewModel;
import com.eshed.fork.Recipe.vm.component.HeaderViewModel;
import com.eshed.fork.Recipe.vm.component.IngredientViewModel;
import com.eshed.fork.Recipe.vm.component.RecipeComponentIsEditable;
import com.eshed.fork.Recipe.vm.component.RecipeInformation;
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
    private int recipeID;
    private List<RecipeInformation> recipeComponents;
    private RecipeRepository repository = DebugRecipeRepository.getInstance();
    private Boolean isEditable = false;

    public Listener listener;

    public RecipeViewModel(int recipeID) {
        this.recipeID = recipeID;
        this.recipe = repository.getRecipeWithID(recipeID);

        recipeComponents = new ArrayList<>();
        recipeComponents.add(new HeaderViewModel("Ingredients"));
        for (int i = 0; i < recipe.getIngredients().size(); i++) {
            Ingredient ingredient = recipe.getIngredients().get(i);
            recipeComponents.add(new IngredientViewModel(ingredient));
        }

        recipeComponents.add(new HeaderViewModel("Directions"));
        for (int i = 0; i < recipe.getDirections().size(); i++) {
            Direction direction = recipe.getDirections().get(i);
            recipeComponents.add(new DirectionViewModel(direction, isEditable));
        }

        // TODO: tags
    }

    public Recipe getRecipe() {
        return recipe;
    }

    public int getRecipeID() {
        return recipeID;
    }

    public List<RecipeInformation> getComponents() {
        return recipeComponents;
    }

    public void toggleEditable() {
        isEditable = !isEditable;
        for (RecipeInformation component: recipeComponents) {
            if (component instanceof RecipeComponentIsEditable) {
                RecipeComponentIsEditable editableComponent = (RecipeComponentIsEditable)component;
                editableComponent.setIsEditable(isEditable);
            }
        }

        listener.onDataChanged();
    }
}

