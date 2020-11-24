package com.eshed.fork.Recipe.vm;

import android.util.Log;

import com.eshed.fork.Data.service.NutritionalAnalysisRequest;
import com.eshed.fork.Data.service.NutritionalAnalysisResponse;
import com.eshed.fork.R;
import com.eshed.fork.Recipe.vm.component.ContributorViewModel;
import com.eshed.fork.Recipe.vm.component.DirectionViewModel;
import com.eshed.fork.Recipe.vm.component.Footer.CancelFooterViewModel;
import com.eshed.fork.Recipe.vm.component.Footer.DirectionFooterViewModel;
import com.eshed.fork.Recipe.vm.component.Footer.IngredientFooterViewModel;
import com.eshed.fork.Recipe.vm.component.HeaderViewModel;
import com.eshed.fork.Recipe.vm.component.ImageViewModel;
import com.eshed.fork.Recipe.vm.component.IngredientViewModel;
import com.eshed.fork.Recipe.vm.component.NutritionViewModel;
import com.eshed.fork.Recipe.vm.component.RecipeComponentViewModel;
import com.eshed.fork.Recipe.vm.component.TagViewModel;
import com.eshed.fork.Data.RecipeRepository;
import com.eshed.fork.Data.model.Direction;
import com.eshed.fork.Data.model.Ingredient;
import com.eshed.fork.Data.model.Nutrients.TotalNutrients;
import com.eshed.fork.Data.model.Recipe;
import com.eshed.fork.Data.service.EdamamService;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.rxjava3.disposables.Disposable;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RecipeViewModel {

    public interface Listener {
        void onDataChanged();
    }

    private Recipe recipe;
    private List<RecipeComponentViewModel> recipeComponents;
    private Boolean isEditable = false;
    private TotalNutrients nutrients;
    private Double servings;
    private int calories;
    private Disposable disposable;

    public Listener listener;

    public RecipeViewModel(
            RecipeRepository recipeRepository,
            EdamamService edamamService,
            int recipeID
    ) {
        disposable = recipeRepository
                .getRecipeWithID(recipeID)
                .subscribe(recipe -> {
                    // TODO: handle async.
                    this.recipe = recipe;
                    NutritionalAnalysisRequest request = NutritionalAnalysisRequest.fromRecipe(recipe);
                    edamamService
                            .getNutritionAnalysis(request)
                            .enqueue(new Callback<NutritionalAnalysisResponse>() {
                                @Override
                                public void onResponse(Call<NutritionalAnalysisResponse> call, Response<NutritionalAnalysisResponse> response) {
                                    calories = response.body().getCalories();
                                    nutrients = response.body().getTotalNutrients();
                                    servings = response.body().getYield();
                                    regenerateComponents();

                                    if (listener != null) {
                                        listener.onDataChanged();
                                    }
                                }

                                @Override
                                public void onFailure(Call<NutritionalAnalysisResponse> call, Throwable t) {

                                }
                            });
                });

        regenerateComponents();
    }

    public RecipeViewModel(Recipe recipe) {
        this.recipe = recipe;
        regenerateComponents();
    }

    public void addIngredientComponent() {
        recipe.getIngredients().add(new Ingredient("", "", recipe.getRecipeID()));
        regenerateComponents();
        listener.onDataChanged();
    }

    public void addDirectionComponent() {
        int directionOrdinal = recipe.getDirections().size() + 1;
        recipe.getDirections().add(new Direction(directionOrdinal, "", recipe.getRecipeID()));
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
        recipeComponents.add(new ImageViewModel(recipe.getImageURL(), isEditable));
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
        recipeComponents.add(new TagViewModel(recipe.getTags(), isEditable));
        Log.d("TAG", "regenerateComponents: tags: " + recipe.getTags().size());
        recipeComponents.add(new CancelFooterViewModel((isEditable)));
        if (nutrients != null) {
            recipeComponents.add(new HeaderViewModel("Nutrition Information"));
            recipeComponents.add(new NutritionViewModel(calories, nutrients, servings));
        }
    }
}

