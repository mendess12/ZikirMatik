package com.yusufmendes.zikirmatik.presentation.view.home

import android.annotation.SuppressLint
import android.os.Bundle
import android.provider.Settings
import androidx.fragment.app.Fragment
import android.view.View
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.yusufmendes.zikirmatik.R
import com.yusufmendes.zikirmatik.data.model.CounterEntity
import com.yusufmendes.zikirmatik.databinding.BottomSheetDialogBinding
import com.yusufmendes.zikirmatik.databinding.FragmentHomeBinding
import com.yusufmendes.zikirmatik.util.extensions.showSnackbar
import dagger.hilt.android.AndroidEntryPoint
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Locale

@AndroidEntryPoint
class HomeFragment : Fragment(R.layout.fragment_home) {

    private lateinit var binding: FragmentHomeBinding
    private lateinit var bottomSheetDialogBinding: BottomSheetDialogBinding
    private val viewModel: HomeFragmentViewModel by viewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentHomeBinding.bind(view)

        binding.buttonSave.setOnClickListener {
            bottomSheetDialog()
        }

        binding.buttonGoList.setOnClickListener {
            findNavController().navigate(R.id.action_homeFragment_to_counterListFragment)
        }
        observeLiveData()
    }

    private fun observeLiveData() {
        viewModel.addCounterLiveData.observe(viewLifecycleOwner) {
            if (it != null) {
                view?.showSnackbar("Zikiriniz Kaydedildi")
            }
        }
    }

    @SuppressLint("HardwareIds")
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
                val calendar = Calendar.getInstance()
                val timeFormat = SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.getDefault())
                val currentTime = timeFormat.format(calendar.time)
                val date = currentTime

                val userId = Settings.Secure.getString(
                    requireContext().contentResolver,
                    Settings.Secure.ANDROID_ID
                )

                val counter = binding.txCounterInfo.text.toString().toInt()
                val id = 3
                val title = counterName

                val counterEntity = CounterEntity(userId, id, title, counter, date)
                viewModel.addCounter(counterEntity)
                dialog.dismiss()
            }
        }
    }
}

