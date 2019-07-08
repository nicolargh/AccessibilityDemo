package com.nicolag.accessibilitydemo.injection

import com.nicolag.accessibilitydemo.ui.MainActivity
import com.nicolag.accessibilitydemo.ui.view.*
import dagger.Component

@Component(modules = [(AppModule::class)])
interface AppComponent {
    fun inject(mainActivity: MainActivity)
    fun inject(homeFragment: HomeFragment)
    fun inject(buttonsFragment: ButtonsFragment)
    fun inject(layoutFragment: LayoutFragment)
    fun inject(tabsFragment: TabsFragment)
    fun inject(ratingBarFragment: RatingBarFragment)
    fun inject(listViewFragment: ListViewFragment)
    fun inject(linksFragment: LinksFragment)
    fun inject(searchFragmentBackground: SearchFragment)
    fun inject(otherTextFragment: OtherTextFragment)
}