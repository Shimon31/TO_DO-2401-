package com.example.todo_list2401

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import com.example.todo_list2401.databinding.ItemDesignBinding
import com.example.todolist2402.Note

class NoteAdapter() : ListAdapter<Note, NoteViewHolder>(comaparotor) {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NoteViewHolder {
        return NoteViewHolder(
            ItemDesignBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: NoteViewHolder, position: Int) {
        getItem(position).let {it->

            holder.binding.apply {

                noteTitle.text = it.title
                dateTV.text = it.date
                timeTV.text = it.time

            }

        }
    }

    companion object{
        var comaparotor = object  : DiffUtil.ItemCallback<Note>(){
            override fun areItemsTheSame(oldItem: Note, newItem: Note): Boolean {
                return  oldItem == newItem
            }

            override fun areContentsTheSame(oldItem: Note, newItem: Note): Boolean {
                return  oldItem == newItem
            }


        }
    }

}
