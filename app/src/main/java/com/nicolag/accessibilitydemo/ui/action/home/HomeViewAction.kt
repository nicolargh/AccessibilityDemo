package com.nicolag.accessibilitydemo.ui.action.home

sealed class HomeViewAction {
    object Load : HomeViewAction()
    class Click(val click: HomeViewClick) : HomeViewAction()
}

sealed class HomeViewClick {
    object Fab : HomeViewClick()
}