package com.nicolag.accessibilitydemo.injection

import com.nicolag.accessibilitydemo.ui.view.HomeFragment
import com.nicolag.accessibilitydemo.ui.MainActivity
import com.nicolag.accessibilitydemo.ui.view.ListViewFragment
import com.nicolag.accessibilitydemo.ui.view.RatingBarFragment
import com.nicolag.accessibilitydemo.ui.view.TabsFragment
import dagger.Component

@Component(modules = [(AppModule::class)])
interface AppComponent {
    fun inject(mainActivity: MainActivity)
    fun inject(homeFragment: HomeFragment)
    fun inject(tabsFragment: TabsFragment)
    fun inject(ratingBarFragment: RatingBarFragment)
    fun inject(listViewFragment: ListViewFragment)
}