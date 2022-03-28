package com.example.employeetrackingapp.repository

import com.example.employeetrackingapp.db.EmpTrackingDao
import com.example.employeetrackingapp.entities.EmpTrackingCrossRef
import javax.inject.Inject

class EmpTrackRepository @Inject constructor(private val empTrackingDao: EmpTrackingDao) {
    suspend fun saveEmpTracking(crossRef: EmpTrackingCrossRef) = empTrackingDao.saveEmpTracking(crossRef)
    fun getALlCrossRef() = empTrackingDao.getAllCross()

    fun getAllEmp() = empTrackingDao.getAllEmployee()
    fun getAllProcess() = empTrackingDao.getAllProcess()
}