package com.nicolag.accessibilitydemo.injection

import com.nicolag.accessibilitydemo.ui.view.MainActivity
import com.nicolag.accessibilitydemo.ui.view.home.HomeFragment
import dagger.Component

@Component(modules = [(AppModule::class)])
interface AppComponent {
    fun inject(mainActivity: MainActivity)
    fun inject(homeFragment: HomeFragment)
}