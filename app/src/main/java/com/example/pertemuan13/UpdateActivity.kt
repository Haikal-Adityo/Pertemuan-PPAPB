package com.example.pertemuan13

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.example.pertemuan13.databinding.ActivityUpdateBinding
import com.google.firebase.Firebase
import com.google.firebase.firestore.firestore

class UpdateActivity : AppCompatActivity() {

    private val binding by lazy {
        ActivityUpdateBinding.inflate(layoutInflater)
    }

    private val firestore = Firebase.firestore
    private var updateId = ""
    private var noteCollectionRef = firestore.collection("notes")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        val noteId = intent.getStringExtra("note_id")
        if (noteId != null) {
            getNoteDetails(noteId)
        }

        with(binding) {

            btnBack.setOnClickListener {
                finish()
            }

            btnSave.setOnClickListener {

                val newNote = Note(
                    id = updateId,
                    title = editTitle.text.toString(),
                    description = editDesc.text.toString()
                )

                updateBudget(newNote)
                finish()
            }

        }

    }

    private fun getNoteDetails(noteId: String) {
        noteCollectionRef.document(noteId).get()
            .addOnSuccessListener { documentSnapshot ->
                if (documentSnapshot.exists()) {
                    val title = documentSnapshot.getString("title")
                    val description = documentSnapshot.getString("description")

                    runOnUiThread {
                        updateId = noteId
                        binding.editTitle.setText(title)
                        binding.editDesc.setText(description)
                    }

                } else {
                    Log.d("UpdateActivity", "Document does not exist")
                }
            }
            .addOnFailureListener { exception ->
                Log.d("UpdateActivity", "Error getting note details: ", exception)
            }
    }


    private fun updateBudget(note: Note) {
        noteCollectionRef.document(note.id).set(note)
            .addOnFailureListener {
                Log.d("MainActivity", "Error updating budget : ", it)
            }
    }

}