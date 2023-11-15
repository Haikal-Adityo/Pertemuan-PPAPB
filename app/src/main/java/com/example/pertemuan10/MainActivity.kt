package com.example.pertemuan10

import android.content.Intent
import android.content.res.Resources
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.pertemuan10.DetailedActivity.Companion.EXTRA_FRUIT
import com.example.pertemuan10.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var fruitsList: ArrayList<Fruit>
    private lateinit var fruitAdapter: FruitAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        fruitsList = ArrayList()

        fruitsList.add(Fruit(R.drawable.ic_strawberry, R.drawable.ic_strawberry_banner, nama = "Strawberry", namaLatin = "Fragaria ananassa", warna = "pink", keterangan = "Strawberries are heart-shaped, vibrant red berries known for their naturally sweet taste and delightful aroma. Packed with vitamin C and antioxidants, they are excellent for skin health and immune support. Strawberries are used in a myriad of culinary delights, from jams and desserts to fresh toppings for salads and breakfast dishes."))
        fruitsList.add(Fruit(R.drawable.ic_apple, R.drawable.ic_apple_banner, nama = "Apple", namaLatin = "Malus", warna = "red", keterangan = "Apples are crisp and juicy fruits with a wide range of colors and flavors. They are known for their versatility and are used in various culinary applications, from snacks to pies and cider. Apples are a great source of dietary fiber and vitamin C, promoting digestive health and overall well-being."))
        fruitsList.add(Fruit(R.drawable.ic_orange, R.drawable.ic_orange_banner, nama = "Orange", namaLatin = "Citrus sinensis", warna = "orange", keterangan = "Oranges are citrus fruits renowned for their vibrant color and refreshing, tangy taste. Rich in vitamin C and other antioxidants, they strengthen the immune system and support healthy skin. Oranges are often enjoyed fresh as a snack or consumed in various forms, including juice, to provide a zesty burst of flavor."))
        fruitsList.add(Fruit(R.drawable.ic_banana, R.drawable.ic_banana_banner, nama = "Banana", namaLatin = "Musa", warna = "yellow", keterangan = "Bananas are potassium-rich fruits with a creamy texture and sweet flavor. They are a convenient, on-the-go snack and are packed with essential nutrients, making them an excellent choice for replenishing energy. Bananas are also a versatile ingredient in smoothies, desserts, and baked goods."))
        fruitsList.add(Fruit(R.drawable.ic_kiwi, R.drawable.ic_kiwi_banner, nama = "Kiwi", namaLatin = "Actinidia deliciosa", warna = "green", keterangan = "Kiwis, with their small, fuzzy exteriors and emerald-green flesh speckled with tiny black seeds, offer a unique and tangy-sweet taste. These vitamin C powerhouses support immune health, and their dietary fiber aids digestion. Kiwis are often sliced and eaten as is, but they also make a delightful addition to fruit salads."))
        fruitsList.add(Fruit(R.drawable.ic_grape, R.drawable.ic_grape_banner, nama = "Grape", namaLatin = "Vitis vinifera", warna = "purple", keterangan = "Grapes come in a variety of colors, such as red, green, and black, and are appreciated for their sweet and juicy nature. Grapes are not only a delicious snack but also a key ingredient in wine production. They are a source of antioxidants and can contribute to heart health when consumed in moderation."))

        fruitAdapter = FruitAdapter(fruitsList, this)
        with(binding) {
            rvFruits.apply {
                rvFruits.adapter = fruitAdapter
                layoutManager = LinearLayoutManager(this@MainActivity)
            }
        }

    }

    fun onItemClick(data: Fruit) {
        val intent = Intent(this, DetailedActivity::class.java)
        intent.putExtra(DetailedActivity.EXTRA_FRUIT, data)
        startActivity(intent)
    }

}