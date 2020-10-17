package com.eshed.fork.Recipe.vm;

import com.eshed.fork.R;
import com.eshed.fork.Recipe.vm.component.DirectionViewModel;
import com.eshed.fork.Recipe.vm.component.FooterViewModel;
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
    private int recipeID;
    private List<RecipeComponentViewModel> recipeComponents;
    private RecipeRepository repository = DebugRecipeRepository.getInstance();
    private Boolean isEditable = false;

    public Listener listener;

    public RecipeViewModel() {
        this.recipeID = (int) (Math.random() * 1000);
        recipeComponents = new ArrayList<>();
        recipeComponents.add(new ImageViewModel(R.drawable.ic_baseline_image_24));
        recipeComponents.add(new HeaderViewModel("Ingredients"));
        recipeComponents.add(new IngredientViewModel(new Ingredient("", ""), isEditable));
        recipeComponents.add(new FooterViewModel(R.drawable.ic_baseline_add_circle_24, isEditable));
        recipeComponents.add(new HeaderViewModel("Directions"));
        recipeComponents.add(new DirectionViewModel(new Direction(1, ""), isEditable));
        recipeComponents.add(new FooterViewModel(R.drawable.ic_baseline_add_circle_24, isEditable));
        recipeComponents.add(new HeaderViewModel("Tags"));
        recipeComponents.add(new TagViewModel(""));
    }

    public RecipeViewModel(int recipeID) {
        this.recipeID = recipeID;
        this.recipe = repository.getRecipeWithID(recipeID);

        recipeComponents = new ArrayList<>();
        recipeComponents.add(new ImageViewModel(R.drawable.ic_baseline_image_24));
        recipeComponents.add(new HeaderViewModel("Ingredients"));
        for (int i = 0; i < recipe.getIngredients().size(); i++) {
            Ingredient ingredient = recipe.getIngredients().get(i);
            recipeComponents.add(new IngredientViewModel(ingredient, isEditable));
        }
        recipeComponents.add(new FooterViewModel(R.drawable.ic_baseline_add_circle_24, isEditable));
        recipeComponents.add(new HeaderViewModel("Directions"));
        for (int i = 0; i < recipe.getDirections().size(); i++) {
            Direction direction = recipe.getDirections().get(i);
            recipeComponents.add(new DirectionViewModel(direction, isEditable));
        }
        recipeComponents.add(new FooterViewModel(R.drawable.ic_baseline_add_circle_24, isEditable));
        recipeComponents.add(new HeaderViewModel("Tags"));
        for (int i = 0; i < recipe.getTags().size(); i++) {
            String tag = recipe.getTags().get(i);
            recipeComponents.add(new TagViewModel(tag));
        }
    }

    public Recipe getRecipe() {
        return recipe;
    }

    public int getRecipeID() {
        return recipeID;
    }

    public List<RecipeComponentViewModel> getComponents() {
        return recipeComponents;
    }

    public void toggleEditable() {
        isEditable = !isEditable;
        for (RecipeComponentViewModel component: recipeComponents) {
            if (component instanceof RecipeComponentIsEditable) {
                RecipeComponentIsEditable editableComponent = (RecipeComponentIsEditable)component;
                editableComponent.setIsEditable(isEditable);
            }
        }

        listener.onDataChanged();
    }
}

