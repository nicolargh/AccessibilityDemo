package com.nicolag.accessibilitydemo.model.viewmodel

import android.arch.lifecycle.*
import android.support.v4.app.FragmentActivity
import com.nicolag.accessibilitydemo.model.action.MainViewAction
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

    fun dispatch(action: MainViewAction) {
        when (action) {
            is MainViewAction.Load -> load()
        }
    }

    private fun load() {
        val state = MainViewState(
            welcomeText = mainViewStringProvider.getWelcomeText()
        )
        postState(state)
    }

    private fun postState(state: MainViewState) {
        liveStateData.postValue(state)
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