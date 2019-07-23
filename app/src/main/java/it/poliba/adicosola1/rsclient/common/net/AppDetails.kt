package it.poliba.adicosola1.rsclient.common.net

data class Data(val name: String, val description: String, val image: String)
data class Body(val success: Boolean, val data: Data)
data class Root(val id: String, val body: Body)

val emptyRoot = Root("-1", Body(false, Data("", "", "")))