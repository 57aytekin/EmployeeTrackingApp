package com.example.employeetrackingapp.ui.process

import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.activity.viewModels
import com.example.employeetrackingapp.R
import com.example.employeetrackingapp.base.BaseActivity
import com.example.employeetrackingapp.databinding.ActivityAddProcessBinding
import com.example.employeetrackingapp.entities.Process
import com.example.employeetrackingapp.viewmodel.EmpTrackingViewModel
import com.example.employeetrackingapp.viewmodel.ProcessViewModel
import dagger.hilt.android.AndroidEntryPoint
import java.lang.Exception

@AndroidEntryPoint
class AddProcessActivity : BaseActivity<ActivityAddProcessBinding, ProcessViewModel>() {
    override fun getViewBinding() = ActivityAddProcessBinding.inflate(layoutInflater)
    override val viewModel: ProcessViewModel by viewModels()

    override fun onActivityCreated() {
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        val list = resources.getStringArray(R.array.DifficultyLevel)
        val adapter = ArrayAdapter(this, android.R.layout.simple_list_item_1, list)
        binding.tvDropDown.setAdapter(adapter)
    }

    override fun observe() {
        binding.btnRegister.setOnClickListener {
            try {
                val processName = binding.etProcessName.text?.trim().toString()
                val difficultyLevel = binding.tvDropDown.text.toString()
                if (checkValidation(processName, difficultyLevel)) {
                    viewModel.saveProcess(Process(processName, difficultyLevel.toInt()))
                    Toast.makeText(this, "İşlem başarıyla eklendi.", Toast.LENGTH_SHORT).show()
                    finish()
                }
            } catch (e: Exception) {
                Toast.makeText(this, "İşlem kaydedilirken bir sorun oluştu.", Toast.LENGTH_SHORT).show()
            }
        }

    }
    private fun checkValidation(name: String, difficultyLevel : String) : Boolean {
        if (name.isEmpty()) {
            binding.containerUserName.error = "Lütfen işlem adını giriniz"
            return false
        } else {
            binding.containerUserName.isErrorEnabled = false
        }
        if (difficultyLevel.isEmpty()) {
            binding.containerDropDown.error = "Lütfen zorluk seviyesini seçiniz."
            return false
        } else {
            binding.containerDropDown.isErrorEnabled = false
        }
        return true
    }

    override fun onSupportNavigateUp(): Boolean {
        finish()
        return true
    }

}