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

class LocalHostEngine(val connectivity: IConnectivity, private val localService: LocalhostService) : IRSEngine<Long,Int,Double> {
    override fun getRecommendations(userId: Long,itemId:Int): Observable<Response<List<RSObject<Int,Double>>>> {
        if (!connectivity.isOnline()) return Observable.empty()

        return localService.getResponse(userId,itemId)

    }

    override fun updateRecommendation(userId: Long, id: Int, score: Double) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }


}