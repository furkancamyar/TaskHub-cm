package com.furkancamyar.taskHub

import android.content.Context
import android.graphics.Paint
import androidx.recyclerview.widget.RecyclerView
import com.furkancamyar.taskHub.databinding.TaskItemCellBinding

class TaskItemViewHolder(

    private val context: Context,
    private val binding: TaskItemCellBinding,
    private val clickListener: TaskItemClickListener

):RecyclerView.ViewHolder(binding.root) {

    fun bindTaskItem(taskItem: TaskItem){
        binding.name.text = taskItem.name
        binding.description.text = taskItem.desc

        if(taskItem.isCompleted()){
            binding.name.paintFlags = Paint.STRIKE_THRU_TEXT_FLAG
            binding.description.paintFlags = Paint.STRIKE_THRU_TEXT_FLAG
        }

        binding.completeButton.setImageResource(taskItem.imageResource())
        binding.completeButton.setColorFilter(taskItem.imageColor(context))

        binding.completeButton.setOnClickListener {
            clickListener.completeTaskItem(taskItem)
        }

        //Edit Task Screen
        binding.taskCellContainer.setOnClickListener {
            clickListener.editTaskItem(taskItem)
        }

    }

}