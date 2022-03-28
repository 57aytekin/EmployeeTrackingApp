package com.example.employeetrackingapp.ui.employee

import android.content.Intent
import android.content.res.ColorStateList
import android.view.View
import android.widget.Toast
import androidx.fragment.app.viewModels
import com.example.employeetrackingapp.R
import com.example.employeetrackingapp.base.BaseFragment
import com.example.employeetrackingapp.databinding.FragmentEmployeeListBinding
import com.example.employeetrackingapp.ui.adapter.EmployeeAdapter
import com.example.employeetrackingapp.viewmodel.EmpTrackingViewModel
import com.example.employeetrackingapp.viewmodel.EmployeeViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class EmployeeListFragment : BaseFragment<FragmentEmployeeListBinding, EmployeeViewModel>() {

    override fun getViewBinding() = FragmentEmployeeListBinding.inflate(layoutInflater)

    override val viewModel: EmployeeViewModel by viewModels()


    override fun onFragmentCreated() {
        var size = 0
        val employeeAdapter : EmployeeAdapter by lazy { EmployeeAdapter() }
        binding.rvEmployee.adapter = employeeAdapter
        viewModel.getAllEmployee().observe(this){list ->
            itemVisibility(list.size)
            employeeAdapter.submitList(list)
            size = list.size
            if (list.size == 6) {
                binding.fabAddEmployee.apply {
                    backgroundTintList = ColorStateList.valueOf( requireContext().getColor(R.color.gray))
                }
            }
        }

        binding.fabAddEmployee.setOnClickListener {
            if (size == 6) {
                Toast.makeText(requireContext(), "Personel sınırına ulaştınız (max:6)", Toast.LENGTH_SHORT).show()
            }else {
                startActivity(Intent(requireContext(), AddEmployeeActivity::class.java))
            }
        }
    }
    private fun itemVisibility(size: Int) {
        binding.apply {
            if (size == 0) {
                linear.visibility = View.GONE
                redLine.visibility = View.GONE
                tvEmpty.visibility = View.VISIBLE
            } else {
                linear.visibility = View.VISIBLE
                redLine.visibility = View.VISIBLE
                tvEmpty.visibility = View.GONE
            }
        }
    }
}