package it.poliba.adicosola1.rsclient.common.net

import com.google.gson.JsonDeserializationContext
import com.google.gson.JsonDeserializer
import com.google.gson.JsonElement
import com.google.gson.JsonObject
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
        if (!body.has("data")) return emptyRoot

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