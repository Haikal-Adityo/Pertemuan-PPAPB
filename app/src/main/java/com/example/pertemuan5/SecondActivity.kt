package com.example.pertemuan5

import android.annotation.SuppressLint
import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import com.example.pertemuan5.ThirdActivity.Companion.ADDRESS_KEY
import com.example.pertemuan5.ThirdActivity.Companion.NAME_KEY
import com.example.pertemuan5.databinding.ActivitySecondBinding

class SecondActivity : AppCompatActivity() {

    private lateinit var binding: ActivitySecondBinding

    @SuppressLint("SetTextI18n")
    private val launcher = registerForActivityResult(ActivityResultContracts.StartActivityForResult()) {
        result ->
        if (result.resultCode == Activity.RESULT_OK) {
            val data = result.data

            val name = data?.getStringExtra(NAME_KEY)
            val address = data?.getStringExtra(ADDRESS_KEY)

            with(binding) {
                txtRecievedName.text = "Hello $name !"
                txtRecievedAddress.text = "di $address"
            }
        }
        //TODO: add a callback function after the next page is dismissed
        //update txtNama dari data dari activity tige
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySecondBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setupViews()
    }

    @SuppressLint("SetTextI18n")
    private fun setupViews() {

        val name = intent?.getStringExtra("name_key")

        with(binding) {
            txtRecievedName.text = "Hello $name !"
        }

        binding.btnToThird.setOnClickListener {
            openThirdActivity()
        }

    }

    private fun openThirdActivity() {
        val name = intent?.getStringExtra("name_key")
        val intent = Intent(this, ThirdActivity::class.java)
        intent.putExtra("name_key", name)
        launcher.launch(intent)
    }


    private fun showToast(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
    }


}