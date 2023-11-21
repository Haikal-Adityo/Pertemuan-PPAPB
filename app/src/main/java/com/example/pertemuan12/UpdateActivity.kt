package com.example.pertemuan12

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.pertemuan12.databinding.ActivityUpdateBinding
import com.example.pertemuan12.note.Note
import com.example.pertemuan12.note.NoteDao
import com.example.pertemuan12.note.NoteRoomDatabase
import java.util.concurrent.ExecutorService
import java.util.concurrent.Executors

class UpdateActivity : AppCompatActivity() {

    private val binding by lazy {
        ActivityUpdateBinding.inflate(layoutInflater)
    }

    private lateinit var mNotesDao: NoteDao
    private lateinit var executorService: ExecutorService

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        executorService = Executors.newSingleThreadExecutor()
        val db = NoteRoomDatabase.getDatabase(this)
        mNotesDao = db!!.noteDao()!!

        val noteId = intent.getIntExtra("note_id", 0)
        getNoteDetails(noteId)

        binding.btnBack.setOnClickListener {
            finish()
        }

        binding.btnSave.setOnClickListener {
            val title = binding.editTitle.text.toString()
            val description = binding.editDesc.text.toString()

            // Check if both title and description are not null or empty
            if (title.isNotBlank() && description.isNotBlank()) {
                // Insert the note only if title and description are not null or empty
                update(Note(
                    id = noteId,
                    title = title,
                    description = description
                ))
            }

            finish()
        }
    }

    private fun getNoteDetails(noteId: Int) {
        executorService.execute {
            val existingNote = mNotesDao.getNoteById(noteId)

            runOnUiThread {
                // Populate UI with existing note details
                existingNote?.let {
                    binding.editTitle.setText(it.title)
                    binding.editDesc.setText(it.description)
                }
            }
        }
    }


    private fun update(note: Note) {
        executorService.execute {
            mNotesDao.update(note)
        }
    }
}
