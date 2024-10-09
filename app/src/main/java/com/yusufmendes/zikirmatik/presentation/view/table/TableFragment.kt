package com.yusufmendes.zikirmatik.presentation.view.table

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.View
import com.yusufmendes.zikirmatik.R
import com.yusufmendes.zikirmatik.databinding.FragmentTableBinding
import com.yusufmendes.zikirmatik.presentation.view.dua.DuaFragment
import com.yusufmendes.zikirmatik.presentation.view.hadith.HadithFragment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class TableFragment : Fragment(R.layout.fragment_table) {

    private lateinit var binding: FragmentTableBinding

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentTableBinding.bind(view)

        loadChildFragment(HadithFragment())


        with(binding) {
            hadithColumn.setBackgroundResource(R.drawable.table_background)

            hadithColumn.setOnClickListener {
                hadithColumn.setBackgroundResource(R.drawable.table_background)
                duaColumn.setBackgroundResource(R.color.screen_background)
                loadChildFragment(HadithFragment())
            }
            duaColumn.setOnClickListener {
                hadithColumn.setBackgroundResource(R.color.screen_background)
                duaColumn.setBackgroundResource(R.drawable.table_background)
                loadChildFragment(DuaFragment())
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