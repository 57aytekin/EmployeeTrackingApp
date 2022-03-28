package com.example.employeetrackingapp.ui.adapter

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.employeetrackingapp.databinding.FragmentEmployeeTrackingBinding
import com.example.employeetrackingapp.databinding.HomeItemRowBinding
import com.example.employeetrackingapp.entities.DailyJobs
import com.example.employeetrackingapp.entities.Employee
import java.text.SimpleDateFormat
import java.util.*

class EmployeeTrackingAdapter : RecyclerView.Adapter<EmployeeTrackingAdapter.EmployeeTrackingViewHolder>() {

    private val diffCallBack = object : DiffUtil.ItemCallback<DailyJobs>() {
        override fun areItemsTheSame(oldItem: DailyJobs, newItem: DailyJobs): Boolean {
            return oldItem == newItem
        }

        override fun areContentsTheSame(
            oldItem: DailyJobs,
            newItem: DailyJobs
        ): Boolean {
            return oldItem.hashCode() == newItem.hashCode()
        }
    }

    private val differ = AsyncListDiffer(this, diffCallBack)
    fun submitList(list : List<DailyJobs>) = differ.submitList(list)
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): EmployeeTrackingAdapter.EmployeeTrackingViewHolder {
        return EmployeeTrackingViewHolder(
            HomeItemRowBinding.inflate(
                LayoutInflater.from(parent.context), parent, false
            )
        )
    }

    override fun onBindViewHolder(
        holder: EmployeeTrackingAdapter.EmployeeTrackingViewHolder,
        position: Int
    ) {
        val item = differ.currentList[position]
        holder.bind(item)
    }

    inner class EmployeeTrackingViewHolder(val binding: HomeItemRowBinding) : RecyclerView.ViewHolder(binding.root){
        private val adapter : EmpJobListAdapter by lazy { EmpJobListAdapter() }
        fun bind(item : DailyJobs) {
            val calendar = Calendar.getInstance().apply {
                timeInMillis = item.list[0].empDate
            }
            val sdf = SimpleDateFormat("dd MMM yyyy EEEE", Locale("tr", "TR"))
            val date = sdf.format(calendar.time)
            binding.tvDate.text = date
            adapter.submitList(item.list)
            binding.rvEmpJob.adapter = this.adapter
        }
    }

    override fun getItemCount() = differ.currentList.size

}