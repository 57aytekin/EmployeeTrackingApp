package com.example.employeetrackingapp.viewmodel

import android.app.Application
import androidx.lifecycle.viewModelScope
import com.example.employeetrackingapp.base.BaseViewModel
import com.example.employeetrackingapp.entities.Employee
import com.example.employeetrackingapp.repository.EmployeeRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class EmployeeViewModel @Inject constructor(
    application: Application,
    private val employeeRepository: EmployeeRepository
) : BaseViewModel(application) {
    //Employee
    fun saveEmployee(employee: Employee) = viewModelScope.launch {
        employeeRepository.saveEmployee(employee)
    }
    fun getAllEmployee() = employeeRepository.getAllEmployee()

}