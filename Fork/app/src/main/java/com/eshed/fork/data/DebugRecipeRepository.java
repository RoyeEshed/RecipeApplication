package com.eshed.fork.data;

import com.eshed.fork.R;
import com.eshed.fork.data.model.Direction;
import com.eshed.fork.data.model.Ingredient;
import com.eshed.fork.data.model.Recipe;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class DebugRecipeRepository implements RecipeRepository {
    private static DebugRecipeRepository instance = new DebugRecipeRepository();

    public static DebugRecipeRepository getInstance() {
        return instance;
    }

    private static List<Recipe> recipes  = new ArrayList<>(Arrays.asList(
            new Recipe(1,"Scrambled Eggs", R.drawable.eggs, "Roye",
                    new ArrayList<>(Arrays.asList(new Ingredient("2", "Eggs"),
                            new Ingredient("1/4 cup", "Milk"),
                            new Ingredient("To taste","Salt"),
                            new Ingredient("To taste","Pepper"),
                            new Ingredient("1 Tbsp","Olive Oil"))),
                    new ArrayList<>(Arrays.asList(new Direction(1, "Get a pan and set it on a stovetop over   low to medium heat"),
                            new Direction(2,"Pour in enough olive oil to coat the pan and swirl it around to ensure that the pan is fully coated"),
                            new Direction(3,"While the pan and olive oil is heating up, crack two to three eggs in a bowl"),
                            new Direction(4,"Pour in milk"),
                            new Direction(5,"Throw in a pinch of salt and pepper"),
                            new Direction(6,"Beat the mixture together until homogeneous"),
                            new Direction(7,"Pour the mixture into the pan"),
                            new Direction(8,"Turn up the heat to medium high"),
                            new Direction(9,"While in the pan, with a spatula or fork, scrape the sides of the pan and mix the curds and raw egg in the center."),
                            new Direction(10,"Continue mixing and moving the egg around with the spatula until the eggs become solid"),
                            new Direction(11,"Pour onto a plate and let rest for a minute or two before eating"))),
                    new ArrayList<>(Arrays.asList("Breakfast, Vegetarian, Eggs"))),
            new Recipe(2,"Pesto", R.drawable.eggs, "Ashley",
                    new ArrayList<>(Arrays.asList(new Ingredient("1/4 cup", "Basil"),
                            new Ingredient("1/2 cup", "Parmesan cheese"),
                            new Ingredient("2", "Garlic cloves"),
                            new Ingredient("1/4 cup", "Pine nuts"),
                            new Ingredient("1/2 cupt", "Olive oil"))),
                    new ArrayList<>(Arrays.asList(new Direction(1, "In a food processor combine basil, pine nuts, and garlic"),
                         new Direction(2,"Slowly add in olive oil, processing until smooth"),
                         new Direction(3,"Add parmesan and continue processing just until combined"))),
                    new ArrayList<>(Arrays.asList("Vegetarian, Dinner, Italian")))
//            new Recipe(3,"Oatmeal", R.drawable.ic_launcher_foreground, "- Rolled Oats\n- Milk\n- Water\n- Salt\n- Cinnamon\n- Other Toppings",
//                    "1. Mix oats, milk, water, salt, and cinnamon in a medium saucepan. Bring to a boil, then reduce heat to low.\n"
//                    + "2. Simmer for 3 - 5 minutes. Stirring occasionally.\n"
//                    + "3. Remove from heat and add additional desired toppings\n",
//                    "Breakfast, Vegetarian"),
//            new Recipe(4,"Vegetable Soup", R.drawable.ic_launcher_foreground, "- Can of tomatoes\n- Can of vegetable broth\n- Water\n- Diced potato\n- Sliced carrot\n- Diced celery\n- Salt and pepper\n",
//                    "1. Combine broth, tomatoes, and water in a large pot. Bring to boil\n"
//                    + "2. Add remaining ingredients\n"
//                    + "3. Simmer for at least 30 minutes\n",
//                    "Vegan, Dinner, Lunch")
    ));

    public List<Recipe> getRecipes() {
        return recipes;
    }

    public Recipe getRecipeWithName(String recipeName) {
        for (Recipe recipe: recipes) {
            if (recipe.getName().equals(recipeName)) {
                return recipe;
            }
        }
        return null;
    }
    public Recipe getRecipeWithID(int recipeID) {
        for (Recipe recipe: recipes) {
            if (recipe.getRecipeID() == recipeID) {
                return recipe;
            }
        }
        return null;
    }

    public void addRecipe(Recipe recipe) {
        recipes.add(recipe);
    }
    public int getSize() {
        return recipes.size();
    }
}
