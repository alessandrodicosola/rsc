package it.poliba.adicosola1.rsclient.common.di

import it.poliba.adicosola1.rsclient.common.authentication.LocalHostAuth
import it.poliba.adicosola1.rsclient.common.authentication.IAuth
import it.poliba.adicosola1.rsclient.ui.login.LoginActivity
import it.poliba.adicosola1.rsclient.ui.login.LoginViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.core.qualifier.named
import org.koin.dsl.module

// Modulo contenente le classi degli oggetti riguardanti il login
val loginModule = module {
    scope(named<LoginActivity>()) {
        scoped { LocalHostAuth(get()) as IAuth<Long> }
        viewModel { LoginViewModel(get()) }
    }
}