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
import com.example.todolist2402.Note
import com.example.todolist2402.NoteDataBase


class HomeFragment : Fragment(), NoteAdapter.NoteEdit {

    lateinit var binding: FragmentHomeBinding


    lateinit var note: Note

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = FragmentHomeBinding.inflate(inflater, container, false)


        var notes: List<Note> = NoteDataBase.getDB(requireContext()).getNoteDao().getAllData()

        notes.let {
            var adapter = NoteAdapter(this)
            adapter.submitList(notes)

            binding.recyclerView.adapter = adapter

        }



        binding.addBtn.setOnClickListener {


            findNavController().navigate(R.id.action_homeFragment_to_addNoteFragment)

        }

        return binding.root
    }

    override fun onNoteEdit(note: Note) {

        var bundle = Bundle()
        bundle.putInt("note", note.id)

        findNavController().navigate(R.id.action_homeFragment_to_addNoteFragment, bundle)

    }


}