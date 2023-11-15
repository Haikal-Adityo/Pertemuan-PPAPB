package com.example.pertemuan5

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import com.example.pertemuan5.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private val TAG = "MainActivityLifecycle"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setupViews()
    }

    private fun setupViews() {

        with (binding) {

            btnLatihan.setOnClickListener {
                openFirstActivity()
            }

            btnTugas.setOnClickListener {
                openFormActivity()
            }
        }
    }

    private fun openFirstActivity() {
        val intent = Intent(this, FirstActivity::class.java)
        startActivity(intent)
    }

    private fun openFormActivity() {
        val intent = Intent(this, FormActivity::class.java)
        startActivity(intent)
    }



    // * MAIN ACTIVITY LIFECYCLE

    override fun onStart() {
        super.onStart()
        Log.d(TAG, "onStart dipanggil")
    }

    override fun onResume() {
        super.onResume()
        Log.d(TAG, "onResume dipanggil")
    }

    override fun onPause() {
        super.onPause()
        Log.d(TAG, "onPause dipanggil")
    }

    override fun onStop() {
        super.onStop()
        Log.d(TAG, "onStop dipanggil")
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.d(TAG, "onDestroy dipanggil")
    }
}