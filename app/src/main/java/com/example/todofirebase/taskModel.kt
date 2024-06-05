package com.example.todofirebase

/***
 * This this data clas module for storing fetch  in fireball
 */
data class taskModel(
    val tital:String? = null,
    val description:String? =null,
    val id: String? =null,
    val isDone:Boolean? = false
){
    fun getmap():Map<String?,Any?>{
        return mapOf(
            "tital" to tital,
            "description" to description,
            "id" to id,
            "isDone" to isDone
        )
    }
}