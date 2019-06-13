package com.nicolag.accessibilitydemo.viewmodel

import android.arch.lifecycle.*
import android.support.v4.app.FragmentActivity
import com.nicolag.accessibilitydemo.ui.MainActivity
import javax.inject.Inject

class MainViewModel : ViewModel() {

    private val liveStateData: MutableLiveData<State> = MutableLiveData()
    fun state(): LiveData<State> {
        return liveStateData
    }

    private val liveEventData: SingleLiveData<Event> = SingleLiveData()
    fun event(): LiveData<Event> {
        return liveEventData
    }

    fun dispatch(action: MainActivity.Action) {
        when (action) {
            is MainActivity.Action.Load -> load()
            is MainActivity.Action.NavItemClick -> handleNavItemClick(action.item)
        }
    }

    private fun load() {
        val state = State(
            navItem = MainActivity.NavItem.Home
        )
        postState(state)
    }

    private fun handleNavItemClick(item: MainActivity.NavItem) {
        if (item == liveStateData.value?.navItem) {
            postEvent(Event.CloseNavDrawer)
        } else {
            postState(
                State(
                    navItem = item
                )
            )
        }
    }

    private fun postState(state: State) {
        liveStateData.postValue(state)
    }

    private fun postEvent(event: Event) {
        liveEventData.postValue(event)
    }

    class MainViewModelFactory @Inject constructor() : ViewModelProvider.Factory {

        fun getViewModel(activity: FragmentActivity): MainViewModel {
            return ViewModelProviders.of(activity, this).get(MainViewModel::class.java)
        }

        @Suppress("UNCHECKED_CAST")
        override fun <T : ViewModel> create(modelClass: Class<T>): T {
            return MainViewModel() as T
        }
    }

    data class State(
        val navItem: MainActivity.NavItem
    )

    sealed class Event {
        object CloseNavDrawer : Event()
    }
}