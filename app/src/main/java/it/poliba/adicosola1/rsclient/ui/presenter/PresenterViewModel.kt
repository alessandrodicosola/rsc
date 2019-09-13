package it.poliba.adicosola1.rsclient.ui.presenter

import android.annotation.SuppressLint
import android.util.Log
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


class PresenterViewModel(private val engine: IRSEngine<Long,Int,Double>, private val translationStrategy: TranslationStrategy<Int,Double>) :
    ViewModel(), KoinComponent {


    val items = MutableLiveData<List<RSObject<Int,Double>>>()
    val status = MutableLiveData<EventHandler>()

    @SuppressLint("CheckResult")
    fun retrieve(userid: String) {

        var internalList = listOf<RSObject<Int,Double>>()
        val tempList = mutableListOf<RSObject<Int,Double>>()
        engine.getRecommendations(userid.toLong()).subscribeOn(Schedulers.io())
            .doOnComplete {
                Observable.fromIterable(internalList.map { translationStrategy.translate(it) })
                    .buffer(10, 10)
                    .concatMap {
                        Observable.fromIterable(it).delay(1, TimeUnit.SECONDS).doOnComplete {
                            Log.d(this.javaClass.simpleName, "Called doOnComplete ")
                            items.postValue(tempList)
                        }
                    }
                    .subscribe {
                        it.subscribe {
                            Log.d(this.javaClass.simpleName, "Adding $it")
                            tempList.add(it)
                        }
                    }
            }.subscribe {
                if (it.error) status.postValue(Error(it.message).toWrapper())
                else internalList = it.body.asSequence().filter { it.score > 0 }.sortedByDescending { it.score }.take(50).toList()
            }
    }

}






