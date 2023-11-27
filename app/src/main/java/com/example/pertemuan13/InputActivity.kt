package com.example.pertemuan13

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.example.pertemuan13.databinding.ActivityInputBinding
import com.google.firebase.Firebase
import com.google.firebase.firestore.firestore

class InputActivity : AppCompatActivity() {

    private val binding by lazy {
        ActivityInputBinding.inflate(layoutInflater)
    }

    private val firestore = Firebase.firestore
    private var noteCollectionRef = firestore.collection("notes")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        with(binding) {

            btnBack.setOnClickListener {
                finish()
            }

            btnSave.setOnClickListener {
                val title = editTitle.text.toString()
                val desc = editDesc.text.toString()

                if (title.isNotBlank() || desc.isNotBlank()) {
                    val newNote = Note(
                        title = title,
                        description = desc
                    )
                    addNote(newNote)
                }

                finish()
            }
        }

    }

    private fun addNote(note: Note) {
        noteCollectionRef.add(note).addOnFailureListener {
            Log.d("MainActivity", "Error adding budget : ", it)
        }
    }

}