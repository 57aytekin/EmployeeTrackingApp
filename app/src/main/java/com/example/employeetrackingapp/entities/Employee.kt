package com.example.employeetrackingapp.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Employee(
    val name : String,
    val lastName: String
) {
    @PrimaryKey(autoGenerate = true)
    var employeeId: Int? = null
}