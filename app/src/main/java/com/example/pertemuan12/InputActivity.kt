package com.example.pertemuan12

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.pertemuan12.databinding.ActivityInputBinding
import com.example.pertemuan12.note.Note
import com.example.pertemuan12.note.NoteDao
import com.example.pertemuan12.note.NoteRoomDatabase
import java.util.concurrent.ExecutorService
import java.util.concurrent.Executors

class InputActivity : AppCompatActivity() {

    private val binding by lazy {
        ActivityInputBinding.inflate(layoutInflater)
    }

    private lateinit var mNotesDao: NoteDao
    private lateinit var executorService: ExecutorService

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        executorService = Executors.newSingleThreadExecutor()
        val db = NoteRoomDatabase.getDatabase(this)
        mNotesDao = db!!.noteDao()!!

        with(binding) {

            btnBack.setOnClickListener {
                finish()
            }

            btnSave.setOnClickListener {
                val title = editTitle.text.toString()
                val description = editDesc.text.toString()

                // Check if both title and description are not null or empty
                if (title.isNotBlank() && description.isNotBlank()) {
                    // Insert the note only if title and description are not null or empty
                    insert(Note(
                        title = title,
                        description = description
                    ))
                }

                finish()
            }
        }

    }

    private fun insert(note: Note) {
        executorService.execute {
            mNotesDao.insert(note)
        }
    }

}
