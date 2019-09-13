package it.poliba.adicosola1.rsclient.common.rsengine

import io.reactivex.Observable

/**
 * Factory class for extending [RSObject] with additional information
 */
interface TranslationStrategy<ItemType, ValueType> {
    /**
     * Async translate
     */
    fun translate(obj: RSObject<ItemType, ValueType>): Observable<RSObject<ItemType, ValueType>>
}