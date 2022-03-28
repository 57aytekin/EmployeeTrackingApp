package com.example.employeetrackingapp.viewmodel

import android.app.Application
import androidx.lifecycle.viewModelScope
import com.example.employeetrackingapp.base.BaseViewModel
import com.example.employeetrackingapp.entities.Process
import com.example.employeetrackingapp.repository.ProcessRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ProcessViewModel @Inject constructor(
    application: Application,
    private val processRepository: ProcessRepository
) : BaseViewModel(application) {
    //Process
    fun saveProcess(process: Process) = viewModelScope.launch {
        processRepository.saveProcessRepo(process)
    }
    fun getAllProcess() = processRepository.getAllProcess()
}