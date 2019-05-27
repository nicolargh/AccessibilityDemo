package com.nicolag.accessibilitydemo.model.viewmodel.home

import android.arch.lifecycle.*
import android.support.design.widget.Snackbar
import android.support.v4.app.Fragment
import com.nicolag.accessibilitydemo.model.SingleLiveData
import com.nicolag.accessibilitydemo.model.event.home.HomeViewEvent
import com.nicolag.accessibilitydemo.model.state.home.HomeViewState
import com.nicolag.accessibilitydemo.provider.HomeViewStringProvider
import com.nicolag.accessibilitydemo.ui.action.home.HomeViewAction
import com.nicolag.accessibilitydemo.ui.action.home.HomeViewClick
import javax.inject.Inject

class HomeViewModel(
    private val homeViewStringProvider: HomeViewStringProvider
) : ViewModel() {

    private val liveStateData: MutableLiveData<HomeViewState> = MutableLiveData()
    fun state(): LiveData<HomeViewState> {
        return liveStateData
    }

    private val liveEventData: SingleLiveData<HomeViewEvent> = SingleLiveData()
    fun event(): LiveData<HomeViewEvent> {
        return liveEventData
    }

    fun dispatch(action: HomeViewAction) {
        when (action) {
            is HomeViewAction.Load -> load()
            is HomeViewAction.Click -> handleClick(action.click)
        }
    }

    private fun load() {
        val state = HomeViewState(
            welcomeText = homeViewStringProvider.getWelcomeText()
        )
        postState(state)
    }

    private fun handleClick(click: HomeViewClick) {
        when (click) {
            is HomeViewClick.Fab -> postEvent(
                HomeViewEvent.ShowSnackbar(
                    string = homeViewStringProvider.getSnackbarText(),
                    length = Snackbar.LENGTH_LONG
                )
            )
        }
    }

    private fun postState(state: HomeViewState) {
        liveStateData.postValue(state)
    }

    private fun postEvent(event: HomeViewEvent) {
        liveEventData.postValue(event)
    }

    class Factory @Inject constructor(
        private val homeViewStringProvider: HomeViewStringProvider
    ) : ViewModelProvider.Factory {

        fun getViewModel(fragment: Fragment): HomeViewModel {
            return ViewModelProviders.of(fragment, this).get(HomeViewModel::class.java)
        }

        @Suppress("UNCHECKED_CAST")
        override fun <T : ViewModel> create(modelClass: Class<T>): T {
            return HomeViewModel(homeViewStringProvider) as T
        }
    }
}