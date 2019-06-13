package com.nicolag.accessibilitydemo.injection

import android.content.Context
import com.nicolag.accessibilitydemo.viewmodel.MainViewModel
import dagger.Module
import dagger.Provides

@Module
class AppModule(private val app: App) {

    @Provides
    fun provideContext(): Context = app

    @Provides
    fun mainViewModelFactory(): MainViewModel.MainViewModelFactory = MainViewModel.MainViewModelFactory()
}
