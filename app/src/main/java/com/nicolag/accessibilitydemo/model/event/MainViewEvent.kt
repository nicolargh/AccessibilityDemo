package com.nicolag.accessibilitydemo.model.event

sealed class MainViewEvent {
    object CloseNavDrawer : MainViewEvent()
}