package com.yusufmendes.zikirmatik.presentation.view.dua

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.View
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.yusufmendes.zikirmatik.R
import com.yusufmendes.zikirmatik.databinding.FragmentDuaBinding
import com.yusufmendes.zikirmatik.presentation.adapter.DuaAdapter
import com.yusufmendes.zikirmatik.util.extensions.gone
import com.yusufmendes.zikirmatik.util.extensions.showSnackbar
import com.yusufmendes.zikirmatik.util.extensions.visible
import com.yusufmendes.zikirmatik.util.resources.Resource
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class DuaFragment : Fragment(R.layout.fragment_dua) {

    private lateinit var binding: FragmentDuaBinding
    private val viewModel: DuaFragmentViewModel by viewModels()
    private lateinit var duaAdapter: DuaAdapter
    private lateinit var mContext: Context

    override fun onAttach(context: Context) {
        super.onAttach(context)
        mContext = context
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentDuaBinding.bind(view)

        duaAdapter = DuaAdapter()
        with(binding) {
            rvDua.setHasFixedSize(true)
            rvDua.layoutManager = LinearLayoutManager(mContext)
            rvDua.adapter = duaAdapter
        }

        viewModel.getDuaList()
        observeLiveData()
    }

    private fun observeLiveData() {
        viewModel.duaLiveData.observe(viewLifecycleOwner) {
            when (it) {
                Resource.Loading -> {
                    binding.pbDua.visible()
                }

                is Resource.Success -> {
                    duaAdapter.updateDuaList(it.data)
                    binding.pbDua.gone()
                }

                is Resource.Error -> {
                    view?.showSnackbar(it.errorMessage.toString())
                    binding.pbDua.gone()
                }
            }
        }
    }
}