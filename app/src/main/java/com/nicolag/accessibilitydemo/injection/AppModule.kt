package com.nicolag.accessibilitydemo.injection

import android.content.Context
import com.nicolag.accessibilitydemo.model.viewmodel.MainViewModel
import com.nicolag.accessibilitydemo.provider.MainViewStringProvider
import dagger.Module
import dagger.Provides

@Module
class AppModule(private val app: App) {

    @Provides
    fun provideContext(): Context {
        return app
    }

    @Provides
    fun mainViewModelFactory(
        stringProvider: MainViewStringProvider
    ): MainViewModel.MainViewModelFactory = MainViewModel.MainViewModelFactory(
        stringProvider
    )

    @Provides
    fun mainViewStringProvider(
        context: Context
    ): MainViewStringProvider = MainViewStringProvider(
        context
    )
}
