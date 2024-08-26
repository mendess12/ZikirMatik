package com.yusufmendes.zikirmatik.presentation.view.hadith

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.View
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.yusufmendes.zikirmatik.R
import com.yusufmendes.zikirmatik.databinding.FragmentHadithBinding
import com.yusufmendes.zikirmatik.presentation.adapter.HadithAdapter
import com.yusufmendes.zikirmatik.util.extensions.gone
import com.yusufmendes.zikirmatik.util.extensions.showSnackbar
import com.yusufmendes.zikirmatik.util.extensions.visible
import com.yusufmendes.zikirmatik.util.resources.Resource
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HadithFragment : Fragment(R.layout.fragment_hadith) {

    private lateinit var binding: FragmentHadithBinding
    private lateinit var hadithAdapter: HadithAdapter
    private val viewModel: HadithFragmentViewModel by viewModels()
    private lateinit var mContext: Context

    override fun onAttach(context: Context) {
        super.onAttach(context)
        mContext = context
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentHadithBinding.bind(view)

        hadithAdapter = HadithAdapter()
        with(binding) {
            rvHadith.setHasFixedSize(true)
            rvHadith.layoutManager = LinearLayoutManager(mContext)
            rvHadith.adapter = hadithAdapter
        }

        viewModel.getHadithList()
        observeLiveData()
    }


    private fun observeLiveData() {
        viewModel.hadithLiveData.observe(viewLifecycleOwner) {
            when (it) {
                Resource.Loading -> {
                    binding.pbHadith.visible()
                }

                is Resource.Success -> {
                    hadithAdapter.updateHadithList(it.data)
                    binding.pbHadith.gone()
                }

                is Resource.Error -> {
                    view?.showSnackbar(it.errorMessage.toString())
                    binding.pbHadith.gone()
                }
            }
        }
    }
}