package com.example.employeetrackingapp.repository

import com.example.employeetrackingapp.db.EmployeeDao
import com.example.employeetrackingapp.entities.Employee
import javax.inject.Inject

class EmployeeRepository @Inject constructor(private val employeeDao: EmployeeDao) {
    suspend fun saveEmployee(employee: Employee) = employeeDao.saveEmployee(employee)
    fun getAllEmployee() = employeeDao.getAllEmployee()
}