package it.poliba.adicosola1.rsclient.common.net

import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.Query

//https://store.steampowered.com/api/appdetails?appids=57690
interface SteamService {
    /**
     * Steam WebApi Response is:
     *
     * id {
     *
     * success: boolean
     *
     * data:    ....
     *
     * }
     *
     * @return Returns [Root] composed by Id and [Body]
     *
     * */
    @GET("appdetails") /* Translate to "appdetails/appIds=value" */
    fun getAppDetails(@Query("appids") appId: Long): Observable<Root>


}