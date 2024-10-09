package com.yusufmendes.zikirmatik.presentation.view.dua

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.View
import com.yusufmendes.zikirmatik.R
import com.yusufmendes.zikirmatik.databinding.FragmentDuaBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class DuaFragment : Fragment(R.layout.fragment_dua) {

    private lateinit var binding : FragmentDuaBinding

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentDuaBinding.bind(view)


    }
}