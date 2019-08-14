package it.poliba.adicosola1.rsclient.common.steam

import android.util.Log
import io.reactivex.Observable
import io.reactivex.ObservableSource
import io.reactivex.Single
import it.poliba.adicosola1.rsclient.common.net.LocalhostService
import it.poliba.adicosola1.rsclient.common.rsengine.IRSEngine
import it.poliba.adicosola1.rsclient.common.rsengine.RSObject
import it.poliba.adicosola1.rsclient.common.util.IConnectivity
import it.poliba.adicosola1.rsclient.common.util.Response

class LocalHostEngine(val connectivity: IConnectivity, private val localService: LocalhostService) : IRSEngine {
    override fun getRecommendations(userId: String): Observable<Response<List<RSObject>>> {
        if (!connectivity.isOnline()) return Observable.empty()

        return localService.getResponse(userId.toLong())

    }

    override fun updateRecommendation(userId: String, id: Long, score: Float) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }


}