package com.nicolag.accessibilitydemo.model.state

import com.nicolag.accessibilitydemo.model.action.NavItem

data class MainViewState(
    val welcomeText: String,
    val navItem: NavItem
)