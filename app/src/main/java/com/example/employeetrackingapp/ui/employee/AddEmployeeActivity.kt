package com.example.employeetrackingapp.ui.employee

import android.widget.Toast
import androidx.activity.viewModels
import com.example.employeetrackingapp.base.BaseActivity
import com.example.employeetrackingapp.databinding.ActivityAddEmployeeBinding
import com.example.employeetrackingapp.entities.Employee
import com.example.employeetrackingapp.viewmodel.EmpTrackingViewModel
import com.example.employeetrackingapp.viewmodel.EmployeeViewModel
import dagger.hilt.android.AndroidEntryPoint
import java.lang.Exception

@AndroidEntryPoint
class AddEmployeeActivity : BaseActivity<ActivityAddEmployeeBinding, EmployeeViewModel>() {
    override fun getViewBinding() = ActivityAddEmployeeBinding.inflate(layoutInflater)
    override val viewModel: EmployeeViewModel by viewModels()

    override fun onActivityCreated() {
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        binding.btnRegister.setOnClickListener {
            try {
                val name = binding.etUserName.text?.trim().toString()
                val lastName = binding.etUserLastName.text?.trim().toString()
                if (checkValidation(name, lastName)) {
                    viewModel.saveEmployee(Employee(name, lastName))
                    Toast.makeText(this, "Personel başarıyla eklendi.", Toast.LENGTH_SHORT).show()
                    finish()
                }
            } catch (e: Exception) {
                Toast.makeText(this, "Kullanıcı kaydedilirken bir sorun oluştu.", Toast.LENGTH_SHORT).show()
            }
        }

    }

    private fun checkValidation(name: String, lastName: String) : Boolean {
        if (name.isEmpty()) {
            binding.containerUserName.error = "Lütfen personel adını giriniz"
            return false
        } else {
            binding.containerUserName.isErrorEnabled = false
        }
        if (lastName.isEmpty()) {
            binding.containerUserLastName.error = "Lütfen personel soyadını giriniz."
            return false
        } else {
            binding.containerUserLastName.isErrorEnabled = false
        }
        return true
    }

    override fun observe() {

    }

    override fun onSupportNavigateUp(): Boolean {
        finish()
        return true
    }

}