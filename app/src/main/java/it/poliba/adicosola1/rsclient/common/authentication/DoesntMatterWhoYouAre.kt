package it.poliba.adicosola1.rsclient.common.authentication

import android.content.Context
import io.reactivex.Observable
import it.poliba.adicosola1.rsclient.R
import java.io.BufferedReader
import java.io.InputStreamReader
import java.io.Reader
import javax.xml.transform.stream.StreamResult

/**
 * This [IAuth] implementation returns a random user id from an internal list
 */
class DoesntMatterWhoYouAre(context: Context) : IAuth<Long> {

    private val ids: List<String> by lazy {
        val buffered = InputStreamReader(context.resources.openRawResource(R.raw.users))
        buffered.readLines()
    }

    override fun connect(username: String, password: String): Observable<Response<Long>> {
        return Observable.just(Response(ids.random().toLong(), false))
    }

    override fun disconnect(username: String) {
        throw NotImplementedError("disconnect")
    }

}