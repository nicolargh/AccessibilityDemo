package com.nicolag.accessibilitydemo.provider

import android.content.Context
import com.nicolag.accessibilitydemo.R
import javax.inject.Inject

class MainViewStringProvider @Inject constructor(
    private val context: Context
) {
    fun getWelcomeText(): String {
        return context.getString(R.string.welcome_text)
    }

    fun getSnackbarText(): String {
        return context.getString(R.string.snackbar_text)
    }
}