package com.nicolag.accessibilitydemo.ui.view

import android.os.Bundle
import android.support.design.widget.TabLayout
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.nicolag.accessibilitydemo.R
import com.nicolag.accessibilitydemo.injection.AppComponent
import kotlinx.android.synthetic.main.tabs_fragment.*

class TabsFragment : BaseFragment() {

    override fun createView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.tabs_fragment, container, false)
    }

    override fun setupView() {
        getFabView(this).setOnClickListener {
            Toast.makeText(requireContext(), R.string.snackbar_text, Toast.LENGTH_LONG).show()
        }

        (1..3).forEach {
            tabLayout.addTab(tabLayout.newTab().apply {
                text = requireContext().getString(R.string.tab_number, it)
                tag = it
            })
        }

        setText(1)

        tabLayout.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener {
            override fun onTabReselected(p0: TabLayout.Tab?) { }

            override fun onTabUnselected(p0: TabLayout.Tab?) { }

            override fun onTabSelected(tab: TabLayout.Tab?) {
                setText(tab?.tag as Int)
            }
        })
    }

    private fun setText(num: Int) {
        textView.text = requireContext().getString(R.string.tab_centre_text, num)
    }

    override fun injectFragment(appComponent: AppComponent) {
        appComponent.inject(this)
    }
}