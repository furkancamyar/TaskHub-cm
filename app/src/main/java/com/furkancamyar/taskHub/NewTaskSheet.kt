package com.furkancamyar.taskHub

import android.os.Bundle
import android.text.Editable
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import com.furkancamyar.taskHub.databinding.FragmentNewTaskSheetBinding
import com.google.android.material.bottomsheet.BottomSheetDialogFragment

class NewTaskSheet(var taskItem: TaskItem?) : BottomSheetDialogFragment() {

    private lateinit var binding: FragmentNewTaskSheetBinding
    private lateinit var taskViewModel: TaskViewModel

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val activity = requireActivity()

        if(taskItem != null) {
            binding.taskTitle.text = getString(R.string.edit_task)
            binding.saveButton.text = getString(R.string.update)
            val editable = Editable.Factory.getInstance()
            binding.name.text = editable.newEditable(taskItem!!.name)
            binding.desc.text = editable.newEditable(taskItem!!.desc)
            
        }
        else{
            binding.taskTitle.text = getString(R.string.new_subtask)

        }

        taskViewModel = ViewModelProvider(activity).get(TaskViewModel::class.java)

        binding.saveButton.setOnClickListener {
            saveAction()
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
        ): View? {

            binding = FragmentNewTaskSheetBinding.inflate(inflater, container, false)
            return binding.root
        }

    private fun saveAction() {
        val name = binding.name.text.toString()
        val desc =  binding.desc.text.toString()


        if (taskItem == null){
            val newTask = TaskItem(name,desc,null)
            taskViewModel.addTaskItem(newTask)

            Toast.makeText(requireContext(), getString(R.string.added_successfully), Toast.LENGTH_LONG)
                .show()
        }
        else{

            taskItem!!.name = name
            taskItem!!.desc = desc

            taskViewModel.updateTaskItem(taskItem!!)
        }

        binding.name.setText("")
        binding.desc.setText("")
        dismiss()
    }

}