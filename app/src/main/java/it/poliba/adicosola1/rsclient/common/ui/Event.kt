package it.poliba.adicosola1.rsclient.common.ui

import androidx.lifecycle.MutableLiveData

/**
 * Represents an event
 */
abstract class Event(val message: String)
class Informative(message: String) : Event(message)
class Error(message: String) : Event(message)

fun Event.toWrapper(): EventHandler {
    return EventHandler(this)
}

/**
 * Class for handling event with MutableLiveData
 */
class EventHandler(private val event: Event) {

    private var consumed = false

    /**
     * Return the [Event] object
     * @return null if has been consumed else returns the event
     */
    fun get(): Event? {
        return if (consumed) {
            null
        } else {
            consumed = true
            event
        }
    }

}


