package com.yusufmendes.zikirmatik.presentation.view.namaz

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.View
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.yusufmendes.zikirmatik.R
import com.yusufmendes.zikirmatik.data.model.Namaz
import com.yusufmendes.zikirmatik.databinding.FragmentNamazBinding
import com.yusufmendes.zikirmatik.presentation.adapter.NamazAdapter
import com.yusufmendes.zikirmatik.util.extensions.gone
import com.yusufmendes.zikirmatik.util.extensions.showSnackbar
import com.yusufmendes.zikirmatik.util.extensions.visible
import com.yusufmendes.zikirmatik.util.resources.Resource
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class NamazFragment : Fragment(R.layout.fragment_namaz) {

    private lateinit var binding: FragmentNamazBinding
    private val viewModel: NamazFragmentViewModel by viewModels()
    private lateinit var namazAdapter: NamazAdapter

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentNamazBinding.bind(view)

        namazAdapter = NamazAdapter(::detailOnClick)
        with(binding) {
            rvNamaz.setHasFixedSize(true)
            rvNamaz.adapter = namazAdapter
        }

        viewModel.getNamazList()
        observeLiveData()
    }

    private fun observeLiveData() {
        viewModel.namazLiveData.observe(viewLifecycleOwner) {
            when (it) {
                Resource.Loading -> {
                    binding.pbNamaz.visible()
                }

                is Resource.Success -> {
                    namazAdapter.updateNamazList(it.data)
                    binding.pbNamaz.gone()
                }

                is Resource.Error -> {
                    view?.showSnackbar(it.errorMessage.toString())
                    binding.pbNamaz.gone()
                }
            }
        }
    }

    private fun detailOnClick(namaz: Namaz) {
        val action = NamazFragmentDirections.actionNamazFragmentToNamazDetailFragment(namaz)
        findNavController().navigate(action)
    }
}