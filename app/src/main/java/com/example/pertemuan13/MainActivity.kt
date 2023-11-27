package com.example.pertemuan13

import android.R
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.ArrayAdapter
import androidx.lifecycle.MutableLiveData
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.pertemuan13.databinding.ActivityMainBinding
import com.google.firebase.Firebase
import com.google.firebase.firestore.firestore

class MainActivity : AppCompatActivity() {

    private val binding by lazy {
        ActivityMainBinding.inflate(layoutInflater)
    }
    private lateinit var recyclerViewAdapter: RecyclerViewAdapter
    private lateinit var noteList: List<Note>

    private val firestore = Firebase.firestore
    private val noteListLiveData : MutableLiveData<List<Note>>
            by lazy {
                MutableLiveData<List<Note>>()
            }
    private var noteCollectionRef = firestore.collection("notes")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        recyclerViewAdapter = RecyclerViewAdapter(
            noteList = emptyList(),
            onClickNote = { context, clickedNote ->
                val intent = Intent(context, UpdateActivity::class.java).apply {
                    putExtra("note_id", clickedNote.id)
                }
                context.startActivity(intent)
            }
        )

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

                deleteBudget(swipedNote.id)

                noteList = noteList.toMutableList().also { it.removeAt(position) }
                recyclerViewAdapter.updateData(noteList)
            }
        }

        with(binding) {
            rvNote.layoutManager = LinearLayoutManager(this@MainActivity)
            rvNote.adapter = recyclerViewAdapter

            ItemTouchHelper(simpleCallback).attachToRecyclerView(rvNote)

            btnAdd.setOnClickListener {
                val intent = Intent(this@MainActivity, InputActivity::class.java)
                startActivity(intent)
            }
        }

        // * Observe dulu baru get
        observeNotes()
        getAllNotes()

    }

    private fun getAllNotes() {
        noteCollectionRef.addSnapshotListener {
            snapshots,
            error ->
            if (error != null) {
                Log.d("MainActivity", "Error listening for note changes ", error)
                return@addSnapshotListener
            }

            val notes = arrayListOf<Note>()
            snapshots?.forEach {
                documentReference ->
                notes.add((
                    Note(
                        documentReference.id,
                        documentReference.get("title").toString(),
                        documentReference.get("description").toString()
                    )
                ))
            }

            if (notes != null) {
                noteListLiveData.postValue(notes)
            }

        }
    }

    private fun observeNotes() {
        noteListLiveData.observe(this) {
            notes ->
            if (notes.isNotEmpty()) {
                for (note in notes) {
                    Log.d("RoomLog", "getAllNotes: ${note.title}")
                }
                noteList = notes
                recyclerViewAdapter.updateData(notes)
            } else {
                Log.d("RoomLog", "getAllNotes: empty")
            }
        }
    }

    private fun deleteBudget(noteId: String) {
        noteCollectionRef.document(noteId).delete()
            .addOnFailureListener {
                Log.d("MainActivity", "Error deleting budget : ", it)
            }
    }

    override fun onResume() {
        super.onResume()
        getAllNotes()
    }

}