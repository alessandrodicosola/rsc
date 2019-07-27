package it.poliba.adicosola1.rsclient.common.steam

import android.util.Log
import androidx.collection.LruCache
import io.reactivex.Observable
import io.reactivex.schedulers.Schedulers
import it.poliba.adicosola1.rsclient.common.net.SteamService
import it.poliba.adicosola1.rsclient.common.net.emptyRoot
import it.poliba.adicosola1.rsclient.common.rsengine.RSObject
import it.poliba.adicosola1.rsclient.common.rsengine.TranslationStrategy
import org.koin.core.KoinComponent

class GameTranslator(private val steam: SteamService) : TranslationStrategy, KoinComponent {

    private val cache: LruCache<Long, RSObject> = LruCache(100) //allow only 100 entries

    override fun translate(obj: RSObject): Observable<RSObject> {
        val hit = cache.get(obj.id)
        if (hit != null) return Observable.just(hit)
        else return steam.getAppDetails(obj.id)
            .subscribeOn(Schedulers.io())
            .doOnError { Log.d(javaClass.simpleName, "Error for ${obj.id}:  ${it.message}") }
            .onErrorReturnItem(emptyRoot)
            .filter { it.body.success }
            .map {
                val game = Game(
                    it.body.data.name,
                    it.body.data.description,
                    it.body.data.image,
                    obj.id,
                    obj.score
                )
                cache.put(game.id, game)
                //Returns game
                game
            }

    }
}

