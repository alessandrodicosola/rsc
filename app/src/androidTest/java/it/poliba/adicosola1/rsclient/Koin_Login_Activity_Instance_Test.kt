package it.poliba.adicosola1.rsclient

import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.rule.ActivityTestRule
import it.poliba.adicosola1.rsclient.common.authentication.IAuth
import it.poliba.adicosola1.rsclient.ui.login.LoginActivity
import it.poliba.adicosola1.rsclient.ui.login.LoginViewModel
import org.junit.Assert
import org.junit.Assert.assertSame
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.koin.androidx.scope.currentScope
import org.koin.androidx.viewmodel.ext.koin.getViewModel

@RunWith(AndroidJUnit4::class)
class Koin_Login_Activity_Instance_Test {

    @get:Rule
    val rule = ActivityTestRule(LoginActivity::class.java)

    @Test
    fun test_koin_instance() {

        val activity = rule.activity
        val scope = activity.currentScope


        val viewmodel1 = scope.getViewModel<LoginViewModel>(activity)
        Assert.assertNotNull(viewmodel1)
        val viewmodel2 = scope.getViewModel<LoginViewModel>(activity)
        Assert.assertNotNull(viewmodel2)

        val auth1 = scope.get<IAuth<Long>>()
        val auth2 = scope.get<IAuth<Long>>()
        assertSame(auth1, auth2)
    }
}

