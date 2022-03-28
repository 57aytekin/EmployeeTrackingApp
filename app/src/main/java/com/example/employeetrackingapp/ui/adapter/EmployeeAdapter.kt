package com.example.employeetrackingapp.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.employeetrackingapp.databinding.EmployeeItemRowBinding
import com.example.employeetrackingapp.entities.Employee

class EmployeeAdapter: RecyclerView.Adapter<EmployeeAdapter.EmployeeViewHolder>() {

    private val diffCallBack = object : DiffUtil.ItemCallback<Employee>() {
        override fun areItemsTheSame(oldItem: Employee, newItem: Employee): Boolean {
            return oldItem == newItem
        }

        override fun areContentsTheSame(
            oldItem: Employee,
            newItem: Employee
        ): Boolean {
            return oldItem.hashCode() == newItem.hashCode()
        }
    }

    private val differ = AsyncListDiffer(this, diffCallBack)
    fun submitList(list : List<Employee>) = differ.submitList(list)

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): EmployeeAdapter.EmployeeViewHolder {
        return EmployeeViewHolder(
            EmployeeItemRowBinding.inflate(
                LayoutInflater.from(parent.context), parent, false
            )
        )
    }

    override fun onBindViewHolder(holder: EmployeeAdapter.EmployeeViewHolder, position: Int) {
        val item = differ.currentList[position]
        holder.bind(item)
    }

    inner class EmployeeViewHolder(val binding : EmployeeItemRowBinding) : RecyclerView.ViewHolder(binding.root){
        fun bind(employee: Employee) {
            binding.tvUserName.text = employee.name
            binding.tvLastName.text = employee.lastName
        }
    }

    override fun getItemCount() = differ.currentList.size
}