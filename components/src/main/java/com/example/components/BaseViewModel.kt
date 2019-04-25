package com.example.components

import androidx.databinding.Observable
import androidx.lifecycle.ViewModel
import com.example.common.utils.Event
import com.example.model.EventIdentifier
import com.example.model.EventType

/**
 * @brief: BaseviewModel class for all viewModels to extend
 * @author: Sumit Lakra
 * @date: 04/25/2019
 */
open class BaseViewModel : ViewModel() {

    val onEventReceived: Event<EventType> = Event()

    /**
     * Function to be used by child view models to trigger any event
     * @author: Sumit Lakra
     * @date: 04/25/2019
     */
    fun triggerEvent(type: EventIdentifier, dataObj: Any = "") {
        val eventType = EventType(type, dataObj)
        onEventReceived(eventType)
    }

    /**
     * define a property change callback which calls "callback " on change
     * @return Unit
     * @author: Sumit Lakra
     * @date: 04/25/2019
     *
     * */
    @Suppress("UNCHECKED_CAST")
    fun <T : Observable> T.addOnPropertyChanged(callback: (T) -> Unit) =
        object : Observable.OnPropertyChangedCallback() {
            @Suppress("UNCHECKED_CAST")
            override fun onPropertyChanged(observable: Observable?, i: Int) =
                callback(observable as T)
        }.also { addOnPropertyChangedCallback(it) }
}