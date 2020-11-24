package com.eshed.fork.Data

import android.util.Log
import com.eshed.fork.Data.model.Direction
import com.eshed.fork.Data.model.Ingredient
import com.eshed.fork.Data.model.Recipe
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.core.Single
import io.reactivex.rxjava3.subjects.BehaviorSubject
import java.util.*


class DbRecipeRepository() : RecipeRepository {
    companion object {
        @JvmStatic val instance = DbRecipeRepository()
    }

    private var recipes: MutableList<Recipe> = mutableListOf()
    private var ingredientsMap: MutableMap<Int, MutableList<Ingredient>> = HashMap()
    private var directionsMap: MutableMap<Int, MutableList<Direction>> = HashMap()
    private var ingredients: MutableList<Ingredient> = mutableListOf()
    private var directions: MutableList<Direction> = mutableListOf()
    private var recipeRelay: BehaviorSubject<List<Recipe>> = BehaviorSubject.createDefault(listOf())

    fun load() {
        val database = FirebaseDatabase.getInstance()
        database.getReference("/ingredients").addValueEventListener(
            object : ValueEventListener {
                override fun onDataChange(dataSnapshot: DataSnapshot) {
                    Log.w("Fork", "load $dataSnapshot")
                    ingredients.clear()
                    for (data in dataSnapshot.children) {
                        val ingredient = data.getValue(Ingredient::class.java)
                        ingredients.add(ingredient!!)

                    }
                    addIngredientsToMap(ingredients);
                }
                override fun onCancelled(error: DatabaseError) {
                    Log.w("Fork", "load ingredients:onCancelled", error.toException())
                }
            })

        database.getReference("/directions").addValueEventListener(
            object : ValueEventListener {
                override fun onDataChange(dataSnapshot: DataSnapshot) {
                    Log.w("Fork", "load $dataSnapshot")
                    directions.clear()
                    for (data in dataSnapshot.children) {
                        val direction = data.getValue(Direction::class.java)
//                        keys[item] = data.key
                        directions.add(direction!!)
                    }
                    addDirectionsToMap(directions)
                }

                override fun onCancelled(error: DatabaseError) {
                    Log.w("Fork", "load directions:onCancelled", error.toException())
                }
            })

        database.getReference("/recipes").addValueEventListener(
            object : ValueEventListener {
                override fun onDataChange(dataSnapshot: DataSnapshot) {
                    Log.w("Fork", "load $dataSnapshot")
                    recipes.clear()
                    for (data in dataSnapshot.children) {
                        val recipe = data.getValue(Recipe::class.java)
                        recipes.add(recipe!!)
                    }
                    addDirectionsToRecipes()
                    addIngredientsToRecipes()
                }

                override fun onCancelled(error: DatabaseError) {
                    Log.w("Fork", "load directions:onCancelled", error.toException())
                }
            })
    }

    override fun retrieveRecipes(): Observable<List<Recipe>> {
        recipeRelay.onNext(recipes)
        return recipeRelay
    }


    override fun getRecipeWithID(recipeID: Int): Single<Recipe>? {
        for (r in recipes) {
            if (r.recipeID == recipeID) {
                return Single.just(r)
            }
        }
        return null
    }

    override fun createNewRecipe(): Recipe? {
        return Recipe(
            recipeID = (Math.random() * 1000).toInt(),
            imageURL = "https://images.unsplash.com/photo-1517870662726-c1d98ee36250?ixlib=rb-1.2.1&ixid=eyJhcHBfaWQiOjEyMDd9&auto=format&fit=crop&w=2700&q=80",
            ingredients = mutableListOf(),
            directions = mutableListOf(),
            tags = mutableListOf(" ")
        )
    }

    override fun createNewRecipeFromRecipe(recipe: Recipe, newName: String): Recipe? {
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

    override fun saveRecipe(recipe: Recipe) {
        saveIngredients(recipe)
        saveDirections(recipe)

        val ref = FirebaseDatabase.getInstance().getReference("/recipes/" + recipe.recipeID)
        ref.setValue(recipe.toMap()).addOnSuccessListener {
                Log.d("Save Recipe", "Saved recipe to database")
        }
    }

    fun saveIngredients(recipe: Recipe) {
        val ref = FirebaseDatabase.getInstance().getReference("/ingredients/")
        for (i in recipe.ingredients) {
            val key = ref.child("posts").push().key
            if (key == null) {
                Log.w("TAG", "Couldn't get push key for posts")
                return
            }

            val ingredientValues = i.toMap()

            val childUpdates = hashMapOf<String, Any>(
                "$key" to ingredientValues
            )

            ref.updateChildren(childUpdates)
        }
    }

    fun saveDirections(recipe: Recipe) {
        val ref = FirebaseDatabase.getInstance().getReference("/directions/")
        for (d in recipe.directions) {
            val key = ref.child("posts").push().key
            if (key == null) {
                Log.w("TAG", "Couldn't get push key for posts")
                return
            }

            val directionsValues = d.toMap()

            val childUpdates = hashMapOf<String, Any>(
                "$key" to directionsValues
            )

            ref.updateChildren(childUpdates)
        }
    }

    fun addIngredientsToMap(list: MutableList<Ingredient>) {
        for (i in list) {
            val recipeID = i.recipeID
            var temp = ingredientsMap.get(recipeID);
            if (temp != null) {
                temp.add(i)
            } else {
                temp = mutableListOf(i)
            }
            ingredientsMap[recipeID] = temp
        }
    }

    fun addDirectionsToMap(list: MutableList<Direction>) {
        for (d in list) {
            val recipeID = d.recipeID
            var temp: MutableList<Direction>? = directionsMap.get(recipeID);
            if (temp != null) {
                temp.add(d)
            } else {
                temp = mutableListOf(d)
            }
            directionsMap[recipeID] = temp
        }
    }

    fun addDirectionsToRecipes() {
        for (r in recipes) {
            if (directionsMap[r.recipeID] != null) {
                r.directions = directionsMap[r.recipeID]!!
            }
        }
    }

    fun addIngredientsToRecipes() {
        for (r in recipes) {
            if (ingredientsMap[r.recipeID] != null) {
                r.ingredients = ingredientsMap[r.recipeID]!!
            }
        }
    }

}