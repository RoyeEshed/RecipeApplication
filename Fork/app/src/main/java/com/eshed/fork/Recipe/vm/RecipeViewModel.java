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
    private int recipeID;
    private List<RecipeComponentViewModel> recipeComponents;
    private RecipeRepository repository = DebugRecipeRepository.getInstance();
    private Boolean isEditable = false;
    private int directionsIndex;

    public Listener listener;

    public RecipeViewModel() {
        this.recipeID = (int) (Math.random() * 1000);
        initEmptyViewModel();
    }

    public RecipeViewModel(int recipeID) {
        this.recipeID = recipeID;
        this.recipe = repository.getRecipeWithID(recipeID);
        initViewModel();
    }

    private void initViewModel() {
        directionsIndex = recipe.getDirections().size();

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

    private void initEmptyViewModel() {
        directionsIndex = 1;
        recipeComponents = new ArrayList<>();
        recipeComponents.add(new ImageViewModel("https://images.unsplash.com/photo-1517870662726-c1d98ee36250?ixlib=rb-1.2.1&ixid=eyJhcHBfaWQiOjEyMDd9&auto=format&fit=crop&w=2700&q=80"));
        recipeComponents.add(new HeaderViewModel("Ingredients"));
        recipeComponents.add(new IngredientViewModel(new Ingredient("", ""), isEditable));
        recipeComponents.add(new IngredientFooterViewModel(R.drawable.ic_baseline_add_circle_24, isEditable));
        recipeComponents.add(new HeaderViewModel("Directions"));
        recipeComponents.add(new DirectionViewModel(new Direction(1, ""), isEditable));
        recipeComponents.add(new DirectionFooterViewModel(R.drawable.ic_baseline_add_circle_24, isEditable));
        recipeComponents.add(new HeaderViewModel("Tags"));
        recipeComponents.add(new TagViewModel(""));
        recipeComponents.add(new CancelFooterViewModel((isEditable)));
    }

    public void addIngredientComponent() {
        int index;
        if (recipe == null) {
            index = 2;
        } else {
            index = 3 + recipe.getIngredients().size();
        }
        recipeComponents.add(index, new IngredientViewModel(new Ingredient("", ""), isEditable));
    }

    public void addDirectionComponent() {
        int index = recipeComponents.size() - 4;
        directionsIndex += 1;
        recipeComponents.add(index, new DirectionViewModel(new Direction(directionsIndex, ""), isEditable));
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

    public List<RecipeComponentViewModel> getComponents() {
        return recipeComponents;
    }

    public Recipe getRecipe() {
        return recipe;
    }

    public boolean isEditable() {
        return isEditable;
    }

    public void saveAsNewRecipe(String recipeName) {
        List<Ingredient> ingredients = new ArrayList<>();
        List<Direction> directions = new ArrayList<>();
        List<String> tags = new ArrayList<>();
        int id = (int) (Math.random() * 1000);
        String imageURL = "";

        for (RecipeComponentViewModel r: recipeComponents) {
            switch (r.getType()) {
                case Image:
                    imageURL = ((ImageViewModel) r).imageURL;
                    break;
                case Ingredient:
                    ingredients.add(((IngredientViewModel) r).ingredient);
                    break;
                case Direction:
                    directions.add(((DirectionViewModel) r).direction);
                    break;
                case Tag:
                    tags.add(((TagViewModel)r).tag);
                    break;
            }
        }
        repository.addRecipe(new Recipe(id, recipeName, imageURL, "test", ingredients, directions, tags));
    }

    public void reset() {
        repository.getRecipeWithID(recipeID);
        initViewModel();
    }
}

