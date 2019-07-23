package it.poliba.adicosola1.rsclient.common.di


import it.poliba.adicosola1.rsclient.common.rsengine.IRSEngine
import it.poliba.adicosola1.rsclient.common.rsengine.TranslationStrategy

import it.poliba.adicosola1.rsclient.common.steam.GameTranslator
import it.poliba.adicosola1.rsclient.common.steam.SteamRSEngine

import it.poliba.adicosola1.rsclient.ui.presenter.PresenterActivity
import it.poliba.adicosola1.rsclient.ui.presenter.PresenterViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.core.qualifier.named
import org.koin.dsl.module

val presenterModule = module {
    scope(named<PresenterActivity>()) {
        scoped { SteamRSEngine() as IRSEngine }
        scoped { GameTranslator(get()) as TranslationStrategy }
        viewModel { PresenterViewModel(get(), get()) }
    }


}