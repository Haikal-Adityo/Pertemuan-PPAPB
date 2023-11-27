package com.example.pertemuan13

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.AdapterView
import android.widget.ArrayAdapter
import androidx.lifecycle.MutableLiveData
import com.example.pertemuan13.databinding.ActivityLatihanBinding
import com.google.firebase.Firebase
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.firestore

class LatihanActivity : AppCompatActivity() {

    private val binding by lazy {
        ActivityLatihanBinding.inflate(layoutInflater)
    }

    private val firestore = Firebase.firestore
    private var updateId = ""
    private val budgetListLiveData : MutableLiveData<List<Budget>>
        by lazy {
            MutableLiveData<List<Budget>>()
        }
    private var budgetCollectionRef = firestore.collection("budgets")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        with(binding) {

            btnAdd.setOnClickListener {
                val nominal = editNominal.text.toString()
                val desc = editDescription.text.toString()
                val date = editDate.text.toString()

                if (nominal.isNotBlank() && desc.isNotBlank() && date.isNotBlank()) {
                    val newBudget = Budget(nominal = nominal, description = desc, date = date)
                    addBudget(newBudget)
                } else {
                    if (nominal.isBlank()) {
                        editNominal.error = "Please enter a nominal value"
                    }
                    if (desc.isBlank()) {
                        editDescription.error = "Please enter a description"
                    }
                    if (date.isBlank()) {
                        editDate.error = "Please enter a date"
                    }
                }
            }

            lv.setOnItemClickListener {
                    adapterView, _, i, _ ->
                val item = adapterView.adapter.getItem(i) as Budget
                updateId = item.id

                editNominal.setText(item.nominal)
                editDescription.setText(item.description)
                editDate.setText(item.date)
            }

            btnUpdate.setOnClickListener {

                val newBudget = Budget(
                    id = updateId,
                    nominal = editNominal.text.toString(),
                    description = editDescription.text.toString(),
                    date = editDate.text.toString(),
                )

                updateBudget(newBudget)
                setEmptyField()
                updateId = ""
            }

            lv.onItemLongClickListener =
                AdapterView.OnItemLongClickListener {
                    adapterView, _, i, _ ->
                    val item = adapterView.adapter.getItem(i) as Budget
                    updateId = item.id
                    deleteBudget()
                    true
                }

        }

        // * Observe dulu baru get
        observeBudgets()
        getAllBudgets()

    }

    private fun getAllBudgets() {
        budgetCollectionRef.addSnapshotListener {
            snapshots,
            error ->
            if (error != null) {
                Log.d("MainActivity", "Error listening for budget changes ", error)
                return@addSnapshotListener
            }

            val budgets = arrayListOf<Budget>()
            snapshots?.forEach {
                documentReference ->
                budgets.add((
                    Budget(
                        documentReference.id,
                        documentReference.get("nominal").toString(),
                        documentReference.get("description").toString(),
                        documentReference.get("date").toString(),
                    )
                ))
            }

            if (budgets != null) {
                budgetListLiveData.postValue(budgets)
            }

        }
    }

    private fun observeBudgets() {
        budgetListLiveData.observe(this) {
            budgets ->
            val adapter = ArrayAdapter(
                this,
                android.R.layout.simple_list_item_1,
                budgets.toMutableList())
            binding.lv.adapter = adapter
        }
    }

    private fun addBudget(budget: Budget) {
        budgetCollectionRef.add(budget).addOnFailureListener {
            Log.d("MainActivity", "Error adding budget : ", it)
        }
    }

    private fun updateBudget(budget: Budget) {
        budgetCollectionRef.document(updateId).set(budget)
            .addOnFailureListener {
                Log.d("MainActivity", "Error updating budget : ", it)
            }
    }

    private fun deleteBudget() {
        budgetCollectionRef.document(updateId).delete()
            .addOnFailureListener {
                Log.d("MainActivity", "Error deleting budget : ", it)
            }
    }

    private fun setEmptyField() {
        with(binding) {
            editNominal.setText("")
            editDescription.setText("")
            editDate.setText("")
        }
    }

}