package com.example.pertemuan12

import android.annotation.SuppressLint
import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.pertemuan12.databinding.ItemNoteBinding
import com.example.pertemuan12.note.Note
import com.example.pertemuan12.note.NoteDao
import java.util.concurrent.ExecutorService

class RecyclerViewAdapter(
    private var noteList: List<Note>,
    private val onClickNote: (context: Context, Note) -> Unit
) : RecyclerView.Adapter<RecyclerViewAdapter.ItemNoteViewHolder>() {

    private lateinit var mNotesDao: NoteDao
    private lateinit var executorService: ExecutorService

    inner class ItemNoteViewHolder(private val binding: ItemNoteBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(data: Note) {
            with(binding) {
                txtTitle.text = data.title
                txtDesc.text = data.description

                itemView.setOnClickListener {
                    onClickNote.invoke(itemView.context, data)
                }
            }
        }
    }

    @SuppressLint("NotifyDataSetChanged")
    fun updateData(newList: List<Note>) {
        noteList = newList
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ItemNoteViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = ItemNoteBinding.inflate(inflater, parent, false)
        return ItemNoteViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ItemNoteViewHolder, position: Int) {
        val note = noteList[position]
        holder.bind(note)
    }

    override fun getItemCount(): Int {
        return noteList.size
    }
}
