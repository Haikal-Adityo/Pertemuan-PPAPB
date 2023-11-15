package com.example.pertemuan10

import android.content.res.Resources
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.example.pertemuan10.databinding.ItemFruitBinding

//typealias OnClickFruit = (Fruit) -> Unit

class FruitAdapter (
    private val listFruits : List<Fruit>, private val listener: MainActivity) :
    RecyclerView.Adapter<FruitAdapter.ItemFruitViewHolder>() {

    inner class ItemFruitViewHolder(val binding: ItemFruitBinding): RecyclerView.ViewHolder(binding.root){
        fun bind(data: Fruit){
            with(binding){
                    imageFruit.setImageResource(data.image)

                    txtFruitName.text = data.nama
                    txtLatinName.text = data.namaLatin

                    data.warna?.let { setTextColorBasedOnWarna(it, txtFruitName, txtLatinName) }

                    itemView.setOnClickListener{
                        listener.onItemClick(data)
                    }
                }
            }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemFruitViewHolder {
        val binding = ItemFruitBinding.inflate(
            LayoutInflater.from(parent.context), parent,
            false)
        return ItemFruitViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return listFruits.size
    }

    override fun onBindViewHolder(holder: ItemFruitViewHolder, position: Int) {
        val fruit = listFruits[position]
        holder.bind(fruit)
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

        txtFruitName.setTextColor(ContextCompat.getColor(txtFruitName.context, colorResId))
        txtLatinName.setTextColor(ContextCompat.getColor(txtLatinName.context, colorResId))
    }

}
