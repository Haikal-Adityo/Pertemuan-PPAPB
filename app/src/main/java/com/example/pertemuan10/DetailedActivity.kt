package com.example.pertemuan10

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import androidx.core.content.ContextCompat
import com.example.pertemuan10.databinding.ActivityDetailedBinding

class DetailedActivity : AppCompatActivity() {

    companion object {
        const val EXTRA_FRUIT = "extra_fruit"
    }

    private lateinit var binding: ActivityDetailedBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailedBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val fruit = intent.getParcelableExtra<Fruit>(EXTRA_FRUIT)
        fruit?.let {
            binding.imageBanner.setImageResource(it.banner)
            binding.txtFruitName.text = it.nama
            binding.txtLatinName.text = it.namaLatin
            binding.txtKeterangan.text = it.keterangan

            it.warna?.let { it1 -> setTextColorBasedOnWarna(it1, binding.txtFruitName, binding.txtLatinName) }
        }


        binding.btnBack.setOnClickListener{
            onBackPressed()
        }
    }

    private fun setTextColorBasedOnWarna(warna: String, txtFruitName: TextView, txtLatinName: TextView) {
        val colorResId = when (warna) {
            "red" -> R.color.redTextColor
            "orange" -> R.color.OrangeTextColor
            "yellow" -> R.color.YellowTextColor
            "green" -> R.color.GreenTextColor
            "pink" -> R.color.PinkTextColor
            "purple" -> R.color.PurpleTextColor
            else -> android.R.color.black
        }

        txtFruitName.setTextColor(ContextCompat.getColor(this, colorResId))
        txtLatinName.setTextColor(ContextCompat.getColor(this, colorResId))
    }

}