package com.example.employeetrackingapp.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.employeetrackingapp.databinding.EmpTrackingRowItemBinding
import com.example.employeetrackingapp.databinding.ProcessItemRowBinding
import com.example.employeetrackingapp.entities.DailyJobs
import com.example.employeetrackingapp.entities.EmployeeTracking

class EmpJobListAdapter : RecyclerView.Adapter<EmpJobListAdapter.EmpJobViewHolder>() {
    private val diffCallBack = object : DiffUtil.ItemCallback<EmployeeTracking>() {
        override fun areItemsTheSame(oldItem: EmployeeTracking, newItem: EmployeeTracking): Boolean {
            return oldItem == newItem
        }

        override fun areContentsTheSame(
            oldItem: EmployeeTracking,
            newItem: EmployeeTracking
        ): Boolean {
            return oldItem.hashCode() == newItem.hashCode()
        }
    }

    private val differ = AsyncListDiffer(this, diffCallBack)
    fun submitList(list : List<EmployeeTracking>) = differ.submitList(list)
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): EmpJobListAdapter.EmpJobViewHolder {
        return EmpJobViewHolder(
            EmpTrackingRowItemBinding.inflate(
                LayoutInflater.from(parent.context), parent, false
            )
        )
    }

    override fun onBindViewHolder(holder: EmpJobListAdapter.EmpJobViewHolder, position: Int) {
        val item = differ.currentList[position]
        holder.bind(item)
    }

    inner class EmpJobViewHolder(private val binding: EmpTrackingRowItemBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(item: EmployeeTracking) {
            binding.tvPerson.text = item.name
            binding.tvJob.text = item.processName
            binding.tvJobLevel.text = item.difficultyLevel.toString()
        }
    }

    override fun getItemCount() = differ.currentList.size

    override fun getItemId(position: Int) = super.getItemId(position)

    override fun getItemViewType(position: Int): Int {
        return super.getItemViewType(position)
    }
}