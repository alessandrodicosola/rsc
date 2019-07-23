package it.poliba.adicosola1.rsclient

import android.app.Application
import android.content.Context
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.rule.ActivityTestRule
import androidx.test.runner.AndroidJUnitRunner
import it.poliba.adicosola1.rsclient.ui.presenter.PresenterActivity
import it.poliba.adicosola1.rsclient.ui.presenter.PresenterViewModel
import org.junit.After
import org.junit.Assert
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.koin.androidx.scope.currentScope
import org.koin.androidx.viewmodel.ext.koin.getViewModel
import org.koin.core.context.stopKoin
import org.koin.test.KoinTest
import org.mockito.ArgumentMatchers.anyString
import org.mockito.junit.MockitoJUnitRunner

@RunWith(AndroidJUnit4::class)
class UI_Presenter_Activity_Test :  KoinTest {


    @get:Rule
    val rule = ActivityTestRule(PresenterActivity::class.java)


    @Test
    fun test_presenter() {

        val activity = rule.activity
        val scope = activity.currentScope
        Assert.assertNotNull(scope)

        val viewmodel = scope.getViewModel<PresenterViewModel>(activity)
        Assert.assertNotNull(viewmodel)

        viewmodel.retrieve(anyString())

        Assert.assertEquals(8, activity.layout.ownedGamesRecyclerView.adapter!!.itemCount)

        Thread.sleep(1000)


    }

}


