package com.app.nativepractice.small

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.RelativeLayout
import androidx.cardview.widget.CardView
import com.app.nativepractice.databinding.ActivitySmallVarThreeBottomBinding
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

class SmallVarThreeBottom : AppCompatActivity() {
    lateinit var binding : ActivitySmallVarThreeBottomBinding
    var small_variant_three_bottom: NativeAd? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySmallVarThreeBottomBinding.inflate(layoutInflater)
        setContentView(binding.root)
        smallVariantThreeBottom()
    }
    private fun smallVariantThreeBottom() {
        binding.apply {
            smallVariantThreeBottomCl.visibility = View.VISIBLE
            smallVariantThreeBottom.rootView.apply {
                findViewById<CardView>(R.id.cardAds).visibility = View.VISIBLE
//                findViewById<RelativeLayout>(R.id.lrLoading).visibility = View.VISIBLE
                findViewById<ShimmerFrameLayout>(R.id.shimmer_view_container).startShimmer()
            }
            if (isOnline()) {
                val adLoader =
                    AdLoader.Builder(
                        this@SmallVarThreeBottom,
                        getString(com.app.nativepractice.R.string.ad_native)
                    ).forNativeAd { NativeAd: NativeAd ->
                        small_variant_three_bottom = NativeAd
                        smallVariantThreeBottom.rootView.apply {
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
                            small_variant_three_bottom = null
                            smallVariantThreeBottom.rootView.apply {
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
                            small_variant_three_bottom = null
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
                smallVariantThreeBottomCl.visibility = View.INVISIBLE
                smallVariantThreeBottom.rootView.findViewById<CardView>(R.id.cardAds).visibility =
                    View.INVISIBLE
            }
        }
    }
}