package it.poliba.adicosola1.rsclient.common.rsengine

import io.reactivex.Observable
import it.poliba.adicosola1.rsclient.common.util.Response


interface IRSEngine<ObjType, ItemType, ValueType> {
    /**
     * @param userId Id used for identifying user inside recommendation database
     */
    fun getRecommendations(userId: ObjType): Observable<Response<List<RSObject<ItemType, ValueType>>>>


    /**
     *
     */
    fun updateRecommendation(userId: ObjType, id: ItemType, score: ValueType)

}