package com.example.employeetrackingapp.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Process(
    val processName : String,
    val difficultyLevel : Int
){
    @PrimaryKey(autoGenerate = true)
    var processId: Int? = null
}