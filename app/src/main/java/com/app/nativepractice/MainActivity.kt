package com.app.nativepractice

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.android.reviewdialog.ReviewDialog
import com.app.nativepractice.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    lateinit var bind: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        bind = ActivityMainBinding.inflate(layoutInflater)
        setContentView(bind.root)

        ReviewDialog.showRateusDialog(this , ArrayList(arrayListOf (getDrawable(R.drawable.ic_profile1) , getDrawable(R.drawable.ic_profile1),getDrawable(R.drawable.ic_profile1))) , ArrayList(arrayListOf ("Excellent app to troubleshoot wireless networkingissues. asfsdffsfsdsag" , "Excellent app to troubleshoot wireless networkingissues. asfsdffsfsdsag" , "Excellent app to troubleshoot wireless networkingissues. asfsdffsfsdsag")) , ArrayList(arrayListOf ("Not so good" , "Not so good" , "Not so good")))


    }



}