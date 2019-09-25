package it.poliba.adicosola1.rsclient.ui.presenter

import android.annotation.SuppressLint
import android.util.Log
import android.util.SparseArray
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import io.reactivex.Completable
import io.reactivex.Observable
import io.reactivex.ObservableSource
import io.reactivex.Scheduler
import io.reactivex.schedulers.Schedulers
import it.poliba.adicosola1.rsclient.common.rsengine.IRSEngine
import it.poliba.adicosola1.rsclient.common.rsengine.RSObject
import it.poliba.adicosola1.rsclient.common.rsengine.TranslationStrategy
import it.poliba.adicosola1.rsclient.common.ui.Error
import it.poliba.adicosola1.rsclient.common.ui.EventHandler
import it.poliba.adicosola1.rsclient.common.ui.toWrapper
import it.poliba.adicosola1.rsclient.common.util.Response

import org.koin.core.KoinComponent

import java.util.concurrent.TimeUnit


class PresenterViewModel(
    private val engine: IRSEngine<Long, Int, Double>,
    private val translationStrategy: TranslationStrategy<Int, Double>
) :
    ViewModel(), KoinComponent {


    val items = MutableLiveData<List<RSObject<Int, Double>>>()
    val status = MutableLiveData<EventHandler>()

    @SuppressLint("CheckResult")
    fun retrieve(userid: String) {
        val lockObject = Object()
        var internalList = listOf<RSObject<Int, Double>>()
        val tempList = mutableListOf<RSObject<Int, Double>>()

        engine.getRecommendations(userid.toLong(), Int.MAX_VALUE).subscribeOn(Schedulers.io())
            .doOnComplete {
                internalList.forEach {
                    translationStrategy.translate(it).delay(500,TimeUnit.MILLISECONDS).doOnComplete { items.postValue(tempList) }.subscribe { synchronized(lockObject){ tempList.add(it); } }
                }

            }.subscribe {
                if (it.error) status.postValue(Error(it.message).toWrapper())
                else internalList =
                    it.body.asSequence().filter { it.score > 0 }.take(20).toList()
            }
    }

}






