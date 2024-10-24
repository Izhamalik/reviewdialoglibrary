package com.app.nativepractice.small

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.RelativeLayout
import androidx.cardview.widget.CardView
import com.app.nativepractice.databinding.ActivitySmallVarOneBottomBinding
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

class SmallVarOneBottom : AppCompatActivity() {
    lateinit var binding : ActivitySmallVarOneBottomBinding
    var small_variant_one_bottom: NativeAd? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySmallVarOneBottomBinding.inflate(layoutInflater)
        setContentView(binding.root)

        smallVariantOneBottom()
    }
    private fun smallVariantOneBottom() {
        binding.apply {
            smallVariantOneBottomCl.visibility = View.VISIBLE
            smallVariantOneBottom.rootView.apply {
                findViewById<CardView>(R.id.cardAds).visibility = View.VISIBLE
//                findViewById<RelativeLayout>(R.id.lrLoading).visibility = View.VISIBLE
                findViewById<ShimmerFrameLayout>(R.id.shimmer_view_container).startShimmer()
            }
            if (isOnline()) {
                val adLoader =
                    AdLoader.Builder(
                        this@SmallVarOneBottom,
                        getString(com.app.nativepractice.R.string.ad_native)
                    ).forNativeAd { NativeAd: NativeAd ->
                        small_variant_one_bottom = NativeAd
                        smallVariantOneBottom.rootView.apply {
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
                            small_variant_one_bottom = null
                            smallVariantOneBottom.rootView.apply {
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
                            small_variant_one_bottom = null
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
                smallVariantOneBottomCl.visibility = View.INVISIBLE
                smallVariantOneBottom.rootView.findViewById<CardView>(R.id.cardAds).visibility =
                    View.INVISIBLE
            }
        }
    }

}