package com.yusufmendes.zikirmatik.presentation.view.namaz_detail

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.View
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.yusufmendes.zikirmatik.R
import com.yusufmendes.zikirmatik.databinding.FragmentNamazDetailBinding
import com.yusufmendes.zikirmatik.util.extensions.downloadImage

class NamazDetailFragment : Fragment(R.layout.fragment_namaz_detail) {

    private lateinit var bindig: FragmentNamazDetailBinding
    private val navArgs: NamazDetailFragmentArgs by navArgs()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        bindig = FragmentNamazDetailBinding.bind(view)

        val namaz = navArgs.namaz
        with(bindig) {
            tvNamazDetailTitle.text = namaz.title
            tvNamazDetailDescription.text = namaz.description
            ivNamazDetailImage.downloadImage(namaz.image_url)
        }

        bindig.includeNamazDetail.ivBack.setOnClickListener {
            findNavController().popBackStack()
        }
    }
}