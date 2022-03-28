package com.example.employeetrackingapp.ui.process

import android.content.Intent
import android.content.res.ColorStateList
import android.view.View
import android.widget.Toast
import androidx.fragment.app.viewModels
import com.example.employeetrackingapp.R
import com.example.employeetrackingapp.base.BaseFragment
import com.example.employeetrackingapp.databinding.FragmentProcessListBinding
import com.example.employeetrackingapp.ui.adapter.ProcessAdapter
import com.example.employeetrackingapp.viewmodel.EmpTrackingViewModel
import com.example.employeetrackingapp.viewmodel.ProcessViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ProcessListFragment : BaseFragment<FragmentProcessListBinding, ProcessViewModel>() {
    override val viewModel: ProcessViewModel by viewModels()

    override fun getViewBinding() = FragmentProcessListBinding.inflate(layoutInflater)

    override fun onFragmentCreated() {
        var size = 0
        val processAdapter : ProcessAdapter by lazy { ProcessAdapter() }
        binding.rvProcess.adapter = processAdapter
        viewModel.getAllProcess().observe(this){list ->
            itemVisibility(list.size)
            processAdapter.submitList(list)
            size = list.size
            if (list.size == 6) {
                binding.fabAddProcess.apply {
                    backgroundTintList = ColorStateList.valueOf( requireContext().getColor(R.color.gray))
                }
            }
        }

        binding.fabAddProcess.setOnClickListener {
            if (size == 6) {
                Toast.makeText(requireContext(), "İşlem sınırına ulaştınız (max:6)", Toast.LENGTH_SHORT).show()
            } else {
                startActivity(Intent(requireContext(), AddProcessActivity::class.java))
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