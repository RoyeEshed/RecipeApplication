package com.eshed.fork.Data

import android.util.Log
import com.eshed.fork.Data.model.Recipe
import com.eshed.fork.Data.model.UserAccount
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.core.Single
import io.reactivex.rxjava3.subjects.BehaviorSubject


class DbRecipeRepository() : RecipeRepository {
    companion object {
        @JvmStatic val instance = DbRecipeRepository()
    }

    private var recipeRelay: BehaviorSubject<List<Recipe>> = BehaviorSubject.createDefault(listOf())
    private var userRelay: BehaviorSubject<List<UserAccount>> = BehaviorSubject.createDefault(listOf())
    private var currentUser: UserAccount? = null

    fun load() {
        val database = FirebaseDatabase.getInstance()
        database.getReference("/recipes").addValueEventListener(
            object : ValueEventListener {
                override fun onDataChange(dataSnapshot: DataSnapshot) {
                    Log.w("Fork", "load $dataSnapshot")
                    dataSnapshot.children
                        .map { it.getValue(Recipe::class.java)!! }
                        .let { recipeRelay.onNext(it) }
                }

                override fun onCancelled(error: DatabaseError) {
                    Log.w("Fork", "load directions:onCancelled", error.toException())
                }
            })
        loadUsers()
    }

    override fun retrieveRecipes(): Observable<List<Recipe>> {
        return recipeRelay
    }


    override fun getRecipeWithID(recipeID: Int): Single<Recipe>? {
        for (r in recipeRelay.value) {
            if (r.recipeID == recipeID) {
                return Single.just(r)
            }
        }
        return null
    }

    override fun createNewRecipe(): Recipe? {
        return Recipe(
            recipeID = (Math.random() * 1000).toInt(),
            imageURL = "gs://fork-15014.appspot.com/images/e4c84136-75af-4bda-a9dc-62de473d79b6",
            ingredients = mutableListOf(),
            directions = mutableListOf(),
            tags = mutableListOf()
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

    override fun saveRecipe(recipe: Recipe, uid: String) {
        for (user in userRelay.value) {
            if (user.uid.equals(uid)) {
                recipe.contributor = user.username
                user.submittedRecipes.add(recipe.recipeID)
                saveUser(user)
            }
        }
        val ref = FirebaseDatabase.getInstance().getReference("/recipes/" + recipe.recipeID)
        ref.setValue(recipe.toMap()).addOnSuccessListener {
            Log.d("Save Recipe", "Saved recipe to database")
        }

    }

    fun loadUsers() {
        var userList: MutableList<UserAccount> = mutableListOf()
        var currentUser: UserAccount
        val database = FirebaseDatabase.getInstance()
        database.getReference("/users/").addValueEventListener(
            object : ValueEventListener {
                override fun onDataChange(dataSnapshot: DataSnapshot) {
                    Log.w("Fork", "load $dataSnapshot")
                    dataSnapshot.children
                        .map { it.getValue(UserAccount::class.java)!! }
                        .let { userRelay.onNext(it) }
                }

                override fun onCancelled(error: DatabaseError) {
                    Log.w("Fork", "load directions:onCancelled", error.toException())
                }
            })
    }

    override fun saveUser(user: UserAccount) {
        val ref = FirebaseDatabase.getInstance().getReference("/users/" + user.uid)
        ref.setValue(user.toMap()).addOnSuccessListener {
            Log.d("Save User", "Saved user to database")
        }
    }

    override fun getUserWithUID(uid: String): Single<UserAccount>? {
        getRecipesByUser(uid)
        for (user in userRelay.value) {
            if (user.uid.equals(uid)) {
                return Single.just(user)
            }
        }
        return null
    }

    fun getRecipesByUser(uid: String): MutableList<Recipe>?{
        var user: UserAccount? = null;
        for (u in userRelay.value) {
            if (u.uid.equals(uid)) {
                user = u
            }
        }

        var userRecipes: MutableList<Recipe> = mutableListOf()
        if (user != null) {
            for (r in recipeRelay.value) {
                if (r.contributor == user.username) {
                    userRecipes.add(r)
                }
            }
            return userRecipes
        }
        return null
    }

    override fun getRecipesStarredByUser(uid: String): MutableList<Recipe>? {
        var user: UserAccount? = null;
        for (u in userRelay.value) {
            if (u.uid.equals(uid)) {
                user = u
            }
        }

        var starredRecipeIDs = user!!.starredRecipes
        var starredRecipes: MutableList<Recipe> = mutableListOf()

        for (i in starredRecipeIDs) {
            for (r in recipeRelay.value) {
                if (r.recipeID == i) {
                    starredRecipes.add(r)
                }
            }
        }

        return starredRecipes;
    }

    override fun getRecipesSubmittedByUser(uid: String?): MutableList<Recipe>? {
        var user: UserAccount? = null;
        for (u in userRelay.value) {
            if (u.uid.equals(uid)) {
                user = u
            }
        }

        var userRecipes: MutableList<Recipe> = mutableListOf()
        if (user != null) {
            for (r in recipeRelay.value) {
                if (r.contributor == user.username) {
                    userRecipes.add(r)
                }
            }
            return userRecipes
        }
        return null
    }

}