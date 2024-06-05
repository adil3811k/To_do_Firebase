package com.example.todofirebase

import android.app.ProgressDialog
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.ProgressBar
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.google.firebase.Firebase
import com.google.firebase.auth.auth
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.database

/***
 * this activity for sing up
 * it mean if you hav not A/c you need to First creat a A/c usein Email and password and name
 */
private const val USER= "user"
class RegisterActivity : AppCompatActivity() {
    //variables dilation
    private lateinit var Eemail:EditText
    private lateinit var Epassword:EditText
    private lateinit var Ename:EditText
    private lateinit var registebut:Button
    private val auth = Firebase.auth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        //set content
        setContentView(R.layout.activity_register)

        //variable initialize
        Eemail = findViewById(R.id.registerEmailtxt)
        Epassword = findViewById(R.id.registerPasswordtxt)
        Ename = findViewById(R.id.registeNametxt)
        registebut = findViewById(R.id.registerButton)
        val databaeReffrence =Firebase.database.getReference(USER)
        val progressDialog=ProgressDialog(this)
        progressDialog.setTitle("Registration in process")
        progressDialog.create()


        registebut.setOnClickListener {
            progressDialog.show()
            val email = Eemail.text.toString()
            val password = Epassword.text.toString()
            val name = Ename.text.toString()
            //most important line to create user
            auth.createUserWithEmailAndPassword(email,password)
                .addOnSuccessListener {
                    Toast.makeText(this, "user successfully register", Toast.LENGTH_SHORT).show()
                    databaeReffrence.child("uid").setValue(auth.currentUser!!.uid)
                    progressDialog.hide()
                    startActivity(Intent(this,MainActivity::class.java))
                    finish()
                }
                .addOnFailureListener {
                    Toast.makeText(this, "Enter correct user Email", Toast.LENGTH_SHORT).show()
                    progressDialog.hide()
                }
        }
    }
}