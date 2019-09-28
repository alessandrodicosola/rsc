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
		//Oggetto per eseguire il lock quando si accede a una risorsa da diversi thread
        val lockObject = Object()
		//Lista temporanea in cui salvare le raccomandazioni ricevute
		var internalList = listOf<RSObject<Int, Double>>()
		//Lista interna in cui inserire le raccomandazioni "tradotte"
        val tempList = mutableListOf<RSObject<Int, Double>>()
		//Prelevo le raccomandazioni: itemId in questo caso non serve
		//Questo viene fatto in un nuovo thread rispetto a quello principale
        engine.getRecommendations(userid.toLong(), -1).subscribeOn(Schedulers.io())
            .doOnComplete {
				
                internalList.forEach {
					//"Traduco" le raccomandazioni con 500ms di ritardo per evitare di sovraccaricare il servizio da cui si ottengolo le informazioni generali sulle raccomandazioni
					//Inizio la traduzione e inserisco l'oggetto nella lista temporanea
					//Quando la traduzione è completata invio la lista all'interfaccia attraverso il pattern Observable/Observer definito da RxJava
					translationStrategy.translate(it).delay(500,TimeUnit.MILLISECONDS).doOnComplete { items.postValue(tempList) }.subscribe {  synchronized(lockObject) { tempList.add(it);}  }
                }
			//Avvio la ricezione delle raccomandazioni verificando che non ci siano errori
            }.subscribe {
                if (it.error) status.postValue(Error(it.message).toWrapper())
                else internalList =
                    it.body.asSequence().filter { it.score > 0 }.take(20).toList()
            }
    }

}






