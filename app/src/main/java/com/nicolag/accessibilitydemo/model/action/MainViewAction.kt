package com.nicolag.accessibilitydemo.model.action

sealed class MainViewAction {
    object Load : MainViewAction()
    class Click(val click: MainViewClick) : MainViewAction()
    class NavItemClick(val item: NavItem) : MainViewAction()
}

sealed class NavItem {
    object Home : NavItem()
}

sealed class MainViewClick {
    object Fab : MainViewClick()
}