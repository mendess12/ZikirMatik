package com.yusufmendes.zikirmatik.presentation.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.yusufmendes.zikirmatik.data.model.Hadith
import com.yusufmendes.zikirmatik.databinding.ItemHadithBinding

class HadithAdapter : RecyclerView.Adapter<HadithAdapter.HadithViewHolder>() {

    private var hadithList = ArrayList<Hadith>()

    inner class HadithViewHolder(private val binding: ItemHadithBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(hadith: Hadith) {
            with(binding) {
                tvItemHadithTitle.text = hadith.title
                tvItemHadithContent.text = hadith.content
            }
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HadithViewHolder {
        val binding = ItemHadithBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return HadithViewHolder(binding)
    }

    override fun getItemCount(): Int = hadithList.size

    override fun onBindViewHolder(holder: HadithViewHolder, position: Int) {
        val hadith = hadithList[position]
        holder.bind(hadith)
    }

    fun updateHadithList(updateHadithList: List<Hadith>) {
        hadithList.clear()
        hadithList.addAll(updateHadithList)
        notifyItemInserted(hadithList.size)
    }
}