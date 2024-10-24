package com.app.nativepractice

import android.content.Context
import android.net.ConnectivityManager
import androidx.appcompat.app.AppCompatActivity


fun Context.isOnline(): Boolean {
    try {
        val connectivityManager =
            (getSystemService(AppCompatActivity.CONNECTIVITY_SERVICE) as ConnectivityManager)
        val networkInfo = connectivityManager.activeNetworkInfo
        return networkInfo != null && networkInfo.isAvailable && networkInfo.isConnected
    } catch (e: java.lang.NullPointerException) {
        e.printStackTrace()
    }
    return false
}