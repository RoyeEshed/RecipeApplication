package com.eshed.fork.Data

import com.eshed.fork.Data.model.Direction
import com.eshed.fork.Data.model.Ingredient
import com.eshed.fork.Data.model.Recipe
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.core.Single
import io.reactivex.rxjava3.subjects.BehaviorSubject

class DebugRecipeRepository : RecipeRepository {

    private val recipeRelay: BehaviorSubject<List<Recipe>>
            = BehaviorSubject.createDefault(listOf())

    override fun getRecipes(): Observable<List<Recipe>> {
        recipeRelay.onNext(Companion.recipes)
        return recipeRelay
    }

    override fun getRecipeWithID(recipeID: Int): Single<Recipe?> {
        for (recipe in Companion.recipes) {
            if (recipe.recipeID == recipeID) {
                return Single.just(recipe.copy())
            }
        }
        return Single.just(null)
    }

    override fun createNewRecipeFromRecipe(recipe: Recipe, newName: String): Recipe {
        return recipe.deepCopy(
            recipeID = (Math.random() * 1000).toInt(),
            name = newName,
            parentRecipeID = recipe.recipeID
        )
    }

    override fun createNewRecipe(): Recipe {
        return Recipe(
            recipeID = (Math.random() * 1000).toInt(),
            imageURL = "https://images.unsplash.com/photo-1517870662726-c1d98ee36250?ixlib=rb-1.2.1&ixid=eyJhcHBfaWQiOjEyMDd9&auto=format&fit=crop&w=2700&q=80"
        )
    }

    override fun saveRecipe(recipe: Recipe) {
        Companion.recipes.add(recipe)
        recipeRelay.onNext(Companion.recipes)
    }

    companion object {
        @JvmStatic
        val instance = DebugRecipeRepository()

        @JvmStatic
        private val recipes: MutableList<Recipe> = mutableListOf(
            Recipe(
                1,
                "Scrambled Eggs",
                "https://images.unsplash.com/photo-1525184782196-8e2ded604bf7?ixlib=rb-1.2.1&ixid=eyJhcHBfaWQiOjEyMDd9&auto=format&fit=crop&w=2767&q=80",
                "user",
                mutableListOf(
                    Ingredient("2", "Eggs"),
                    Ingredient("1/4 cup", "Milk"),
                    Ingredient("To taste", "Salt"),
                    Ingredient("To taste", "Pepper"),
                    Ingredient("1 Tbsp", "Olive Oil")
                ),
                mutableListOf(
                    Direction(
                        1,
                        "Get a pan and set it on a stovetop over   low to medium heat"
                    ),
                    Direction(
                        2,
                        "Pour in enough olive oil to coat the pan and swirl it around to ensure that the pan is fully coated"
                    ),
                    Direction(
                        3,
                        "While the pan and olive oil is heating up, crack two to three eggs in a bowl"
                    ),
                    Direction(4, "Pour in milk"),
                    Direction(
                        5,
                        "Throw in a pinch of salt and pepper"
                    ),
                    Direction(
                        6,
                        "Beat the mixture together until homogeneous"
                    ),
                    Direction(7, "Pour the mixture into the pan"),
                    Direction(
                        8,
                        "Turn up the heat to medium high"
                    ),
                    Direction(
                        9,
                        "While in the pan, with a spatula or fork, scrape the sides of the pan and mix the curds and raw egg in the center."
                    ),
                    Direction(
                        10,
                        "Continue mixing and moving the egg around with the spatula until the eggs become solid"
                    ),
                    Direction(
                        11,
                        "Pour onto a plate and let rest for a minute or two before eating"
                    )
                ),
                mutableListOf("Breakfast, Vegetarian, Eggs")
            ),
            Recipe(
                2,
                "Pesto",
                "https://images.unsplash.com/photo-1592571832175-cbbedad399a6?ixlib=rb-1.2.1&ixid=eyJhcHBfaWQiOjEyMDd9&auto=format&fit=crop&w=2700&q=80",
                "user",
                mutableListOf(
                    Ingredient("1/4 cup", "Basil"),
                    Ingredient("1/2 cup", "Parmesan cheese"),
                    Ingredient("2", "Garlic cloves"),
                    Ingredient("1/4 cup", "Pine nuts"),
                    Ingredient("1/2 cupt", "Olive oil")
                ),
                mutableListOf(
                    Direction(
                        1,
                        "In a food processor combine basil, pine nuts, and garlic"
                    ),
                    Direction(
                        2,
                        "Slowly add in olive oil, processing until smooth"
                    ),
                    Direction(
                        3,
                        "Add parmesan and continue processing just until combined"
                    )
                ),
                mutableListOf("Vegetarian, Dinner, Italian")
            ),
            Recipe(
                3,
                "Oatmeal",
                "https://images.unsplash.com/photo-1586420362117-e448a3dd5ef5?ixlib=rb-1.2.1&ixid=eyJhcHBfaWQiOjEyMDd9&auto=format&fit=crop&w=2700&q=80",
                "user",
                mutableListOf(
                    Ingredient("1 cup", "Rolled Oats"),
                    Ingredient("2 cup", "Almond Milk"),
                    Ingredient("1/2 tsp", "Vanilla extract"),
                    Ingredient("Pinch", "Salt")
                ),
                mutableListOf(
                    Direction(
                        1,
                        "Combine all ingredients into a small saucepan and heat at medium-high."
                    ),
                    Direction(
                        2,
                        "Bring to a boil. Lower heat to medium-low and stir continuously for 3-5 minutes until oatmeal thickens"
                    ),
                    Direction(
                        3,
                        "Remove from heat, and add any additional toppings."
                    )
                ),
                mutableListOf("Vegetarian, Vegan, Breakfast")
            )
        )
    }
}