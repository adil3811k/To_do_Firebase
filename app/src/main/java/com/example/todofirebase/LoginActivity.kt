package com.example.todofirebase

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.Firebase
import com.google.firebase.auth.auth

/***
 * if once you login in you will direcy go to mainActivity no need to login again and again
 * if you not login yet so first so and make a a/c first then come to login
 */
class LoginActivity : AppCompatActivity() {

    //Variable Declare
    private lateinit var Eemail:EditText
    private lateinit var Epassword:EditText
    private lateinit var butlogin:Button
    private lateinit var singup:TextView
    private val auth= Firebase.auth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        //Set content
        setContentView(R.layout.activity_login)

        //Variable initialize
        Eemail = findViewById(R.id.loginEmailEdittxt)
        Epassword  =findViewById(R.id.loginPsaawordtxt)
        butlogin = findViewById(R.id.loginButton)
        singup  = findViewById(R.id.loginSingUptxt)

        //set on click listen on Login Button
        butlogin.setOnClickListener {
            val email = Eemail.text.toString()
            val password = Epassword.text.toString()
            if (email.isNotBlank() &&password.isNotBlank() ){
                auth.signInWithEmailAndPassword(email,password)
                    .addOnSuccessListener {
                        Toast.makeText(this, "User is Login ", Toast.LENGTH_SHORT).show()
                        startMainActivity()
                    }
                    .addOnFailureListener {
                        Toast.makeText(this, "Enter the valid Email", Toast.LENGTH_SHORT).show()
                        Eemail.text.clear()
                        Epassword.text.clear()
                    }
            }else{
                Toast.makeText(this, "Enter the Email and password", Toast.LENGTH_SHORT).show()
            }
        }

        //set on click listen on sing up text
        singup.setOnClickListener {
            val intent = Intent(this,RegisterActivity::class.java)
            startActivity(intent)
            finish()
        }
    }
    //check if user login alrady user redirect to mainActivity
    override fun onStart() {
        super.onStart()
        val user = auth.currentUser
        if (user!=null){
            startMainActivity()
        }
    }

    private fun startMainActivity() {
        val intent = Intent(this,MainActivity::class.java)
        startActivity(intent)
        finish()
    }

}