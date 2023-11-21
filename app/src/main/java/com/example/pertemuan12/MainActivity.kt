package com.example.pertemuan12

import android.R
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.AdapterView
import android.widget.ArrayAdapter
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.SimpleItemAnimator
import com.example.pertemuan12.RecyclerViewAdapter
import com.example.pertemuan12.databinding.ActivityMainBinding
import com.example.pertemuan12.note.Note
import com.example.pertemuan12.note.NoteDao
import com.example.pertemuan12.note.NoteRoomDatabase
import java.util.concurrent.ExecutorService
import java.util.concurrent.Executors

class MainActivity : AppCompatActivity() {

    private val binding by lazy {
        ActivityMainBinding.inflate(layoutInflater)
    }

    private lateinit var noteList: List<Note>
    private lateinit var mNotesDao: NoteDao
    private lateinit var executorService: ExecutorService
    private lateinit var recyclerViewAdapter: RecyclerViewAdapter
    private var updateId: Int = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        executorService = Executors.newSingleThreadExecutor()
        val db = NoteRoomDatabase.getDatabase(this)
        mNotesDao = db!!.noteDao()!!

        recyclerViewAdapter = RecyclerViewAdapter(
            noteList = emptyList(),
            onClickNote = { context, clickedNote ->
                val intent = Intent(context, UpdateActivity::class.java).apply {
                    putExtra("note_id", clickedNote.id)
                }
                context.startActivity(intent)
            }
        )

        with(binding) {
            rvNote.layoutManager = LinearLayoutManager(this@MainActivity)
            rvNote.adapter = recyclerViewAdapter

            ItemTouchHelper(simpleCallback).attachToRecyclerView(rvNote)

            btnAdd.setOnClickListener {
                val intent = Intent(this@MainActivity, InputActivity::class.java)
                startActivity(intent)
            }
        }
    }

    val simpleCallback = object : ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.LEFT) {
        override fun onMove(
            recyclerView: RecyclerView,
            viewHolder: RecyclerView.ViewHolder,
            target: RecyclerView.ViewHolder
        ): Boolean {
            return false
        }

        override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
            val position = viewHolder.adapterPosition
            val swipedNote = noteList[position]

            delete(swipedNote)

            noteList = noteList.toMutableList().also { it.removeAt(position) }
            recyclerViewAdapter.updateData(noteList)
        }
    }



    private fun getAllNotes() {
        mNotesDao.allNotes.observe(this) { notes ->
            if (notes.isNotEmpty()) {
                for (note in notes) {
                    Log.d("RoomLog", "getAllNotes: ${note.title}")
                }
                // Update the RecyclerView data when notes change
                noteList = notes
                recyclerViewAdapter.updateData(notes)
            } else {
                Log.d("RoomLog", "getAllNotes: empty")
            }
        }

    }

    override fun onResume() {
        super.onResume()
        getAllNotes()
    }

    private fun delete(note: Note) {
        executorService.execute {
            mNotesDao.delete(note)
        }
    }
}
