package com.nicolag.accessibilitydemo.ui.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.nicolag.accessibilitydemo.R
import com.nicolag.accessibilitydemo.injection.AppComponent
import kotlinx.android.synthetic.main.home_fragment.*

class HomeFragment : BaseFragment() {

    override fun createView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.home_fragment, container, false)
    }

    override fun setupView() {
        // as home view contains two textviews and an imageview (which is not important for accessibility), it's nicer
        // to read out both textviews at once to avoid excessive swiping. It's important that the inner views are not
        // focusable as well, as this leads to double selection
        homeFragmentContainer.isFocusableInTouchMode = true // can also be set in XML

        getFabView(this).apply {
            setOnClickListener {
                Toast.makeText(requireContext(), R.string.toast_text, Toast.LENGTH_LONG).show()
            }
            visibility = View.VISIBLE
        }
    }

    override fun injectFragment(appComponent: AppComponent) {
        appComponent.inject(this)
    }
}