package com.yusufmendes.zikirmatik.presentation.view.counter_list

import android.content.Context
import android.os.Bundle
import android.view.View
import android.widget.Button
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.widget.SearchView.OnQueryTextListener
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.gms.ads.AdError
import com.google.android.gms.ads.AdRequest
import com.google.android.gms.ads.FullScreenContentCallback
import com.google.android.gms.ads.LoadAdError
import com.google.android.gms.ads.interstitial.InterstitialAd
import com.google.android.gms.ads.interstitial.InterstitialAdLoadCallback
import com.yusufmendes.zikirmatik.R
import com.yusufmendes.zikirmatik.data.model.CounterEntity
import com.yusufmendes.zikirmatik.databinding.FragmentCounterListBinding
import com.yusufmendes.zikirmatik.presentation.adapter.CounterAdapter
import com.yusufmendes.zikirmatik.util.extensions.gone
import com.yusufmendes.zikirmatik.util.extensions.showSnackbar
import com.yusufmendes.zikirmatik.util.extensions.visible
import com.yusufmendes.zikirmatik.util.storage.SharedPrefManager
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class CounterListFragment : Fragment(R.layout.fragment_counter_list) {

    private lateinit var binding: FragmentCounterListBinding
    private val viewModel: CounterListFragmentViewModel by viewModels()
    private lateinit var counterAdapter: CounterAdapter
    private lateinit var mContext: Context
    private var interstitialAd: InterstitialAd? = null

    override fun onAttach(context: Context) {
        super.onAttach(context)
        mContext = context
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentCounterListBinding.bind(view)

        counterAdapter = CounterAdapter(::deleteCounter, ::continueCount)
        with(binding) {
            rvCounter.setHasFixedSize(true)
            rvCounter.layoutManager = LinearLayoutManager(mContext)
            rvCounter.adapter = counterAdapter
        }

        binding.includeCount.ivBack.setOnClickListener {
            findNavController().popBackStack()
        }

        search()
        viewModel.getCounterList()
        observeLiveData()
        loadInterstitialAd()

    }

    private fun deleteCounter(counterEntity: CounterEntity) {
        showAlertDialog(counterEntity)
    }

    private fun continueCount(counterEntity: CounterEntity) {
        if (interstitialAd != null) {
            interstitialAd?.fullScreenContentCallback = object : FullScreenContentCallback() {
                override fun onAdDismissedFullScreenContent() {
                    val action =
                        CounterListFragmentDirections.actionCounterListFragmentToHomeFragment(
                            counterEntity
                        )
                    findNavController().navigate(action) // Reklam kapatılınca zikire devam et
                    SharedPrefManager(mContext).saveNavArgs(counterEntity)
                    SharedPrefManager(mContext).isNavArgs(true)
                    loadInterstitialAd() // Yeni bir reklam yükle
                }

                override fun onAdFailedToShowFullScreenContent(error: AdError) {
                    val action =
                        CounterListFragmentDirections.actionCounterListFragmentToHomeFragment(
                            counterEntity
                        )
                    findNavController().navigate(action) // Reklam gösterilemezse de devam et
                    SharedPrefManager(mContext).saveNavArgs(counterEntity)
                    SharedPrefManager(mContext).isNavArgs(true)
                }
            }
            interstitialAd?.show(requireActivity())
        } else {
            // Reklam yoksa direkt zikire devam et
            val action =
                CounterListFragmentDirections.actionCounterListFragmentToHomeFragment(
                    counterEntity
                )
            findNavController().navigate(action)
            SharedPrefManager(mContext).saveNavArgs(counterEntity)
            SharedPrefManager(mContext).isNavArgs(true)
        }

    }

    private fun observeLiveData() {
        viewLifecycleOwner.lifecycleScope.launch {
            viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.counterListSharedFlow.collect { counterList ->
                    if (counterList.isNotEmpty()) {
                        counterAdapter.updateCounterList(counterList)
                        binding.tvEmptyInfo.gone()
                    } else {
                        counterAdapter.updateCounterList(emptyList())
                        binding.tvEmptyInfo.setText(R.string.empty_count_info)
                        binding.tvEmptyInfo.visible()
                    }
                }
            }
        }
        viewLifecycleOwner.lifecycleScope.launch {
            viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.searchSharedFlow.collect { searchList ->
                    if (searchList.isNotEmpty()) {
                        counterAdapter.updateCounterList(searchList)
                        binding.tvEmptyInfo.gone()
                    } else {
                        counterAdapter.updateCounterList(emptyList())
                        binding.tvEmptyInfo.setText(R.string.search_info)
                        binding.tvEmptyInfo.visible()
                    }
                }
            }
        }
        viewModel.deleteCountLiveData.observe(viewLifecycleOwner) {
            if (it != null) {
                view?.showSnackbar("Zikir silindi")
            } else {
                view?.showSnackbar("Zikir listeniz boş!")
            }
        }
    }

    private fun search() {
        binding.searchCounter.setOnQueryTextListener(object : OnQueryTextListener {
            override fun onQueryTextSubmit(query: String): Boolean {
                viewModel.searchCounter(query)
                return true
            }

            override fun onQueryTextChange(newText: String): Boolean {
                viewModel.searchCounter(newText)
                return true
            }
        })
    }

    private fun showAlertDialog(counterEntity: CounterEntity) {

        val dialogView = layoutInflater.inflate(R.layout.alert_dialog, null)
        val alertDialog = AlertDialog.Builder(mContext)
            .setView(dialogView)
        val dialog = alertDialog.create()
        dialog.setCancelable(false)

        dialogView.findViewById<Button>(R.id.ad_btn_cancel).setOnClickListener {
            dialog.dismiss()
        }
        dialogView.findViewById<Button>(R.id.ad_btn_delete).setOnClickListener {
            viewModel.deleteCounter(counterEntity)
            dialog.dismiss()
        }
        dialog.show()
    }

    private fun loadInterstitialAd() {
        val adRequest = AdRequest.Builder().build()
        InterstitialAd.load(requireContext(), "ca-app-pub-5347985551957293/1418537841", adRequest,
            object : InterstitialAdLoadCallback() {
                override fun onAdLoaded(ad: InterstitialAd) {
                    interstitialAd = ad
                }

                override fun onAdFailedToLoad(error: LoadAdError) {
                    interstitialAd = null // Reklam yüklenemediyse null yap
                }
            })
    }

    override fun onResume() {
        super.onResume()
        viewModel.getCounterList()
    }

}