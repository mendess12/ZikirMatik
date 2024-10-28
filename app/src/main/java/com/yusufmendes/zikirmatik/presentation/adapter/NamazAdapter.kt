package com.yusufmendes.zikirmatik.presentation.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.yusufmendes.zikirmatik.data.model.Namaz
import com.yusufmendes.zikirmatik.databinding.ItemNamazBinding
import com.yusufmendes.zikirmatik.util.extensions.downloadImage

class NamazAdapter : RecyclerView.Adapter<NamazAdapter.NamazViewHolder>() {

    private val namazList = ArrayList<Namaz>()

    inner class NamazViewHolder(private val binding: ItemNamazBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(namaz: Namaz) {
            with(binding) {
                tvItemNamazTitle.text = namaz.title
                ivItemNamazImage.downloadImage(namaz.image_url)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NamazViewHolder {
        val binding = ItemNamazBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return NamazViewHolder(binding)
    }

    override fun getItemCount(): Int = namazList.size

    override fun onBindViewHolder(holder: NamazViewHolder, position: Int) {
        val namaz = namazList[position]
        holder.bind(namaz)
    }

    fun updateNamazList(updateNamazList: List<Namaz>) {
        namazList.clear()
        namazList.addAll(updateNamazList)
        notifyItemInserted(namazList.size)
    }
}