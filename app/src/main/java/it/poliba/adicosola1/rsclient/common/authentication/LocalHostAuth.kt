package it.poliba.adicosola1.rsclient.common.authentication

import android.content.Context
import io.reactivex.Observable
import it.poliba.adicosola1.rsclient.R
import it.poliba.adicosola1.rsclient.common.util.Response
import java.io.InputStreamReader

/**
 * This [IAuth] implementation returns a random user id from an internal list
 */
class LocalHostAuth(context: Context) : IAuth<Long> {


    /*
        private val ids: List<String> by lazy {
            val buffered = InputStreamReader(context.resources.openRawResource(R.raw.users))
            buffered.readLines()
        }
    */
    private val ids = listOf<Long>(76561198014912110, 76561198015082830)

    override fun connect(username: String, password: String): Observable<Response<Long>> {
        return Observable.just(Response(ids.random(), false, ""))
    }

    override fun disconnect(username: String): Observable<Response<Boolean>> {
        throw NotImplementedError("disconnect")
    }

}