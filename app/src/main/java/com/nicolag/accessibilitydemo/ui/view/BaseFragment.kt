package com.nicolag.accessibilitydemo.ui.view

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.nicolag.accessibilitydemo.injection.App
import com.nicolag.accessibilitydemo.injection.AppComponent
import kotlinx.android.synthetic.main.app_bar_main.fab

abstract class BaseFragment : Fragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return createView(inflater, container, savedInstanceState)
    }

    abstract fun createView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View?

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        injectSelf()
        setupView()
    }

    private fun injectSelf() {
        injectFragment(App.appComponent)
    }

    abstract fun setupView()

    abstract fun injectFragment(appComponent: AppComponent)

    fun getFabView(fragment: Fragment): View = fragment.activity?.fab
        ?: throw IllegalArgumentException("Fragment must be attached to MainActivity")
}