package it.poliba.adicosola1.rsclient.common.di

import com.google.gson.GsonBuilder
import it.poliba.adicosola1.rsclient.common.net.Root
import it.poliba.adicosola1.rsclient.common.net.RootAdapter
import it.poliba.adicosola1.rsclient.common.net.SteamService
import it.poliba.adicosola1.rsclient.ui.presenter.PresenterActivity
import okhttp3.OkHttpClient
import org.koin.core.qualifier.named
import org.koin.dsl.module
import retrofit2.CallAdapter
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


private fun createGsonConverter(): GsonConverterFactory {
    return GsonConverterFactory.create(
        GsonBuilder().registerTypeAdapter(Root::class.java, RootAdapter()).create()
    )
}


private fun createSteamService(
    converter: GsonConverterFactory,
    callAdapterFactory: CallAdapter.Factory,
    httpClient: OkHttpClient
): SteamService {
    return Retrofit.Builder().baseUrl("https://store.steampowered.com/api/")
        .addConverterFactory(converter)
        .addCallAdapterFactory(callAdapterFactory)
        .client(httpClient)
        .build().create(SteamService::class.java)
}

val steamModule = module {
    scope(named<PresenterActivity>()) {
        scoped { createGsonConverter() }
        scoped { createSteamService(get(), get(), get()) }
    }
}