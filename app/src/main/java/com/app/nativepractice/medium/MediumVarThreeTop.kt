package com.app.nativepractice.medium

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.RelativeLayout
import androidx.cardview.widget.CardView
import com.app.nativepractice.databinding.ActivityMediumVarThreeTopBinding
import com.app.nativepractice.isOnline
import com.facebook.shimmer.ShimmerFrameLayout
import com.google.android.ads.nativetemplates.R
import com.google.android.ads.nativetemplates.TemplateView
import com.google.android.gms.ads.AdListener
import com.google.android.gms.ads.AdLoader
import com.google.android.gms.ads.AdRequest
import com.google.android.gms.ads.LoadAdError
import com.google.android.gms.ads.nativead.NativeAd
import com.google.android.gms.ads.nativead.NativeAdOptions

class MediumVarThreeTop : AppCompatActivity() {
    var medium_variant_three_top: NativeAd? = null
    lateinit var binding : ActivityMediumVarThreeTopBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMediumVarThreeTopBinding.inflate(layoutInflater)
        setContentView(binding.root)
        mediumVariantThreeTop()
    }
    private fun mediumVariantThreeTop() {
        binding.apply {
            mediumVariantThreeTopCl.visibility = View.VISIBLE
            mediumVariantThreeTop.rootView.apply {
                findViewById<CardView>(R.id.cardAds).visibility = View.VISIBLE
//                findViewById<RelativeLayout>(R.id.lrLoading).visibility = View.VISIBLE
                findViewById<ShimmerFrameLayout>(R.id.shimmer_view_container).startShimmer()
            }
            if (isOnline()) {
                val adLoader =
                    AdLoader.Builder(
                        this@MediumVarThreeTop,
                        getString(com.app.nativepractice.R.string.ad_native)
                    ).forNativeAd { NativeAd: NativeAd ->
                        medium_variant_three_top = NativeAd
                        mediumVariantThreeTop.rootView.apply {
                            findViewById<TemplateView>(R.id.googleTemplate).setNativeAd(NativeAd)
                            findViewById<CardView>(R.id.cardAds).visibility = View.VISIBLE
                            findViewById<RelativeLayout>(R.id.rlAds).visibility = View.VISIBLE
                            findViewById<RelativeLayout>(R.id.lrLoading).visibility = View.GONE
                            findViewById<ShimmerFrameLayout>(R.id.shimmer_view_container).stopShimmer()
                            findViewById<ShimmerFrameLayout>(R.id.shimmer_view_container).hideShimmer()
                            findViewById<TemplateView>(R.id.shimmerEffect).visibility = View.GONE
                        }
                    }.withAdListener(object : AdListener() {
                        override fun onAdFailedToLoad(loadAdError: LoadAdError) {
                            super.onAdFailedToLoad(loadAdError)
                            medium_variant_three_top = null
                            mediumVariantThreeTop.rootView.apply {
                                findViewById<RelativeLayout>(R.id.rlAds).visibility = View.GONE
                                findViewById<RelativeLayout>(R.id.lrLoading).visibility = View.GONE
                                findViewById<CardView>(R.id.cardAds).visibility = View.GONE
                                findViewById<ShimmerFrameLayout>(R.id.shimmer_view_container).stopShimmer()
                                findViewById<ShimmerFrameLayout>(R.id.shimmer_view_container).hideShimmer()
                                findViewById<TemplateView>(R.id.shimmerEffect).visibility = View.GONE
                            }
                        }

                        override fun onAdClicked() {
                            super.onAdClicked()
                        }

                        override fun onAdImpression() {
                            super.onAdImpression()
                            medium_variant_three_top = null
                        }
                    }).withNativeAdOptions(
                        NativeAdOptions.Builder()
                            .setRequestCustomMuteThisAd(true)
                            .setAdChoicesPlacement(NativeAdOptions.ADCHOICES_TOP_RIGHT)
                            .build()
                    )
                        .build()
                adLoader.loadAd(AdRequest.Builder().build())


            } else {
                mediumVariantThreeTopCl.visibility = View.INVISIBLE
                mediumVariantThreeTop.rootView.findViewById<CardView>(R.id.cardAds).visibility =
                    View.INVISIBLE
            }
        }
    }
}