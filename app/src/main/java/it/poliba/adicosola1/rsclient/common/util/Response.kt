package it.poliba.adicosola1.rsclient.common.util

import com.google.gson.reflect.TypeToken
import it.poliba.adicosola1.rsclient.common.rsengine.RSObject

/**
 * Generic response class. It could be an HttpResponse or IOResponse where error could be file not found, etc...
 * @param body Content of the response
 * @param error True if there is an error
 * @param message Description of the error
 */
data class Response<T>(val body: T, val error: Boolean, val message: String)

/**
 * must for deserialization
 */
class ResponseListRSObjectToken : TypeToken<Response<List<RSObject<Int,Double>>>>()