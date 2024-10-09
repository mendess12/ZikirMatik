package com.yusufmendes.zikirmatik.presentation.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.View
import com.yusufmendes.zikirmatik.R
import com.yusufmendes.zikirmatik.databinding.FragmentTableBinding
import com.yusufmendes.zikirmatik.presentation.view.hadith.HadithFragment
import com.yusufmendes.zikirmatik.util.extensions.showSnackbar
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class TableFragment : Fragment(R.layout.fragment_table) {

    private lateinit var binding: FragmentTableBinding

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentTableBinding.bind(view)

        loadChildFragment(HadithFragment())

        with(binding) {
            hadithColumn.setOnClickListener {
                loadChildFragment(HadithFragment())
            }
            duaColumn.setOnClickListener {
                view.showSnackbar("Dua fragmentı")
            }
        }
    }

    private fun loadChildFragment(fragment: Fragment) {
        val transaction = childFragmentManager.beginTransaction()
        transaction.replace(R.id.fragmentContainer, fragment)
        transaction.addToBackStack(null)
        transaction.commit()
    }

}