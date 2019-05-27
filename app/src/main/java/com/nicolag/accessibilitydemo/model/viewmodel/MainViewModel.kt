package com.nicolag.accessibilitydemo.model.viewmodel

import android.arch.lifecycle.*
import android.support.v4.app.FragmentActivity
import com.nicolag.accessibilitydemo.model.SingleLiveData
import com.nicolag.accessibilitydemo.model.event.MainViewEvent
import com.nicolag.accessibilitydemo.model.state.MainViewState
import com.nicolag.accessibilitydemo.ui.action.MainViewAction
import com.nicolag.accessibilitydemo.ui.action.NavItem
import javax.inject.Inject

class MainViewModel : ViewModel() {

    private val liveStateData: MutableLiveData<MainViewState> = MutableLiveData()
    fun state(): LiveData<MainViewState> {
        return liveStateData
    }

    private val liveEventData: SingleLiveData<MainViewEvent> = SingleLiveData()
    fun event(): LiveData<MainViewEvent> {
        return liveEventData
    }

    fun dispatch(action: MainViewAction) {
        when (action) {
            is MainViewAction.Load -> load()
            is MainViewAction.NavItemClick -> handleNavItemClick(action.item)
        }
    }

    private fun load() {
        val state = MainViewState(
            navItem = NavItem.Home
        )
        postState(state)
    }

    private fun handleNavItemClick(item: NavItem) {
        if (item == liveStateData.value?.navItem) {
            postEvent(MainViewEvent.CloseNavDrawer)
        } else {
            postState(MainViewState(
                navItem = item
            ))
        }
    }

    private fun postState(state: MainViewState) {
        liveStateData.postValue(state)
    }

    private fun postEvent(event: MainViewEvent) {
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
}}