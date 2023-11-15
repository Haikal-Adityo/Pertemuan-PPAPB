package com.example.pertemuan3

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.pertemuan3.databinding.ActivityCalculatorBinding

class CalculatorActivity : AppCompatActivity() {

    private lateinit var binding: ActivityCalculatorBinding
    private var operator: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCalculatorBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setupViews()
    }

    private fun setupViews() {

        // Set up click listeners for number buttons
        binding.btn0.setOnClickListener { appendNumber("0") }
        binding.btn1.setOnClickListener { appendNumber("1") }
        binding.btn2.setOnClickListener { appendNumber("2") }
        binding.btn3.setOnClickListener { appendNumber("3") }
        binding.btn4.setOnClickListener { appendNumber("4") }
        binding.btn5.setOnClickListener { appendNumber("5") }
        binding.btn6.setOnClickListener { appendNumber("6") }
        binding.btn7.setOnClickListener { appendNumber("7") }
        binding.btn8.setOnClickListener { appendNumber("8") }
        binding.btn9.setOnClickListener { appendNumber("9") }

        // Set up click listeners for operator buttons
        binding.btnKali.setOnClickListener { setOperator("x") }
        binding.btnBagi.setOnClickListener { setOperator(":") }
        binding.btnTambah.setOnClickListener { setOperator("+") }
        binding.btnKurang.setOnClickListener { setOperator("-") }

        // Set up click listener for equals button
        binding.btnSamadengan.setOnClickListener {
            if (binding.txtAngka1.text.isNotEmpty() && binding.txtAngka2.text.isNotEmpty()) {
                calculateResult()
            } else {
                showToast("Mohon isi kedua angka")
            }
        }

    }

    private fun appendNumber(number: String) {
        if (operator == null) {
            val currentText = binding.txtAngka1.text.toString()
            val newText = currentText + number
            binding.txtAngka1.text = newText
        } else {
            val currentText = binding.txtAngka2.text.toString()
            val newText = currentText + number
            binding.txtAngka2.text = newText
        }
    }

    private fun setOperator(newOperator: String) {
        if (binding.txtAngka1.text.isNotEmpty()) {
            operator = newOperator
            binding.txtOperator.text = operator
        } else {
            showToast("Isi angka dahulu")
        }
    }

    private fun calculateResult() {
        val num1 = binding.txtAngka1.text.toString().toDouble()
        val num2 = binding.txtAngka2.text.toString().toDouble()

        val result = when (operator) {
            "+" -> num1 + num2
            "-" -> num1 - num2
            "x" -> num1 * num2
            ":" -> num1 / num2
            else -> 0.0
        }

        val hasil = result.toString()
        showToast("Hasil $num1 $operator $num2 = $hasil")

        binding.txtHasil.text = hasil
        binding.txtAngka1.text = ""
        binding.txtAngka2.text = ""
        binding.txtOperator.text = ""
        operator = null
    }

    private fun showToast(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
    }

}

