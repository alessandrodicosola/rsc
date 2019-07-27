package it.poliba.adicosola1.rsclient.common.di

import io.reactivex.Observable
import it.poliba.adicosola1.rsclient.common.rsengine.IRSEngine
import it.poliba.adicosola1.rsclient.common.rsengine.RSObject
import it.poliba.adicosola1.rsclient.common.rsengine.TranslationStrategy
import it.poliba.adicosola1.rsclient.common.steam.Game
import it.poliba.adicosola1.rsclient.common.util.IConnectivity
import it.poliba.adicosola1.rsclient.ui.presenter.PresenterActivity
import org.koin.core.qualifier.named
import org.koin.dsl.module

class TestEngine : IRSEngine {
    override fun updateRecommendation(userId: String, id: Long, score: Float) {
        throw NotImplementedError("updateRecommendation")
    }

    override fun getRecommendations(userId: String): Observable<List<RSObject>> {
        return Observable.just(
            listOf(
                RSObject(859580, 1.0f),
                RSObject(976310, 1.0f),
                RSObject(814380, 1.0f),
                RSObject(323190, 1.0f),
                RSObject(466560, 1.0f),
                RSObject(418240, 1.0f),
                RSObject(690790, 1.0f),
                RSObject(750920, 1.0f)
            )
        )
    }
}

class TestTranslator : TranslationStrategy {
    override fun translate(obj: RSObject): Observable<RSObject> {
        return Observable.just(
            Game(
                obj.id.toString(),
                "",
                "https://steamcdn-a.akamaihd.net/steam/apps/${obj.id}/header.jpg",
                obj.id,
                obj.score
            )
        )
    }

}

class AlwaysOnline : IConnectivity {
    override fun isOnline(): Boolean {
        return true;
    }

}

val testPresenterModule = module {
    scope(named<PresenterActivity>()) {
        scoped(override = true) { TestEngine() as IRSEngine }
        scoped(override = true) { TestTranslator() as TranslationStrategy }

    }
}

val testGlobalModule = module {
    single(override = true) { AlwaysOnline() as IConnectivity }
}

