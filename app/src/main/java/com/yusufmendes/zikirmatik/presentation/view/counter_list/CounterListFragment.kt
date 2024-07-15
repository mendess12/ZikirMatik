package com.yusufmendes.zikirmatik.presentation.view.counter_list

import android.os.Bundle
import android.view.View
import android.widget.Button
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.yusufmendes.zikirmatik.R
import com.yusufmendes.zikirmatik.data.model.CounterEntity
import com.yusufmendes.zikirmatik.databinding.FragmentCounterListBinding
import com.yusufmendes.zikirmatik.presentation.adapter.CounterAdapter
import com.yusufmendes.zikirmatik.util.extensions.showSnackbar
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class CounterListFragment : Fragment(R.layout.fragment_counter_list) {

    private lateinit var binding: FragmentCounterListBinding
    private val viewModel: CounterListFragmentViewModel by viewModels()
    private lateinit var counterAdapter: CounterAdapter

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentCounterListBinding.bind(view)

        counterAdapter = CounterAdapter(::deleteCounter)
        with(binding) {
            rvCounter.setHasFixedSize(true)
            rvCounter.layoutManager = LinearLayoutManager(requireContext())
            rvCounter.adapter = counterAdapter
        }

        viewModel.getCounterList()
        observeLiveData()

    }

    private fun deleteCounter(counterEntity: CounterEntity) {
        showAlertDialog(counterEntity)
    }

    private fun observeLiveData() {
        viewModel.counterListLiveData.observe(viewLifecycleOwner) {
            if (it != null) {
                counterAdapter.updateCounterList(it)
            } else {
                view?.showSnackbar("Zikir listeniz boş!")
            }
        }
        viewModel.deleteCountLiveData.observe(viewLifecycleOwner) {
            if (it != null) {
                view?.showSnackbar("Zikir silindi")
            } else {
                view?.showSnackbar("Zikir listeniz boş!")
            }
        }
    }

    private fun showAlertDialog(counterEntity: CounterEntity) {

        val dialogView = layoutInflater.inflate(R.layout.alert_dialog, null)
        val alertDialog = AlertDialog.Builder(requireContext())
            .setView(dialogView)
        val dialog = alertDialog.create()
        dialog.setCancelable(false)

        dialogView.findViewById<Button>(R.id.ad_btn_cancel).setOnClickListener {
            dialog.dismiss()
        }
        dialogView.findViewById<Button>(R.id.ad_btn_delete).setOnClickListener {
            viewModel.deleteCounter(counterEntity)
            dialog.dismiss()
        }
        dialog.show()
    }
}