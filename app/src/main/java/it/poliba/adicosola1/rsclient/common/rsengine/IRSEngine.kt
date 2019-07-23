package it.poliba.adicosola1.rsclient.common.rsengine

import io.reactivex.Observable


interface IRSEngine {
    /**
     * @param userId Id used for identifying user inside recommendation database
     */
    fun getRecommendations(userId: String): Observable<List<RSObject>>


    /**
     *
     */
    fun updateRecommendation(userId: String,id:Long,score:Float)

}
