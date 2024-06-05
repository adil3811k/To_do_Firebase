package com.example.todofirebase

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.google.firebase.Firebase
import com.google.firebase.auth.auth
import com.google.firebase.database.FirebaseDatabase

/***
 * This activity take task and upload to upload to fire base realtime database with random key
 */
class AddingActivity : AppCompatActivity() {
    private lateinit var task:EditText
    private lateinit var discriprion:EditText
    private lateinit var but:Button
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_adding)
        task =findViewById(R.id.addTitletxt)
        discriprion = findViewById(R.id.addDiscriptiontxt)
        but = findViewById(R.id.addbut)
        val auth = Firebase.auth
        val reffrence = FirebaseDatabase.getInstance().reference.child(auth.currentUser!!.uid)

        but.setOnClickListener {
            val tital= task.text.toString()
            val discription = discriprion.text.toString()
            val key = reffrence.push().key
            reffrence.push().setValue(taskModel(tital,discription,key).getmap())
                .addOnSuccessListener {
                    Toast.makeText(this, "Your task added", Toast.LENGTH_SHORT).show()
                    startActivity(Intent(this,MainActivity::class.java))
                    finish()
                }
                .addOnFailureListener {
                    Toast.makeText(this, "Something this wrong", Toast.LENGTH_SHORT).show()
                }

        }

    }
}