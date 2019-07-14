package com.nicolag.accessibilitydemo.ui.view

import android.os.Build
import android.os.Bundle
import android.support.v7.widget.TooltipCompat
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.nicolag.accessibilitydemo.R
import com.nicolag.accessibilitydemo.injection.AppComponent
import kotlinx.android.synthetic.main.buttons_fragment.*

class ButtonsFragment : BaseFragment() {
    override fun createView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.buttons_fragment, container, false)
    }

    override fun setupView() {
        setupTickCross1()
        setupTickCross2()
        setupTickCross3()
    }

    private fun setupTickCross1() {
        val text = "These buttons don't have any content descriptions."
        tickCross1.text = text
    }

    private fun setupTickCross2() {
        val text = "These buttons have no content descriptions, and are wrapped in another layout."
        tickCross2.text = text
    }

    private fun setupTickCross3() {
        val text = "These buttons have content descriptions."
        tickCross3.text = text
        tick3.contentDescription = requireContext().getString(R.string.tick)
        cross3.contentDescription = requireContext().getString(R.string.cross)
    }

    override fun injectFragment(appComponent: AppComponent) {
        appComponent.inject(this)
    }
}