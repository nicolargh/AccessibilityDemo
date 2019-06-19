package com.nicolag.accessibilitydemo.ui.view

import android.content.Context
import android.os.Build
import android.os.Bundle
import android.text.Html
import android.text.method.LinkMovementMethod
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.accessibility.AccessibilityManager
import com.nicolag.accessibilitydemo.R
import com.nicolag.accessibilitydemo.injection.AppComponent
import kotlinx.android.synthetic.main.links_fragment.*

class LinksFragment : BaseFragment() {
    override fun createView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.links_fragment, container, false)
    }

    override fun setupView() {
        getFabView(this).visibility = View.GONE

        val text1 = "This text has an example of a hyperlink <a href=\"http://www.google.com\">inline</a>, and we set the movement method."
        textView1.text = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N)
            Html.fromHtml(text1, Html.FROM_HTML_MODE_COMPACT)
        else Html.fromHtml(text1)
        textView1.movementMethod = LinkMovementMethod.getInstance()

        val text2 = "This text doesn't have a hyperlink, but we set the movement method just in case."
        textView2.text = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N)
            Html.fromHtml(text2, Html.FROM_HTML_MODE_COMPACT)
        else Html.fromHtml(text2)
        textView2.movementMethod = LinkMovementMethod.getInstance()

        val text3 = "This text also doesn't have a hyperlink, but we checked it before setting the movement method."
        textView3.text = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N)
            Html.fromHtml(text3, Html.FROM_HTML_MODE_COMPACT)
        else Html.fromHtml(text3)
        textView3.movementMethod = if (textView3.urls.isNotEmpty()) {
            LinkMovementMethod.getInstance()
        } else null

        val text4 = "This text has a <a href=\"http://www.microsoft.com\">few</a> hyperlinks, " +
                "<a href=\"https://stackoverflow.com/\">just</a> for good " +
                "<a href=\"http://www.yammer.com\">measure</a>. We only set the movement method if not using talkback."
        textView4.text = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N)
            Html.fromHtml(text4, Html.FROM_HTML_MODE_COMPACT)
        else Html.fromHtml(text4)
        val accessibilityManager = requireContext().getSystemService(Context.ACCESSIBILITY_SERVICE) as AccessibilityManager
        textView4.movementMethod = if (textView4.urls.isNotEmpty() && !accessibilityManager.isTouchExplorationEnabled) {
           LinkMovementMethod.getInstance()
        } else null // explicit null to silence onClick listener
    }

    override fun injectFragment(appComponent: AppComponent) {
        appComponent.inject(this)
    }
}