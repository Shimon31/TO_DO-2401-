package com.example.todo_list2401

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.fragment.findNavController
import androidx.room.Room
import com.example.todo_list2401.databinding.FragmentHomeBinding
import com.example.todolist2402.NoteDataBase


class HomeFragment : Fragment() {

    lateinit var binding:FragmentHomeBinding

    lateinit var database: NoteDataBase

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding=FragmentHomeBinding.inflate(inflater,container,false)


        database = Room.databaseBuilder(requireActivity(), NoteDataBase::class.java, "Note-DB")
            .allowMainThreadQueries().build()

        database.getNoteDao().getAllData().forEach {

            Toast.makeText(requireActivity(), "$it", Toast.LENGTH_SHORT).show()

        }


        binding.addBtn.setOnClickListener {


            findNavController().navigate(R.id.action_homeFragment_to_addNoteFragment)

        }


        return binding.root
    }


}