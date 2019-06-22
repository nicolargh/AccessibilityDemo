package com.nicolag.accessibilitydemo.ui.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.Toast
import com.nicolag.accessibilitydemo.R
import com.nicolag.accessibilitydemo.injection.AppComponent
import kotlinx.android.synthetic.main.list_view_fragment.*

class ListViewFragment : BaseFragment() {
    override fun createView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.list_view_fragment, container, false)
    }

    override fun setupView() {
        getFabView(this).setOnClickListener {
            Toast.makeText(requireContext(), R.string.toast_text, Toast.LENGTH_LONG).show()
        }
        listView.adapter = ArrayAdapter<String>(
            requireContext(),
            android.R.layout.simple_list_item_1,
            (1..50).map { requireContext().getString(R.string.item, it) }.toTypedArray()
        )
    }

    override fun injectFragment(appComponent: AppComponent) {
        appComponent.inject(this)
    }
}