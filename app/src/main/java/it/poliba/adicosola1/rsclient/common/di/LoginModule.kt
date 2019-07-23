package it.poliba.adicosola1.rsclient.common.di

import it.poliba.adicosola1.rsclient.common.authentication.DoesntMatterWhoYouAre
import it.poliba.adicosola1.rsclient.common.authentication.IAuth
import it.poliba.adicosola1.rsclient.ui.login.LoginActivity
import it.poliba.adicosola1.rsclient.ui.login.LoginViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.core.qualifier.named
import org.koin.dsl.module

val loginModule = module {
    scope(named<LoginActivity>()) {
        scoped { DoesntMatterWhoYouAre() as IAuth<Long> }
        viewModel { LoginViewModel(get()) }
    }
}