package com.yusufmendes.zikirmatik.presentation.view.counter_list

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.yusufmendes.zikirmatik.R
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

        counterAdapter = CounterAdapter()
        with(binding) {
            rvCounter.setHasFixedSize(true)
            rvCounter.layoutManager = LinearLayoutManager(requireContext())
            rvCounter.adapter = counterAdapter
        }

        viewModel.getCounterList()
        observeLiveData()

    }

    private fun observeLiveData() {
        viewModel.counterListLiveData.observe(viewLifecycleOwner) {
            if (it != null) {
                counterAdapter.updateCounterList(it)
            } else {
                view?.showSnackbar("Zikir listeniz boş!")
            }
        }
    }
}