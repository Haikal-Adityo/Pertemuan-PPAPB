package com.example.pertemuan6_tugas

import android.R
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Toast
import com.example.pertemuan6_tugas.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding : ActivityMainBinding
    private val alasan = arrayOf(
        "Hadir Tepat Waktu",
        "Sakit",
        "Terlambat",
        "Izin"
    )
    private var selectedDate: String? = null
    private var selectedTime: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        with(binding) {
// *        DATE PICKER
            datePicker.init(
                datePicker.year,
                datePicker.month,
                datePicker.dayOfMonth
            ) { _, year, monthOfYear, dayOfMonth ->
                selectedDate = "$dayOfMonth/${monthOfYear + 1}/$year"
            }

// *        TIME PICKER
            timePicker.setOnTimeChangedListener{view, hourOfDay, minute ->
                selectedTime = String.format("%02d:%02d", hourOfDay, minute)
            }

// *        PRESENSI SPINNER
            val adapterPresensi = ArrayAdapter(this@MainActivity,
                R.layout.simple_spinner_item, alasan)
            adapterPresensi.setDropDownViewResource(
                com.google.android.material.R.layout.support_simple_spinner_dropdown_item)
            spinnerPresensi.adapter = adapterPresensi

            spinnerPresensi.onItemSelectedListener =
                object : AdapterView.OnItemSelectedListener {
                    override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                        if (position == 1) {
                            editTextPresensi.visibility = View.VISIBLE;
                        } else {
                            editTextPresensi.visibility = View.GONE;
                        }
                    }

                    override fun onNothingSelected(parent: AdapterView<*>?) {
                        TODO("Not yet implemented")
                    }

                }

// *        BUTTON SUBMIT
            btnPresensi.setOnClickListener {
                if (selectedDate != null && selectedTime != null) {
                    showToast("Presensi berhasil $selectedDate jam $selectedTime")
                } else {
                    showToast("Please select a date and time before submitting.")
                }
            }
        }
    }

    private fun showToast(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
    }

}