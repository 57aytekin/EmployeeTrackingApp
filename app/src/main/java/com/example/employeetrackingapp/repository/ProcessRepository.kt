package com.example.employeetrackingapp.repository

import com.example.employeetrackingapp.db.ProcessDao
import com.example.employeetrackingapp.entities.Process
import javax.inject.Inject

class ProcessRepository @Inject constructor(private val processDao: ProcessDao) {
    suspend fun saveProcessRepo(process: Process) = processDao.saveProcessDao(process)
    fun getAllProcess() = processDao.getAllProcess()
}