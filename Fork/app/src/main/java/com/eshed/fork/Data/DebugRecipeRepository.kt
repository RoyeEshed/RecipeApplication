package com.eshed.fork.Data

import android.util.Log
import com.eshed.fork.Data.model.*
import com.google.firebase.database.FirebaseDatabase
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.core.Single
import io.reactivex.rxjava3.subjects.BehaviorSubject

class DebugRecipeRepository : RecipeRepository {

    private val recipeRelay: BehaviorSubject<List<Recipe>>
            = BehaviorSubject.createDefault(listOf())

    override fun retrieveRecipes(): Observable<List<Recipe>> {
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
        var newRecipe = recipe.deepCopy(
            recipeID = (Math.random() * 1000).toInt(),
            name = newName,
            parentRecipeID = recipe.recipeID
        )
        for (i in newRecipe.ingredients) {
            i.recipeID = newRecipe.recipeID
        }

        for (d in newRecipe.directions) {
            d.recipeID = newRecipe.recipeID
        }

        return newRecipe
    }

    override fun retrieveChildrenOfRecipe(parentRecipeID: Int): Observable<MutableList<Recipe>> {
        TODO("Not yet implemented")
    }

    override fun getRecipesStarredByUser(uid: String?): MutableList<Recipe> {
        TODO("Not yet implemented")
    }

    override fun saveUser(user: UserAccount?) {
        TODO("Not yet implemented")
    }

    override fun createNewRecipe(): Recipe {
        return Recipe(
            recipeID = (Math.random() * 1000).toInt(),
            imageURL = "https://images.unsplash.com/photo-1517870662726-c1d98ee36250?ixlib=rb-1.2.1&ixid=eyJhcHBfaWQiOjEyMDd9&auto=format&fit=crop&w=2700&q=80"
        )
    }

    override fun saveRecipe(recipe: Recipe, uid: String) {
        Companion.recipes.add(recipe)
        recipeRelay.onNext(Companion.recipes)
        val db: DbRecipeRepository = DbRecipeRepository()
        val ref = FirebaseDatabase.getInstance().getReference("/recipes/" + recipe.recipeID)
        ref.setValue(recipe).addOnSuccessListener {
            Log.d("TAG", "saveRecipe: to firebase?")
        }
        db.saveRecipe(recipe, "")
        db.load()

    }

    override fun getRecipesSubmittedByUser(uid: String?): MutableList<Recipe> {
        TODO("Not yet implemented")
    }

    override fun getUserWithUID(uid: String?): Single<UserAccount> {
        TODO("Not yet implemented")
    }

    override fun numberOfChildren(recipe: Recipe): Int {
        return 0
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
                    Ingredient("2", "Eggs", 1),
                    Ingredient("1/4 cup", "Milk", 1),
                    Ingredient("To taste", "Salt", 1),
                    Ingredient("To taste", "Pepper", 1),
                    Ingredient("1 Tbsp", "Olive Oil", 1)
                ),
                mutableListOf(
                    Direction(
                        1,
                        "Get a pan and set it on a stovetop over low to medium heat",
                        1
                    ),
                    Direction(
                        2,
                        "Pour in enough olive oil to coat the pan and swirl it around to ensure that the pan is fully coated",
                        1
                    ),
                    Direction(
                        3,
                        "While the pan and olive oil is heating up, crack two to three eggs in a bowl",
                        1
                    ),
                    Direction(4, "Pour in milk"),
                    Direction(
                        5,
                        "Throw in a pinch of salt and pepper",
                        1
                    ),
                    Direction(
                        6,
                        "Beat the mixture together until homogeneous",
                        1
                    ),
                    Direction(
                        7,
                        "Pour the mixture into the pan",
                        1
                    ),
                    Direction(
                        8,
                        "Turn up the heat to medium high",
                        1
                    ),
                    Direction(
                        9,
                        "While in the pan, with a spatula or fork, scrape the sides of the pan and mix the curds and raw egg in the center.",
                        1
                    ),
                    Direction(
                        10,
                        "Continue mixing and moving the egg around with the spatula until the eggs become solid",
                        1
                    ),
                    Direction(
                        11,
                        "Pour onto a plate and let rest for a minute or two before eating",
                        1
                    )
                ),
                mutableListOf(Comment("abcd","Andy Pakriel", "", 1)),
                mutableListOf("Breakfast, Vegetarian, Eggs")
            ),
            Recipe(
                2,
                "Pesto",
                "https://images.unsplash.com/photo-1592571832175-cbbedad399a6?ixlib=rb-1.2.1&ixid=eyJhcHBfaWQiOjEyMDd9&auto=format&fit=crop&w=2700&q=80",
                "user",
                mutableListOf(
                    Ingredient("1/4 cup", "Basil", 2),
                    Ingredient("1/2 cup", "Parmesan cheese", 2),
                    Ingredient("2", "Garlic cloves", 2),
                    Ingredient("1/4 cup", "Pine nuts", 2),
                    Ingredient("1/2 cupt", "Olive oil", 2)
                ),
                mutableListOf(
                    Direction(
                        1,
                        "In a food processor combine basil, pine nuts, and garlic",
                        2
                    ),
                    Direction(
                        2,
                        "Slowly add in olive oil, processing until smooth",
                        2
                    ),
                    Direction(
                        3,
                        "Add parmesan and continue processing just until combined",
                        2
                    )
                ),
                mutableListOf(Comment("abcd","Andy Pakriel", "", 2)),
                mutableListOf("Vegetarian, Dinner, Italian")
            ),
            Recipe(
                3,
                "Oatmeal",
                "https://images.unsplash.com/photo-1586420362117-e448a3dd5ef5?ixlib=rb-1.2.1&ixid=eyJhcHBfaWQiOjEyMDd9&auto=format&fit=crop&w=2700&q=80",
                "user",
                mutableListOf(
                    Ingredient("1 cup", "Rolled Oats", 3),
                    Ingredient("2 cup", "Almond Milk", 3),
                    Ingredient("1/2 tsp", "Vanilla extract", 3),
                    Ingredient("Pinch", "Salt", 3)
                ),
                mutableListOf(
                    Direction(
                        1,
                        "Combine all ingredients into a small saucepan and heat at medium-high.",
                        3
                    ),
                    Direction(
                        2,
                        "Bring to a boil. Lower heat to medium-low and stir continuously for 3-5 minutes until oatmeal thickens",
                        3
                    ),
                    Direction(
                        3,
                        "Remove from heat, and add any additional toppings.",
                        3
                    )
                ),
                mutableListOf(Comment("abcd","Andy Pakriel", "", 3)),
                mutableListOf("Vegetarian, Vegan, Breakfast")
            )
        )
    }
}