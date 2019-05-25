package com.nicolag.accessibilitydemo.injection

import com.nicolag.accessibilitydemo.ui.MainActivity
import dagger.Component

@Component(modules = [(AppModule::class)])
interface AppComponent {
    fun inject(mainActivity: MainActivity)
}