package com.nicolag.accessibilitydemo.injection

import android.content.Context
import com.nicolag.accessibilitydemo.model.viewmodel.MainViewModel
import com.nicolag.accessibilitydemo.model.viewmodel.home.HomeViewModel
import com.nicolag.accessibilitydemo.provider.HomeViewStringProvider
import dagger.Module
import dagger.Provides

@Module
class AppModule(private val app: App) {

    @Provides
    fun provideContext(): Context = app

    @Provides
    fun mainViewModelFactory(): MainViewModel.MainViewModelFactory = MainViewModel.MainViewModelFactory()

    @Provides
    fun homeViewModelFactory(
        homeViewStringProvider: HomeViewStringProvider
    ): HomeViewModel.Factory = HomeViewModel.Factory(
        homeViewStringProvider
    )

    @Provides
    fun mainViewStringProvider(
        context: Context
    ): HomeViewStringProvider = HomeViewStringProvider(
        context
    )
}
