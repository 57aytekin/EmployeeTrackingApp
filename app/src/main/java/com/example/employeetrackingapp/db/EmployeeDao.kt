package com.example.employeetrackingapp.db

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.employeetrackingapp.entities.Employee

@Dao
interface EmployeeDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun saveEmployee(employee: Employee)

    @Query("Select * from employee Order by employeeId desc ")
    fun getAllEmployee() : LiveData<List<Employee>>
}