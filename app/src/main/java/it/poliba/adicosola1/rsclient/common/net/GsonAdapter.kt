package it.poliba.adicosola1.rsclient.common.net

import com.google.gson.JsonDeserializationContext
import com.google.gson.JsonDeserializer
import com.google.gson.JsonElement
import com.google.gson.JsonObject
import it.poliba.adicosola1.rsclient.common.util.Response
import it.poliba.adicosola1.rsclient.common.rsengine.RSObject
import java.lang.reflect.Type

/**
 * Custom class for deserialize json response from steam web api service
 */
class RootAdapter : JsonDeserializer<Root> {
    override fun deserialize(json: JsonElement, typeOfT: Type, context: JsonDeserializationContext): Root {
        val root = json.asJsonObject
        val values = root.entrySet()
        if (values.size > 1) throw Exception("Too many app id. Must be one")
        val appId = values.first().key

        val body = values.first().value.asJsonObject
        if (!body.has("data")) return Root(appId, Body(false, Data("unknow", "", "")))

        val data = body.get("data") as JsonObject

        return Root(
            appId,
            Body(
                body.get("success").asBoolean,
                Data(
                    data.get("name").asString,
                    data.get("short_description").asString,
                    data.get("header_image").asString
                )
            )
        )
    }
}

class LocalhostAdapter : JsonDeserializer<Response<List<RSObject>>> {
    override fun deserialize(
        json: JsonElement,
        typeOfT: Type,
        context: JsonDeserializationContext
    ): Response<List<RSObject>> {
        val root = json.asJsonObject
        val error = root.get("error").asBoolean
        val msg = root.get("message").asString
        val recommendations = root.get("recommendations").asJsonArray

        val list: MutableList<RSObject> = mutableListOf()

        recommendations.forEach {
            list.add(RSObject(it.asJsonObject.get("id").asLong, it.asJsonObject.get("score").asDouble))
        }

        return Response(list, error, msg)
    }

}