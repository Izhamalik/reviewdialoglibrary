package com.app.nativepractice

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.android.reviewdialog.ReviewDialog
import com.app.nativepractice.databinding.ActivityMainBinding
import com.app.nativepractice.large.LargeVarOneBottom
import com.app.nativepractice.large.LargeVarOneTop
import com.app.nativepractice.large.LargeVarThreeBottom
import com.app.nativepractice.large.LargeVarThreeTop
import com.app.nativepractice.large.LargeVarTwoBottom
import com.app.nativepractice.large.LargeVarTwoTop
import com.app.nativepractice.medium.MediumVarOneBottom
import com.app.nativepractice.medium.MediumVarOneTop
import com.app.nativepractice.medium.MediumVarThreeBottom
import com.app.nativepractice.medium.MediumVarThreeTop
import com.app.nativepractice.medium.MediumVarTwoBottom
import com.app.nativepractice.medium.MediumVarTwoTop
import com.app.nativepractice.small.SmallVarOneBottom
import com.app.nativepractice.small.SmallVarOneTop
import com.app.nativepractice.small.SmallVarThreeBottom
import com.app.nativepractice.small.SmallVarThreeTop
import com.app.nativepractice.small.SmallVarTwoBottom
import com.app.nativepractice.small.SmallVarTwoTop
import java.util.ArrayList

class MainActivity : AppCompatActivity() {
    lateinit var bind: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        bind = ActivityMainBinding.inflate(layoutInflater)
        setContentView(bind.root)

        ReviewDialog.showRateusDialog(this , ArrayList(arrayListOf (getDrawable(R.drawable.ic_profile1) , getDrawable(R.drawable.ic_profile1),getDrawable(R.drawable.ic_profile1))) , ArrayList(arrayListOf ("Excellent app to troubleshoot wireless networkingissues. asfsdffsfsdsag" , "Excellent app to troubleshoot wireless networkingissues. asfsdffsfsdsag" , "Excellent app to troubleshoot wireless networkingissues. asfsdffsfsdsag")) , ArrayList(arrayListOf ("Not so good" , "Not so good" , "Not so good")))

        bind.apply {

            bannerVarOne.setOnClickListener {
                startActivity(Intent(this@MainActivity, BannerVarOne::class.java))
            }
            bannerVarTwo.setOnClickListener {
                startActivity(Intent(this@MainActivity, BannerVarTwo::class.java))
            }
            smallVarOneTop.setOnClickListener {
                startActivity(Intent(this@MainActivity, SmallVarOneTop::class.java))
            }
            smallVarOneBottom.setOnClickListener {
                startActivity(Intent(this@MainActivity, SmallVarOneBottom::class.java))
            }
            smallVarTwoTop.setOnClickListener {
                startActivity(Intent(this@MainActivity, SmallVarTwoTop::class.java))
            }
            smallVarTwoBottom.setOnClickListener {
                startActivity(Intent(this@MainActivity, SmallVarTwoBottom::class.java))
            }
            smallVarThreeTop.setOnClickListener {
                startActivity(Intent(this@MainActivity, SmallVarThreeTop::class.java))
            }
            smallVarThreeBottom.setOnClickListener {
                startActivity(Intent(this@MainActivity, SmallVarThreeBottom::class.java))
            }

            mediumVarOneTop.setOnClickListener {
                startActivity(Intent(this@MainActivity, MediumVarOneTop::class.java))
            }
            mediumVarOneBottom.setOnClickListener {
                startActivity(Intent(this@MainActivity, MediumVarOneBottom::class.java))
            }

            mediumVarTwoTop.setOnClickListener {
                startActivity(Intent(this@MainActivity, MediumVarTwoTop::class.java))
            }
            mediumVarTwoBottom.setOnClickListener {
                startActivity(Intent(this@MainActivity, MediumVarTwoBottom::class.java))
            }

            mediumVarThreeTop.setOnClickListener {
                startActivity(Intent(this@MainActivity, MediumVarThreeTop::class.java))
            }
            mediumVarThreeBottom.setOnClickListener {
                startActivity(Intent(this@MainActivity, MediumVarThreeBottom::class.java))
            }


            largeVarOneTop.setOnClickListener {
                startActivity(Intent(this@MainActivity, LargeVarOneTop::class.java))
            }
            largeVarOneBottom.setOnClickListener {
                startActivity(Intent(this@MainActivity, LargeVarOneBottom::class.java))
            }

            largeVarTwoTop.setOnClickListener {
                startActivity(Intent(this@MainActivity, LargeVarTwoTop::class.java))
            }
            largeVarTwoBottom.setOnClickListener {
                startActivity(Intent(this@MainActivity, LargeVarTwoBottom::class.java))
            }

            largeVarThreeTop.setOnClickListener {
                startActivity(Intent(this@MainActivity, LargeVarThreeTop::class.java))
            }
            largeVarThreeBottom.setOnClickListener {
                startActivity(Intent(this@MainActivity, LargeVarThreeBottom::class.java))
            }
        }

    }



}