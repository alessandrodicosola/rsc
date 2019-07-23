package it.poliba.adicosola1.rsclient.common.authentication

import io.reactivex.Observable

/**
 * @param T Response type from authentication
 */
interface IAuth<T> {
    fun connect(username: String, password: String): Observable<Response<T>>
    fun disconnect(username: String)
}

class Response<T>(val body: T, val error: Boolean)
