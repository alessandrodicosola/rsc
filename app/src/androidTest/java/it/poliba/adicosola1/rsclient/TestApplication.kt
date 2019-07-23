package it.poliba.adicosola1.rsclient

import android.app.Application
import it.poliba.adicosola1.rsclient.common.di.*
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
import org.koin.core.context.stopKoin
import org.koin.core.logger.Level

class TestKoinApplicationWithoutModules : Application() {
    override fun onCreate() {

        super.onCreate()

        startKoin {
            androidContext(this@TestKoinApplicationWithoutModules)
            androidLogger(Level.DEBUG)

            modules(
                networkModule, steamModule, loginModule, presenterModule,

                testGlobalModule, testPresenterModule
            )
        }

    }


    override fun onTerminate() {
        super.onTerminate()
        stopKoin()
    }

}