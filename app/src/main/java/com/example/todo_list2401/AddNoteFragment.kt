package com.example.todo_list2401

import android.app.DatePickerDialog
import android.app.TimePickerDialog
import android.icu.util.Calendar
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import androidx.room.Room
import com.example.todo_list2401.databinding.FragmentAddNoteBinding
import com.example.todolist2402.Note
import com.example.todolist2402.NoteDataBase


class AddNoteFragment : Fragment() {

    lateinit var binding: FragmentAddNoteBinding

    var showTime: String? = null
    var showDate: String? = null

    lateinit var note: Note

    var noteId = 0

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = FragmentAddNoteBinding.inflate(inflater, container, false)

        noteId = requireArguments().getInt("note")

        if (noteId != 0) {

            note = NoteDataBase.getDB(requireContext()).getNoteDao()
                .loadAllByIds(listOf<Int>(noteId))[0]

            binding.apply {

                titleET.setText(note.title)
                timeBtn.setText(note.time)
                dateBtn.setText(note.date)

            }

        }

        binding.dateBtn.setOnClickListener {

            PickADate()
        }
        binding.timeBtn.setOnClickListener {

            pickATime()
        }
        binding.submitBtn.setOnClickListener {
            val titleStr = binding.titleET.text.toString()
            val timeStr = showTime ?: "00:00"
            val dateStr = showDate ?: "00/00/0000"

            val note = Note(title = titleStr, time = timeStr, date = dateStr)

            if (noteId == 0){
                NoteDataBase.getDB(requireContext()).getNoteDao().insertData(note)
            }else{
                note.id = noteId
                NoteDataBase.getDB(requireContext()).getNoteDao().updateData(note)
            }



            findNavController().navigate(R.id.action_addNoteFragment_to_homeFragment)

        }



        return binding.root
    }

    private fun pickATime() {
        val calendar = java.util.Calendar.getInstance()

        val minute = calendar.get(java.util.Calendar.MINUTE)
        val hour = calendar.get(java.util.Calendar.HOUR_OF_DAY)

        val timePicker = TimePickerDialog(
            requireActivity(), TimePickerDialog.OnTimeSetListener { _, hourOfDay, minute ->

                showTime = "$hourOfDay : $minute"
                binding.timeBtn.text = showTime


            }, hour, minute, false

        )
        timePicker.show()


    }

    private fun PickADate() {
        val calender = Calendar.getInstance()

        val day = calender.get(Calendar.DAY_OF_MONTH)
        val month = calender.get(Calendar.MONTH)
        val year = calender.get(Calendar.YEAR)

        val showDatePicker = DatePickerDialog(
            requireActivity(), DatePickerDialog.OnDateSetListener { view, year, month, dayOfMonth ->

                showDate = "$dayOfMonth/${month + 1}/$year"
                binding.dateBtn.text = showDate

            }, year, month, day

        )

        showDatePicker.show()


    }


}