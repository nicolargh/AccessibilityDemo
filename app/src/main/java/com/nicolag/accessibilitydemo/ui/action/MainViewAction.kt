package com.nicolag.accessibilitydemo.ui.action

sealed class MainViewAction {
    class Load(val navItem: NavItem = NavItem.Home) : MainViewAction()
    class NavItemClick(val item: NavItem) : MainViewAction()
}

sealed class NavItem {
    object Home : NavItem()
}