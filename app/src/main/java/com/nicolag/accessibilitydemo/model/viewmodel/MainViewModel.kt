package com.nicolag.accessibilitydemo.model.viewmodel

import android.arch.lifecycle.*
import android.support.design.widget.Snackbar
import android.support.v4.app.FragmentActivity
import com.nicolag.accessibilitydemo.model.SingleLiveData
import com.nicolag.accessibilitydemo.model.action.MainViewAction
import com.nicolag.accessibilitydemo.model.action.MainViewClick
import com.nicolag.accessibilitydemo.model.event.MainViewEvent
import com.nicolag.accessibilitydemo.model.state.MainViewState
import com.nicolag.accessibilitydemo.provider.MainViewStringProvider
import javax.inject.Inject

class MainViewModel(
    private val mainViewStringProvider: MainViewStringProvider
) : ViewModel() {

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
            is MainViewAction.Click -> handleClick(action.click)
        }
    }

    private fun load() {
        val state = MainViewState(
            welcomeText = mainViewStringProvider.getWelcomeText()
        )
        postState(state)
    }

    private fun handleClick(click: MainViewClick) {
        when(click) {
            is MainViewClick.Fab -> postEvent(MainViewEvent.ShowSnackbar(
                string = mainViewStringProvider.getSnackbarText(),
                length = Snackbar.LENGTH_LONG
            ))
        }
    }

    private fun postState(state: MainViewState) {
        liveStateData.postValue(state)
    }

    private fun postEvent(event: MainViewEvent) {
        liveEventData.postValue(event)
    }

    class MainViewModelFactory @Inject constructor(
        private val mainViewStringProvider: MainViewStringProvider
    ) : ViewModelProvider.Factory {

        fun getViewModel(activity: FragmentActivity): MainViewModel {
            return ViewModelProviders.of(activity, this).get(MainViewModel::class.java)
        }

        @Suppress("UNCHECKED_CAST")
        override fun <T : ViewModel> create(modelClass: Class<T>): T {
            return MainViewModel(mainViewStringProvider) as T
        }
}}