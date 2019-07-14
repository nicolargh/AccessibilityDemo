package com.nicolag.accessibilitydemo.ui.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.nicolag.accessibilitydemo.R
import com.nicolag.accessibilitydemo.injection.AppComponent
import kotlinx.android.synthetic.main.card_1.*
import kotlinx.android.synthetic.main.card_2.*

class LayoutFragment : BaseFragment() {
    override fun createView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.layout_fragment, container, false)
    }

    override fun setupView() {
        val name = "Other User"

        avatar1.contentDescription = requireContext().getString(R.string.avatar_content_description, name)
        avatar1.setOnClickListener { showToast("Opening $name's profile") }
        title1.text = name
        title1.setOnClickListener { showToast("Opening $name's profile") }
        content1.setOnClickListener { showToast("Opening full conversation") }
        likes1.setOnClickListener { showToast("Opening 'who liked this' list") }
        heart1.setOnClickListener { showToast("Liking...") }
        comment1.setOnClickListener { showToast("Commenting...") }
        share1.setOnClickListener { showToast("Sharing...") }

        card2.setOnClickListener { showToast("Opening full conversation") }
        avatar2.setOnClickListener { showToast("Opening $name's profile") }
        title2.text = name
        title2.setOnClickListener { showToast("Opening $name's profile") }
        likes2.setOnClickListener { showToast("Opening 'who liked this' list") }
        heart2.setOnClickListener { showToast("Liking...") }
        comment2.setOnClickListener { showToast("Commenting...") }
        share2.setOnClickListener { showToast("Sharing...") }
    }

    private fun showToast(string: String) {
        Toast.makeText(requireContext(), string, Toast.LENGTH_SHORT).show()
    }

    override fun injectFragment(appComponent: AppComponent) {
        appComponent.inject(this)
    }
}