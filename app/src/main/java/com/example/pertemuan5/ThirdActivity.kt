package com.example.pertemuan5

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.pertemuan5.databinding.ActivitySecondBinding
import com.example.pertemuan5.databinding.ActivityThirdBinding

class ThirdActivity : AppCompatActivity() {

    private lateinit var binding: ActivityThirdBinding
    companion object {
        const val NAME_KEY = "name_key"
        const val ADDRESS_KEY = "address_key" //! VARIABEL CONST BIASANYA KAPITAL
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityThirdBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setupViews()
    }

    private fun setupViews() {

        binding.btnToSecond.setOnClickListener {
            if (binding.editTxtAddress.text.isNotEmpty()) {
                openSecondActivity()
            } else {
                showToast("Masukan address anda!")
            }
        }

    }

    private fun openSecondActivity() {
        val name = intent.getStringExtra(NAME_KEY)

        val intent = Intent()
        intent.putExtra(NAME_KEY, name)

        val address = binding.editTxtAddress.text.toString()
        intent.putExtra(ADDRESS_KEY, address)

        setResult(Activity.RESULT_OK, intent)
        finish()
    }

    private fun showToast(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
    }

}