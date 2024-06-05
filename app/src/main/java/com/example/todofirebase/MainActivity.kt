package com.example.todofirebase

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.ImageButton
import android.widget.LinearLayout
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.firebase.ui.database.FirebaseRecyclerOptions
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.firebase.Firebase
import com.google.firebase.FirebaseOptions
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.auth
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener

class MainActivity : AppCompatActivity() {
    //initialize the all use element
    private lateinit var recyclerView: RecyclerView
    private lateinit var addbut:FloatingActionButton
    private lateinit var logoutbut:ImageButton
    private lateinit var adpter:myadepter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        //Define FirebaseRecyclerOptions for recyclerview option

        //Define initialize element
        setContentView(R.layout.activity_main)
        addbut = findViewById(R.id.addingbut)
        recyclerView = findViewById(R.id.recyclerView)
        logoutbut = findViewById(R.id.logoutbut)
        val databse=  FirebaseDatabase.getInstance().reference.child(Firebase.auth.currentUser!!.uid)
        val oprion :FirebaseRecyclerOptions<taskModel> = FirebaseRecyclerOptions.Builder<taskModel>().setQuery(databse,taskModel::class.java).build()
        adpter = myadepter(this,oprion)

        //set attribute for recyclerview
        recyclerView.setHasFixedSize(false)
        recyclerView.layoutManager=LinearLayoutManager(this)

        // set adepter
        recyclerView.adapter =adpter

        //add functionality on add flotation button[fab button]
        addbut.setOnClickListener {
            val intent = Intent(this,AddingActivity::class.java)
            startActivity(intent)
        }

        //add functionality on logout Imageview
        logoutbut.setOnClickListener {
            Firebase.auth.signOut()
            val intent = Intent(this,LoginActivity::class.java)
            startActivity(intent)
            finish()
        }

    }

    override fun onStart() {
        super.onStart()
        adpter.startListening()
    }

    override fun onStop() {
        super.onStop()
        adpter.stopListening()
    }
}