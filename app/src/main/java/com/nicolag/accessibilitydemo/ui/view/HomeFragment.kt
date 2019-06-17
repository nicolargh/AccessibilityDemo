package com.nicolag.accessibilitydemo.ui.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.nicolag.accessibilitydemo.R
import com.nicolag.accessibilitydemo.injection.AppComponent

class HomeFragment : BaseFragment() {

    override fun createView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.home_fragment, container, false)
    }

    override fun setupView() {
        getFabView(this).setOnClickListener {
            Toast.makeText(requireContext(), R.string.snackbar_text, Toast.LENGTH_LONG).show()
        }
    }

    override fun injectFragment(appComponent: AppComponent) {
        appComponent.inject(this)
    }
}