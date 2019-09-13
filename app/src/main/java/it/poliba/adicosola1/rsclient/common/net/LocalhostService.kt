package it.poliba.adicosola1.rsclient.common.net

import io.reactivex.Observable
import it.poliba.adicosola1.rsclient.common.util.Response
import it.poliba.adicosola1.rsclient.common.rsengine.RSObject
import retrofit2.http.GET
import retrofit2.http.Query


interface LocalhostService {
    //http://127.0.0.1:3306/recommendations/calculate.php?id=xxxx
    @GET("calculate.php")
    fun getResponse(@Query("id") userId: Long): Observable<Response<List<RSObject<Int,Double>>>>
}