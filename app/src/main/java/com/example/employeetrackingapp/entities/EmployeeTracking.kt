package com.example.employeetrackingapp.entities

import androidx.room.Embedded
import androidx.room.Junction
import androidx.room.Relation

data class EmployeeTracking(
    val empTrackingCrossRefId: Int,
    val employeeId : Int,
    val processId : Int,
    val name : String,
    val lastName : String,
    val processName : String,
    val difficultyLevel: Int,
    val empDate : Long
)
