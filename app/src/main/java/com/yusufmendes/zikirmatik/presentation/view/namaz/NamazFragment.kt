package com.yusufmendes.zikirmatik.presentation.view.namaz

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.View
import com.yusufmendes.zikirmatik.R
import com.yusufmendes.zikirmatik.databinding.FragmentNamazBinding

class NamazFragment : Fragment(R.layout.fragment_namaz) {

    private lateinit var binding: FragmentNamazBinding

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentNamazBinding.bind(view)
    }
}