package it.poliba.adicosola1.rsclient.common.util

import android.content.Context
import android.net.ConnectivityManager

class ConnectivityHelper(private val context: Context) : IConnectivity {
    override fun isOnline(): Boolean {
        val connectivityManager = context.getSystemService(ConnectivityManager::class.java)
        return connectivityManager != null && connectivityManager.activeNetwork != null
    }

}