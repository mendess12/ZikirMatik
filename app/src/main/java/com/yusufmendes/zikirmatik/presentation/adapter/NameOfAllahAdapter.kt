package com.yusufmendes.zikirmatik.presentation.adapter

import android.graphics.Color
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.yusufmendes.zikirmatik.R
import com.yusufmendes.zikirmatik.data.model.NameOfAllah
import com.yusufmendes.zikirmatik.databinding.ItemNameOfAllahBinding

class NameOfAllahAdapter : RecyclerView.Adapter<NameOfAllahAdapter.NameOfAllahViewHolder>() {

    private var nameOfAllahList = ArrayList<NameOfAllah>()
    private val colors: Array<String> = arrayOf(
        "#13bd27",
        "#29c1e1",
        "#b129e1",
        "#ACB60E",
        "#f6bd0c",
        "#71BA66",
        "#0d9de3",
        "#D3BD77"
    )

    inner class NameOfAllahViewHolder(private val binding: ItemNameOfAllahBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(nameOfAllah: NameOfAllah, colors: Array<String>, position: Int) {
            with(binding) {
                clItemNameOfAllah.setBackgroundResource(R.drawable.item_background)
                clItemNameOfAllah.background.setTint(Color.parseColor(colors[position % 8]))
                tvItemArabicName.text = nameOfAllah.arabicName
                tvItemTurkishName.text = nameOfAllah.turkishName
                tvItemMean.text = nameOfAllah.mean
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NameOfAllahViewHolder {
        val binding =
            ItemNameOfAllahBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return NameOfAllahViewHolder(binding)
    }

    override fun getItemCount(): Int = nameOfAllahList.size

    override fun onBindViewHolder(holder: NameOfAllahViewHolder, position: Int) {
        val nameOfAllah = nameOfAllahList[position]
        holder.bind(nameOfAllah, colors, position)
    }

    fun updateNameOfAllahList(updateNameOfAllahList: List<NameOfAllah>) {
        nameOfAllahList.clear()
        nameOfAllahList.addAll(updateNameOfAllahList)
        notifyItemInserted(nameOfAllahList.size)
    }
}