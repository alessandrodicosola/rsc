package it.poliba.adicosola1.rsclient.common.rsengine

/**
 * Class used for contain base information about recommendations.
 * @param id number used for identify object inside database
 * @param score value calculated for recommendation object
 */
open class RSObject<ItemType,ValueType>(val id: ItemType, val score: ValueType)