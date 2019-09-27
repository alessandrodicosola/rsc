package it.poliba.adicosola1.rsclient.common.di


import it.poliba.adicosola1.rsclient.common.rsengine.IRSEngine
import it.poliba.adicosola1.rsclient.common.rsengine.TranslationStrategy

import it.poliba.adicosola1.rsclient.common.steam.GameTranslator
import it.poliba.adicosola1.rsclient.common.steam.LocalHostEngine

import it.poliba.adicosola1.rsclient.ui.presenter.PresenterActivity
import it.poliba.adicosola1.rsclient.ui.presenter.PresenterViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.core.qualifier.named
import org.koin.dsl.module

// Modulo contenente le classi degli oggetti riguardanti il l'interfaccia su cui si visualizzano i giochi
val presenterModule = module {
    scope(named<PresenterActivity>()) {
        scoped { LocalHostEngine(get(),get()) as IRSEngine<Long,Int,Double> }
        scoped { GameTranslator(get()) as TranslationStrategy<Int,Double> }
        viewModel { PresenterViewModel(get(), get()) }
    }


}