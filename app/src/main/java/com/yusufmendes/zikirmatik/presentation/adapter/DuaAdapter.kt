package com.yusufmendes.zikirmatik.presentation.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.yusufmendes.zikirmatik.data.model.Dua
import com.yusufmendes.zikirmatik.databinding.DuaItemBinding

class DuaAdapter : RecyclerView.Adapter<DuaAdapter.DuaViewHolder>() {

    private val duaList = ArrayList<Dua>()

    inner class DuaViewHolder(private val binding: DuaItemBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(dua: Dua) {
            with(binding) {
                tvDuaTitle.text = dua.title
                tvDuaDescription.text = dua.description
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DuaViewHolder {
        val binding = DuaItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return DuaViewHolder(binding)
    }

    override fun getItemCount(): Int = duaList.size

    override fun onBindViewHolder(holder: DuaViewHolder, position: Int) {
        val dua = duaList[position]
        holder.bind(dua)
    }

    fun updateDuaList(updateDuaList: List<Dua>) {
        duaList.clear()
        duaList.addAll(updateDuaList)
        notifyItemInserted(duaList.size)
    }
}