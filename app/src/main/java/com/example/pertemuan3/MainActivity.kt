package com.example.pertemuan3

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.pertemuan3.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setupViews()
    }

    private fun setupViews() {

        binding.btnPhone.setOnClickListener {
            openPhoneActivity()
        }

        binding.btnCalculator.setOnClickListener {
            openCalculatorActivity()
        }

    }

    private fun openPhoneActivity() {
        val intent = Intent(this, PhoneActivity::class.java)
        startActivity(intent)
    }

    private fun openCalculatorActivity() {
        val intent = Intent(this, CalculatorActivity::class.java)
        startActivity(intent)
    }
}

