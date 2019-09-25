package it.poliba.adicosola1.rsclient.common.di

import io.reactivex.Observable
import it.poliba.adicosola1.rsclient.common.rsengine.IRSEngine
import it.poliba.adicosola1.rsclient.common.rsengine.RSObject
import it.poliba.adicosola1.rsclient.common.rsengine.TranslationStrategy
import it.poliba.adicosola1.rsclient.common.steam.Game
import it.poliba.adicosola1.rsclient.common.util.IConnectivity
import it.poliba.adicosola1.rsclient.common.util.Response
import it.poliba.adicosola1.rsclient.ui.presenter.PresenterActivity
import org.koin.core.qualifier.named
import org.koin.dsl.module

class TestEngine : IRSEngine<Long, Int, Double> {


    override fun updateRecommendation(userId: Long, id: Int, score: Double) {
        throw NotImplementedError("updateRecommendation")
    }

    override fun getRecommendations(userId: Long, itemId: Int): Observable<Response<List<RSObject<Int, Double>>>> {
        return Observable.just(
            Response(
                listOf(
                    RSObject(859580, 1.0),
                    RSObject(976310, 1.0),
                    RSObject(814380, 1.0),
                    RSObject(323190, 1.0),
                    RSObject(466560, 1.0),
                    RSObject(418240, 1.0),
                    RSObject(690790, 1.0),
                    RSObject(750920, 1.0)
                ), false, ""
            )
        )
    }
}

class TestTranslator : TranslationStrategy<Int, Double> {
    override fun translate(obj: RSObject<Int, Double>): Observable<RSObject<Int, Double>> {
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
        scoped(override = true) { TestEngine() as IRSEngine<Long, Int, Double> }
        scoped(override = true) { TestTranslator() as TranslationStrategy<Int, Double> }

    }
}

val testGlobalModule = module {
    single(override = true) { AlwaysOnline() as IConnectivity }
}

