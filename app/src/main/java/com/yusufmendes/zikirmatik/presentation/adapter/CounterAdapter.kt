package com.yusufmendes.zikirmatik.presentation.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.yusufmendes.zikirmatik.data.model.CounterEntity
import com.yusufmendes.zikirmatik.databinding.ItemCounterBinding

class CounterAdapter : RecyclerView.Adapter<CounterAdapter.CounterViewHolder>() {

    private val counterList = ArrayList<CounterEntity>()

    inner class CounterViewHolder(private val binding: ItemCounterBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(counter: CounterEntity) {
            with(binding) {
                tvItemCounterName.text = counter.title
                tvItemCounterDate.text = counter.date
                tvItemCounter.text = counter.counter.toString()
            }
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CounterViewHolder {
        val binding = ItemCounterBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return CounterViewHolder(binding)
    }

    override fun getItemCount(): Int = counterList.size

    override fun onBindViewHolder(holder: CounterViewHolder, position: Int) {
        val counter = counterList[position]
        holder.bind(counter)
    }

    fun updateCounterList(updateCounterList: List<CounterEntity>) {
        counterList.clear()
        counterList.addAll(updateCounterList)
        notifyDataSetChanged()
    }
}