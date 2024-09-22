package com.example.todo_list2401

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import com.example.todo_list2401.databinding.ItemDesignBinding
import com.example.todolist2402.Note

class NoteAdapter(var noteEdit: NoteEdit) : ListAdapter<Note, NoteViewHolder>(comaparotor) {

    interface NoteEdit{

        fun onNoteEdit(note: Note)

    }

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

            holder.itemView.setOnClickListener {_ ->
                noteEdit.onNoteEdit(it)
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
