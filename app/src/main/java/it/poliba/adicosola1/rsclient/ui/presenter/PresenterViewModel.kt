package it.poliba.adicosola1.rsclient.ui.presenter

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import it.poliba.adicosola1.rsclient.common.rsengine.IRSEngine
import it.poliba.adicosola1.rsclient.common.rsengine.RSObject
import it.poliba.adicosola1.rsclient.common.rsengine.TranslationStrategy
import it.poliba.adicosola1.rsclient.common.ui.Error
import it.poliba.adicosola1.rsclient.common.ui.EventHandler
import it.poliba.adicosola1.rsclient.common.ui.toWrapper
import it.poliba.adicosola1.rsclient.common.util.IConnectivity
import org.koin.core.KoinComponent
import org.koin.core.inject

class PresenterViewModel(private val engine: IRSEngine, private val translationStrategy: TranslationStrategy) :
    ViewModel(), KoinComponent {

    //TODO:Move this
    private val connectivityHelper: IConnectivity by inject()

    private val internalList = mutableListOf<RSObject>()

    val items = MutableLiveData<List<RSObject>>()
    val status = MutableLiveData<EventHandler>()

    fun retrieve(userid: String) {

        internalList.clear()


        //TODO: Move this into specific class
        /* Check network */
        if (!connectivityHelper.isOnline()) {
            status.postValue(Error("No network available").toWrapper())
            return
        }

        //TODO Find a better way
        /* What happens:
            1.Get recommendations
            2.Transform the flow of List<RSObject> into RSObject
            3.Transform single RSObject with TranslationStrategy
            4.Every time map function return transformed RSObject:
                4.1 Add it into [internalList]
                4.2 Send new modified [internalList] to [items] (observed by View)
                NOTE: Since View is using RecyclerViewAdapter with [androidx.recyclerview.widget.AsyncListDiffer],
                      the UI will be updated with only new items
        */
        engine.getRecommendations(userid)
            .flatMapIterable { it }
            .doAfterNext { Log.d(javaClass.simpleName, "Processed ${it.id}") }
            .map { translationStrategy.translate(it) }
            .doOnNext {
                it
                    .doOnComplete { items.postValue(internalList) }
                    .subscribe { internalList.add(it) }
            }
            .subscribe()


    }


}



