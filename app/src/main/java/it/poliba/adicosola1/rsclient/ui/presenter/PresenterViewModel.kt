package it.poliba.adicosola1.rsclient.ui.presenter

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


class PresenterViewModel(private val engine: IRSEngine, private val translationStrategy: TranslationStrategy) :
    ViewModel(), KoinComponent {


    val items = MutableLiveData<List<RSObject>>()
    val status = MutableLiveData<EventHandler>()

    fun retrieve(userid: String) {

        var internalList = listOf<RSObject>()
        val tempList = mutableListOf<RSObject>()
        engine.getRecommendations(userid).subscribeOn(Schedulers.io())
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
                else internalList = it.body
            }
    }

}






