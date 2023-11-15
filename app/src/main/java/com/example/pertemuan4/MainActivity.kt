package com.example.pertemuan4

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.pertemuan4.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setupViews()
    }

    private fun setupViews() {

        binding.btnNews.setOnClickListener {
            openPhoneActivity()
        }

        binding.btnTugas.setOnClickListener {
            openBankActivity()
        }

    }

    private fun openPhoneActivity() {
        val intent = Intent(this, NewsActivity::class.java)
        startActivity(intent)
    }

    private fun openBankActivity() {
        val intent = Intent(this, BankActivity::class.java)
        startActivity(intent)
    }
}

