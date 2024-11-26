package com.yusufmendes.zikirmatik.presentation.splash

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.View
import androidx.navigation.fragment.findNavController
import com.google.android.gms.ads.AdError
import com.google.android.gms.ads.AdRequest
import com.google.android.gms.ads.FullScreenContentCallback
import com.google.android.gms.ads.LoadAdError
import com.google.android.gms.ads.interstitial.InterstitialAd
import com.google.android.gms.ads.interstitial.InterstitialAdLoadCallback
import com.yusufmendes.zikirmatik.R
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class SplashFragment : Fragment(R.layout.fragment_splash) {

    private var interstitialAd: InterstitialAd? = null
    private var isAdLoaded = false

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        CoroutineScope(Dispatchers.Main).launch {
            loadInterstitialAd()
            delay(2000)
        }
    }

    private fun loadInterstitialAd() {
        val adRequest = AdRequest.Builder().build()

        InterstitialAd.load(requireContext(), "ca-app-pub-5347985551957293/1996288195", adRequest,
            object : InterstitialAdLoadCallback() {
                override fun onAdLoaded(ad: InterstitialAd) {
                    interstitialAd = ad
                    isAdLoaded = true
                    showAd()
                }

                override fun onAdFailedToLoad(error: LoadAdError) {
                    isAdLoaded = false
                    navigateToMainActivity()
                }
            })
    }

    private fun showAd() {
        if (isAdLoaded) {
            interstitialAd?.fullScreenContentCallback = object : FullScreenContentCallback() {
                override fun onAdDismissedFullScreenContent() {
                    navigateToMainActivity()
                }

                override fun onAdFailedToShowFullScreenContent(error: AdError) {
                    navigateToMainActivity()
                }
            }
            interstitialAd?.show(requireActivity())
        } else {
            navigateToMainActivity()
        }
    }

    private fun navigateToMainActivity() {
        findNavController().navigate(R.id.action_splashFragment_to_homeFragment)
    }
}