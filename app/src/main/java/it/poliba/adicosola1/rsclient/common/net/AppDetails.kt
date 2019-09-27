package it.poliba.adicosola1.rsclient.common.net
//Classe che definisce la struttura json ricevuta dall'API di steam nel paradigma OOP
data class Data(val name: String, val description: String, val image: String)
data class Body(val success: Boolean, val data: Data)
data class Root(val id: String, val body: Body)

