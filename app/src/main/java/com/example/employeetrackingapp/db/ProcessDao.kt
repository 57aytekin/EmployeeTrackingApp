package com.example.employeetrackingapp.db

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.employeetrackingapp.entities.Employee
import com.example.employeetrackingapp.entities.Process

@Dao
interface ProcessDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun saveProcessDao(process: Process)

    @Query("Select * from process Order by processId desc ")
    fun getAllProcess() : LiveData<List<Process>>
}