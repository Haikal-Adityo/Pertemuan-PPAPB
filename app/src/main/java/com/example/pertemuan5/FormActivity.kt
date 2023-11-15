package com.example.pertemuan5

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Html
import android.util.Log
import android.widget.CheckBox
import android.widget.Toast
import com.example.pertemuan5.databinding.ActivityFirstBinding
import com.example.pertemuan5.databinding.ActivityFormBinding

class FormActivity : AppCompatActivity() {

    private lateinit var binding: ActivityFormBinding
    private val TAG = "FirstActivityLifecycle"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityFormBinding.inflate(layoutInflater)
        setContentView(binding.root)



        val checkBox = binding.checkBox
        val tac = "<font color=#FF000000>By checking the box you agree to our</font> " +
                "<font color=#525BFF>Terms</font> " +
                "<font color=#FF000000>and</font> " +
                "<font color=#525BFF>Conditions.</font>"
        checkBox.text = Html.fromHtml(tac)

        val login = binding.txtLogin
        val loginText = "<font color=#FF000000>Already Have an Account?</font> " +
                "<font color=#525BFF>Log In</font> "
        login.text = Html.fromHtml(loginText)

        binding.btnToHome.setOnClickListener {
            openHomeActivity()
        }

    }

    private fun openHomeActivity() {
        // * MEMANGGIL EDIT TEXT
        val name = binding.editTxtName.text.toString()
        val email = binding.editTxtEmail.text.toString()
        val phone = binding.editTxtPhone.text.toString()
        val password = binding.editTxtPassword.text.toString()

        val intent = Intent(this, HomeActivity::class.java)
            .apply {
                // * APPLY PUT EXTRA
                putExtra("NAME", name)
                putExtra("EMAIL", email)
                putExtra("PHONE", phone)
                putExtra("PASSWORD", password)
        }

        // * START HOME ACTIVITY
        val conditions = listOf(name.isNotEmpty(), phone.isNotEmpty(), email.isNotEmpty(), password.isNotEmpty())

        if (conditions.all { it }) {
            if (binding.checkBox.isChecked) {
                startActivity(intent)
            } else {
                showToast("Mohon centang Terms and conditions")
            }
        } else {
            showToast("Mohon isi semua field")
        }

    }

    private fun showToast(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
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