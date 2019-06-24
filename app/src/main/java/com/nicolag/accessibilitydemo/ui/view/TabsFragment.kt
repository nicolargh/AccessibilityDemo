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
        getFabView(this).apply {
            setOnClickListener {
                Toast.makeText(requireContext(), R.string.toast_text, Toast.LENGTH_LONG).show()
            }
            visibility = View.VISIBLE
        }

        val tabContents = listOf(
            getString(R.string.camera_tab) to resources.getDrawable(R.drawable.ic_menu_camera),
            getString(R.string.gallery_tab) to resources.getDrawable(R.drawable.ic_menu_gallery),
            getString(R.string.manage_tab) to resources.getDrawable(R.drawable.ic_menu_manage)
        )
        (1..3).forEach {
            tabLayout.addTab(tabLayout.newTab().apply {
                val tabContent = tabContents[it-1]
                text = tabContent.first
                contentDescription = getString(R.string.tab_item, tabContent.first)
                tag = it
            })
        }

        textView.text = requireContext().getString(R.string.tab_centre_text, 1)

        tabLayout.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener {
            override fun onTabReselected(p0: TabLayout.Tab?) { }

            override fun onTabUnselected(p0: TabLayout.Tab?) { }

            override fun onTabSelected(tab: TabLayout.Tab?) {
                val pos = tab?.tag as Int
                textView.text = getString(R.string.tab_centre_text, pos)
                mainImageView.setImageDrawable(tabContents[pos - 1].second)
            }
        })
    }

    override fun injectFragment(appComponent: AppComponent) {
        appComponent.inject(this)
    }
}