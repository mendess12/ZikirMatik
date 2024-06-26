package com.yusufmendes.zikirmatik.presentation.nameofAllah

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import com.yusufmendes.zikirmatik.R
import com.yusufmendes.zikirmatik.databinding.FragmentNameOfAllahBinding

class NameOfAllahFragment : Fragment(R.layout.fragment_name_of_allah) {

    private lateinit var binding: FragmentNameOfAllahBinding

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentNameOfAllahBinding.bind(view)
    }

}