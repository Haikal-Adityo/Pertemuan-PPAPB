package com.example.pertemuan12

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.AdapterView
import android.widget.ArrayAdapter
import com.example.pertemuan12.databinding.ActivityLatihanBinding
import com.example.pertemuan12.note.Note
import com.example.pertemuan12.note.NoteDao
import com.example.pertemuan12.note.NoteRoomDatabase
import java.util.concurrent.Executors
import java.util.concurrent.ExecutorService

class LatihanActivity : AppCompatActivity() {

    private val binding by lazy {
        ActivityLatihanBinding.inflate(layoutInflater)
    }

    private lateinit var mNotesDao: NoteDao
    private lateinit var executorService: ExecutorService
    private var updateId: Int = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        executorService = Executors.newSingleThreadExecutor()
        val db = NoteRoomDatabase.getDatabase(this)
        mNotesDao = db!!.noteDao()!!

        with(binding) {
            btnAdd.setOnClickListener {
                insert( Note(
                    title = editTitle.text.toString(),
                    description = editDesc.text.toString()
                ))
                setEmptyField()
            }

            listView.setOnItemClickListener {
                adapterView, _, i, _ ->
                val item = adapterView.adapter.getItem(i) as Note
                updateId = item.id

                editTitle.setText(item.title)
                editDesc.setText(item.description)
            }

            btnUpdate.setOnClickListener {
                update( Note(
                    id = updateId,
                    title = editTitle.text.toString(),
                    description = editDesc.text.toString()
                ))
                updateId = 0
                setEmptyField()
            }

            listView.onItemLongClickListener =
                AdapterView.OnItemLongClickListener {
                    adapterView, _, i, _ ->
                    val item = adapterView.adapter.getItem(i) as Note
                    delete(item)
                    true
                }

        }

    }

    private fun setEmptyField() {
        with(binding) {
            editTitle.setText("")
            editDesc.setText("")
        }
    }

    private fun getAllNotes() {
        mNotesDao.allNotes.observe(this) {
            notes ->
            if (notes.isNotEmpty()){
                for (note in notes){
                    Log.d("RoomLog", "getAllNotes: ${note.title}")
                }
            } else Log.d("RoomLog", "getAllNotes: empty")

            val adapter: ArrayAdapter<Note> =
                ArrayAdapter<Note>(
                    this,
                    android.R.layout.simple_list_item_1,
                    notes
                )
            binding.listView.adapter = adapter
        }
    }

    override fun onResume() {
        super.onResume()
        getAllNotes()
    }

    private fun insert(note: Note) {
        executorService.execute {
            mNotesDao.insert(note)
        }
    }

    private fun update(note: Note) {
        executorService.execute {
            mNotesDao.update(note)
        }
    }

    private fun delete(note: Note) {
        executorService.execute {
            mNotesDao.delete(note)
        }
    }


}
