package com.eshed.fork.Login.view

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.eshed.fork.Data.model.UserAccount
import com.eshed.fork.R
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.FirebaseDatabase
import kotlinx.android.synthetic.main.activity_register.*

class RegisterActivity: AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)

        Log.d("RegisterActivity", "And who is this?" + FirebaseAuth.getInstance().uid)

        registerButton.setOnClickListener {
            performRegister()
        }
    }

    private fun performRegister() {
        val email = emailEditText2.text.toString()
        val password = passwordEditText2.text.toString()

        if (email.isEmpty() || password.isEmpty()) {
            Toast.makeText(this, "Please enter text in email/pw", Toast.LENGTH_SHORT).show()
            return
        }


        FirebaseAuth.getInstance().createUserWithEmailAndPassword(email, password)
            .addOnCompleteListener{
                if (!it.isSuccessful) return@addOnCompleteListener

                Log.d("Main", "Successfully created user with uid: ${it.result?.user?.uid}")
                Toast.makeText(this, "Created User Successfully", Toast.LENGTH_SHORT).show()

                saveUserToFirebaseDatabase()
                val intent = Intent(this, LoginActivity::class.java)
                startActivity(intent)
            }
            .addOnFailureListener {
                Log.d("Main", "Badly formatted email, failed to create user")
                Toast.makeText(this, "Failed to create user. ${it.message}", Toast.LENGTH_LONG).show()
            }
    }

    private fun saveUserToFirebaseDatabase() {
        val uid = FirebaseAuth.getInstance().uid ?: ""
        val ref = FirebaseDatabase.getInstance().getReference("/users/$uid")

        val username = usernameEditText.text.toString()
        val email = emailEditText2.text.toString()
        val imageURL = "gs://fork-15014.appspot.com/images/users/702e824c-e20f-4122-bf0e-72abb424117e.jpg"
        val favoritedRecipes: MutableList<Int> = mutableListOf()
        val submittedRecipes: MutableList<Int> = mutableListOf()
        val user: UserAccount

        if (usernameEditText.text.toString().isEmpty()) {
            user = UserAccount(email, uid, "username", imageURL, favoritedRecipes, submittedRecipes)
        } else {
            user = UserAccount(email, uid, username, imageURL, favoritedRecipes, submittedRecipes)
        }

        ref.setValue(user)
            .addOnSuccessListener {
                Log.d("RegisterActivity", "Finally added user to database")
            }
    }
}
//class User(val uid: String, val username: String, val email: String, val userInfo: String)
