package com.ahmrh.serene.domain

import android.util.Log
import com.ahmrh.serene.common.enums.Event
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import javax.inject.Singleton

@Singleton
class EventHandler{

    private val _eventList: MutableList<Event> = mutableListOf()

    val eventList: List<Event>
        get() = _eventList

    fun addEvent(eventList: List<Event>){
        for(event in eventList)
            _eventList.add(event)
    }

    fun logAllEvent(){
        Log.d(TAG, "$eventList")
    }


    fun addEvent(event: Event) {
        _eventList.add(event)
        logAllEvent()
    }
    fun popEvent(): Event{
        val event = _eventList.removeFirst()
        logAllEvent()
        return event
    }

    fun isEmpty(): Boolean = _eventList.isEmpty()

    companion object{
        const val TAG = "EventHandler"
    }

}