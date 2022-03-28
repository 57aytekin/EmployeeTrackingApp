package com.example.employeetrackingapp.ui.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.employeetrackingapp.R
import com.example.employeetrackingapp.databinding.ProcessItemRowBinding
import com.example.employeetrackingapp.entities.Process

class ProcessAdapter : RecyclerView.Adapter<ProcessAdapter.ProcessViewHolder>() {

    private val diffCallBack = object : DiffUtil.ItemCallback<Process>() {
        override fun areItemsTheSame(oldItem: Process, newItem: Process): Boolean {
            return oldItem == newItem
        }

        override fun areContentsTheSame(
            oldItem: Process,
            newItem: Process
        ): Boolean {
            return oldItem.hashCode() == newItem.hashCode()
        }
    }

    private val differ = AsyncListDiffer(this, diffCallBack)
    fun submitList(list : List<Process>) = differ.submitList(list)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProcessViewHolder {
        return ProcessViewHolder(
            ProcessItemRowBinding.inflate(
                LayoutInflater.from(parent.context), parent, false
            )
        )
    }

    override fun onBindViewHolder(holder: ProcessViewHolder, position: Int) {
        val item = differ.currentList[position]
        holder.bind(item)
    }
    inner class ProcessViewHolder(private val binding : ProcessItemRowBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(process: Process) {
            binding.tvProcessName.text = process.processName
            binding.tvProcessLevel.text = process.difficultyLevel.toString()
        }
    }

    override fun getItemCount() = differ.currentList.size
}