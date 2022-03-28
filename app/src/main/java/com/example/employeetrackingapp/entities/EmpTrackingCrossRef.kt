package com.example.employeetrackingapp.entities

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey

@Entity(foreignKeys = [
        ForeignKey(entity = Employee::class,
        parentColumns = ["employeeId"],
        childColumns = ["employeeId"]),
        ForeignKey(entity = Process::class,
        parentColumns = ["processId"],
        childColumns = ["processId"])
    ])
data class EmpTrackingCrossRef(
    val employeeId : Int,
    val processId : Int,
    val empDate : Long
){
    @PrimaryKey(autoGenerate = true)
    var empTrackingCrossRefId: Int? = null
}
