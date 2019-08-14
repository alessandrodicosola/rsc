package it.poliba.adicosola1.rsclient.common.di

import com.google.gson.GsonBuilder
import it.poliba.adicosola1.rsclient.common.net.*
import it.poliba.adicosola1.rsclient.common.rsengine.RSObject
import it.poliba.adicosola1.rsclient.common.util.Response
import it.poliba.adicosola1.rsclient.common.util.ResponseListRSObjectToken
import it.poliba.adicosola1.rsclient.ui.presenter.PresenterActivity
import okhttp3.OkHttpClient
import org.koin.core.qualifier.named
import org.koin.dsl.module
import retrofit2.CallAdapter

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.*


private fun createRootConverter(): GsonConverterFactory {
    return GsonConverterFactory.create(
        GsonBuilder().registerTypeAdapter(
            Root::class.java,
            RootAdapter()
        ).create()
    )
}

private fun createLocalhostConverter(): GsonConverterFactory {
    return GsonConverterFactory.create(
        GsonBuilder().registerTypeAdapter(
            ResponseListRSObjectToken().rawType,
            LocalhostAdapter()
        ).setLenient().create()
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

private fun createLocalhostService(
    converter: GsonConverterFactory,
    callAdapterFactory: CallAdapter.Factory,
    httpClient: OkHttpClient
): LocalhostService {
    return Retrofit.Builder().baseUrl("http://192.168.1.170/recommendations/")
        .addConverterFactory(converter)
        .addCallAdapterFactory(callAdapterFactory)
        .client(httpClient)
        .build().create(LocalhostService::class.java)
}


val steamModule = module {
    scope(named<PresenterActivity>()) {
        scoped(named("rootConverter")) { createRootConverter() }
        scoped(named("localhostConverter")) { createLocalhostConverter() }
        scoped { createSteamService(get(named("rootConverter")), get(), get()) }
        scoped { createLocalhostService(get(named("localhostConverter")), get(), get()) }
    }
}