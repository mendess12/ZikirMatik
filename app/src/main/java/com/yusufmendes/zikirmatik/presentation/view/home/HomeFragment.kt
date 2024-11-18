package com.yusufmendes.zikirmatik.presentation.view.home

import android.annotation.SuppressLint
import android.content.Context
import android.os.Build
import android.os.Bundle
import android.os.VibrationEffect
import android.os.Vibrator
import android.os.VibratorManager
import android.provider.Settings
import androidx.fragment.app.Fragment
import android.view.View
import android.widget.Button
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.yusufmendes.zikirmatik.R
import com.yusufmendes.zikirmatik.data.model.CounterEntity
import com.yusufmendes.zikirmatik.databinding.BottomSheetDialogBinding
import com.yusufmendes.zikirmatik.databinding.FragmentHomeBinding
import com.yusufmendes.zikirmatik.util.extensions.gone
import com.yusufmendes.zikirmatik.util.extensions.showSnackbar
import com.yusufmendes.zikirmatik.util.extensions.visible
import com.yusufmendes.zikirmatik.util.storage.SharedPrefManager
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Locale

@AndroidEntryPoint
class HomeFragment : Fragment(R.layout.fragment_home) {

    private lateinit var binding: FragmentHomeBinding
    private lateinit var bottomSheetDialogBinding: BottomSheetDialogBinding
    private val navArgs: HomeFragmentArgs by navArgs()
    private val viewModel: HomeFragmentViewModel by viewModels()
    private var vibrateCount = 0
    private lateinit var mContext: Context

    override fun onAttach(context: Context) {
        super.onAttach(context)
        mContext = context
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentHomeBinding.bind(view)

        val count = navArgs.count

        with(binding) {
            buttonSave.setOnClickListener {
                if (SharedPrefManager(mContext).getIsNavArgs()) {
                    showUpdateAlertDialog(
                        SharedPrefManager(mContext).getCounter(),
                        SharedPrefManager(mContext).getNavArgs()!!.counterId,
                        getCurrentDate()
                    )
                } else {
                    bottomSheetDialog()
                }
            }
            buttonGoList.setOnClickListener {
                findNavController().navigate(R.id.action_homeFragment_to_counterListFragment)
            }
            buttonCounter.setOnClickListener {
                viewModel.getCounter()
                SharedPrefManager(mContext).saveCounter(viewModel.count)
                vibratePhone()
            }
            buttonReset.setOnClickListener {
                showAlertDialog()
            }
            buttonVibration.setOnClickListener {
                vibrateCount = SharedPrefManager(mContext).getVibrateState()
                vibrateCount++
                SharedPrefManager(mContext).saveVibrateState(vibrateCount)
                vibrateStateBackground()
            }
            buttonStar.setOnClickListener {
                showPlayStoreAlertDialog()
            }
            tvCountDelete.setOnClickListener {
                SharedPrefManager(mContext).isNavArgs(false)
                SharedPrefManager(mContext).saveCounter(0)
                binding.txCounterInfo.text = SharedPrefManager(mContext).getCounter().toString()
                viewModel.count = SharedPrefManager(mContext).getCounter()
                tvCountDelete.gone()
            }
        }

        if (SharedPrefManager(mContext).getIsNavArgs()) {
            binding.tvCountDelete.text =
                "${count?.title} adlı zikirinizi sonlandırmak için Buraya Tıklayınız!"
            binding.tvCountDelete.visible()
            if (count != null) {
                binding.txCounterInfo.text = count.counter.toString()
                viewModel.count = count.counter!!
                SharedPrefManager(mContext).saveCounter(viewModel.count)
            } else {
                binding.txCounterInfo.text = SharedPrefManager(mContext).getCounter().toString()
                viewModel.count = SharedPrefManager(mContext).getCounter()
            }
        } else {
            binding.txCounterInfo.text = SharedPrefManager(mContext).getCounter().toString()
            viewModel.count = SharedPrefManager(mContext).getCounter()
            binding.tvCountDelete.gone()
        }

        vibrateStateBackground()
        observeLiveData()
    }

    private fun observeLiveData() {
        viewLifecycleOwner.lifecycleScope.launch {
            viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.addCountSharedFlow.collect {
                    view?.showSnackbar("Zikiriniz Kaydedildi")
                }
            }
        }

        viewLifecycleOwner.lifecycleScope.launch {
            viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.updateCountSharedFlow.collect {
                    view?.showSnackbar("Zikriniz Güncellendi")
                }
            }
        }

        viewModel.countLiveData.observe(viewLifecycleOwner) {
            binding.txCounterInfo.text = it.toString()
        }

        viewModel.deleteCounterLiveData.observe(viewLifecycleOwner) {
            binding.txCounterInfo.text = SharedPrefManager(mContext).getCounter().toString()
        }

        viewLifecycleOwner.lifecycleScope.launch {
            viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.playStoreSharedFlow.collect { result ->
                    if (result) {
                        view?.showSnackbar("İşlem başarılı.")
                    } else {
                        view?.showSnackbar("İşlem başarısız.")
                    }
                }
            }
        }
    }

    private fun vibratePhone() {
        if (SharedPrefManager(mContext).getVibrateState() % 2 == 0) {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.S) {
                // For API level 31 and above
                val vibratorManager =
                    mContext.getSystemService(Context.VIBRATOR_MANAGER_SERVICE) as VibratorManager
                val vibrator = vibratorManager.defaultVibrator
                vibrator.vibrate(
                    VibrationEffect.createOneShot(
                        150,
                        VibrationEffect.DEFAULT_AMPLITUDE
                    )
                )
            } else {
                // For below API level 31
                val vibrator =
                    mContext.getSystemService(Context.VIBRATOR_SERVICE) as Vibrator
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                    // For API level 26 and above
                    vibrator.vibrate(
                        VibrationEffect.createOneShot(
                            150,
                            VibrationEffect.DEFAULT_AMPLITUDE
                        )
                    )
                } else {
                    // For below API level 26
                    vibrator.vibrate(150)
                }
            }
        }
    }

    @SuppressLint("HardwareIds")
    private fun bottomSheetDialog() {
        val dialog = BottomSheetDialog(mContext)
        bottomSheetDialogBinding = BottomSheetDialogBinding.inflate(layoutInflater)
        dialog.setContentView(bottomSheetDialogBinding.root)
        dialog.show()
        bottomSheetDialogBinding.btdSave.setOnClickListener() {
            val counterName = bottomSheetDialogBinding.etCounterName.text.toString()
            if (counterName.isEmpty()) {
                view?.showSnackbar("Lütfen bir isim giriniz")
            } else {

                val date = getCurrentDate()
                val userId = Settings.Secure.getString(
                    mContext.contentResolver,
                    Settings.Secure.ANDROID_ID
                )

                val counter = binding.txCounterInfo.text.toString().toInt()

                val counterEntity = CounterEntity(userId, 0, counterName, counter, date)
                viewModel.addCounter(counterEntity)
                dialog.dismiss()
            }
        }
    }

    private fun vibrateStateBackground() {
        if (SharedPrefManager(mContext).getVibrateState() % 2 == 0) {
            binding.buttonVibration.setImageResource(R.drawable.volume_on)
        } else {
            binding.buttonVibration.setImageResource(R.drawable.volume_off)
        }
    }

    private fun showAlertDialog() {

        val dialogView = layoutInflater.inflate(R.layout.reset_alert_dialog, null)
        val alertDialog = AlertDialog.Builder(mContext)
            .setView(dialogView)
        val dialog = alertDialog.create()
        dialog.setCancelable(false)

        dialogView.findViewById<Button>(R.id.rad_btn_cancel).setOnClickListener {
            dialog.dismiss()
        }
        dialogView.findViewById<Button>(R.id.rad_btn_reset).setOnClickListener {
            viewModel.resetCounter()
            SharedPrefManager(mContext).saveCounter(viewModel.count)
            dialog.dismiss()
        }
        dialog.show()
    }

    private fun showPlayStoreAlertDialog() {

        val dialogView = layoutInflater.inflate(R.layout.play_store_alert_dialog, null)
        val alertDialog = AlertDialog.Builder(mContext)
            .setView(dialogView)
        val dialog = alertDialog.create()
        dialog.setCancelable(false)

        dialogView.findViewById<Button>(R.id.psad_btn_cancel).setOnClickListener {
            dialog.dismiss()
        }
        dialogView.findViewById<Button>(R.id.psad_btn_go).setOnClickListener {
            viewModel.openPlayStore(mContext)
            dialog.dismiss()
        }
        dialog.show()
    }

    private fun showUpdateAlertDialog(count: Int, countId: Int, date: String) {
        val dialogView = layoutInflater.inflate(R.layout.update_count_alert_dialog, null)
        val alertDialog = AlertDialog.Builder(mContext)
            .setView(dialogView)
        val dialog = alertDialog.create()
        dialog.setCancelable(false)

        dialogView.findViewById<Button>(R.id.uad_btn_cancel).setOnClickListener {
            dialog.dismiss()
        }
        dialogView.findViewById<Button>(R.id.uad_btn_update).setOnClickListener {
            viewModel.updateCount(count, countId, date)
            dialog.dismiss()
        }
        dialog.show()
    }

    private fun getCurrentDate(): String {
        val calendar = Calendar.getInstance()
        val timeFormat = SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.getDefault())
        val currentTime = timeFormat.format(calendar.time)
        val date = currentTime
        return date
    }
}

