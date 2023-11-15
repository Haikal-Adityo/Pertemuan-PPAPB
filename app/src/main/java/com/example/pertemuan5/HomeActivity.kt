package com.example.pertemuan5

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Html
import com.example.pertemuan5.databinding.ActivityHomeBinding

class HomeActivity : AppCompatActivity() {

    private lateinit var binding: ActivityHomeBinding

    @SuppressLint("SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHomeBinding.inflate(layoutInflater)
        setContentView(binding.root)



        // * MEMANGGIL STRING EXTRA
        val name = intent?.getStringExtra("NAME")
        val email = intent?.getStringExtra("EMAIL")
        val phone = intent?.getStringExtra("PHONE")

        // * SET TEXT
        with (binding) {
            val txtName = txtRecievedName
            val nameText = "<font color=#FF000000>Welcome</font> " +
                    "<font color=#525BFF>$name</font>"
            txtName.text = Html.fromHtml(nameText)

            val txtEmail = txtRecievedEmail
            val emailText = "<font color=#FF000000>Your</font> " +
                    "<font color=#525BFF>$email</font> " +
                    "<font color=#FF000000>has been activated</font> "
            txtEmail.text = Html.fromHtml(emailText)

            val txtPhone = txtRecievedPhone
            val phoneText = "<font color=#FF000000>Your</font> " +
                    "<font color=#525BFF>$phone</font> " +
                    "<font color=#FF000000>has been registered</font> "
            txtPhone.text = Html.fromHtml(phoneText)
        }

        // * START FORM ACTIVITY
        val intent = Intent(this, FormActivity::class.java)

        binding.btnLogout.setOnClickListener {
            startActivity(intent)
        }
    }

}