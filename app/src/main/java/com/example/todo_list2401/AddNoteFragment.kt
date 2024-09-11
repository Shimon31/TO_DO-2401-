package com.example.todo_list2401

import android.app.DatePickerDialog
import android.content.DialogInterface
import android.icu.util.Calendar
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.example.todo_list2401.databinding.FragmentAddNoteBinding


class AddNoteFragment : Fragment() {

    lateinit var binding: FragmentAddNoteBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentAddNoteBinding.inflate(inflater, container, false)

        binding.dateBtn.setOnClickListener {

            PickADate()
        }



        return binding.root
    }

    private fun PickADate() {
        val calender = Calendar.getInstance()

        val day = calender.get(Calendar.DAY_OF_MONTH)
        val month = calender.get(Calendar.MONTH)
        val year = calender.get(Calendar.YEAR)

        val showDatePicker = DatePickerDialog(
            requireActivity(), DatePickerDialog.OnDateSetListener { view, year, month, dayOfMonth ->

                val showdate = "$dayOfMonth/$month/$year"
                binding.dateBtn.text = showdate

            }, year, month, day

        )

        showDatePicker.show()


    }


}