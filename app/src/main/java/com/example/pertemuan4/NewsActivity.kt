package com.example.pertemuan4

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.pertemuan4.databinding.ActivityNewsBinding

class NewsActivity : AppCompatActivity() {

    private lateinit var binding: ActivityNewsBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityNewsBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setupViews()
    }

    private fun setupViews() {

        binding.btnPrevious.setOnClickListener {
            onBackPressed()
        }

        binding.btnNext.setOnClickListener {
            openBankActivity()
        }

    }

    private fun openBankActivity() {
        val intent = Intent(this, BankActivity::class.java)
        startActivity(intent)
    }
}