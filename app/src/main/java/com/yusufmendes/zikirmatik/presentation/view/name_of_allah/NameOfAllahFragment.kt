package com.yusufmendes.zikirmatik.presentation.view.name_of_allah

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.View
import com.yusufmendes.zikirmatik.R
import com.yusufmendes.zikirmatik.databinding.FragmentNameOfAllahBinding

class NameOfAllahFragment : Fragment(R.layout.fragment_name_of_allah) {

    private lateinit var binding: FragmentNameOfAllahBinding

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentNameOfAllahBinding.bind(view)
    }
}