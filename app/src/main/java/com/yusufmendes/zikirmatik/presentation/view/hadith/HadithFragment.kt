package com.yusufmendes.zikirmatik.presentation.view.hadith

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.View
import com.yusufmendes.zikirmatik.R
import com.yusufmendes.zikirmatik.databinding.FragmentHadithBinding


class HadithFragment : Fragment(R.layout.fragment_hadith) {

    private lateinit var binding: FragmentHadithBinding

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentHadithBinding.bind(view)
    }
}