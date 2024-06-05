package com.example.todofirebase

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.firebase.ui.database.FirebaseRecyclerAdapter
import com.firebase.ui.database.FirebaseRecyclerOptions
import com.google.firebase.Firebase
import com.google.firebase.auth.auth
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.database

/***
 * THis is Adapter for recycler view and my custom module
 */
class myadepter(
    val context: Context,option:FirebaseRecyclerOptions<taskModel>
):
    FirebaseRecyclerAdapter<taskModel,myadepter.taskviewholder>(option){
    inner class taskviewholder(itemVIew:View):RecyclerView.ViewHolder(itemVIew){
        val title = itemVIew.findViewById<TextView>(R.id.singelitemtital)
        val description = itemVIew.findViewById<TextView>(R.id.singelitemDisc)
        val detetebut = itemVIew.findViewById<ImageView>(R.id.singleDelebutton)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): taskviewholder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.singel_iterm,parent,false)
        return  taskviewholder(view)
    }

    override fun onBindViewHolder(holder: taskviewholder, position: Int, model: taskModel) {
        holder.title.text = model.tital
        holder.description.text = model.description
        val uid = Firebase.auth.currentUser!!.uid
        holder.detetebut.setOnClickListener {
            getRef(position).key?.let { it1 ->
                FirebaseDatabase.getInstance().reference.child(uid).child(it1).removeValue()
                    .addOnSuccessListener {
                        Toast.makeText(context, "delete button click where id is ${model.id}", Toast.LENGTH_LONG).show()
                    }
                    .addOnFailureListener {
                        Toast.makeText(context, "Delete Failed "+ it.message, Toast.LENGTH_LONG).show()
                    }
            }

        }
    }

}
