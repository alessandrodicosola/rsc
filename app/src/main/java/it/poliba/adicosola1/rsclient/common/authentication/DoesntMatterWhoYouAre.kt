package it.poliba.adicosola1.rsclient.common.authentication

import io.reactivex.Observable

/**
 * This [IAuth] implementation returns a random user id from an internal list
 */
class DoesntMatterWhoYouAre : IAuth<Long> {
    //TODO Insert user id list
    private val ids = listOf<Long>(1, 2, 3)

    override fun connect(username: String, password: String): Observable<Response<Long>> {
        return Observable.just(Response(ids.random(), false))
    }

    override fun disconnect(username: String) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

}