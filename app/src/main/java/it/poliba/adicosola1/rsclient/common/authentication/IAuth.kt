package it.poliba.adicosola1.rsclient.common.authentication

import io.reactivex.Observable
import it.poliba.adicosola1.rsclient.common.util.Response

/**
 * @param T Response type from authentication
 */
interface IAuth<T> {
    fun connect(username: String, password: String): Observable<Response<T>>
    fun disconnect(username: String)
}

