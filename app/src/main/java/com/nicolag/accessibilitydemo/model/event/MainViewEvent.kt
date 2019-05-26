package com.nicolag.accessibilitydemo.model.event

sealed class MainViewEvent {
    class ShowSnackbar(val string: String, val length: Int) : MainViewEvent()
    object CloseNavDrawer : MainViewEvent()
}