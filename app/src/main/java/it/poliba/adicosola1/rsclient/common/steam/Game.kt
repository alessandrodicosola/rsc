package it.poliba.adicosola1.rsclient.common.steam

import it.poliba.adicosola1.rsclient.common.rsengine.RSObject

class Game(val name: String, val description: String, val image: String, id: Int, score: Double) : RSObject<Int,Double>(id, score)