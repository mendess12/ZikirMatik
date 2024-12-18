package com.yusufmendes.zikirmatik.presentation.view.name_of_allah

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.View
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.GridLayoutManager
import com.yusufmendes.zikirmatik.R
import com.yusufmendes.zikirmatik.databinding.FragmentNameOfAllahBinding
import com.yusufmendes.zikirmatik.presentation.adapter.NameOfAllahAdapter
import com.yusufmendes.zikirmatik.util.extensions.gone
import com.yusufmendes.zikirmatik.util.extensions.showSnackbar
import com.yusufmendes.zikirmatik.util.extensions.visible
import com.yusufmendes.zikirmatik.util.resources.Resource
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class NameOfAllahFragment : Fragment(R.layout.fragment_name_of_allah) {

    private lateinit var binding: FragmentNameOfAllahBinding
    private val viewModel: NameOfAllahFragmentViewModel by viewModels()
    private lateinit var nameOfAllahAdapter: NameOfAllahAdapter
    private lateinit var mContext: Context

    override fun onAttach(context: Context) {
        super.onAttach(context)
        mContext = context
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentNameOfAllahBinding.bind(view)

        nameOfAllahAdapter = NameOfAllahAdapter()
        with(binding) {
            rvNameOfAllah.setHasFixedSize(true)
            rvNameOfAllah.layoutManager =
                GridLayoutManager(mContext, 2, GridLayoutManager.VERTICAL, false)
            rvNameOfAllah.adapter = nameOfAllahAdapter
        }
        viewModel.getNameOfAllahList()
        observeLiveData()
    }

    private fun observeLiveData() {
        viewModel.nameOfAllahLiveData.observe(viewLifecycleOwner) {
            when (it) {
                Resource.Loading -> {
                    binding.pbNameOfAllah.visible()
                }

                is Resource.Success -> {
                    nameOfAllahAdapter.updateNameOfAllahList(it.data)
                    binding.pbNameOfAllah.gone()
                }

                is Resource.Error -> {
                    view?.showSnackbar(it.errorMessage.toString())
                    binding.pbNameOfAllah.gone()
                }
            }
        }
    }
}