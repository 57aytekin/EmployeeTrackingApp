package com.example.employeetrackingapp.db

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.employeetrackingapp.entities.EmpTrackingCrossRef
import com.example.employeetrackingapp.entities.Employee
import com.example.employeetrackingapp.entities.EmployeeTracking
import com.example.employeetrackingapp.entities.Process

@Dao
interface EmpTrackingDao {
    @Insert()
    suspend fun saveEmpTracking(crossRef: EmpTrackingCrossRef)

    @Query("Select cros.empDate, cros.empTrackingCrossRefId ,employee.name, employee.employeeId, employee.lastName, " +
            "process.processId ,process.processName, process.difficultyLevel " +
            "from emptrackingcrossref as 'cros' " +
            "INNER JOIN employee as 'employee' on cros.employeeId = employee.employeeId " +
            "INNER JOIN process as 'process' on cros.processId = process.processId Order by cros.empTrackingCrossRefId desc")
    fun getAllCross() : LiveData<List<EmployeeTracking>>

    @Query("Select * from employee Order by employeeId desc ")
    fun getAllEmployee() : List<Employee>

    @Query("Select * from process Order by processId desc ")
    fun getAllProcess() : List<Process>
}