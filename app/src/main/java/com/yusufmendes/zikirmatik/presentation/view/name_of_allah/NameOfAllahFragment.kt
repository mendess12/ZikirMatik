package com.yusufmendes.zikirmatik.presentation.view.name_of_allah

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.View
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.GridLayoutManager
import com.yusufmendes.zikirmatik.R
import com.yusufmendes.zikirmatik.databinding.FragmentNameOfAllahBinding
import com.yusufmendes.zikirmatik.presentation.adapter.NameOfAllahAdapter
import com.yusufmendes.zikirmatik.util.extensions.showSnackbar
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class NameOfAllahFragment : Fragment(R.layout.fragment_name_of_allah) {

    private lateinit var binding: FragmentNameOfAllahBinding
    private val viewModel: NameOfAllahFragmentViewModel by viewModels()
    private lateinit var nameOfAllahAdapter: NameOfAllahAdapter

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentNameOfAllahBinding.bind(view)

        nameOfAllahAdapter = NameOfAllahAdapter()
        with(binding) {
            rvNameOfAllah.setHasFixedSize(true)
            rvNameOfAllah.layoutManager =
                GridLayoutManager(requireContext(), 2, GridLayoutManager.VERTICAL, false)
            rvNameOfAllah.adapter = nameOfAllahAdapter
        }
        viewModel.getNameOfAllahList()
        observeLiveData()
    }

    private fun observeLiveData() {
        viewModel.nameOfAllahLiveData.observe(viewLifecycleOwner) {
            if (it != null) {
                nameOfAllahAdapter.updateNameOfAllahList(it)
            } else {
                view?.showSnackbar("Allah'ın isimleri listesi boşé")
            }
        }
    }
}