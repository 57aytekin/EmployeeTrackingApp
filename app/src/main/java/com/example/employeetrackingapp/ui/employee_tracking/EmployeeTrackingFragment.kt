package com.example.employeetrackingapp.ui.employee_tracking

import android.annotation.SuppressLint
import android.view.View
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.employeetrackingapp.base.BaseFragment
import com.example.employeetrackingapp.databinding.FragmentEmployeeTrackingBinding
import com.example.employeetrackingapp.entities.DailyJobs
import com.example.employeetrackingapp.entities.EmpTrackingCrossRef
import com.example.employeetrackingapp.entities.Employee
import com.example.employeetrackingapp.entities.Process
import com.example.employeetrackingapp.other.PrefUtils
import com.example.employeetrackingapp.ui.adapter.EmployeeTrackingAdapter
import com.example.employeetrackingapp.viewmodel.EmpTrackingViewModel
import dagger.hilt.android.AndroidEntryPoint
import java.util.*
import javax.inject.Inject

@AndroidEntryPoint
class EmployeeTrackingFragment : BaseFragment<FragmentEmployeeTrackingBinding, EmpTrackingViewModel>() {
    @Inject
    lateinit var prefUtils: PrefUtils
    override fun getViewBinding() = FragmentEmployeeTrackingBinding.inflate(layoutInflater)

    override val viewModel: EmpTrackingViewModel by viewModels()
    private val empTrackingAdapter : EmployeeTrackingAdapter by lazy { EmployeeTrackingAdapter() }

    var userList = listOf<Employee>()
    var processList = listOf<Process>()

    @SuppressLint("NotifyDataSetChanged")
    override fun onFragmentCreated() {
        userList = viewModel.getAllEmployee()
        processList = viewModel.getAllProcess()

        binding.rvHome.adapter = empTrackingAdapter
        binding.rvHome.layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL,false)
        viewModel.getAllCrossRef().observe(this){list ->
            if (list.size % 6 == 0) {
                if (list.isNullOrEmpty()) {
                    calculateProcess()
                    binding.btnNextDay.visibility = View.GONE
                } else {
                    binding.btnNextDay.visibility = View.VISIBLE
                    val dailyList = mutableListOf<DailyJobs>()
                    val newList = list.chunked(6)
                    for (n in newList.indices) {
                        val dailyJobs = DailyJobs(prefUtils.getId(), newList[n])
                        dailyList.add(dailyJobs)
                        prefUtils.save(LIST_ID, prefUtils.getId()+1)
                    }
                    empTrackingAdapter.submitList(dailyList)
                }
            }
        }
        binding.btnNextDay.setOnClickListener {
            calculateProcess()
        }
    }
    private fun calculateProcess() {
        if (userList.size == 6 && processList.size == 6) {
            binding.tvEmpty.visibility = View.GONE
            val currentTimestamp = prefUtils.getCurrentDay()
            var index = prefUtils.getLastSavedIndex() % 6
            for (i in userList.indices) {
                if (index == 6) index %= 6
                val crossRef = EmpTrackingCrossRef(
                    userList[i].employeeId!!,
                    processList[index].processId!!,
                    currentTimestamp
                )
                viewModel.saveEmpTracking(crossRef)
                index++
            }
            prefUtils.save(LAST_INDEX, prefUtils.getLastSavedIndex() + 1)
            //Increase and save the day
            val calendar = Calendar.getInstance().apply { timeInMillis = prefUtils.getCurrentDay() }
            calendar.add(Calendar.DAY_OF_MONTH, 1)
            prefUtils.save(DAY_TIMESTAMP, calendar.timeInMillis)
        } else {
            binding.tvEmpty.visibility = View.VISIBLE
        }
    }

    companion object {
        const val LAST_INDEX = "last_index"
        const val LIST_ID = "list_id"
        const val DAY_TIMESTAMP = "day_timestamp"
    }

}
