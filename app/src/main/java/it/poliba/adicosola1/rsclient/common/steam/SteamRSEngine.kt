package it.poliba.adicosola1.rsclient.common.steam

import io.reactivex.Observable
import it.poliba.adicosola1.rsclient.common.rsengine.IRSEngine
import it.poliba.adicosola1.rsclient.common.rsengine.RSObject

class SteamRSEngine : IRSEngine {
    override fun updateRecommendation(userId: String, id: Long, score: Float) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun getRecommendations(userId: String): Observable<List<RSObject>> {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

}