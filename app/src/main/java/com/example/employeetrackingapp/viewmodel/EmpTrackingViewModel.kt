package com.example.employeetrackingapp.viewmodel

import android.app.Application
import androidx.lifecycle.viewModelScope
import com.example.employeetrackingapp.base.BaseViewModel
import com.example.employeetrackingapp.entities.EmpTrackingCrossRef
import com.example.employeetrackingapp.entities.Employee
import com.example.employeetrackingapp.entities.Process
import com.example.employeetrackingapp.repository.EmpTrackRepository
import com.example.employeetrackingapp.repository.EmployeeRepository
import com.example.employeetrackingapp.repository.ProcessRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class EmpTrackingViewModel @Inject constructor(
    application: Application,
    private val empTrackRepository: EmpTrackRepository
) : BaseViewModel(application) {
    fun saveEmpTracking(crossRef: EmpTrackingCrossRef) = viewModelScope.launch {
        empTrackRepository.saveEmpTracking(crossRef)
    }
    fun getAllCrossRef() = empTrackRepository.getALlCrossRef()

    fun getAllEmployee() = empTrackRepository.getAllEmp()


    fun getAllProcess() = empTrackRepository.getAllProcess()
}