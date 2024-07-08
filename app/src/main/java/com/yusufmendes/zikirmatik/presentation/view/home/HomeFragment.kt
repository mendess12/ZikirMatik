package com.yusufmendes.zikirmatik.presentation.view.home

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.View
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.google.android.material.snackbar.Snackbar
import com.yusufmendes.zikirmatik.R
import com.yusufmendes.zikirmatik.databinding.BottomSheetDialogBinding
import com.yusufmendes.zikirmatik.databinding.FragmentHomeBinding
import com.yusufmendes.zikirmatik.util.extensions.showSnackbar

class HomeFragment : Fragment(R.layout.fragment_home) {

    private lateinit var binding: FragmentHomeBinding
    private lateinit var bottomSheetDialogBinding: BottomSheetDialogBinding

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentHomeBinding.bind(view)

        binding.buttonSave.setOnClickListener {
            bottomSheetDialog()
        }
    }

    private fun bottomSheetDialog() {
        val dialog = BottomSheetDialog(requireContext())
        bottomSheetDialogBinding = BottomSheetDialogBinding.inflate(layoutInflater)
        dialog.setContentView(bottomSheetDialogBinding.root)
        dialog.show()
        bottomSheetDialogBinding.btdSave.setOnClickListener() {
            val counterName = bottomSheetDialogBinding.etCounterName.text.toString()
            if (counterName.isEmpty()) {
                view?.showSnackbar("Lütfen bir isim giriniz")
            } else {
                view?.showSnackbar("$counterName kaydedildi")
            }
        }
    }
}

