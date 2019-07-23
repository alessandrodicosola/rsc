package it.poliba.adicosola1.rsclient

import android.app.Application
import it.poliba.adicosola1.rsclient.common.di.loginModule
import it.poliba.adicosola1.rsclient.common.di.networkModule
import it.poliba.adicosola1.rsclient.common.di.presenterModule
import it.poliba.adicosola1.rsclient.common.di.steamModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin

open class Application : Application() {

    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidContext(this@Application)
            androidLogger()

            modules(networkModule, steamModule, loginModule, presenterModule)
        }
    }
}







