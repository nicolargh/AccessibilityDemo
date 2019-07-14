package com.nicolag.accessibilitydemo.ui.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.nicolag.accessibilitydemo.R
import com.nicolag.accessibilitydemo.injection.AppComponent
import kotlinx.android.synthetic.main.list_view_fragment.*
import android.widget.SimpleAdapter

class ListViewFragment : BaseFragment() {
    override fun createView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.list_view_fragment, container, false)
    }

    override fun setupView() {
        getFabView(this).apply {
            setOnClickListener {
                Toast.makeText(requireContext(), R.string.toast_text, Toast.LENGTH_LONG).show()
            }
            visibility = View.VISIBLE
        }

        val data = ArrayList<Map<String, String>>()
        (1..100).forEach { num ->
            data.add(HashMap<String, String>(2).also {
                it["title"] = requireContext().getString(R.string.item, num)
                it["subtitle"] = requireContext().getString(R.string.subtitle, num)
            })
        }
        listView.adapter = SimpleAdapter(
            requireContext(),
            data,
            android.R.layout.simple_list_item_2,
            arrayOf("title", "subtitle"),
            intArrayOf(android.R.id.text1, android.R.id.text2)
        )
    }

    override fun injectFragment(appComponent: AppComponent) {
        appComponent.inject(this)
    }
}