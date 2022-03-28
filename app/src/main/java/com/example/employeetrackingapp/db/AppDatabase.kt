package com.example.employeetrackingapp.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.employeetrackingapp.entities.EmpTrackingCrossRef
import com.example.employeetrackingapp.entities.Employee
import com.example.employeetrackingapp.entities.Process

@Database(
    entities = [Employee::class, Process::class, EmpTrackingCrossRef::class],
    exportSchema = false,
    version = 14
)
abstract class AppDatabase : RoomDatabase(){
    abstract fun processDao() : ProcessDao
    abstract fun employeeDao() : EmployeeDao
    abstract fun crossRefDao() : EmpTrackingDao
}