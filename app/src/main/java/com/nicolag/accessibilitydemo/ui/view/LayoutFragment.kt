package com.nicolag.accessibilitydemo.ui.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.nicolag.accessibilitydemo.R
import com.nicolag.accessibilitydemo.injection.AppComponent
import kotlinx.android.synthetic.main.card_1.*

class LayoutFragment : BaseFragment() {
    override fun createView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.layout_fragment, container, false)
    }

    override fun setupView() {
        val name = "Other User"
        avatar.contentDescription = requireContext().getString(R.string.avatar_content_description)
        avatar.setOnClickListener { showToast("Opening $name's profile") }
        title.text = name
        title.setOnClickListener { showToast("Opening $name's profile") }
        content.setOnClickListener { showToast("Opening full conversation") }
        likes.setOnClickListener { showToast("Opening 'who liked this' list") }
        heart.setOnClickListener { showToast("Liking...") }
        comment.setOnClickListener { showToast("Commenting...") }
        share.setOnClickListener { showToast("Sharing...") }
    }

    private fun showToast(string: String) {
        Toast.makeText(requireContext(), string, Toast.LENGTH_SHORT).show()
    }

    override fun injectFragment(appComponent: AppComponent) {
        appComponent.inject(this)
    }
}