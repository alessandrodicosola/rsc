package it.poliba.adicosola1.rsclient.common.net

import android.util.Log
import okhttp3.Interceptor
import okhttp3.Response

const val TAG: String = "LogInterceptor"

class LogInterceptor : Interceptor {

    override fun intercept(chain: Interceptor.Chain): Response {
        Log.d(TAG, chain.request().url().toString())
        return chain.proceed(chain.request())

    }

}