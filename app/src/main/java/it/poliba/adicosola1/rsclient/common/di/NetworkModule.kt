package it.poliba.adicosola1.rsclient.common.di

import it.poliba.adicosola1.rsclient.common.net.LocalhostService
import it.poliba.adicosola1.rsclient.common.net.LogInterceptor
import it.poliba.adicosola1.rsclient.common.util.ConnectivityHelper
import it.poliba.adicosola1.rsclient.common.util.IConnectivity
import okhttp3.OkHttpClient
import org.koin.dsl.module
import retrofit2.CallAdapter
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory

private fun createCallAdapterFactory(): CallAdapter.Factory {
    return RxJava2CallAdapterFactory.create()
}

private fun createOkHttpClient(): OkHttpClient {
    return OkHttpClient.Builder()
        .addInterceptor(LogInterceptor())
        .build()
}



val networkModule = module {
    single { ConnectivityHelper(get()) as IConnectivity }
    single { createCallAdapterFactory() }
    single { createOkHttpClient() }
}