package com.app.nativepractice.large

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.LinearLayout
import android.widget.RelativeLayout
import androidx.cardview.widget.CardView
import com.app.nativepractice.databinding.ActivityLargeVarTwoBottomBinding
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

class LargeVarTwoBottom : AppCompatActivity() {
    lateinit var binding : ActivityLargeVarTwoBottomBinding
    var large_variant_two_bottom: NativeAd? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLargeVarTwoBottomBinding.inflate(layoutInflater)
        setContentView(binding.root)
        largeVariantTwoBottom()
    }
    private fun largeVariantTwoBottom() {
        binding.apply {
            largeVariantTwoBottomCl.visibility = View.VISIBLE
            largeVariantTwoBottom.rootView.apply {
                findViewById<LinearLayout>(R.id.adsContainer).visibility= View.VISIBLE
//                findViewById<RelativeLayout>(R.id.lrLoading).visibility = View.VISIBLE
                findViewById<ShimmerFrameLayout>(R.id.shimmer_view_container).startShimmer()
            }
            if (isOnline()) {
                val adLoader =
                    AdLoader.Builder(
                        this@LargeVarTwoBottom,
                        getString(com.app.nativepractice.R.string.ad_native)
                    ).forNativeAd { NativeAd: NativeAd ->
                        large_variant_two_bottom = NativeAd
                        largeVariantTwoBottom.rootView.apply {
                            findViewById<TemplateView>(R.id.googleTemplate).setNativeAd(NativeAd)
                            findViewById<LinearLayout>(R.id.adsContainer).visibility = View.VISIBLE
                            findViewById<RelativeLayout>(R.id.rlAds).visibility = View.VISIBLE
                            findViewById<RelativeLayout>(R.id.lrLoading).visibility = View.GONE
                            findViewById<ShimmerFrameLayout>(R.id.shimmer_view_container).stopShimmer()
                            findViewById<ShimmerFrameLayout>(R.id.shimmer_view_container).hideShimmer()
                            findViewById<TemplateView>(R.id.shimmerEffect).visibility = View.GONE
                        }
                    }.withAdListener(object : AdListener() {
                        override fun onAdFailedToLoad(loadAdError: LoadAdError) {
                            super.onAdFailedToLoad(loadAdError)
                            large_variant_two_bottom = null
                            largeVariantTwoBottom.rootView.apply {
                                findViewById<RelativeLayout>(R.id.rlAds).visibility = View.GONE
                                findViewById<RelativeLayout>(R.id.lrLoading).visibility = View.GONE
                                findViewById<LinearLayout>(R.id.adsContainer).visibility = View.GONE
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
                            large_variant_two_bottom = null
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
                largeVariantTwoBottomCl.visibility = View.INVISIBLE
                largeVariantTwoBottom.rootView.findViewById<CardView>(R.id.cardAds).visibility =
                    View.INVISIBLE
            }
        }
    }
}