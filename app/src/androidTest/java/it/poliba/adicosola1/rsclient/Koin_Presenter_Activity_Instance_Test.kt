package it.poliba.adicosola1.rsclient

import androidx.test.rule.ActivityTestRule
import it.poliba.adicosola1.rsclient.common.rsengine.IRSEngine
import it.poliba.adicosola1.rsclient.common.rsengine.TranslationStrategy
import it.poliba.adicosola1.rsclient.ui.presenter.PresenterActivity
import it.poliba.adicosola1.rsclient.ui.presenter.PresenterViewModel
import org.junit.Assert
import org.junit.Rule
import org.junit.Test
import org.koin.androidx.scope.currentScope
import org.koin.androidx.viewmodel.ext.koin.getViewModel

class Koin_Presenter_Activity_Instance_Test {

    @get:Rule
    val rule =
        ActivityTestRule(PresenterActivity::class.java)


    @Test
    fun test_koin_instance() {


        val activity = rule.activity
        val scope = activity.currentScope

        Assert.assertNotNull(activity)
        Assert.assertNotNull(scope)

        /* TEST VIEW MODEL */
        val viewmodel1 = scope.getViewModel<PresenterViewModel>(activity)
        Assert.assertNotNull(viewmodel1)
        val viewmodel2 = scope.getViewModel<PresenterViewModel>(activity)
        Assert.assertNotNull(viewmodel2)

        Assert.assertSame(viewmodel1, viewmodel2)

        /* TEST IRSENGINE */
        val eng1 = scope.get<IRSEngine>()
        Assert.assertNotNull(eng1)
        val eng2 = scope.get<IRSEngine>()
        Assert.assertNotNull(eng2)

        Assert.assertSame(eng1, eng2)

        /* TEST TRANSLATIONSTRATEGY */
        val ts1 = scope.get<TranslationStrategy>()
        Assert.assertNotNull(ts1)
        val ts2 = scope.get<TranslationStrategy>()
        Assert.assertNotNull(ts2)

        Assert.assertSame(ts1, ts2)
    }


}