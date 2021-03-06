package com.eshed.fork.Login.view

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.eshed.fork.Browse.view.BrowseActivity
import com.eshed.fork.Fork
import com.eshed.fork.R
import com.google.firebase.auth.FirebaseAuth
import kotlinx.android.synthetic.main.activity_login.*

class LoginActivity : AppCompatActivity()  {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)



        loginButton.setOnClickListener {
            val email = emailEditText.text.toString()
            val password = passwordEditText.text.toString()
            Log.d("MainActivity","Password is " + password)
            Log.d("MainActivity","Email is " + email)

            FirebaseAuth.getInstance().signInWithEmailAndPassword(email, password)
                .addOnCompleteListener {
                    if (!it.isSuccessful) return@addOnCompleteListener

                    val uid = it.getResult()!!.user!!.uid
                    val app: Fork = application as Fork
                    app.uid = uid

                    val intent = Intent(this, BrowseActivity::class.java)
                    intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TASK.or(Intent.FLAG_ACTIVITY_NEW_TASK)
                    startActivity(intent)
                }
                .addOnFailureListener {
                    Toast.makeText(this, "Please enter valid email and/or password", Toast.LENGTH_SHORT).show()
                }
        }

        registerAccountTextView.setOnClickListener {
            Log.d("MainActivity","try to show log in activity")

            val intent = Intent(this, RegisterActivity::class.java)
            startActivity(intent)
        }
    }
}