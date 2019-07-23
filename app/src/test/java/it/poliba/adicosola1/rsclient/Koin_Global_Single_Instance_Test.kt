package it.poliba.adicosola1.rsclient

import it.poliba.adicosola1.rsclient.common.authentication.IAuth
import it.poliba.adicosola1.rsclient.common.di.loginModule
import it.poliba.adicosola1.rsclient.common.di.networkModule
import it.poliba.adicosola1.rsclient.ui.login.LoginActivity
import it.poliba.adicosola1.rsclient.ui.login.LoginViewModel
import junit.framework.Assert.*
import okhttp3.OkHttpClient
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.koin.androidx.viewmodel.getViewModel
import org.koin.core.context.startKoin
import org.koin.core.logger.Level
import org.koin.core.qualifier.named
import org.koin.core.scope.ScopeCallback
import org.koin.test.AutoCloseKoinTest
import org.koin.test.get
import org.koin.test.mock.declare
import org.mockito.Mock
import org.mockito.junit.MockitoJUnitRunner
import retrofit2.CallAdapter

@RunWith(MockitoJUnitRunner::class)
class Koin_Global_Single_Instance_Test : AutoCloseKoinTest() {

    @Before
    fun init() {
        startKoin {
            printLogger(Level.DEBUG)
            declare {
                modules(listOf(networkModule, loginModule))
            }
        }

        // Check global objects are not null
        assertNotNull(get<CallAdapter.Factory>())
        assertNotNull(get<OkHttpClient>())

    }

    /**
     * This test assert that objects in network module use always the same reference
     */
    @Test
    fun test_single_networkmodule() {
        assertTrue(get<CallAdapter.Factory>() == get<CallAdapter.Factory>())
        assertTrue(get<OkHttpClient>() == get<OkHttpClient>())

    }

}