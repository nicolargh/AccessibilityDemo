package com.nicolag.accessibilitydemo.model.action

sealed class MainViewAction {
    object Load : MainViewAction()
    class Click(val click: MainViewClick) : MainViewAction()
}

sealed class MainViewClick {
    object Fab : MainViewClick()
}