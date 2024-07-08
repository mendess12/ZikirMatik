package com.yusufmendes.zikirmatik.presentation.view.hadith

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.View
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.yusufmendes.zikirmatik.R
import com.yusufmendes.zikirmatik.databinding.FragmentHadithBinding
import com.yusufmendes.zikirmatik.presentation.adapter.HadithAdapter
import com.yusufmendes.zikirmatik.util.extensions.showSnackbar
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HadithFragment : Fragment(R.layout.fragment_hadith) {

    private lateinit var binding: FragmentHadithBinding
    private lateinit var hadithAdapter: HadithAdapter
    private val viewModel: HadithFragmentViewModel by viewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentHadithBinding.bind(view)

        hadithAdapter = HadithAdapter()
        with(binding) {
            rvHadith.setHasFixedSize(true)
            rvHadith.layoutManager = LinearLayoutManager(requireContext())
            rvHadith.adapter = hadithAdapter
        }

        viewModel.getHadithList()
        observeData()
    }


    private fun observeData() {
        viewModel.hadithLiveData.observe(viewLifecycleOwner) {
            if (it != null) {
                hadithAdapter.updateHadithList(it)
            } else {
                view?.showSnackbar("Hadis ve Ayet listesi boş!")
            }
        }
    }
}