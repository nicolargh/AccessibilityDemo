package com.nicolag.accessibilitydemo.model.event.home

sealed class HomeViewEvent {
    class ShowSnackbar(val string: String, val length: Int) : HomeViewEvent()
}