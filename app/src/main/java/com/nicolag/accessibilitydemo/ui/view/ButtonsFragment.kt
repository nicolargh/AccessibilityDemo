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
        val text = "These buttons don't have any content descriptions or tool tips."
        tickCross1.text = text
    }

    private fun setupTickCross2() {
        val text = "These buttons have no content descriptions, but have tool tips."
        tickCross2.text = text
        addHints(tick2, cross2)
    }

    private fun setupTickCross3() {
        val text = "These buttons have both content descriptions and tool tips."
        tickCross3.text = text
        addHints(tick3, cross3)
        tick3.contentDescription = requireContext().getString(R.string.tick)
        cross3.contentDescription = requireContext().getString(R.string.cross)
    }

    private fun addHints(tick: View, cross: View) {
        val tickToolTip = requireContext().getString(R.string.tick)
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            tick.tooltipText = tickToolTip
        } else {
            TooltipCompat.setTooltipText(tick, tickToolTip)
        }

        val crossToolTip = requireContext().getString(R.string.cross)
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            cross.tooltipText = crossToolTip
        } else {
            TooltipCompat.setTooltipText(cross, crossToolTip)
        }
    }

    override fun injectFragment(appComponent: AppComponent) {
        appComponent.inject(this)
    }
}